/**
 * 
 */
package com.bitstunnel.jectools.DAO;

import java.util.ArrayList;
import java.util.Iterator;

import com.bitstunnel.jectools.models.IssueVO;



/**
 * @author maijing-001
 *
 */
public class TestMain {

	/**
	 * 
	 */
	public TestMain() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		IssueVO ivoitem = null;
		System.out.println(" req_key | Summary | Type | Reporter | req_status | CreateDate");
		FetchIssuesDao testdao = new FetchIssuesDAOImpl();
		ArrayList<IssueVO> issvolist = (ArrayList<IssueVO>) testdao.getIssuesByAnalyst(null);
		Iterator<IssueVO>  ivo = issvolist.iterator();
		while (ivo.hasNext()){
			 ivoitem = ivo.next();
			 System.out.print(ivoitem.getPkey() + " | ");
			 System.out.print(ivoitem.getSummary() + " | ");
			 System.out.print(ivoitem.getIssuetype() + " | ");
			 System.out.print(ivoitem.getReporter() + " | ");
			 System.out.print(ivoitem.getIssuestatus() + " | ");
			 System.out.print(ivoitem.getCreateDate().toString() + "\n");
			 
		}
		 System.out.println("Print out was finished");

/*		String req_key = "ncb-1234";
		String summary = "It is test summary";
		String Type  = "It is a type string";
		String Reporter = "maijing";
		System.out.print(req_key + " | ");
		System.out.print(summary + " | ");
		System.out.print(Type + " | ");	
		System.out.print(Reporter + " \n");
		System.out.println("test....");*/
	}

}
