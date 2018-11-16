<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Person</title>
</head>
<body>
	<a href="usershome"><img src="FitFriendsHome.jpg" alt="FitFriends Home" width="175" height="50"></a>
	<font color="blue"><h1>Create Person</h1></font>
	<form action="personcreate" method="post">
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
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>