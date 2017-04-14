package com.narrabossdatabase.XMLservlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.NarraBossWebsite.admin_LoginServlet;
import com.NarraBossWebsite.DAOfiles.UserDao;
import com.NarraBossWebsite.ItemFiles.User;

@SuppressWarnings("serial")
public class DisplayLoginDetailsXML extends HttpServlet    
{ 

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException  
	{  
		
		resp.setContentType("text/xml");
		if(admin_LoginServlet.admin_login==1){ 
		PrintWriter out=resp.getWriter(); 
		
		List<User> customers = UserDao.INSTANCE.listCustomers();
		 
		//loop through items list and print each item 
		out.println("<LoginData>");
		for (User c:customers)   
	        {  
				out.println("\n\t<Customer>");
				out.println("\n\t\t<CustomerId>"+c.getCustomerId()+"</CustomerId>");
				out.println("\n\t\t<Password>"+c.getPassword()+"</Password>");
				out.println("\n\t\t<SecurityQuestion>");
					 
					   out.println("\n\t\t\t\t<Question>" + c.getQuestion()+ "</Question>");
					   out.println("\n\t\t\t\t<Answer>" + c.getAnswer() + "</Answer>");
					  
				   }
				   
				   out.println("\n\t\t</SecurityQuestion>");
				out.println("\n\t</Customer>");  
	       
		out.println("</LoginData>");  
		
		out.close();  
		}  
	 } 
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException  
	{  
		doGet(req, resp);  
	}  
}  
