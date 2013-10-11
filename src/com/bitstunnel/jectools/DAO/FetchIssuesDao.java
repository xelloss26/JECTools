package com.bitstunnel.jectools.DAO;

import java.util.ArrayList;

import com.bitstunnel.jectools.models.IssueVO;




public interface FetchIssuesDao {
	
	// return the numbers of issues what be analysis by Issueanalyst
	public int getRowNumByAnlyName(String IssueAnalyst);
	
	public ArrayList<IssueVO> getIssuesByAnalyst(String IssueAanalyst);
	
	public int getRowNumberByProName(String ProjectName);

}
