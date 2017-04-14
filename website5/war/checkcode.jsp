<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.NarraBossWebsite.loginservlet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Narraboss</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<style type="text/css">
body {
	margin-top: 20%;
}
</style>
</head>
<body>
	<%
		if (session.getAttribute("login").toString() == "0" || session.getAttribute("login") == ""
				|| session.getAttribute("login") == null) {
			response.sendRedirect("Login.jsp");
		}
	%>
	<h1 align="center">Enter the OTP</h1>
	<form align="center" action="/checkcode" method="get">
		<input type="text" name="coddd"> <input type="submit"
			value="submit">

	</form>

</body>
</html>