<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.NarraBossWebsite.loginservlet"%>
<%@ page import="com.NarraBossWebsite.SQservlet"%>

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

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>




</head>
<body>

	<div id="logo">
		<h1>NARRABOSS</h1>
		<p>
			<em>Bank for everyone...</em>
		</p>
	</div>
	<!-- end #logo -->

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
			<a class="navbar-brand" href="home.jsp">Narraboss</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="home.jsp" role="button" aria-haspopup="true"
					aria-expanded="false">HOME </a></li>
				<li><a href="aboutus.jsp" aria-haspopup="true"
					aria-expanded="false">AboutUs </a></li>
				<li><a href="services.jsp">Services</a></li>
				<li><a href="contactus.jsp" aria-haspopup="true"
					aria-expanded="false">Contact Us </a></li>
			</ul>




		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>


	<div class="well">
		<ul>
			<h2>Customer Login</h2>
			<li>
				<%
					if (loginservlet.login == 0) {

						if (loginservlet.loginAttempt < 2) {
				%>
				<form name="login" method="get" action="/login">
					<table>

						<tr>
							<td class="status" colspan="2" name="cidstatus" id="cidstatus"><%=loginservlet.cs%></td>
						</tr>

						<tr>
							<td><label>CustomerID</label></td>
							<td><input type="text" name="customerId" id="customerId"
								size="10" required /></td>
						</tr>

						<tr>
							<td class="status" colspan="2" name="pwdstatus" id="pwdstatus"><%=loginservlet.ps%></td>
						</tr>

						<tr>
							<td><label>Password</label></td>
							<td><input type="password" name="password" id="password"
								size="10" required /></td>
						</tr>

						<tr>
							<td colspan="2"><input type="submit" value="Submit" /></td>
						</tr>

					</table>
				</form> <%
 	} else {
 %>
				<form name="login" method="get" action="/SQ">
					<table>

						<tr>
							<td class="status" colspan="2" name="status" id="status"><%=loginservlet.cs%></td>
						</tr>
						<tr>
							<td colspan="2"><label><%=SQservlet.q%></label></td>
						</tr>
						<tr>
							<td><label>Enter Answer</label></td>
							<td><input type="text" name="answer" id="answer" size="10" /></td>
						</tr>

						<tr>
							<td colspan="2"><input type="submit" value="Submit" /></td>
						</tr>

					</table>
				</form> <%
 	}

 	} else {
 %><br> <br>
				<form method="get" action="/logout">
					<table>
						<tr>
							<td><input type="submit" value="Logout" /></td>
						</tr>
					</table>
				</form> <%
 	}
 %>
			</li>



		</ul>

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

