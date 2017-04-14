package com.NarraBossWebsite.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import com.NarraBossWebsite.ItemFiles.Account;
import com.NarraBossWebsite.ItemFiles.Beneficiary;
import com.NarraBossWebsite.loginservlet;
import com.NarraBossWebsite.DAOfiles.AccountDao;
import com.NarraBossWebsite.DAOfiles.BeneficiaryDao;

@SuppressWarnings("serial")
public class AddBeneficiary extends HttpServlet {

	public static String s1 = "";
	int flag;
	int ben = 0;

	@SuppressWarnings("unused")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		flag = 0;
		HttpSession session;
		session = req.getSession(true);
		if (loginservlet.login == 1) {

			String beneficiaryAccountNo = req.getParameter("beneficiaryAccountNo");
			s1 = checkBeneficiaryAccountNo(beneficiaryAccountNo, loginservlet.customerId);
			String beneficiaryName = req.getParameter("beneficiaryName");
			String beneficiaryBankCode = req.getParameter("beneficiaryBankCode");
			beneficiaryBankCode.trim();

			 checkBankCode(beneficiaryAccountNo, beneficiaryBankCode);

			List<Beneficiary> beneficiaries = BeneficiaryDao.INSTANCE.listBeneficiaries();
			for (Beneficiary b : beneficiaries) {
				if ((b.getCustomerId()).equals(loginservlet.customerId))
					if (b.getBeneficiaryAccountNo().equals(beneficiaryAccountNo)) {
						s1 = "Beneficiary already exists";
						flag = 1;
					}
			}

			resp.getWriter().println(
					"<html><body style=\"border-style:solid;" + "border-width: 55px;" + "border-color:#FFFFFF;\">");
			if (flag == 0 && ben != 1) {
				BeneficiaryDao.INSTANCE.add(loginservlet.customerId, beneficiaryAccountNo, beneficiaryName,
						beneficiaryBankCode);
				resp.getWriter().println("<h3 style=\"color:#1975D1\">" + "Beneficiary details are added</h3>");
				resp.getWriter().println("<h3 style=\"color:#1975D1\">" + "<a href=\"AddBeneficiary.jsp\"back</h3>");
			}

			else if(ben==1){
				resp.getWriter().println(" " + "acc not from this bank");
			resp.getWriter().println("</body></html>");
			resp.getWriter().println("<h3 style=\"color:#1975D1\">" + "<a href=\"AddBeneficiary.jsp\"back</h3>");
		} 
		
		else 
		{
			resp.getWriter().println(" " + s1);
			resp.getWriter().println("</body></html>");
			resp.getWriter().println("<h3 style=\"color:#1975D1\">" + "<a href=\"AddBeneficiary.jsp\"back</h3>");
			
		}
			
		}else
			
			resp.sendRedirect("/Login.jsp");
	}

	/*
	 * private String checkbankcode(String code, String s) { System.out.println(
	 * "check bank code"); if (code.equals("10259271")) {
	 * 
	 * System.out.println("after check bank code"); List<Account> a =
	 * AccountDao.INSTANCE.listAccounts(); for (Account acc : a) { if
	 * ((acc.getAccountNo()).equals(s)) { return "";
	 * 
	 * } else { ben = 1; return "Corresponding account not exist in bank "; }
	 * 
	 * }
	 * 
	 * } return "";
	 * 
	 * }
	 */

	private void checkBankCode(String s, String s2) {
		if (s2.equals("10259271")) {
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
					flag = 1;
					return "Corresponding account belongs to same customer";
				}
			}
		}
		return "";
	}
}
