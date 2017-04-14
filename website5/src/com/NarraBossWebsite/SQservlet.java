package com.NarraBossWebsite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.*;

import com.NarraBossWebsite.DAOfiles.AccountDao;
import com.NarraBossWebsite.DAOfiles.CustomerDao;
import com.NarraBossWebsite.ItemFiles.Account;
import com.NarraBossWebsite.ItemFiles.Customer;

@SuppressWarnings("serial")
public class SQservlet extends HttpServlet {

	public static String q = "";
	public static String a = "";
	public static int match;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("text/html");
		HttpSession session;
		session = req.getSession(true);
		match = 0;
		String answer = req.getParameter("answer");
		List<String> accList = new ArrayList<String>();
		if (a.equals(answer)) {
			match = 1;
			session.setAttribute("login", "1");
			List<Customer> customers = CustomerDao.INSTANCE.listCustomers();
			session.setAttribute("loginAttempt", "1");
			for (Customer c : customers)
				if (session.getAttribute("customerId").equals(c.getCustomerId()))
					session.setAttribute("customerName", c.getName());
			List<Account> accounts = AccountDao.INSTANCE.listAccounts();
			for (Account a : accounts)
				if (session.getAttribute("customerId").equals(a.getCustomerId()))
					accList.add(a.getAccountNo());
			Collections.sort(accList);
			session.setAttribute("accList", accList);
		}
		if (session.getAttribute("login").equals("1")) {
			resp.sendRedirect("/afterlogin.html");
		} else
			resp.sendRedirect("/home.jsp");
	}
}
