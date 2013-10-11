package com.bitstunnel.jectools.models;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class IssueVO {

	private TreeMap<String,String> IssueLabelDesc = new TreeMap<String,String>();
	private String IssueID = null;
	private String pkey = null;
	private String reporter = null;
	private String issuetype = null;
	private String summary = null;
	private String issuestatus = null;
	private Date CreateDate = null;
	private List <TaskVO> tasksObj = null;
	public IssueVO(){
		IssueLabelDesc.put("0_IssueID", "����ID");
		IssueLabelDesc.put("1_Pkey", "�����");
		IssueLabelDesc.put("2_Reporter", "������");
		IssueLabelDesc.put("3_Issuetype", "��������");
		IssueLabelDesc.put("4_Summary", "�������");
		IssueLabelDesc.put("5_Issuestatus", "����״̬");
		IssueLabelDesc.put("6_CreateDate", "��������");
	
		
	}
	
	
	public String getIssueID() {
		return IssueID;
	}
	public void setIssueID(String issueID) {
		IssueID = issueID;
	}
	public String getPkey() {
		return pkey;
	}
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public String getIssuetype() {
		return issuetype;
	}
	public void setIssuetype(String issutype) {
		this.issuetype = issutype;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getIssuestatus() {
		return issuestatus;
	}
	public void setIssuestatus(String issuestatus) {
		this.issuestatus = issuestatus;
	}
	public Date getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
	
	public List<TaskVO> getTasksVO(){
		return tasksObj;
	}
	
	public void setTasksVO(List<TaskVO> tasksobj){
		tasksObj = tasksobj;
	}
	
	public TreeMap<String,String> getLabelsDesc()
	{
		return IssueLabelDesc;
	}
}
