package com.NarraBossWebsite.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.NarraBossWebsite.loginservlet;

@SuppressWarnings("serial")
public class CheckBalance extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		HttpSession session;
		session = req.getSession(true);
		if ((session.getAttribute("login").toString()).equals("1")) {

			resp.sendRedirect("AccountBalance1.jsp");

		} else
			resp.sendRedirect("Login.jsp");
	}

}
