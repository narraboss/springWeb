package com.narrabossdatabase.XMLservlets;


import java.io.IOException;  
import java.io.PrintWriter;    
import java.util.List;  
    
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.NarraBossWebsite.admin_LoginServlet;
import com.NarraBossWebsite.DAOfiles.AccountDao;
import com.NarraBossWebsite.ItemFiles.Account;
  
@SuppressWarnings("serial")
public class DisplayAccountXML extends HttpServlet    
{    
   
public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException  
 {  
	 resp.setContentType("text/xml");
	 //first query all items  
  if(admin_LoginServlet.admin_login==1){ 
  List<Account> accounts = AccountDao.INSTANCE.listAccounts();
     
  PrintWriter out = resp.getWriter();  
  out.println("<AccountData>");    
  //loop through items list and print each item  
  for (Account a : accounts)   
        {  
   out.println("\n\t<Account>");  
   out.println("\n\t\t<AccountNo>" + a.getAccountNo() + "</AccountNo>");  
   out.println("\n\t\t<CustomerId>" + a.getCustomerId() + "</CustomerId>");
   out.println("\n\t\t<Balance>" + a.getBalance() + "</Balance>");
   out.println("\n\t\t<LastStatementDate>" + a.getLastStatementDate() + "</LastStatementDate>");
   out.println("\n\t\t<LastTransactionDate>" + a.getLastTransactionDate() + "</LastTransactionDate>");
   out.println("\n\t\t<CreationDate>" + a.getCreationDate() + "</CreationDate>");
   out.println("\n\t</Account>");  
        }  
  out.println("</AccountData>");  
  // Close without finalizing and save the file path for writing later  
  out.close();  
  } 
 }  
 public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException  
 {  
  doGet(req, resp);  
 }  
}  

