package com.NarraBossWebsite.Servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.NarraBossWebsite.loginservlet;
import com.NarraBossWebsite.DAOfiles.AccountDao;
import com.NarraBossWebsite.DAOfiles.CustomerDao;
import com.NarraBossWebsite.DAOfiles.TransactionDao;
import com.NarraBossWebsite.ItemFiles.Account;
import com.NarraBossWebsite.ItemFiles.Customer;

@SuppressWarnings("serial")
public class TransferFunds extends HttpServlet {

	String fromaccount, toaccount, amount, customerId;
	DateFormat dateFormat, timeFormat;
	Date date, time;
	int flag;
	int ben;
	String fromcustomer = loginservlet.customerId;
	String tocustomer;
	String strTo;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("text/html");
		HttpSession session;
		session = req.getSession(true);
		customerId = loginservlet.customerId;

		if (loginservlet.login == 1) {

			flag = 0;
			ben = 0;

			fromaccount = CheckTransfer.fromaccount;
			String s1 = checkFromAccountNo(fromaccount);
			toaccount = CheckTransfer.toaccount;
			checkBankCode(toaccount);
			amount = CheckTransfer.amount;

			// fromaccount = (req.getParameter("fromaccount"));
			// String s1 = checkFromAccountNo(fromaccount);
			// toaccount = (req.getParameter("toaccount"));
			// checkBankCode(toaccount);
			// amount = req.getParameter("amount");

			String bal = checkBalance2(amount, fromaccount, toaccount);
			resp.getWriter().println(
					"<html><body style=\"border-style:solid;" + "border-width: 55px;" + "border-color:#FFFFFF;\">");
			if (flag == 0) {
				resp.getWriter().println("<h3 style=\"color:#1975D1\">" + amount + " has been transfered from"
						+ fromaccount + " to " + toaccount + "</h3>");
			} else
				resp.getWriter().println("<h3 style=\"color:#1975D1\">" + s1 + " " + bal + "");
			resp.getWriter().println(
					"<a href=\"TransferFunds.jsp\">" + "<h3 style=\"color:#1975D1\">Back</h3></a>" + "</body></html>");
		} else
			resp.sendRedirect("Login.jsp");
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

