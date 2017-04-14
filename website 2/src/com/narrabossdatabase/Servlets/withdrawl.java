package com.narrabossdatabase.Servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.NarraBossWebsite.admin_LoginServlet;
import com.NarraBossWebsite.DAOfiles.AccountDao;
import com.NarraBossWebsite.DAOfiles.TransactionDao;
import com.NarraBossWebsite.ItemFiles.Account;

@SuppressWarnings("serial")

public class withdrawl extends HttpServlet {

	String toaccount, amount;

	int flag;


	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// System.out.println("deposit-- to bal");
		resp.setContentType("text/html");
		HttpSession session;
		session = req.getSession(true);

		if (admin_LoginServlet.admin_login == 1) {

			toaccount = (req.getParameter("accountNo"));

			String s1 = checkFromAccountNo(toaccount);
			amount = req.getParameter("balance");

			flag = 0;
			

			String bal1 = checkBalance(amount, toaccount);
			resp.getWriter().println(
					"<html><body style=\"border-style:solid;" + "border-width: 55px;" + "border-color:#FFFFFF;\">");
			if (flag == 0) {
				resp.getWriter().println(
						"<h3 style=\"color:#1975D1\">" + amount + " has been debited  to " + toaccount + "</h3>");
			} else
				resp.getWriter().println("<h3 style=\"color:#1975D1\">" + s1 + " " + bal1 + "");
			resp.getWriter().println("<a href=\"/JSPfiles/Withdrawl.jsp\">" + "<h3 style=\"color:#1975D1\">Back</h3></a>"
					+ "</body></html>");
		} else
			resp.sendRedirect("/JSPfiles/AdminLogin.jsp");
	}

	private String checkFromAccountNo(String s) {
		List<Account> a = AccountDao.INSTANCE.listAccounts();
		for (Account acc : a)
			if ((acc.getAccountNo()).equals(s))
				return "";
		flag = 1;
		return "Account does not exist";
	}



	private String checkBalance(String s, String f) {
		List<Account> a = AccountDao.INSTANCE.listAccounts();
		for (Account acc : a) {
			if ((acc.getAccountNo()).equals(f)) {
				Long id = acc.getId();
				if (Double.parseDouble(acc.getBalance()) > Double.parseDouble(s))
				{
						
								double b = Double.parseDouble(acc.getBalance());
								double am = Double.parseDouble(s);
								String toBal = Double.toString(b - am);
								AccountDao.INSTANCE.updateBalance(id, toBal);// toaccount
																				// updation
								
								TransactionDao.INSTANCE.add(acc.getCustomerId(), acc.getAccountNo(), new Date(), null, amount, toBal,
										null, "Withdrawl");
								
								AccountDao.INSTANCE.updateTransactionDate(id, new Date());
								return "";
							}
					
				
			}
		}
		flag = 1;
		return "Insufficient Balance";
	}
}
