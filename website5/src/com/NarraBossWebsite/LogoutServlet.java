package com.NarraBossWebsite;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		HttpSession session = req.getSession(true);
		session.invalidate();
		/*
		 * loginservlet.login = 0; loginservlet.loginAttempt = 1;
		 * loginservlet.cs = ""; loginservlet.ps = ""; loginservlet.customerName
		 * = ""; loginservlet.gender = "Mr."; loginservlet.customerId = "";
		 * loginservlet.accList.clear();
		 */
		resp.sendRedirect("/home.jsp");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doGet(req, resp);
	}
}
