/**
 * 
 */
package com.bitstunnel.jectools.Control;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.bitstunnel.jectools.DAO.FetchIssuesDAOImpl;
import com.bitstunnel.jectools.models.IssueVO;

/**
 * @author Mai
 *
 */
public class ModelControl {

	/**
	 * 
	 */
	private ArrayList <IssueVO> issueList = new ArrayList<IssueVO>();
	private ArrayList <String> labelList = new ArrayList<String>();
	
 	public ModelControl() {
		// TODO Auto-generated constructor stub
//		create the content model for test;
		
//-----------------------------------------------
//		IssueVO issue1 = new IssueVO();
//		issue1.setIssueID("10000");
//		issue1.setPkey("NCB-100");
//		issue1.setIssuestatus("待发布");
//		issue1.setIssuetype("业务需求");
//		issue1.setCreateDate(new Date());
//		issue1.setReporter("边凡");
//		issue1.setSummary("建立电销的打印模块");
//		issue1.setTasksVO(null);
//
//		
////	------------------------------------
//		IssueVO issue2 = new IssueVO();
//		issue2.setIssueID("10001");
//		issue2.setPkey("NCB-101");
//		issue2.setIssuestatus("等待排期");
//		issue2.setIssuetype("业务需求");
//		issue2.setCreateDate(new Date());
//		issue2.setReporter("郭竞乐");
//		issue2.setSummary("建立电销的承保模块");
//		issue2.setTasksVO(null);
////		--------------------------------
//		issueList.add(issue1);
//		issueList.add(issue2);
//		
////		-----------------
//		labelList.add("req_id");
//		labelList.add("pkey");
//		labelList.add("Issue status");
//		labelList.add("需求类型");
//		labelList.add("Create Date");
//		labelList.add("Reporter");
//		labelList.add("Summary");
//		
		
		
		
		
	}
 	
 	
 	public void DBdataprcessor(){
 		FetchIssuesDAOImpl daoimpl = new FetchIssuesDAOImpl();
 		issueList = daoimpl.getIssuesByAnalyst("ptts_maijing");
 		
 		
 	}
 	
	
	public ArrayList getIssueLabelsList()
	{
		if (issueList != null){
			IssueVO issLabelvo = (IssueVO)issueList.get(0);
			HashMap lb = (HashMap)convertMapTolMap(issLabelvo.getLabelsDesc());
			return (ArrayList)lb.get("labelsDescList");
		} else
		{
			return null;
		}
		
		
	}
	
	public ArrayList<IssueVO> getIssuesContentList(){

		return issueList;

	}
	
	public  String extractIssueVOContent(IssueVO rowContent, int index) throws 
	ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
	
		Class clazz = Class.forName("com.bitstunnel.jectools.models.IssueVO");
		if (rowContent instanceof IssueVO){

			if (issueList != null) {
				IssueVO issLabelvo = (IssueVO) issueList.get(0);
				HashMap lb = (HashMap) convertMapTolMap(issLabelvo
						.getLabelsDesc());
				ArrayList methodNames = (ArrayList) lb.get("columnNmList");
				String methodStr = "get"+ removeSerialInName((String)methodNames.get(index));
				Method vomethod = clazz.getMethod(methodStr);
				Object rtnValue = vomethod.invoke(rowContent);
				if (rtnValue instanceof String) {
					return (String) rtnValue;
				} else {
					return rtnValue.toString();
				}
			} else return null;
		} return null;
					
	}
	
	
	
	public Color getRowColor(){
		return null;
	}
	
	private String removeSerialInName(String orgStr){
		int dashIndx = orgStr.indexOf("_");
		return orgStr.substring(dashIndx+1); 
		
	}
	
	private Map convertMapTolMap(Map<String,String> labelMap){
		ArrayList<String> columnNmList  = new ArrayList<String>();
		ArrayList<String> labelsDescList = new ArrayList<String>();

		HashMap<String, ArrayList> rtnMap = new HashMap<String,ArrayList>();
//		Iterator iter = labelMap.entrySet().iterator();
//		
//		while(iter.hasNext()){
//			Map.Entry entry = (Map.Entry) iter.next();
//			ColumnNmList.add((String)entry.getKey());
//			LabelsDescList.add((String)entry.getValue());
//		}
//		
		Iterator iter = labelMap.keySet().iterator();
		while (iter.hasNext()){		
			String key = (String)iter.next();
			columnNmList.add(key);
			String value  = (String)labelMap.get(key);
			labelsDescList.add(value);
//			System.out.println("column=" + key + "|" + "value=" + value);
		}
		rtnMap.put("columnNmList", columnNmList);
		rtnMap.put("labelsDescList", labelsDescList);
		return rtnMap;
		
	}
	
	private void temp(){


	}
	
}
