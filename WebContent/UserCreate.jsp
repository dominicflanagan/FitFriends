<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Create a User</title>
</head>
<body>
	<div class ="container theme-showcase" role="main">
			<form action="usercreate" method="post">
	<div class="jumbotron">
		<h1>${messages.title}</h1>
	</div>
	<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
		<p>
			<label style="width:95px" for="username">UserName</label>
			<input id="username" name="username" value="">
		</p>
		<p>
			<label style="width:95px" for="psw">Password</label>
			<input id="psw" name="psw" value="">
		</p>
		<p>
			<label style="width:95px" for="firstname">FirstName</label>
			<input id="firstname" name="firstname" value="">
		</p>
		<p>
			<label style="width:95px" for="lastname">LastName</label>
			<input id="lastname" name="lastname" value="">
		</p>
		<p>
			<label style="width:95px" for="email">Email</label>
			<input id="email" name="email" value="">
		</p>
		<p>
			<label style="width:95px" for="dob">Date of Birth</label>
			<input id="dob" name="dob" value="">
		</p>
		<p>
			<label for="fitnesslevel">Please select your fitness level</label>
			</p>
			<div>
    		<input type="radio" id="fitnesslevel1" name="fitnesslevel" value="Beginner">
    		<label for="fitnesslevel1">Beginner</label>
   			<input type="radio" id="fitnesslevel2" name="fitnesslevel" value="Intermediate">
    		<label for="fitnesslevel1">Intermediate</label>
        	<input type="radio" id="fitnesslevel3" name="fitnesslevel" value="Advanced">
    		<label for="fitnesslevel1">Advanced</label>
			</div>
  		<p></p>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit" class="btn btn-lg btn-primary">
			</span>
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	</div>
	     <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>