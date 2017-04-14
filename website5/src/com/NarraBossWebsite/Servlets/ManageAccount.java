package com.NarraBossWebsite.Servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@SuppressWarnings("serial")
public class ManageAccount extends HttpServlet {

         int flag;

		public void doGet(HttpServletRequest req, HttpServletResponse resp)
        		 throws IOException {
        	 		resp.setContentType("text/html");
        	 		flag=0;
        	 		PrintWriter out=resp.getWriter();
        	 		HttpSession session;
      	 		session=req.getSession(true);
                    if((session.getAttribute("login").toString()).equals("1")){
        	 		out.println("<html>");
        	 		out.println("<body style=\"border-style:solid;"+
	                                      "border-width: 55px;"+
	                                      "border-color:#FFFFFF;\">");
        	 		out.println("<h3 style=\"color:#1975D1\">Thank you! Your contact information will be updated</h3>");
        	 		out.println("</body>");
        	 		out.println("</html>");
                    }
                    else resp.sendRedirect("Login.jsp");
         }

}