package com.narrabossdatabase.XMLservlets;

import java.io.IOException;  
import java.io.PrintWriter;    
import java.util.List;  
    
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.NarraBossWebsite.admin_LoginServlet;
import com.NarraBossWebsite.DAOfiles.CustomerDao;
import com.NarraBossWebsite.ItemFiles.Customer;
  
@SuppressWarnings("serial")
public class DisplayCustomerXML extends HttpServlet    
{    
   
public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException  
 {  
	 resp.setContentType("text/xml");
	 //first query all items  
	 if(admin_LoginServlet.admin_login==1){  
  List<Customer> customers = CustomerDao.INSTANCE.listCustomers();
     
  PrintWriter out = resp.getWriter();  
  out.println("<CustomerData>");    
  //loop through items list and print each item  
  for (Customer c : customers)   
        {  
   out.println("\n\t<Customer>");  
   out.println("\n\t\t<Name>" + c.getName() + "</Name>");  
   out.println("\n\t\t<DateOfBirth>" + c.getDateOfBirth() + "</DateOfBirth>");
   out.println("\n\t\t<CustomerId>" + c.getCustomerId() + "</CustomerId>");
   out.println("\n\t\t<MobileNo>" + c.getMobileNo() + "</MobileNo>");
   out.println("\n\t\t<PermanentAddress>" + c.getPermanentAddress() + "</PermanentAddress>");
   out.println("\n\t\t<CurrentAddress>" + c.getCurrentAddress() + "</CurrentAddress>");
   out.println("\n\t\t<FathersName>" + c.getFathersName() + "</FathersName>");
   out.println("\n\t\t<Nationality>" + c.getNationality() + "</Nationality>");
   out.println("\n\t\t<Sex>" + c.getSex() + "</Sex>");
   out.println("\n\t\t<Occupation>" + c.getOccupation() + "</Occupation>");
   out.println("\n\t\t<AnnualIncome>" + c.getAnnualIncome() + "</AnnualIncome>");
   out.println("\n\t\t<EmailAddress>" + c.getEmailAddress() + "</EmailAddress>");
   out.println("\n\t</Customer>");  
        }  
  out.println("</CustomerData>");  
  // Close without finalizing and save the file path for writing later  
  out.close();  
	 }
 }  
 public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException  
 {  
  doGet(req, resp);  
 }  
}  
