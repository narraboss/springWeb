package com.narrabossdatabase.Servlets;

import java.io.IOException;
import javax.servlet.http.*;

import com.NarraBossWebsite.admin_LoginServlet;
import com.NarraBossWebsite.DAOfiles.BeneficiaryDao;

@SuppressWarnings("serial")
public class AddBeneficiary extends HttpServlet {
	private String message;
	int flag=0;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		
		if(admin_LoginServlet.admin_login==1){ 
            
			String customerId= checkCustomerId(req.getParameter("customerId"));
			String beneficiaryAccountNo=
					checkBeneficiaryAccountNo(req.getParameter("beneficiaryAccountNo"));
			String beneficiaryName=checkBeneficiaryName(req.getParameter("beneficiaryName"));
			String beneficiaryBankCode = 
					checkBeneficiaryBankCode(req.getParameter("beneficiaryBankCode"));
			
        if(flag==0){
			
			BeneficiaryDao.INSTANCE.add(customerId,beneficiaryAccountNo,
					beneficiaryName,beneficiaryBankCode);
			resp.getWriter().println("Beneficiary details are added");
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

	private String checkCustomerId(String s) {
			if (s != "" && s.matches("\\d{8}")) {
				return s;
			
			}
			else{
			flag=1;
			message+=(",invalid customer id");

			 }
			return "";

			}
		private String checkBeneficiaryAccountNo(String s) {
			if (s != "" && s.matches("\\d{12}")) {
				
               return s;
			}
			else{
				flag=1;
				message+=(",invalid beneficiary account number");			
			 }
			return "";

			}
		private String checkBeneficiaryName(String s) {
			if (s != "" && s.matches("^[a-zA-Z ]+$")) {
				return s;

			}
			else{
			flag=1;
			message+=(",invalid beneficiary name");

			 }
            return "";
			}
		private String checkBeneficiaryBankCode(String s) {
			if (s != "" && s.matches("\\d{8}")) {
				
               return s;
			}
			else{
			flag=1;
			message+=(",invalid bbc");
			 }
			return "";

		}
		}
		

