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

import com.bitstunnel.jectools.models.TaskVO;


/**
 * @author maijing-001
 *
 */
public class FetchTasksDAOImpl implements FetchTasksDAO {

	/**
	 * 
	 */
	public FetchTasksDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.bitstunnel.easyjiraapp.JiraDB.FetchTasksDAO#getTasksList(java.lang.String)
	 */
	@Override
	public ArrayList<TaskVO> getTasksList(String IssueID) {
		// TODO Auto-generated method stub
		ArrayList<TaskVO> taskvoList = new ArrayList<TaskVO>();
		String sqlstr  = "SELECT ji.ID AS TaskID, ji.pkey AS TaskKey, ji.SUMMARY AS TaskSummary, "
				+ " itype.pname AS TaskTYPE, cuser.display_name AS TaskReporter,"
				+ " ist.pname AS Task_status, ji.CREATED AS Tcreatedate"
				+ " pv.vname as ReleaseVersion, pv.RELEASEDATE as ReleaseDate"
				+ " FROM jiraissue ji LEFT JOIN nodeassociation nasrc ON (ji.ID = nasrc.SOURCE_NODE_ID AND nasrc.ASSOCIATION_TYPE = 'IssueFixVersion') "
				+ " LEFT JOIN projectversion pv on nasrc.SINK_NODE_ID = pv.ID,"
				+ " cwd_user cuser, issuestatus ist, issuetype itype, issuelink ilink"
				+ " WHERE ilink.DESTINATION  = " + IssueID
				+ " AND ilink.SOURCE = ji.ID AND cuser.user_name = ji.REPORTER"
				+ " AND ji.issuestatus = ist.ID AND ji.issuetype = itype.ID"
				+ " UNION"
				+ " SELECT ji.ID AS TaskID, ji.pkey AS TaskKey, ji.SUMMARY AS TaskSummary,"
				+ " itype.pname AS TaskTYPE, cuser.display_name AS TaskReporter,"
				+ " ist.pname AS Task_status, ji.CREATED AS Tcreatedate,"
				+ " pv.vname as ReleaseVersion, pv.RELEASEDATE as ReleaseDate"
				+ " FROM jiraissue ji, cwd_user cuser, issuestatus ist, issuetype itype, issuelink ilink"
				+ " WHERE ilink.SOURCE  = " + IssueID
				+ " AND ilink.DESTINATION = ji.ID AND cuser.user_name = ji.REPORTER AND ji.issuestatus = ist.ID "
				+ " AND ji.issuetype = itype.ID";

		System.out.println("SQL string is fullfill");

		try {
			Connection conn = JiraDBConnection.getConnection();
			System.out.println("connection has be get");
			Statement stm = conn.prepareStatement(sqlstr);
			System.out.println("statement has be set");
			ResultSet rltSet = stm.executeQuery(sqlstr);
			while (rltSet.next()){
				TaskVO taskvo = new TaskVO();
				taskvo.setTaskID(rltSet.getString("TaskID"));
				taskvo.setTaskKey(rltSet.getString("TaskKey"));
				taskvo.setTaskSummary(rltSet.getString("TaskSummary"));
				taskvo.setTaskTYPE(rltSet.getString("TaskTYPE"));
				taskvo.setTaskReporter(rltSet.getString("TaskReporter"));
				taskvo.setTcreatedate(rltSet.getDate("Tcreatedate"));
				taskvo.setReleaseVersion(rltSet.getString("ReleaseVersion"));
				taskvo.setReleaseDate(rltSet.getDate("ReleaseDate"));
				
				taskvoList.add(taskvo);
				
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return taskvoList;
	}

}
