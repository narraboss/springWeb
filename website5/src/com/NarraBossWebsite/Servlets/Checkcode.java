package com.NarraBossWebsite.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.NarraBossWebsite.loginservlet;
import com.NarraBossWebsite.DAOfiles.AccountDao;
import com.NarraBossWebsite.DAOfiles.CustomerDao;
import com.NarraBossWebsite.ItemFiles.Account;
import com.NarraBossWebsite.ItemFiles.Customer;

public class Checkcode extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static String fromaccount, toaccount, amount, customerId;

	public static int flag;
	public static int ben;
	public static String fromcustomer;
	public static String tocustomer;
	public static String strTo;
	public static String code;
	List<Customer> cus = CustomerDao.INSTANCE.listCustomers();
	List<Account> accounts = AccountDao.INSTANCE.listAccounts();

	@SuppressWarnings("unused")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();

		resp.setContentType("text/html");
		HttpSession session;
		session = req.getSession(true);
		customerId = (String)session.getAttribute("customerId");
		String code = req.getParameter("coddd");
		System.out.println(customerId);
		if ((session.getAttribute("login").toString()).equals("1")) {

			if (code.equals(CheckTransfer.code)) {
				resp.sendRedirect("/transferFunds");
			} else {
				
				resp.sendRedirect("TransferFunds.jsp");
			}

		} else {
			resp.sendRedirect("/logout");
		}
	}
}