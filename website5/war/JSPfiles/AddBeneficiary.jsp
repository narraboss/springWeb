<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="com.NarraBossWebsite.admin_LoginServlet"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<title>beneficiary</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css" />



</head>
<body topmargin="3" marginheight="3" onload="StartTimers();"
	onmousemove="ResetTimers();">


	<center>
		<h1>
			<b>Narraboss</b>
		</h1>
		<br> <br>
		<%
			String urlLinktext = "Login";
			if (admin_LoginServlet.admin_login == 0) {
		%>
		<form method="get" action="/admin_Login">
			<table>
				<tr>
					<td><label>Username:</label></td>
					<td><input type="text" name="username" required /></td>
				</tr>
				<tr>
					<td><label>Password:</label></td>
					<td><input type="password" name="password" required /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Login" /></td>
				</tr>
			</table>
		</form>
		<%
			} else {
		%>

		<h2>Add Beneficiary</h2>
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
	<a href="/BeneficiaryViewXml">View XML</a> &nbsp;&nbsp;
	<a href="Menu.jsp">Menu</a>
	<br>
	<form action="/newBeneficiary" method="get" accept-charset="utf-8">
		<table>
			<tr>
				<td><label>Customer id</label></td>
				<td><input type="text" name="customerId" id="customerId"
					size="8" required /></td>
			</tr>

			<tr>
				<td><label>Beneficiary Account number</label></td>
				<td><input type="text" name="beneficiaryAccountNo"
					id="beneficiaryAccountNo" size="12"required /></td>
			</tr>


			<tr>
				<td><label>Beneficiary name</label></td>
				<td><input type="text" name="beneficiaryName"
					id="beneficiaryName" size="65" required /></td>
			</tr>
			<tr>
				<td><label>Beneficiary bank code</label></td>
				<td><input type="text" name="beneficiaryBankCode"
					id="beneficiaryBankCode" size="8" required /></td>
			</tr>


			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Create new Beneficiary" /></td>

			</tr>
		</table>
	</form>
	<%
		}
	%>

</body>
</html>
