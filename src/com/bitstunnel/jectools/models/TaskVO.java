package com.bitstunnel.jectools.models;

import java.util.Date;
import java.util.HashMap;

public class TaskVO {
	


	private HashMap<String, String> taskLabelDesc = new HashMap<String, String>();
	
	private String TaskID = null;
	private String TaskKey = null;
	private String TaskReporter = null;
	private String TaskTYPE = null;
	private String TaskSummary = null;
	private String taskStatus = null;
	private Date Tcreatedate = null;
	private String ReleaseVersion = null;
	private Date ReleaseDate = null;
	
	public  TaskVO(){
		taskLabelDesc.put("TaskID", "����ID");
		taskLabelDesc.put("Taskkey", "�����");
		taskLabelDesc.put("TaskReporter", "���񱨸���");
		taskLabelDesc.put("TaskTYPE", "��������");
		taskLabelDesc.put("TaskSummary", "�������");
		taskLabelDesc.put("TaskStatus", "����״̬");
		taskLabelDesc.put("Tcreatedate", "���񴴽�ʱ��");
		taskLabelDesc.put("ReleaseDate", "��������");
		taskLabelDesc.put("ReleaseVersion", "�����汾");
	}
	
	public String getTaskID() {
		return TaskID;
	}
	public void setTaskID(String taskID) {
		TaskID = taskID;
	}
	public String getTaskKey() {
		return TaskKey;
	}
	public void setTaskKey(String taskKey) {
		TaskKey = taskKey;
	}
	public String getTaskReporter() {
		return TaskReporter;
	}
	public void setTaskReporter(String taskReporter) {
		TaskReporter = taskReporter;
	}
	public String getTaskTYPE() {
		return TaskTYPE;
	}
	public void setTaskTYPE(String taskTYPE) {
		TaskTYPE = taskTYPE;
	}
	public String getTaskSummary() {
		return TaskSummary;
	}
	public void setTaskSummary(String taskSummary) {
		TaskSummary = taskSummary;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String task_status) {
		taskStatus = task_status;
	}
	public Date getTcreatedate() {
		return Tcreatedate;
	}
	public void setTcreatedate(Date tcreatedate) {
		Tcreatedate = tcreatedate;
	}
	public String getReleaseVersion() {
		return ReleaseVersion;
	}
	public void setReleaseVersion(String releaseVersion) {
		ReleaseVersion = releaseVersion;
	}
	public Date getReleaseDate() {
		return ReleaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		ReleaseDate = releaseDate;
	}
	
	public HashMap<String,String> getLabelsDesc()
	{
		return taskLabelDesc;
	}
}
