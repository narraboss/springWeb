package com.NarraBossWebsite.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.NarraBossWebsite.codegenerator;
import com.NarraBossWebsite.loginservlet;
import com.NarraBossWebsite.DAOfiles.AccountDao;
import com.NarraBossWebsite.DAOfiles.CustomerDao;
import com.NarraBossWebsite.ItemFiles.Account;
import com.NarraBossWebsite.ItemFiles.Customer;

public class CheckTransfer extends HttpServlet {

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
		customerId = loginservlet.customerId;
		System.out.println(customerId);
		if (loginservlet.login == 1) {

			flag = 0;
			ben = 0;

			fromaccount = (req.getParameter("fromaccount"));
			String s1 = checkFromAccountNo(fromaccount);
			toaccount = (req.getParameter("toaccount"));
			checkBankCode(toaccount);
			amount = req.getParameter("amount");

			if (Integer.parseInt(amount) >= 100 || ben != 1) {

				code = codegenerator.randomWord();
				for (Account acc : accounts) {
					if (acc.getAccountNo().equals(fromaccount)) {
						String customer = acc.getCustomerId();
						for (Customer c : cus) {
							if (c.getCustomerId().equals(customer)) {
								strTo = c.getEmailAddress();
							}
							if (strTo == null) {

								out.print("try again after updating the mail-id ");

							}

							try {
								sendSimpleMail(code);
							} catch (Exception e) {

								e.printStackTrace();
							}

						}
					}
				}
				System.out.println("call checkcode.jsp");
				resp.sendRedirect("checkcode.jsp");
			} else
				resp.sendRedirect("/transferFunds");
		} else
			resp.sendRedirect("/logout");

	}

	private String checkFromAccountNo(String s) {
		List<Account> a = AccountDao.INSTANCE.listAccounts();
		for (Account acc : a)
			if ((acc.getAccountNo()).equals(s))
				return "";
		flag = 1;
		return "Account does not exist";
	}

	private void checkBankCode(String s) {
		List<Account> a = AccountDao.INSTANCE.listAccounts();
		for (Account acc : a) {
			if ((acc.getAccountNo()).equals(s))
				ben = 1;
		}
	}

	private void sendSimpleMail(String s) throws Exception {
		// [START simple_example]
		// String strTo= "basanth.narra@gmail.com";

		// Trim the stuff

		System.out.println(strTo);
		if (strTo.length() == 0)
			throw new Exception("To field cannot be empty.");
		// Call the GAEJ Email Service
		Properties props = new Properties();
		Session session1 = Session.getDefaultInstance(props, null);
		MimeMessage msg = new MimeMessage(session1);
		msg.setFrom(new InternetAddress("basanth.narra@gmail.com"));
		msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(strTo));
		msg.setSubject("OTP alert");
		msg.setText(s);

		Transport.send(msg);

	}

}