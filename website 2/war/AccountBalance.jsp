<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@ page import="com.NarraBossWebsite.loginservlet"%>

<%@ page import="com.NarraBossWebsite.ItemFiles.Account"%>

<%@ page import="com.NarraBossWebsite.DAOfiles.AccountDao"%>

<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>Narraboss</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-default nav-main">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header ">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="afterLogin.jsp">Narraboss</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false" hover="dropdown">Check My Account <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="AccountBalance.jsp">Account Summary</a></li>
						<li><a href="TransactionHistory.jsp">Account Transaction
								history</a></li>

					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false" hover="dropdown">Fund Transfer <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="AddBeneficiary.jsp">Add Beficiary</a></li>
						<li><a href="TransferFunds.jsp">Transfer Funds</a></li>


					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false" hover="dropdown"> manage Account Details<span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="ManageAccount.jsp">Account Details </a></li>



					</ul></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li class="position-right"><form method="get" action="/logout">
						<input type="submit" value="Logout" />
					</form></li>

			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<div class="container-fluid well accb">

		<%
			if (loginservlet.login == 0) {
				response.sendRedirect("Login.jsp");
			}
		%>

		<div id="content">
			<div class="post">


				<div class="table-responsive">
					<div style="border-styl border-width: 55px; border-color: #FFFFFF;">
						<form name="form1" action="/ministatement" method="get">
							<table class="table" style="border-spacing: 4px;">



								<thead>
									<tr>
										<th>#</th>
										<th><label>Account Number</label></th>
										<th><label>Name</label></th>
										<th><label>Balance</label></th>
										<th><label>View MiniStatement</label></th>

									</tr>
								</thead>


								<%
									List<Account> a = AccountDao.INSTANCE.listAccounts();
									for (Account acc : a) {
										if ((acc.getCustomerId()).equals(loginservlet.customerId.toString())) {
								%>
								<tr>
									<th></th>
									<th><label><%=acc.getAccountNo()%></label></th>
									<th><label><%=loginservlet.customerName%></label></th>
									<th><label>$ <%=acc.getBalance()%>
									</label></th>
									<th><label><input name="acc" type="submit"
											value=<%=acc.getAccountNo()%>></label></th>
								</tr>

								<%
									}
									}
								%>

							</table>
						</form>
					</div>
				</div>




			</div>
			<!-- end #content -->

		</div>


		<nav class="navbar navbar-default navbar-fixed-bottom">
		<div class="container">
			<div class="text-center">
				<a target="_blank" href="main.jsp">www.narraboss.com</a>
			</div>
		</div>
		</nav>
</body>
</html>
