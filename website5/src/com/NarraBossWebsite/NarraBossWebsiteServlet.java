package com.NarraBossWebsite;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.*;

import com.NarraBossWebsite.DAOfiles.AccountDao;
import com.NarraBossWebsite.ItemFiles.Account;

@SuppressWarnings("serial")
public class NarraBossWebsiteServlet extends HttpServlet {
	private List<String> accList;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		
		
		List<Account> accounts = AccountDao.INSTANCE.listAccounts();

		accList = null;
		/*for (Account a : accounts) {
			if ((loginservlet.customerId) == (a.getCustomerId())) {

				accList.add(a.getAccountNo());

			}
		}*/

		Collections.sort(accList);
		
		
		System.out.println(accList);
		resp.getWriter().println("Hell" + accList);

	}
}
