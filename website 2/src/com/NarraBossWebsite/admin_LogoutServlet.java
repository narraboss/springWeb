package com.NarraBossWebsite;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class admin_LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException  
	{  
		admin_LoginServlet.admin_login=0;
		resp.sendRedirect("/JSPfiles/AdminLogin.jsp");
	} 
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException  
	{
		doGet(req,resp);
	}
}
