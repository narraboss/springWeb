package com.NarraBossWebsite.Servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.List;

import javax.servlet.http.*;

import com.NarraBossWebsite.loginservlet;
import com.NarraBossWebsite.DAOfiles.AccountDao;
import com.NarraBossWebsite.ItemFiles.Account;


@SuppressWarnings("serial")
public class TransactionSelect extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
				    resp.setContentType("text/html");
		    PrintWriter out=resp.getWriter();	    
		    HttpSession session;
		    session=req.getSession(true);
		  if(loginservlet.login==1){ 
			  String stDate=req.getParameter("startDate");
				//String edDate=req.getParameter("endDate");
				//String s3=req.getParameter("s3");
				
			     SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
			    
			     SimpleDateFormat newFormater = new SimpleDateFormat("dd-MM-yyyy");
			 String sDate = null;
			String eDate = null;
			try {
				sDate = newFormater.format(formater.parse(stDate)).toString();
				 eDate = newFormater.format(formater.parse(stDate)).toString();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("sDate",sDate);
			session.setAttribute("eDate",eDate);
			  
				out.println("<html>");
				out.println("<body style=\"border-style:solid;"+
	                                      "border-width: 55px;"+
	                                      "border-color:#FFFFFF;\">");
				out.println("<form name=\"form1\" action=\"/transactionHistory\" method=\"get\">");
				out.println("<table style=\"border-spacing:4px;\"><tr>"+
			            "<th style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
					                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                    "background-color:#EBF0FF;color:#1975D1;\""+
					                "><label>Account Number</label></th>"+
					                "<th style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
					                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                    "background-color:#EBF0FF;color:#1975D1;\""+
					                "><label>Name</label></th>"+
					                "<th style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
					                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                    "background-color:#EBF0FF;color:#1975D1;\""+
					                "><label>Balance</label></th>"+
					                "<th style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
					                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                    "background-color:#EBF0FF;color:#1975D1;\""+
					                "><label>View Transaction History</label></th></tr>");
				List<Account> a=AccountDao.INSTANCE.listAccounts();
		       	for(Account acc:a){
	        		if((acc.getCustomerId()).equals((session.getAttribute("customerId")).toString())){	        			
	        			out.println("<tr><td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
				                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                "background-color:#EBF0FF;color:#1975D1;\""+
				                ">"+acc.getAccountNo()+"</td>"+
                                "<td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
				                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                "background-color:#EBF0FF;color:#1975D1;\""+
				                ">"+session.getAttribute("customerName").toString()+"</td>"+
				                "<td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
				                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                "background-color:#EBF0FF;color:#1975D1;\""+
				                ">"+acc.getBalance()+"</td>"+
				                "<td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
				                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                "background-color:#EBF0FF;color:#1975D1;\""+
				                "><input name=\"acc\" type=\"submit\" value=\""+acc.getAccountNo()+"\"/></td></tr>");
	        			}        		
		       	}
				
		       	out.println("</table></form>");	
				out.println("</body>");
				out.println("</html>");			
			
		  }else resp.sendRedirect("Login.jsp");
  }
			
}