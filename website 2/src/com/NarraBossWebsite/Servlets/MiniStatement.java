package com.NarraBossWebsite.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.NarraBossWebsite.loginservlet;
import com.NarraBossWebsite.DAOfiles.AccountDao;
import com.NarraBossWebsite.DAOfiles.TransactionDao;
import com.NarraBossWebsite.ItemFiles.Account;
import com.NarraBossWebsite.ItemFiles.Transaction;

@SuppressWarnings("serial")
public class MiniStatement extends HttpServlet {

	public static String balance = "";
	public static String accountNo = "";
	public static List<Transaction> date = new ArrayList<>();

	int flag;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		date.clear();
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		flag = 0;
		// HttpSession session;
		// session = req.getSession(true);
		if (loginservlet.login == 1) {
			accountNo = req.getParameter("acc");

			System.out.println(accountNo);

			List<Account> accounts = AccountDao.INSTANCE.listAccounts();
			for (Account a : accounts) {
				if (a.getAccountNo().equals(accountNo)) {
					balance = a.getBalance();
					Long id = a.getId();
					Date date = new Date();
					AccountDao.INSTANCE.updateStatementDate(id, date);
				}
			}

			List<Transaction> transactions = TransactionDao.INSTANCE.listTransactions();
			// ArrayList<Transaction> date=new ArrayList<>();

			for (Transaction t : transactions) {
				if (accountNo.equals(t.getToAccount()) || accountNo.equals(t.getAccountNo())) {
					// System.out.println(t.getAccountNo());
					date.add(t);
				}
				Collections.sort(date, new Comparator<Transaction>() {
					public int compare(Transaction o1, Transaction o2) {
						return o1.getDateOfTransaction().compareTo(o2.getDateOfTransaction());
					}
				});

				Collections.reverse(date);

				for (int i = 0; i < date.size(); i++) {
					System.out.println(date.get(i).getToAccount());
				}

			}

			resp.sendRedirect("ministatement1.jsp");

		} else
			resp.sendRedirect("Login.jsp");
	}
}
