<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.NarraBossWebsite.admin_LoginServlet"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>New Customer</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css" />


<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker();
		$("#datepicker1").datepicker();
	});
</script>

<style type="text/css">
#timeout {
	display: none;
}
</style>
</head>

<body class="container-fluid" topmargin="3" marginheight="3">

	<div id="timeout">
		<h1>Session About To Timeout</h1>
		<p>
			You will be automatically logged out in 1 minute.<br /> To remain
			logged in move your mouse over this window.
	</div>

	<%
		String urlLinktext = "Login";
		if (admin_LoginServlet.admin_login == 0) {
	%>
	<form method="get" action="/admin_Login">
		<table>
			<tr>
				<td><label>Username:</label></td>
				<td><input type="text" name="username"  required/></td>
			</tr>
			<tr>
				<td><label>Password:</label></td>
				<td><input type="password" name="password"  required/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Login" /></td>
			</tr>
		</table>
	</form>
	<%
		} else {
	%>

	<center>
		<h1>
			<b>Narraboss</b>
		</h1>
		<br> <br>
		<h2>Add Customer</h2>
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
	<a href="/CustomerViewXml">View XML</a> &nbsp;&nbsp;
	<a href="Menu.jsp">Menu</a>
	<br>
	<form action="/newCustomer" method="get" enctype="multipart/form-data"
		class=".form-control">
		<table class=" .table table-striped" Style="padding: 2">
			<tr>
				<td valign="top"><label>Name</label></td>
				<td><input type="text" name="name" id="name" size="65" required/></td>
			</tr>

			<tr>
				<td>date of Birth :</td>
				<td>
					<p>
						<input type="text" id="datepicker" name="DOB" required>
					</p>
				</td>
			</tr>
			<tr>
				<td><label>Customer id</label></td>
				<td><input type="text" name="customerId" id="customerId" placeholder="1234XXXX"
					size="8" required /></td>
			</tr>
			<tr>
				<td><label>Mobile Number</label></td>
				<td><input type="text" name="mobileNo" id="mobileNo" size="12" placeholder="12-digits" required/></td>
			</tr>
			<tr>
				<td><label>Permanent Address</label></td>
				<td><textarea rows=7 name="permanentAddress"
						id="permanentAddress" required></textarea><br> <br></td>
			</tr>
			<tr>
				<td><label>Current Address</label></td>
				<td><textarea rows=7 name="currentAddress" id="currentAddress" required></textarea><br>
					<br></td>
			</tr>
			<tr>
				<td><label>Name of Father</label></td>
				<td><input type="text" name="fathersName" id="fathersName"
					size="65"  required/></td>
			</tr>
			<tr>
				<td><label>Nationality</label></td>
				<td><select name="nationality" id="nationality" size=4>
						<option selected>--Select Nationality--</option>
						<option>India</option>
						<option>Malaysia</option>
						<option>Philippines</option>
						<option>USA</option>
				</select><br></td>
			</tr>
			<tr>
				<td><label>Sex</label></td>
				<td><input type="radio" name="sex" id="sex" value="Male"
					checked /> Male <input type="radio" name="sex" id="sex"
					value="Female" /> Female<br></td>
			</tr>
			<tr>
				<td><label>Occupation</label></td>
				<td><input type="radio" name="occupation" id="occupation"
					value="student" checked />Student <input type="radio"
					name="occupation" id="occupation" value="service" /> Service <input
					type="radio" name="occupation" id="occupation" value="selfemployed" />
					Self Employed <input type="radio" name="occupation" id="occupation"
					value="professional" /> Professional<br> <input type="radio"
					name="occupation" id="occupation" value="business" /> Business <input
					type="radio" name="occupation" id="occupation" value="agriculture" />
					Agriculture <input type="radio" name="occupation" id="occupation"
					value="labor" /> Labor <input type="radio" name="occupation"
					id="occupation" value="other" /> Other <input type="radio"
					name="occupation" id="occupation" value="none" /> None</td>
			</tr>
			<tr>
				<td><label>Annual Income</label></td>
				<td><input type="text" name="annualIncome" id="annualIncome"
					size="10" required/></td>
			</tr>
			<tr>
				<td><label>Email Address</label></td>
				<td><input type="email" name="emailAddress" id="emailAddress"
					size="65" required/></td>
			</tr>
			<tr>
				<td><label>Password</label></td>
				<td><input type="password" name="password" id="password"
					size="25" required/></td>
			</tr>
			<tr>
				<td><label>Re-enter Password</label></td>
				<td><input type="password" name="rePassword" id="rePassword"
					size="25" required /></td>
			</tr>
			<tr>
				<td><label>Select a Question</label></td>
				<td><select id="question" name="question">
						<option value="What is your mother's maiden name?" selected>What
							is your mother's maiden name?</option>
						<option value="What is your pet's name?">What is your
							pet's name?</option>
						<option value="What is your first school'€™s name?">What
							is your first school'€™s name?</option>
						<option value="Who is your favorite teacher?">Who is your
							favorite teacher?</option>
						<option value="What is your street name?">What is your
							street name?</option>
				</select></td>
			</tr>
			<tr>
				<td><label>Answer</label></td>
				<td><input type="text" id="answer" name="answer" size="65" required /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><br> <input type="submit"
					value="Next" /></td>
			</tr>
		</table>
	</form>
	<%
		}
	%>


</body>
</html>