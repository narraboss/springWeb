<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.NarraBossWebsite.admin_LoginServlet"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<title>Transactions</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css" />



</head>


<body class="container-fluid" topmargin="3" marginheight="3"
	onload="StartTimers();" onmousemove="ResetTimers();">

	

	<center>
		<h1>
			<b>Narraboss</b>
		</h1>
		<br>
		<%
			String urlLinktext = "Login";
			if (admin_LoginServlet.admin_login == 0) {
		%>

		<form method="get" action="/admin_Login">
			<table>
				<tr>
					<td><label>Username:</label></td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td><label>Password:</label></td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Login" /></td>
				</tr>
			</table>
		</form>
		<%
			} else {
		%>

		<br>
		<h2>New Transaction</h2>
	</center>
	<hr />
	<form method="get" action="/admin_Logout">
		<table>
			<tr>
				<td><input type="submit" value="Logout" /></td>
			</tr>
		</table>
	</form>
	<br>
	<a href="/TransactionViewXml">View XML</a> &nbsp;&nbsp;
	<a href="Menu.jsp">Menu</a>
	<br>
	<form action="/newTransaction" method="get" accept-charset="utf-8">
		<table>

			<tr>
				<td><label>Customer id</label></td>
				<td><input type="text" name="customerId" id="customerId"
					size="8" required /></td>
			</tr>

			<tr>
				<td><label>Account Number</label></td>
				<td><input type="text" name="accountNo" id="accountNo"
					size="12" required /></td>
			</tr>

			<tr>
				<td><label>To account</label></td>
				<td><input type="text" name="toAccount" id="toAccount"
					size="12"  required/></td>
			</tr>
			<tr>
				<td><label>Amount</label></td>
				<td><input type="text" name="amount" id="amount" size="15" required /></td>
			</tr>
			<tr>
				<td><label>Balance</label></td>
				<td><input type="text" name="balance" id="balance" size="15"  required/></td>
			</tr>
			<tr>
				<td><label>Description</label></td>
				<td><input type="text" name="description" id="description"
					size="45" required/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Create new transaction" /></td>
			</tr>
		</table>
	</form>
	<%
		}
	%>

</body>
</html>
