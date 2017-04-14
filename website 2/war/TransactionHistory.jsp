<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.NarraBossWebsite.loginservlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>

<%@ page import="com.NarraBossWebsite.ItemFiles.Account"%>

<%@ page import="com.NarraBossWebsite.Servlets.TransactionHistory"%>

<%@ page import="com.NarraBossWebsite.DAOfiles.AccountDao"%>
<%@ page import="com.NarraBossWebsite.DAOfiles.TransactionDao"%>
<%@ page import="com.NarraBossWebsite.ItemFiles.Account"%>
<%@ page import="com.NarraBossWebsite.ItemFiles.Transaction"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>


<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />

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

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	function myFunction() {

		document.getElementById("tab").style.display = "block";

	}
</script>
<script>
	$(function() {
		$("#datepicker").datepicker();
		$("#datepicker1").datepicker();
	});
</script>



</head>
<body>
	<%
		if (loginservlet.login == 0) {
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


	<div class="content well">
		<h2 class="title">Transaction History</h2>

		<div class="entry">

			<h3 class="title">To have a transaction history select start and
				end dates and click on the account number</h3>
			<br>
			<form name="form1" action="/transactionHistory" method="get">
				<table>
					<tr>
						<td><b></>Select Account</b> ..</td>
						<td><select name="accno">

								<%
									List<Account> a = AccountDao.INSTANCE.listAccounts();
									for (Account acc : a) {
										if ((acc.getCustomerId()).equals(loginservlet.customerId.toString())) {
								%>
								<option value="<%=acc.getAccountNo()%>"><%=acc.getAccountNo()%></option>

								<%
									}
									}
								%>

						</select></td>
					</tr>
					<tr>
						<td>.</td>
					</tr>


					<tr>
						<td>To date :</td>
						<td>
							<p>
								<input type="text" id="datepicker" name="startDate" required>
							</p>
						</td>
					</tr>

					<td>From date :</td>
					<td>
						<p>
							<input type="text" id="datepicker1" name="endDate" required>
						</p>
					</td>
					</tr>
					<tr>
						<td></td>
						<td><button onclick="myFunction()" type="submit"
								value="submit">Submit</button></td>
					</tr>
				</table>
			</form>
		</div>
		<!-- end #content -->



		<div id="tab"
			style="border-style: solid; border-width: 55px; border-color: #FFFFFF; Display: none;">
			<h3 style="color: #1975D1" class="text-center">
				 Statement for account number:
				<%=TransactionHistory.accountNo%>
			</h3>


			<div style="border-styl border-width: 55px; border-color: #FFFFFF;">

				<div class="table-responsive">

					<table class="table" style="border-spacing: 4px;">



						<thead>
							<tr>
								<th>#</th>
								<th><label>Date</label></th>
								<th><label>Description</label></th>
								<th><label>To/From Account</label></th>
								<th><label>Amount</label></th>

								


							</tr>
						</thead>
						<tbody>

							<%
								List<Transaction> date = TransactionHistory.trans;
								for (Transaction t : date) {

									if (t.getToAccount().equals(TransactionHistory.accountNo)) {
							%>
							<tr>
								<td>.</td>

								<td><%=t.getDateOfTransaction()%></td>
								<td><%=t.getDescription()%></td>
								<td><%=t.getToAccount()%></td>
								<td><%=t.getAmount()%></td>
								
							</tr>

							<%
								} else if (t.getAccountNo().equals(TransactionHistory.accountNo)) {
							%>
							<tr>
								<td>.</td>

								<td><%=t.getDateOfTransaction()%></td>
								<td><%=t.getDescription() %></td>
								<td><%="Sent to " + t.getToAccount() %></td>
								<td><%=t.getAmount()%></td>
								
								<%	}%>


							</tr>

							<%
								}
								
								date.clear();
								
							%>

						</tbody>

					</table>

				</div>
			</div>
		</div>


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



