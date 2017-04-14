<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ page import="com.NarraBossWebsite.admin_LoginServlet"%>
<%@ page import="com.NarraBossWebsite.DAOfiles.AccountDao"%>
<%@ page import="com.NarraBossWebsite.DAOfiles.CustomerDao"%>
<%@ page import="com.NarraBossWebsite.ItemFiles.Customer"%>
<%@ page import="com.NarraBossWebsite.ItemFiles.Account"%>
<%@ page import="com.NarraBossWebsite.DAOfiles.TransactionDao"%>
<%@ page import="com.NarraBossWebsite.ItemFiles.Account"%>
<%@ page import="com.NarraBossWebsite.ItemFiles.Transaction"%>
<%@ page import="com.NarraBossWebsite.Servlets.MiniStatement"%>


<%@ page import="com.NarraBossWebsite.Servlets.TransactionHistory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>NarraBoss Acc</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css" />

<style type="text/css">
#myInput {
	background-image: url('/css/searchicon.png');
	/* Add a search icon to input */
	background-position: 10px 12px; /* Position the search icon */
	background-repeat: no-repeat; /* Do not repeat the icon image */
	width: 100%; /* Full-width */
	font-size: 16px; /* Increase font-size */
	padding: 12px 20px 12px 40px; /* Add some padding */
	border: 1px solid #ddd; /* Add a grey border */
	margin-bottom: 12px; /* Add some space below the input */
}

#myTable {
	border-collapse: collapse; /* Collapse borders */
	width: 100%; /* Full-width */
	border: 1px solid #ddd; /* Add a grey border */
	font-size: 18px; /* Increase font-size */
}

#myTable th, #myTable td {
	text-align: left; /* Left-align text */
	padding: 12px; /* Add padding */
}

#myTable tr {
	/* Add a bottom border to all table rows */
	border-bottom: 1px solid #ddd;
}

#myTable tr.header, #myTable tr:hover {
	/* Add a grey background color to the table header and on hover */
	background-color: #f1f1f1;
}
</style>
<script>
	function myFunction() {
		var input, filter, table, tr, td, i;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		table = document.getElementById("myTable");
		tr = table.getElementsByTagName("tr");
		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[0];
			if (td) {
				if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
					tr[i].style.display = "";
				} else {
					tr[i].style.display = "none";
				}
			}
		}
	}
</script>


</head>

<body topmargin="3" marginheight="3" onload="StartTimers();"
	onmousemove="ResetTimers();">


	<center>
		<br> <br>
		<h1>
			<b>Narraboss</b>
		</h1>

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
		<p>
			<a href="Menu.jsp">Menu</a>
		</p>
		<form method="get" action="/admin_Logout">
			<table>
				<tr>
					<td><input type="submit" value="Logout" /></td>
				</tr>
			</table>
		</form>
		<br> <input type="text" id="myInput" onkeyup="myFunction()"
			placeholder="Search for Customer name">

		<table id="myTable">

			<thead>
				<tr>

					<td>Customer Id</td>
					<td>accountNo</td>
					<td>dateOfTransaction</td>
					<td>amount</td>
					<td>toAccount</td>
					<td>balance</td>
					<td>toAccBalance</td>
					<td>description</td>

				</tr>
			</thead>
			<tbody>
				<%
					List<Transaction> transactions = TransactionDao.INSTANCE.listTransactions();
						for (Transaction a : transactions) {
				%>

				<tr>

					<td><%=a.getCustomerId()%></td>
					<td><%=a.getAccountNo()%></td>
					<td><%=a.getDateOfTransaction()%></td>
					<td><%=a.getAmount()%></td>
					<td><%=a.getToAccount()%></td>
					<td><%=a.getBalance()%></td>
					<td><%=a.getToAccBalance()%></td>
					<td><%=a.getDescription()%></td>





				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<%
			}
		%>

	</center>
</body>
</html>