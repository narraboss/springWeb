package com.NarraBossWebsite;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.NarraBossWebsite.DAOfiles.AccountDao;
import com.NarraBossWebsite.DAOfiles.CustomerDao;
import com.NarraBossWebsite.DAOfiles.LastLoginDao;
import com.NarraBossWebsite.DAOfiles.UserDao;
import com.NarraBossWebsite.ItemFiles.Account;
import com.NarraBossWebsite.ItemFiles.Customer;
import com.NarraBossWebsite.ItemFiles.LastLogin;
import com.NarraBossWebsite.ItemFiles.User;

@SuppressWarnings("serial")
public class loginservlet extends HttpServlet {
	int flag;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int login = 0;
		int loginAttempt = 1;
		String customerName = "";
		String gender = "Mr.";

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		HttpSession session;
		session = req.getSession(true);
		List<String> accList = new ArrayList<String>();
		flag = 0;
		String customerId = req.getParameter("customerId");
		String password = req.getParameter("password");
		String cs = checkCustomerId(customerId);
		session.setAttribute("cs", cs);
		String ps = checkPassword(password);
		session.setAttribute("ps", ps);
		List<User> users = UserDao.INSTANCE.listCustomers();
		for (User u : users) {
			if (customerId.equals(u.getCustomerId())) {
				session.setAttribute("customerId", customerId);
				loginAttempt++;
				if (password.equals(u.getPassword())) {
					List<Customer> customers = CustomerDao.INSTANCE.listCustomers();
					for (Customer c : customers)
						if (customerId.equals(c.getCustomerId())) {
							customerName = c.getName();
							session.setAttribute("customerName", customerName);
							if (c.getSex().equals("Female"))
								gender = "Ms.";
							session.setAttribute("gender", gender);
						}
					List<Account> accounts = AccountDao.INSTANCE.listAccounts();
					for (Account a : accounts)
						if ((session.getAttribute("customerId").toString()) == (a.getCustomerId()))
							accList.add(a.getAccountNo());
					Collections.sort(accList);
					session.setAttribute("accList", accList);
					req.setAttribute("accList", accList);
					login = 1;
					session.setAttribute("login", login);
					loginAttempt = 1;
					session.setAttribute("loginAttempt", loginAttempt);
				} else
					session.setAttribute("ps", "Invalid Password");
			}
		}

		if (loginAttempt == 2) {

			List<User> customers = UserDao.INSTANCE.listCustomers();
			for (User c : customers) {
				if (c.getCustomerId().equals(customerId)) {

					if (session.getAttribute("match").equals("0")) {
						session.setAttribute("q", c.getQuestion());
						session.setAttribute("a", c.getAnswer());
						resp.sendRedirect("/Login.jsp");
					}

				}
			}
		}

		if (login == 1) {
			resp.sendRedirect("/afterLogin.jsp");
		} else
			resp.sendRedirect("/Login.jsp");
		out.close();

	}

	private String checkCustomerId(String s) {
		if (s != null && s.matches("\\d{8}")) {
			return "";
		} else {
			flag = 1;
		}
		return "Invalid CustomerId";
	}

	private String checkPassword(String s) {
		if (s != null && s.matches("^[a-zA-Z]\\w*[\\.@$!_]*\\w*")) {
			return "";
		} else {
			flag = 1;
		}
		return "Invalid Password";
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doGet(req, resp);
	}

}
