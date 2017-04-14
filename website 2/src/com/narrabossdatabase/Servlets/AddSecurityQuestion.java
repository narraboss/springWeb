package com.narrabossdatabase.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.NarraBossWebsite.admin_LoginServlet;
import com.NarraBossWebsite.DAOfiles.UserDao;
import com.NarraBossWebsite.ItemFiles.User;


@SuppressWarnings("serial")
public class AddSecurityQuestion extends HttpServlet {
	
	int flag=0;
	String message="";
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		
		    resp.setContentType("text/html");
		    if(admin_LoginServlet.admin_login==1){ 
			String customerId= checkCustomerId(req.getParameter("customerId"));
			String ques=req.getParameter("question");
			String ans=req.getParameter("answer");
			
			
        	List<User> c=UserDao.INSTANCE.listCustomers();
            int cc=0;
        if(flag==0){  	
        	for(User cus:c){
        		if((cus.getCustomerId()).equals(customerId)){
        			cc=1;
        			if(ans!=null){
        				    cus.setQuestion(ques);
        				    cus.setPassword(ans);
        				    
          			       resp.getWriter().println("Selected question and answer are added");        				
        			}
        			else resp.getWriter().println("Please enter an answer");
        		}
        	}
        	if(cc==0)
        	resp.getWriter().println("Customer id does not exist");
        }  

        else
        {
        	String[] msg1=message.split(",");
        	for(String m1:msg1)
        	{
        		resp.getWriter().println(m1);
        	}
        }
      } else resp.sendRedirect("/JSPfiles/AdminLogin.jsp");
    }

	String checkCustomerId(String s) 
	{
			if (s!=null && s.matches("\\d{8}")) 
			{
				return s;
			}
			else
			{
				flag=1;
				message+=("invalid customerId,");
			}
			
			return "";
	}
	
}
		
