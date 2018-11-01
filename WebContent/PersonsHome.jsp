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
<title>Persons</title>
</head>
<body>
	<div class ="container theme-showcase" role="main">
	<form action="personshome" method="post">
	<div class="jumbotron">
		<h1>Person Home Page</h1>
		</div>
		<p>
			<label for="lastname">Person Last Name</label>
			<input id="lastname" name="lastname" value="${fn:escapeXml(param.lastname)}">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="personcreate"><a href="personcreate">Create a New Person</a></div>
	<br/>
	<h1>Matching Persons</h1>
        <table class="table table-striped">
            <tr>
                <th>MemberId</th>
                <th>UserName</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Password</th>
                <th>Delete Person</th>
            </tr>
            <c:forEach items="${persons}" var="person" >
                <tr>
                    <td><c:out value="${person.getMemberId()}" /></td>
                    <td><c:out value="${person.getUserName()}" /></td>
                    <td><c:out value="${person.getFirstName()}" /></td>
                    <td><c:out value="${person.getLastName()}" /></td>
                    <td><c:out value="${person.getEmail()}" /></td>
                    <td><c:out value="${person.getPsw()}" /></td>
                    <td><a href="persondelete?username=<c:out value="${person.getUserName()}"/>">Delete</a></td>
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
