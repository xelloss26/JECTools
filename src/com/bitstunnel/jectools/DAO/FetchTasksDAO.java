package com.bitstunnel.jectools.DAO;

import java.util.ArrayList;

import com.bitstunnel.jectools.models.TaskVO;



public interface FetchTasksDAO {
	
	ArrayList<TaskVO> getTasksList(String IssueID);

}
