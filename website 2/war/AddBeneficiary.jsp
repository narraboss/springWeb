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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
      function toggle(){
        var bn = document.getElementById("beneficiaryName").value;
        var bc = document.getElementById("beneficiaryBankCode").value;
        var ba = document.getElementById("beneficiaryAccountNo").value;
        var regexp=/\d{12}/;
        var regex=/\d{8}/;
        var patt=/^[A-Za-z ]+/;
        if(bn.match(patt)==null)
          document.getElementById("error").innerHTML="Please enter valid name";
        else if(bc.match(regex)==null)
          document.getElementById("error").innerHTML="Please enter valid bank code";
        else if(ba.match(regexp)==null)
          document.getElementById("error").innerHTML="Please enter valid Account number";
        else {document.getElementById("error").innerHTML="";
              document.getElementById("check").style.visibility="hidden";
              document.getElementById("add").style.visibility="visible";}
      }
      function makeVisible(){
        document.getElementById("error").innerHTML="";
        document.getElementById("check").style.visibility="visible";
        document.getElementById("add").style.visibility="hidden";
      }
    </script>
</head>
<body>

	<% if(loginservlet.login==0){
       response.sendRedirect("Login.jsp");} %>

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


	<div class="well content">

		<div class="post">
			<h2 class="title">Add New Beneficiary account</h2>

			<div class="entry">

				<h3 class="title">To add a new beneficiary to your account fill
					the details and click on submit</h3>

				<form name="form1" action="/addBeneficiary" method="get">

					<table>
						<tr>
							<td class="status" colspan="2"><label name="error"
								id="error"></label></td>
						</tr>
						<tr>
							<td><label>Beneficiary Name</label></td>
							<td><input type="text" name="beneficiaryName"
								id="beneficiaryName" size="20" onFocus="makeVisible();" required/ ></td>
							<b></b>
						</tr>
						<tr>
							<td>.</td>
						</tr>
						<tr>
							<td><label>Bank Code</label></td>
							<td><input   type="text" name="beneficiaryBankCode"
								id="beneficiaryBankCode" size="20" onFocus="makeVisible();" required /></td>
						</tr>
						<tr>
							<td>.</td>
						</tr>
						<tr>
							<td><label>Beneficairy Account No</label></td>
							<td><input type="text" name="beneficiaryAccountNo"
								id="beneficiaryAccountNo" size="20" onFocus="makeVisible();"  required/></td>
						</tr>
						<tr>
							<td>.</td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="button"
								value="Submit" name="check" id="check" onclick="toggle();" /> <input
								type="submit" value="Add" name="add" id="add"
								style="visibility: hidden;"  /></td>
						</tr>
					</table>
				</form>
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
