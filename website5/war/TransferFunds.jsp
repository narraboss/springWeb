<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import=" java.util.List"%>


<%@ page import="com.NarraBossWebsite.loginservlet"%>
<%@ page import="com.NarraBossWebsite.DAOfiles.AccountDao"%>
<%@ page import="com.NarraBossWebsite.DAOfiles.TransactionDao"%>
<%@ page import="com.NarraBossWebsite.ItemFiles.Account"%>
<%@ page import="com.NarraBossWebsite.ItemFiles.Transaction"%>
<%@ page import="com.NarraBossWebsite.Servlets.MiniStatement"%>

<%@ page import="com.NarraBossWebsite.DAOfiles.BeneficiaryDao"%>
<%@ page import="java.util.Properties"%>



<%@ page import="com.NarraBossWebsite.ItemFiles.Beneficiary"%>
<%@ page import="com.NarraBossWebsite.ItemFiles.Customer"%>
<%@ page import="com.NarraBossWebsite.DAOfiles.CustomerDao"%>

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
	<%
		if (session.getAttribute("login").toString() == "0" || session.getAttribute("login") == ""
				|| session.getAttribute("login") == null) {
			response.sendRedirect("Login.jsp");
		}
	%>

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

	<div class="well ">
		<h2 class="title">Fund Transfer</h2>
		<div class="entry">

			<h3 class="title">To transfer money from your account fill the
				details carefully and click on submit</h3>
			<form name="form1" action="/checktransfer" method="get">

				<table>
					<tr>
						<td class="status" colspan="2"><label id="error" name="error"></label></td>
					</tr>
					<tr>
						<td><b></>From Account</b> ..</td>
						<td><select name="fromaccount" required>

								<%
									List<Account> a = AccountDao.INSTANCE.listAccounts();
									for (Account acc : a) {
										if ((acc.getCustomerId()).equals((String) session.getAttribute("customerId"))) {
								%>
								<option value="<%=acc.getAccountNo()%>"><%=acc.getAccountNo() + "---" + acc.getBalance()%></option>

								<%
									}
									}
								%>

						</select></td>
					</tr>
					<tr>
						<td><b></>To Account</b> ..</td>
						<td><select name="toaccount" required>

								<%
									List<Beneficiary> a1 = BeneficiaryDao.INSTANCE.listBeneficiaries();
									if (!a1.isEmpty()) {
										for (Beneficiary acc : a1) {
											if ((acc.getCustomerId()).equals(session.getAttribute("customerId").toString())) {
								%>
								<option value="<%=acc.getBeneficiaryAccountNo()%>"><%=acc.getBeneficiaryName() + "--" + acc.getBeneficiaryAccountNo()%></option>

								<%
									}
										}
									} else {
								%><option value="">No beneficiaries please add</option>
								<%
									}
								%>

						</select></td>

						<td><a href="AddBeneficiary.jsp">ADD beneficiary</a></td>
					</tr>
					<tr>
						<td><label>Amount</label></td>
						<td><input type="text" name="amount" id="amount" size="12"
							onFocus="makeVisible();" required /></td>
					</tr>
					<tr>
						<td colspan="2"><input id="transfer" name="transfer"
							type="submit" value="Transfer" /></td>
					</tr>
				</table>
			</form>


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
