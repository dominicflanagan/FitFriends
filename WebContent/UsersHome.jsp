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
<title>Users</title>
</head>
<body>
	<div class ="container theme-showcase" role="main">
	<form action="usershome" method="post">
	<div class="jumbotron">
		<h1>Welcome to FitFriends!</h1>
		</div>
		<br/><div id="toprunnershome"><a href="toprunnershome">Top Runners</a></div><br/>
		<br><div id="topliftershome"><a href="topliftershome">Top Lifters</a></div><br/>
		<br><div id="userCreate"><a href="usercreate">Create a New User</a></div><br/>
		<br><h1>Find A User</h1><br/>
		<p>
			<label for="lastname">User Last Name</label>
			<input id="lastname" name="lastname" value="${fn:escapeXml(param.lastname)}">
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
                <th style="text-align:right">Date of Birth</th>
                <th>Fitness Level</th>
                <th>Nutrition Plan</th>
                <th>Run Progress</th>
                <th>Lift Progress</th>
                <th>Delete User</th>
            </tr>
            <c:forEach items="${users}" var="user" >
   		    	<tr>
                    <td><c:out value="${user.getUserName()}" /></td>
                    <td><c:out value="${user.getFirstName()}" /></td>
                    <td><c:out value="${user.getLastName()}" /></td>
                    <td><c:out value="${user.getEmail()}" /></td>
                    <td style="text-align:right"><fmt:formatDate value="${user.getDob()}" pattern="MM/dd/YYYY" /></td>
                    <td><c:out value="${user.getFitnessLevel()}" /></td>
                    <td><a href="nutritionplanhome?username=<c:out value="${user.getUserName()}"/>">Nutrition Plan</a></td>
                    <td><a href="runprogresshome?username=<c:out value="${user.getUserName()}"/>">Run Progress</a></td>
                    <td><a href="liftprogresshome?username=<c:out value="${user.getUserName()}"/>">Lift Progress</a></td>
                    <td><a href="userdelete?username=<c:out value="${user.getUserName()}"/>">Delete</a></td>
                 </tr>
            </c:forEach>
       </table>
       </div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>
