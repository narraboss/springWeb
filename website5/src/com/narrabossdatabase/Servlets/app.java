package com.narrabossdatabase.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.NarraBossWebsite.loginservlet;
import com.NarraBossWebsite.DAOfiles.AccountDao;
import com.NarraBossWebsite.DAOfiles.BeneficiaryDao;
import com.NarraBossWebsite.ItemFiles.Account;
import com.NarraBossWebsite.ItemFiles.Beneficiary;

@SuppressWarnings("serial")
public class app extends HttpServlet {

	public static String s1 = "";
	public static String s2 = "";
	int flag;
	int ben;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		flag = 0;
		ben = 0;
		List<Account> a = AccountDao.INSTANCE.listAccounts();
		HttpSession session;
		session = req.getSession(true);
		if ((session.getAttribute("login").toString()).equals("1")) {

			String beneficiaryAccountNo = req.getParameter("beneficiaryAccountNo");
			// s1 = checkBeneficiaryAccountNo(beneficiaryAccountNo,
			// loginservlet.customerId);
			String beneficiaryName = req.getParameter("beneficiaryName");
			String beneficiaryBankCode = req.getParameter("beneficiaryBankCode");
			// checkBankCode(beneficiaryAccountNo, beneficiaryBankCode);

			List<Beneficiary> beneficiaries = BeneficiaryDao.INSTANCE.listBeneficiaries();
			for (Beneficiary b : beneficiaries) {
				if ((b.getCustomerId()).equals((String)session.getAttribute("customerId")))
					if (b.getBeneficiaryAccountNo().equals(beneficiaryAccountNo)) {
						s1 = "Beneficiary already exists";
						flag = 1;

					}

			}

			if (Integer.parseInt(beneficiaryBankCode) == 10259271) {
				// List<Account> a = AccountDao.INSTANCE.listAccounts();
				for (Account acc : a) {
					if ((acc.getAccountNo()).equals(beneficiaryAccountNo)) {
						ben = 1;
						flag = 1;
						break;

					} else
						ben = 0;

				}

			} else
				ben = 1;

			for (Account acc : a) {
				if ((acc.getAccountNo()).equals(beneficiaryAccountNo)) {
					if ((acc.getCustomerId()).equals((String)session.getAttribute("customerId"))) {
						System.out.println("check ben");
						flag = 1;
						s1 = "Corresponding account belongs to same customer";
					}
				}
			}

			resp.getWriter().println(
					"<html><body style=\"border-style:solid;" + "border-width: 55px;" + "border-color:#FFFFFF;\">");
			if (flag == 0 && ben == 1) {
				BeneficiaryDao.INSTANCE.add((String)session.getAttribute("customerId"), beneficiaryAccountNo, beneficiaryName,
						beneficiaryBankCode);
				resp.getWriter().println("<h3 style=\"color:#1975D1\">" + "Beneficiary details are added</h3>");
				resp.getWriter().println("<h3 style=\"color:#1975D1\">" + "<a href=\"AddBeneficiary.jsp\"back</h3>");
			}

			else if (ben != 1 && s1.equals("")) {
				resp.getWriter().println(" acc not from same bank ");
				resp.getWriter().println("<h3 style=\"color:#1975D1\">" + "<a href=\"AddBeneficiary.jsp\"back</h3>");
				resp.getWriter().println("</body></html>");
			} else {
				resp.getWriter().println(" " + s1);
				resp.getWriter().println("<h3 style=\"color:#1975D1\">" + "<a href=\"AddBeneficiary.jsp\"back</h3>");
				resp.getWriter().println("</body></html>");
			}
			s1 = "";
			ben = 0;
			flag = 0;

		} else
			resp.sendRedirect("/Login.jsp");
	}

	private void checkBankCode(String s, String s2) {
		if (Integer.parseInt(s2) == 10259271) {
			List<Account> a = AccountDao.INSTANCE.listAccounts();
			for (Account acc : a) {
				if ((acc.getAccountNo()).equals(s)) {
					ben = 1;

				}

			}

		}

	}

	private String checkBeneficiaryAccountNo(String s, String c) {

		List<Account> a = AccountDao.INSTANCE.listAccounts();
		for (Account acc : a) {
			if ((acc.getAccountNo()).equals(s)) {
				if ((acc.getCustomerId()).equals(c)) {
					System.out.println("check ben");
					flag = 1;
					return "Corresponding account belongs to same customer";
				} else
					return "";
			}
		}
		return "";
	}

}
