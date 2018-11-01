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
		<h1>User Home Page</h1>
		</div>
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
	<br/>
	<div id="userCreate"><a href="usercreate">Create a New User</a></div>
	<br/>
	<h1>Matching Users</h1>
        <table class="table table-striped">
            <tr>
                <th>MemberId</th>
                <th>UserName</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Password</th>
                <th style="text-align:right">Date of Birth</th>
                <th>Fitness Level</th>
                <th>Delete User</th>
            </tr>
            <c:forEach items="${users}" var="user" >
   		    	<tr>
                    <td><c:out value="${user.getMemberId()}" /></td>
                    <td><c:out value="${user.getUserName()}" /></td>
                    <td><c:out value="${user.getFirstName()}" /></td>
                    <td><c:out value="${user.getLastName()}" /></td>
                    <td><c:out value="${user.getEmail()}" /></td>
                    <td><c:out value="${user.getPsw()}" /></td>
                    <td style="text-align:right"><fmt:formatDate value="${user.getDob()}" pattern="MM/dd/YYYY" /></td>
                    <td><c:out value="${user.getFitnessLevel()}" /></td>
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