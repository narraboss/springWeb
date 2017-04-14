<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.NarraBossWebsite.loginservlet"%>
<%@ page import="com.NarraBossWebsite.DAOfiles.AccountDao"%>
<%@ page import="com.NarraBossWebsite.DAOfiles.CustomerDao"%>
<%@ page import="com.NarraBossWebsite.ItemFiles.Customer"%>
<%@ page import="com.NarraBossWebsite.ItemFiles.Account"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>
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

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<script>
	function verify() {
		var vis1 = document.getElementById('phoneno').style.visibility;
		var val = document.getElementById('phoneno').value;
		var regexp = /\d{10}/;
		var flag = 0;
		if (vis1 == 'visible' && val.match(regexp) == null) {
			document.getElementById("error").innerHTML = "Please enter valid phone number";
			flag = 1;
		}
		var vis2 = document.getElementById('address').style.visibility;
		val = document.getElementById('address').value;
		if (vis2 == 'visible' && val == "") {
			document.getElementById("error").innerHTML = "Please enter valid address";
			flag = 1;
		}
		var vis3 = document.getElementById('email').style.visibility;
		val = document.getElementById('email').value;
		regexp = /^[a-zA-Z]\w*[\._]*\w*@[a-zA-Z]+\.[a-zA-Z]+/;
		if (vis3 == 'visible' && val.match(regexp) == null) {
			document.getElementById("error").innerHTML = "Please enter valid email address";
			flag = 1;
		}
		if (vis1 == 'hidden' && vis2 == 'hidden' && vis3 == 'hidden') {
			document.getElementById("error").innerHTML = "Please choose one of the options";
			flag = 1;
		}
		if (flag == 0) {
			document.getElementById("error").innerHTML = "";
			document.getElementById("check").style.visibility = "hidden";
			document.getElementById("change").style.visibility = "visible";
		}
	}
	function toggle(value) {
		var vis = document.getElementById(value).style.visibility;
		if (vis == 'hidden')
			document.getElementById(value).style.visibility = 'visible';
		else
			document.getElementById(value).style.visibility = 'hidden';
		document.getElementById("check").style.visibility = "visible";
		document.getElementById("change").style.visibility = "hidden";
	}
</script>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />



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



	<div id="page">
		<div id="page-bgtop">



			<div id="content">
				<div class="post">

					<h2 class="title">Change Contact Details</h2>

					<div class="entry">

						<h3 class="title">To edit your contact information fill the
							details and click on submit</h3>

						<form name="form1" action="/manageAccount" method="get">

							<table class="table-striped" align="center">
								<%!Customer a;%>

								<%
									List<Customer> customers = CustomerDao.INSTANCE.listCustomers();

									for (Customer c : customers) {

										if (c.getCustomerId().equals(loginservlet.customerId)) {
											a = c;
										}
									}
								%>

								<tr>
									<td class="status" colspan="2"><label id="error"
										name="error"></label></td>
								</tr>
								<tr>

									<td class="text-primary">Current Mobile No:--</td>
									<td><%=a.getMobileNo()%></td>
									<td><button type="button" name="cbg"
											onclick="toggle('phoneno'); toggle('phoneno1');">change
											Mobile</button></td>

								</tr>
								<tr>
									<td class="bg-warning" id="phoneno1" style="visibility: hidden;">New Mobile
										no</td>
									<td><input type="text" name="phoneno" id="phoneno"
										size="10" style="visibility: hidden;" /></td>
								</tr>


								<tr>

									<td class="text-primary">Current address :--</td>
									<td><%=a.getCurrentAddress()%></td>
									<td><button type="button" name="cbg"
											onclick="toggle('address');toggle('address1');">change
											Address</button></td>

								</tr>
								<tr>
									<td class="bg-warning" id="address1" style="visibility: hidden;">New
										Address</td>
									<td><textarea id="address" name="address" rows="4"
											cols="25" style="visibility: hidden;"
											value="<%=a.getCurrentAddress()%>"></textarea></td>
								</tr>
								<tr>
									<td class="text-primary" >Current Email Id:--</td>
									<td><%=a.getEmailAddress()%></td>
									<td><button type="button" name="cbg"
											onclick="toggle('email');toggle('email1');">Email id</button></td>

								</tr>
								<tr>

									<td class="bg-warning" id="email1" style="visibility: hidden;">New Mail Id</td>
									<td><input type="text" id="email" name="email" id="email"
										size="20" style="visibility: hidden;" /></td>
								</tr>

								<tr>
									<td colspan="2"><input id="check" name="check"
										type="button" value="Check" onclick="verify();" /> <input
										id="change" name="change" type="submit" value="Submit"
										style="visibility: hidden;" /></td>
								</tr>

							</table>
						</form>
					</div>
				</div>
			</div>
			<!-- end #content -->
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
