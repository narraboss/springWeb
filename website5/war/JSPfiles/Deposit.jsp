<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="com.NarraBossWebsite.admin_LoginServlet"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<title>Accounts</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css" />
</head>
<body topmargin="3" marginheight="3" onload="StartTimers();"
	onmousemove="ResetTimers();">



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
		<h2>Deposit</h2>
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

	<a href="Menu.jsp">Menu</a>
	<br>
	<form action="/Deposit" method="get" enctype="multipart/form-data">
		<table>
			<tr>
				<td><label>Account Number</label></td>
				<td><input size="35" type="text" name="accountNo"
					id="accountNo" required></td>
			</tr>

			<tr>
				<td><label>Amount</label></td>
				<td><div>
						<input size="35" type="text" name="balance" id="balance" required>
					</div></td>
			</tr>

			<tr>
				<td colspan=100%><input type="submit" value="SUBMIT" /></td>
			</tr>

		</table>
	</form>

	<%
		}
	%>

</body>
</html>