	private String checkBalance1(String s, String f, String t) {

		List<Account> a = AccountDao.INSTANCE.listAccounts();
		for (Account acc : a) {
			System.out.println(f + "from account");
			System.out.println(acc.getAccountNo() + "fr AACC");
			if ((acc.getAccountNo()).equals(f)) {
				Long id1 = acc.getId();
				fromcustomer = acc.getCustomerId();

				if (Double.parseDouble(acc.getBalance()) > Double.parseDouble(s)) {
					if (ben == 1) {
						for (Account ac : a)
							if ((ac.getAccountNo()).equals(t)) {
								Long id2 = ac.getId();
								double b = Double.parseDouble(ac.getBalance());
								double am = Double.parseDouble(s);
								String toBal = Double.toString(b + am);
								AccountDao.INSTANCE.updateBalance(id2, toBal);// toaccount
																				// updation
								b = Double.parseDouble(acc.getBalance());
								String Bal = Double.toString(b - am);
								TransactionDao.INSTANCE.add(customerId, fromaccount, new Date(), toaccount, amount, Bal,
										toBal, "online transfer");
								AccountDao.INSTANCE.updateBalance(id1, Bal);// from
																			// account
																			// updation
								AccountDao.INSTANCE.updateTransactionDate(id1, new Date());

								/////////////////////////////////////////////////////////////

								List<Customer> cus = CustomerDao.INSTANCE.listCustomers();

								for (Customer c : cus) {
									if (c.getCustomerId().equals(fromcustomer))
										strTo = c.getEmailAddress();
								}
								try {
									if (strTo == null)
										throw new Exception("Email cannot be empty.");

									// Trim the stuff
									strTo = strTo.trim();
									if (strTo.length() == 0)
										throw new Exception("Email cannot be empty.");
									// Call the GAEJ Email Service
									Properties props = new Properties();
									Session session1 = Session.getDefaultInstance(props, null);
									MimeMessage msg = new MimeMessage(session1);
									msg.setFrom(new InternetAddress("basanth.narra@gmail.com"));
									msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(strTo));
									msg.setSubject("email alert");
									msg.setText("Transfer done over specified limit .The amount is " + amount);
									Transport.send(msg);
								} catch (AddressException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (MessagingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								////////////////////////////////////

							}
					}
					// ben else
					else {
						double b = Double.parseDouble(acc.getBalance());
						double am = Double.parseDouble(s);
						String Bal = Double.toString(b - am);

						TransactionDao.INSTANCE.add(customerId, fromaccount, new Date(), toaccount, amount, Bal,
								"unavailable", "online transfer - other bank");
						AccountDao.INSTANCE.updateBalance(id1, Bal);// from
																	// account
																	// updation
						AccountDao.INSTANCE.updateTransactionDate(id1, new Date());

						///////////////////////////////////////////////
						List<Customer> cus = CustomerDao.INSTANCE.listCustomers();

						for (Customer c : cus) {
							if (c.getCustomerId().equals(fromcustomer))
								strTo = c.getEmailAddress();
						}
						try {
							if (strTo == null)
								throw new Exception("Email cannot be empty.");

							// Trim the stuff
							strTo = strTo.trim();
							if (strTo.length() == 0)
								throw new Exception("Email cannot be empty.");
							// Call the GAEJ Email Service
							Properties props = new Properties();
							Session session1 = Session.getDefaultInstance(props, null);
							MimeMessage msg = new MimeMessage(session1);
							msg.setFrom(new InternetAddress("basanth.narra@gmail.com"));
							msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(strTo));
							msg.setSubject("email alert");
							msg.setText("Transfer done over specified limit .The amount is " + amount);
							Transport.send(msg);
						} catch (AddressException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						///////////////////////////////////////////////
						return "";
					}

				} else {
					return "insufficient funds";
				}
			} else {
				return "invalid From Acount no";
			}
		}

		return "";

	}

	/*
	 * private String checkBalance(String s, String f, String t) {
	 * 
	 * List<Account> a = AccountDao.INSTANCE.listAccounts(); for (Account acc :
	 * a) { if ((acc.getAccountNo()).equals(f)) { Long id1 = acc.getId(); if
	 * (Double.parseDouble(acc.getBalance()) > Double.parseDouble(s)) { if (ben
	 * == 1) { for (Account ac : a) if ((ac.getAccountNo()).equals(t)) { Long
	 * id2 = ac.getId(); double b = Double.parseDouble(ac.getBalance()); double
	 * am = Double.parseDouble(s); String toBal = Double.toString(b + am);
	 * AccountDao.INSTANCE.updateBalance(id2, toBal);// toaccount // updation b
	 * = Double.parseDouble(acc.getBalance()); String Bal = Double.toString(b -
	 * am); TransactionDao.INSTANCE.add(customerId, fromaccount, new Date(),
	 * toaccount, amount, Bal, toBal, "online transfer");
	 * AccountDao.INSTANCE.updateBalance(id1, Bal);// from // account //
	 * updation AccountDao.INSTANCE.updateTransactionDate(id1, new Date());
	 * 
	 * List<Customer> cus = CustomerDao.INSTANCE.listCustomers(); List<Account>
	 * accounts = AccountDao.INSTANCE.listAccounts();
	 * 
	 * for (Account acc1 : accounts) { if
	 * (acc1.getAccountNo().equals(fromaccount)) { fromcustomer =
	 * acc1.getCustomerId(); } if (acc1.getAccountNo().equals(toaccount))
	 * tocustomer = acc.getCustomerId(); } if (fromcustomer.equals(tocustomer))
	 * {
	 * 
	 * for (Customer c : cus) { if (c.getCustomerId().equals(fromcustomer))
	 * strTo = c.getEmailAddress(); } try { if (strTo == null) throw new
	 * Exception("To field cannot be empty.");
	 * 
	 * // Trim the stuff strTo = strTo.trim(); if (strTo.length() == 0) throw
	 * new Exception("To field cannot be empty."); // Call the GAEJ Email
	 * Service Properties props = new Properties(); Session session2 =
	 * Session.getDefaultInstance(props, null); MimeMessage msg = new
	 * MimeMessage(session2); msg.setFrom(new
	 * InternetAddress("basanth.narra@gmail.com"));
	 * msg.addRecipient(MimeMessage.RecipientType.TO, new
	 * InternetAddress(strTo)); msg.setSubject("email alert"); msg.setText(
	 * "Transfer done over specified limit .The amount is " + amount);
	 * Transport.send(msg); } catch (AddressException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } catch
	 * (MessagingException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (Exception e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); }
	 * 
	 * return ""; } } // if ben is with in the bank else {
	 * 
	 * double b = Double.parseDouble(acc.getBalance()); double am =
	 * Double.parseDouble(s); String Bal = Double.toString(b - am);
	 * TransactionDao.INSTANCE.add(customerId, fromaccount, new Date(),
	 * toaccount, amount, Bal, "unavailable", "online transfer - other bank");
	 * AccountDao.INSTANCE.updateBalance(id1, Bal);// from // account //
	 * updation AccountDao.INSTANCE.updateTransactionDate(id1, new Date());
	 * return ""; } } } else { flag = 1; return "Insufficient Balance"; } } }
	 * return "success";
	 * 
	 * }
	 */

	private String checkBalance2(String s, String f, String t) {
		List<Account> a = AccountDao.INSTANCE.listAccounts();
		for (Account acc : a) {
			if ((acc.getAccountNo()).equals(f)) {
				Long id1 = acc.getId();
				if (Double.parseDouble(acc.getBalance()) > Double.parseDouble(s))
					if (ben == 1) {
						for (Account ac : a)
							if ((ac.getAccountNo()).equals(t)) {
								Long id2 = ac.getId();
								double b = Double.parseDouble(ac.getBalance());
								double am = Double.parseDouble(s);
								String toBal = Double.toString(b + am);
								AccountDao.INSTANCE.updateBalance(id2, toBal);// toaccount
																				// updation
								b = Double.parseDouble(acc.getBalance());
								String Bal = Double.toString(b - am);
								TransactionDao.INSTANCE.add(customerId, fromaccount, new Date(), toaccount, amount, Bal,
										toBal, "online transfer");
								AccountDao.INSTANCE.updateBalance(id1, Bal);// from
								// account
								// updation
								AccountDao.INSTANCE.updateTransactionDate(id1, new Date());
								/////////////////////////////////////////////////////////////

								List<Customer> cus = CustomerDao.INSTANCE.listCustomers();

								for (Customer c : cus) {
									if (c.getCustomerId().equals(fromcustomer))
										strTo = c.getEmailAddress();

								}

								System.out.println(strTo);
								try {
									if (strTo == null)
										throw new Exception("Email cannot be empty.");

									// Trim the stuff
									strTo = strTo.trim();
									if (strTo.length() == 0)
										throw new Exception("Email cannot be empty.");
									// Call the GAEJ Email Service
									Properties props = new Properties();
									Session session1 = Session.getDefaultInstance(props, null);
									MimeMessage msg = new MimeMessage(session1);
									msg.setFrom(new InternetAddress("basanth.narra@gmail.com"));
									msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(strTo));
									msg.setSubject("email alert");
									msg.setText("Transfer done over specified limit .The amount is " + amount);
									Transport.send(msg);
								} catch (AddressException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (MessagingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								////////////////////////////////////

								return "";
							}
					} // if ben
					else {
						double b = Double.parseDouble(acc.getBalance());
						double am = Double.parseDouble(s);
						String Bal = Double.toString(b - am);
						TransactionDao.INSTANCE.add(customerId, fromaccount, new Date(), toaccount, amount, Bal,
								"unavailable", "online transfer - other bank");
						AccountDao.INSTANCE.updateBalance(id1, Bal);// from
																	// account
																	// updation
						AccountDao.INSTANCE.updateTransactionDate(id1, new Date());

						///////////////////////////////////////////////
						List<Customer> cus = CustomerDao.INSTANCE.listCustomers();

						for (Customer c : cus) {
							if (c.getCustomerId().equals(fromcustomer))
								strTo = c.getEmailAddress();
						}
						try {
							if (strTo == null)
								throw new Exception("Email cannot be empty.");

							// Trim the stuff
							strTo = strTo.trim();
							if (strTo.length() == 0)
								throw new Exception("Email cannot be empty.");
							// Call the GAEJ Email Service
							Properties props = new Properties();
							Session session1 = Session.getDefaultInstance(props, null);
							MimeMessage msg = new MimeMessage(session1);
							msg.setFrom(new InternetAddress("basanth.narra@gmail.com"));
							msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(strTo));
							msg.setSubject("email alert");
							msg.setText("Transfer done over specified limit .The amount is " + amount);
							Transport.send(msg);
						} catch (AddressException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						///////////////////////////////////////////////

						return "";
					}
			}
		}
		flag = 1;
		return "Insufficient Balance";
	}
}
