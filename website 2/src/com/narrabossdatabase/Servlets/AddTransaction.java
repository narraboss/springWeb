package com.narrabossdatabase.Servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.NarraBossWebsite.admin_LoginServlet;
import com.NarraBossWebsite.DAOfiles.AccountDao;
import com.NarraBossWebsite.DAOfiles.BeneficiaryDao;
import com.NarraBossWebsite.DAOfiles.TransactionDao;
import com.NarraBossWebsite.ItemFiles.Account;
import com.NarraBossWebsite.ItemFiles.Beneficiary;

@SuppressWarnings("serial")
public class AddTransaction extends HttpServlet {

	String message, balance;
	int flag = 0;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("text/html");

		message = "";
		balance = "";
		if (admin_LoginServlet.admin_login == 1) {

			String customerId = checkCustomerId(req.getParameter("customerId"));

			String toAccount = checkToAccountNo(req.getParameter("toAccount"));
			String amount = checkAmount(req.getParameter("amount"));
			String description = checkIsNull(req.getParameter("description"));
			balance += checkBalance(req.getParameter("balance"));
			String toAccBalance = checkIsNull(req.getParameter("toAccBalance"));
			String accountNo = checkAccountNo(req.getParameter("accountNo"), customerId);

			// date convention
			Date d = new Date();

			if (flag == 0) {

				TransactionDao.INSTANCE.add(customerId, accountNo, d, toAccount, amount, balance, toAccBalance,
						description);
				resp.getWriter().println("Transaction is added");
			}

			else {
				String[] msg1 = message.split(",");
				for (String m1 : msg1) {
					resp.getWriter().println(m1);
				}
			}
		} else
			resp.sendRedirect("/JSPfiles/AdminLogin.jsp");
	}

	private String checkCustomerId(String s) {
		if (s != null && s.matches("\\d{8}")) {
			List<Account> a = AccountDao.INSTANCE.listAccounts();
			for (Account acc : a) {
				if ((acc.getCustomerId()).equals(s)) {
					return s;
				}
			}
			flag = 1;
			message += ",customer does not exist";
			return "";
		} else {
			flag = 1;
			message += (",invalid customer id");

		}
		return "";

	}

	private String checkAccountNo(String s, String c) {
		if (s != null && s.matches("\\d{12}")) {
			List<Account> a = AccountDao.INSTANCE.listAccounts();
			for (Account acc : a) {
				if ((acc.getAccountNo()).equals(s)) {
					if ((acc.getCustomerId()).equals(c)) {
						AccountDao.INSTANCE.updateBalance(acc.getId(), balance);
						return s;
					} else {
						flag = 1;
						message += ",Corresponding customer id is wrong";
						return "";
					}
				}
			}
			flag = 1;
			message += ",account does not exist";
			return "";
		} else {
			flag = 1;
			message += (",invalid account number");
		}
		return "";

	}

	private String checkToAccountNo(String s) {
		if (s != "" && s.matches("\\d{12}")) {
			List<Beneficiary> a = BeneficiaryDao.INSTANCE.listBeneficiaries();
			for (Beneficiary acc : a) {
				if ((acc.getBeneficiaryAccountNo()).equals(s)) {
					return s;
				}
			}
			flag = 1;
			message += ",account does not exist";
			return "";

		} else {
			flag = 1;
			message += (",invalid to account number");

		}
		return "";
	}

	private String checkAmount(String s) {
		if (s != "" && s.matches("^[1-9]\\d*(\\.\\d)?$")) {
			return s;
		} else {
			flag = 1;
			message += (",invalid amount");
		}
		return "";
	}

	private String checkBalance(String s) {
		if (s != "" && s.matches("^[1-9]\\d*(\\.\\d)?$")) {
			return s;
		} else {
			flag = 1;
			message += (",Invalid balance");
		}
		return "";
	}

	private String checkIsNull(String s) {
		if (s != "") {
			return s;
		} else {
			flag = 1;
			message += (",Invalid description");

		}
		return "";
	}

}
