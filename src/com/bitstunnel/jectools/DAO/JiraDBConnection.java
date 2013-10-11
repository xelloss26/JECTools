package com.bitstunnel.jectools.DAO;

import java.sql.Connection;
import java.sql.DriverManager;


public class JiraDBConnection {

	private static String driverName = "com.mysql.jdbc.Driver";
	private static String DBurl = "jdbc:mysql://10.196.19.64:3306/group-jira";
	private static String username = "jiraquser";
	private static String password = "cpic1234";
	
	public void setDBInfo(String dn,String url,String user, String pwd){
		driverName = dn;
		DBurl = url;
		username = user;
		password = pwd;
	}
	
	
	public  static Connection getConnection(){
		Connection conn = null;
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(DBurl,username,password);
					
		} catch (Exception e)
		{
			e.printStackTrace();
			return conn;
		
		}
		return conn;
	}
}
