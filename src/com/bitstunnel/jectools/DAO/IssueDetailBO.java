/**
 * 
 */
package com.bitstunnel.jectools.DAO;

import java.util.ArrayList;



import java.util.Iterator;

import com.bitstunnel.jectools.models.IssueVO;
import com.bitstunnel.jectools.models.TaskVO;


/**
 * @author maijing-001
 *
 */
public class IssueDetailBO {

	/**
	 * 
	 */
	public ArrayList <IssueVO> issuesDetail = null;
	public IssueDetailBO() {
		// TODO Auto-generated constructor stub
		
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList <IssueVO> getAllIssuleByName(String name ){
		FetchIssuesDao issuedao = new FetchIssuesDAOImpl();
		FetchTasksDAO taskdao = new FetchTasksDAOImpl();
		
		issuesDetail = (ArrayList<IssueVO>) issuedao.getIssuesByAnalyst(name);
		
//		fetching the Tasks Information by Issues ID
		
		Iterator<IssueVO> iter = issuesDetail.iterator();
		while (iter.hasNext()){
			IssueVO operIssue = iter.next();
			String currIssueID  = operIssue.getIssueID();
			ArrayList<TaskVO> tasksList = taskdao.getTasksList(currIssueID);
		    operIssue.setTasksVO(tasksList);
			
		}
		
		return issuesDetail;
		
	}

	public ArrayList <IssueVO> getIssuesDetailByStatus(String status){
		if (status == null)
		{
			return null;
		}
		return null;
	}
	
}
