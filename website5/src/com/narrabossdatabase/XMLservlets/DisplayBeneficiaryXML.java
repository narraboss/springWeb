package com.narrabossdatabase.XMLservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.NarraBossWebsite.admin_LoginServlet;
import com.NarraBossWebsite.DAOfiles.BeneficiaryDao;
import com.NarraBossWebsite.ItemFiles.Beneficiary;

@SuppressWarnings("serial")
public class DisplayBeneficiaryXML extends HttpServlet    
{ 
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException  
    {  
		
		resp.setContentType("text/xml");
		 //first query all items  
		if(admin_LoginServlet.admin_login==1){  
	    List<Beneficiary> beneficiaries = BeneficiaryDao.INSTANCE.listBeneficiaries();
		
		PrintWriter out=resp.getWriter(); 
		out.println("<BeneficiaryData>");  
		//loop through items list and print each item  
		for (Beneficiary b: beneficiaries)   
	        {  
				out.println("\n\t<Beneficiary>");
				out.println("\n\t\t<CustomerId>"+b.getCustomerId()+"</CustomerId>");
				out.println("\n\t\t<BeneficiaryAccountNo> " + b.getBeneficiaryAccountNo() +
						"</BeneficiaryAccountNo>");  
				out.println("\n\t\t<BeneficiaryName>"+b.getBeneficiaryName()+
						"</BeneficiaryName>");
				out.println("\n\t\t<BeneficiaryBankCode> " + b.getBeneficiaryBankCode() +
						"</BeneficiaryBankCode>");  
				out.println("\n\t</Beneficiary>");  
	        }  
		out.println("</BeneficiaryData>");  
		// Close without finalizing and save the file path for writing later  
		out.close();  
		}
	 } 
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException  
	{  
		doGet(req, resp);  
	}  
}  


