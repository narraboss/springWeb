<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<style>
@import url(http://fonts.googleapis.com/css?family=Roboto);

/****** LOGIN MODAL ******/
.loginmodal-container {
	padding: 30px;
	max-width: 350px;
	width: 100% !important;
	background-color: #F7F7F7;
	margin: 0 auto;
	border-radius: 2px;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	overflow: hidden;
	font-family: roboto;
}

.loginmodal-container h1 {
	text-align: center;
	font-size: 1.8em;
	font-family: roboto;
}

.loginmodal-container input[type=submit] {
	width: 100%;
	display: block;
	margin-bottom: 10px;
	position: relative;
}

.loginmodal-container input[type=text], input[type=password] {
	height: 44px;
	font-size: 16px;
	width: 100%;
	margin-bottom: 10px;
	-webkit-appearance: none;
	background: #fff;
	border: 1px solid #d9d9d9;
	border-top: 1px solid #c0c0c0;
	/* border-radius: 2px; */
	padding: 0 8px;
	box-sizing: border-box;
	-moz-box-sizing: border-box;
}

.loginmodal-container input[type=text]:hover, input[type=password]:hover
	{
	border: 1px solid #b9b9b9;
	border-top: 1px solid #a0a0a0;
	-moz-box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1);
	-webkit-box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1);
	box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1);
}

.loginmodal {
	text-align: center;
	font-size: 14px;
	font-family: 'Arial', sans-serif;
	font-weight: 700;
	height: 36px;
	padding: 0 8px;
	/* border-radius: 3px; */
	/* -webkit-user-select: none;
        user-select: none; */
}

.loginmodal-submit {
	/* border: 1px solid #3079ed; */
	border: 0px;
	color: #fff;
	text-shadow: 0 1px rgba(0, 0, 0, 0.1);
	background-color: #4d90fe;
	padding: 17px 0px;
	font-family: roboto;
	font-size: 14px;
	/* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#4787ed)); */
}

.loginmodal-submit:hover {
	/* border: 1px solid #2f5bb7; */
	border: 0px;
	text-shadow: 0 1px rgba(0, 0, 0, 0.3);
	background-color: #357ae8;
	/* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#357ae8)); */
}

.loginmodal-container a {
	text-decoration: none;
	color: #666;
	font-weight: 400;
	text-align: center;
	display: inline-block;
	opacity: 0.6;
	transition: opacity ease 0.5s;
}

.login-help {
	font-size: 12px;
}
</style>

</head>
<body>
	<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-dialog">
			<div class="loginmodal-container">
				<h1>Login to Your Account</h1>
				<br>
				<form name="login" method="get" action="/login">
<table>

<tr>
<td><label>CustomerID</label></td>
<td><input type="text" name="customerId" id="customerId" size="10" required/></td>
</tr>

<tr>
<td><label>Password</label></td>
<td><input type="password" name="password" id="password" size="10" required/></td>
</tr>

<tr>
<td colspan="2"><input type="submit" value="Submit"/></td>		
</tr>

</table>
</form>

			</div>
		</div>
	</div>

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
				<li ><a href="home.jsp" role="button" aria-haspopup="true" aria-expanded="false">HOME </a></li>
				<li><a href="aboutus.jsp" aria-haspopup="true" aria-expanded="false" >AboutUs </a></li>
				<li><a href="services.jsp">Services</a></li>
				<li><a href="contactus.jsp" aria-haspopup="true" aria-expanded="false" >Contact Us </a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li class="position-right btn btn-block btn-info"><a href="#" data-toggle="modal" data-target="#login-modal">Login</a></li>

				</ul>

			<ul class="nav navbar-nav navbar-right">
				<li class="position-right btn btn-block btn-info"><a href="#" data-toggle="modal" data-target="#login-modal">Login</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<div class="well">

		<h3 class="title">"Our goal is to create creditability and be the
			most trustworthy bank in serving people"</h3>
		<br>

		<div class="entry">

			<p>Our's is a leading bank with total assests $1Billon.Our bank
				has a network of 100 branches in MO and 200 ATM's. Our Bank stands
				second to none in extending wide range of financial services and
				treats customers with lot of care and concern.We blend tradition
				with technology to deliver innovative products and services.</p>

			<p>Our bank is well equipped to meet the challenges of 21st
				century in the area of Information Technology and competition.Our
				bank's personal are being upgraded through a very variety of
				training programs to promote customer delight in every sphere.</p>

			<h3 class="title">Technological Accomplishments:</h3>

			<p>Our bank has achieved full networking of its branches and
				offers services through multiple delivery channels.Some of the
				accomplishments of our bank are:-</p>
			<ul>
				<li>Internet Banking</li>
				<li>Mobile Banking</li>
				<li>24*7 toll free professional help desk services for all
					ATMs, Internet Banking and Mobile Banking</li>
			</ul>

			<h3 class="title">Noteworthy Achievements:-</h3>
			<p>Our bank has been earning profits since its inception.</p>

		</div>

		<!-- end #content -->


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

