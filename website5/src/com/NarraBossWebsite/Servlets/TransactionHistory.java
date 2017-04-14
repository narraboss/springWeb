package com.NarraBossWebsite.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.NarraBossWebsite.loginservlet;
import com.NarraBossWebsite.DAOfiles.AccountDao;
import com.NarraBossWebsite.DAOfiles.TransactionDao;
import com.NarraBossWebsite.ItemFiles.Account;
import com.NarraBossWebsite.ItemFiles.Transaction;

@SuppressWarnings("serial")
public class TransactionHistory extends HttpServlet {

	public static String accountNo;

	public static String balance;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		int flag = 0;
		String s = "";
		HttpSession session;
		session = req.getSession(true);
		if ((session.getAttribute("login").toString()).equals("1")) {

			String eDate = req.getParameter("endDate");
			String sDate = req.getParameter("startDate");
			accountNo = req.getParameter("accno");

			System.out.println(accountNo);

			Date start_date = dateformat(sDate);
			Date end_date = dateformat(eDate);

			// System.out.println(start_date);
			// System.out.println(end_date);

			if (flag == 0) {
				List<Account> accounts = AccountDao.INSTANCE.listAccounts();
				for (Account a : accounts) {
					if (a.getAccountNo().equals(accountNo)) {
						balance = a.getBalance();
						Long id = a.getId();
						// DateFormat dateFormat = new
						// SimpleDateFormat("dd-MM-yyyy");

						Date date = new Date();
						AccountDao.INSTANCE.updateStatementDate(id, date);
					}
				}
				session.setAttribute("tracc", accountNo);
				session.setAttribute("sdate", sDate);
				session.setAttribute("edate", eDate);
				
				
				
				/*
				 * List<Transaction> transactions =
				 * TransactionDao.INSTANCE.listTransactions();
				 * ArrayList<Transaction> date = new ArrayList<Transaction>();
				 * date.clear();
				 * 
				 * for (Transaction t : transactions) { if
				 * (accountNo.equals(t.getToAccount()) ||
				 * accountNo.equals(t.getAccountNo())) { date.add(t); } }
				 * 
				 * Collections.sort(date, new Comparator<Transaction>() { public
				 * int compare(Transaction o1, Transaction o2) { return
				 * o1.getDateOfTransaction().compareTo(o2.getDateOfTransaction()
				 * ); } }); Collections.reverse(date);
				 * 
				 * trans.clear(); for (Transaction t1 : date) { if
				 * (t1.getDateOfTransaction().after(start_date) &&
				 * t1.getDateOfTransaction().before(end_date)) { trans.add(t1);
				 * 
				 * } }
				 */
				resp.sendRedirect("TransactionHistory1.jsp");

			}
		} else
			resp.sendRedirect("Login.jsp");
	}

	private Date dateformat(String d) {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date d1 = null;

		try {
			d1 = dateFormat.parse(d);
			// System.out.println( d);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return d1;

	}

}
