/**
 * 
 */
package com.bitstunnel.jectools.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bitstunnel.jectools.models.IssueVO;



/**
 * @author maijing-001
 *
 */
public class FetchIssuesDAOImpl implements FetchIssuesDao {

	/**
	 * 
	 */
	public FetchIssuesDAOImpl() {
		// TODO Auto-generated constructor stub
		
	}

	/* (non-Javadoc)
	 * @see com.bitstunnel.easyjiraapp.JiraDB.FetchIssuesDao#getRowNumByAnlyName(java.lang.String)
	 */
	@Override
	public int getRowNumByAnlyName(String IssueAnalyst) {
		// TODO Auto-generated method stub
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.bitstunnel.easyjiraapp.JiraDB.FetchIssuesDao#getIssuesByAnalyst(java.lang.String)
	 */

	@Override
	public ArrayList<IssueVO> getIssuesByAnalyst(String issueAnalystName) {
		// TODO Auto-generated method stub
		ArrayList <IssueVO> issuevolist = new ArrayList<IssueVO>();
		String sqlstr  = "SELECT ji.ID AS ID, ji.pkey AS req_key, ji.SUMMARY AS Summary, itype.pname AS TYPE, "
				+ " cuser.display_name AS reporter, ist.pname AS req_status, ji.CREATED AS createdate "
				+ " FROM jiraissue ji, cwd_user cuser, issuestatus ist, issuetype itype "
				+ " WHERE ji.PROJECT = '11300' AND ji.issuestatus NOT IN ('10034','10103') "
				+ " AND ji.ASSIGNEE = 'ptts_maijing' AND cuser.user_name = ji.REPORTER "
				+ " AND ji.issuestatus = ist.ID AND ji.issuetype = itype.ID "
				+ " UNION "
				+ " SELECT DISTINCT ji.id AS ID, ji.pkey AS req_key, ji.SUMMARY AS Summary,  itype.pname AS TYPE,"
				+ " cuser.display_name AS reporter,ist.pname AS req_status, ji.CREATED AS createdate"
				+ " FROM cwd_user cuser, issuestatus ist, issuetype itype,jiraissue ji "
				+ " INNER JOIN"
				+ " (SELECT cfv.issue AS issueid FROM customfieldvalue cfv"
				+ " WHERE cfv.CUSTOMFIELD IN (11542,12077) AND cfv.STRINGVALUE = " + "'" + issueAnalystName + "'"
				+ " ) AS temp_issueid ON ji.ID = temp_issueid.issueid"
				+ " WHERE ji.issuestatus NOT IN ('10034','10103') AND ji.REPORTER = cuser.user_name AND ji.issuestatus = ist.ID AND ji.issuetype = itype.ID";
		System.out.println("SQL string is fullfill");

		try {
			Connection conn = JiraDBConnection.getConnection();
			System.out.println("connection has be get");
			Statement stm = conn.prepareStatement(sqlstr);
			System.out.println("statement has be set");
			ResultSet rltSet = stm.executeQuery(sqlstr);
			while (rltSet.next()){
				IssueVO issuevo = new IssueVO();
				issuevo.setIssueID(rltSet.getString("ID"));
				issuevo.setPkey(rltSet.getString("req_key"));
				issuevo.setSummary(rltSet.getString("Summary"));
				issuevo.setIssuetype(rltSet.getString("TYPE"));
				issuevo.setReporter(rltSet.getString("reporter"));
				issuevo.setIssuestatus(rltSet.getString("req_status"));
				issuevo.setCreateDate(rltSet.getDate("createdate"));
				issuevo.setTasksVO(null);
				issuevolist.add(issuevo);
				
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return issuevolist;
	}

	/* (non-Javadoc)
	 * @see com.bitstunnel.easyjiraapp.JiraDB.FetchIssuesDao#getRowNumberByProName(java.lang.String)
	 */
	@Override
	public int getRowNumberByProName(String ProjectName) {
		// TODO Auto-generated method stub
		return 0;
	}

}
