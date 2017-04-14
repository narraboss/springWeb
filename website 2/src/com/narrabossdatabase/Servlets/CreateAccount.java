package com.narrabossdatabase.Servlets;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.NarraBossWebsite.admin_LoginServlet;
import com.NarraBossWebsite.DAOfiles.AccountDao;
import com.NarraBossWebsite.DAOfiles.UserDao;
import com.NarraBossWebsite.ItemFiles.Account;
import com.NarraBossWebsite.ItemFiles.User;

@SuppressWarnings("serial")
public class CreateAccount extends HttpServlet{
	
	private String message;
    int flag=0;
	
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException,ServletException {
		System.out.println("Creating new Account ");
		resp.setContentType("text/html");
		
		if(admin_LoginServlet.admin_login==1){ 
		String accountNo = checkAccountNo(req.getParameter("accountNo"));
		String customerId = checkCustomerId(req.getParameter("customerId"));
		String balance = checkBalance(req.getParameter("balance"));		
		
		Date date= new Date();
	
		if(flag==0){
		AccountDao.INSTANCE.add(accountNo,customerId,balance,date,date,date);

		resp.getWriter().println(" Account is added");

	}
    else{
    	String[] msg1=message.split(",");
    	for(String m1:msg1)
    	{
    		resp.getWriter().println(m1);
    	}
      }
		} else resp.sendRedirect("/JSPfiles/AdminLogin.jsp");
	}


	private String checkAccountNo(String s)
	{
		if (s !=null && s.matches("\\d{12}")) {
			List<Account> a=AccountDao.INSTANCE.listAccounts();
		       
        	for(Account acc:a){
        		if((acc.getAccountNo()).equals(s)){
        		  flag=1;
        		  message+=",account already exists";
        		  return "";
        		}
        	}
            return s;
		
		}
		else{
		flag=1;
		message+=(",invalid account number");

		 }
		return "";
	}
	private String checkCustomerId(String s)
	{
		if (s != null && s.matches("\\d{8}")) {
			
			List<User> c=UserDao.INSTANCE.listCustomers();
	       
        	for(User cus:c){
        		if((cus.getCustomerId()).equals(s)){
        		  return s;
        		}
        	}
        	message+=",customer does not exists";
            return "";
		}
		else{
		flag=1;
		message+=(",invalid customer id");
		 }
		return "";
	}
	private String checkBalance(String s)
	{
		if (s != null && s.matches("^[1-9]\\d*(\\.\\d)?$")) {
			return s;
		
		}
		else{
		flag=1;
		message+=(",invalid balance");

		 }
		return "";
	}
	
	
}

