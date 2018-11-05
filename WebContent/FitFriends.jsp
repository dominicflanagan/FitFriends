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
<title>FitFriends</title>
</head>
<body>
	<div class ="container theme-showcase" role="main">
	<form action="fitfriends" method="post">
	<div class="jumbotron">
		<h1>Welcome to FitFriends!</h1>
	</div>
	<br/>
		<div id="toprunnershome"><a href="toprunnershome">Top Runners</a></div>
	<br/>
	<br/>
		<div id="topliftershome"><a href="topliftershome">Top Lifters</a></div>
	<br/>
	<br/>
		<div id="personsHome"><a href="personshome">Persons</a></div>
	<br/>
	<br/>
		<div id="usersHome"><a href="usershome">Users</a></div>
	<br/>
	<br/>
		<div id="nutritionhome"><a href="nutritionhome">Nutrition</a></div>
	<br/>
	<br/>
		<div id="nutritionplanhome"><a href="nutritionplanhome">User Nutrition Plan</a></div>
	<br/>
	<br/>
		<div id="runprogresshome"><a href="runprogresshome">Run Progress</a></div>
	<br/>
	<br/>
		<div id="liftprogresshome"><a href="liftprogresshome">Lift Progress</a></div>
	<br/>
<!-- we can decide later what data is loaded on the main landing page -->
<!-- 
	<p>
		<label for="username">UserName</label>
		<input id="username" name="username" value="${fn:escapeXml(param.username)}">
	</p>
	<p>
		<input type="submit" class="btn btn-lg btn-primary">
		<br/><br/><br/>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	</form>
	<h1>Matching Users</h1>
        <table class="table table-striped">
            <tr>
                <th>UserName</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Reviews</th>
                <th>Recommendations</th>
                <th>Reservations</th>
                <th>Delete User</th>
            </tr>
            <c:forEach items="${users}" var="user" >
                <tr>
                    <td><c:out value="${user.getUserName()}" /></td>
                    <td><c:out value="${user.getFirstName()}" /></td>
                    <td><c:out value="${user.getLastName()}" /></td>
                    <td><c:out value="${user.getEmail()}" /></td>
                    <td><c:out value="${user.getPhone()}" /></td>
                    <td><a href="userreviews?username=<c:out value="${user.getUserName()}"/>">Reviews</a></td>
                    <td><a href="blogcomments?username=<c:out value="${user.getUserName()}"/>">Recommendations</a></td>
                    <td><a href="blogcomments?username=<c:out value="${user.getUserName()}"/>">Reservations</a></td>
                    <td><a href="userdelete?username=<c:out value="${user.getUserName()}"/>">Delete</a></td>
                 </tr>
            </c:forEach>
       </table>
       </div>
-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>
