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
<title>MeetUps</title>
</head>
<body>
	<div class ="container theme-showcase" role="main">
	<form action="meetup" method="post">
	<div class="jumbotron">
		<h1>MeetUps Home Page</h1>
		</div>
		<p>
			<label for="district"> Filter by meet up district</label>
			<input id="district" name="district" value="${fn:escapeXml(param.intensity)}">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<!-- <br/>
	<div id="workoutcreate"><a href="workoutcreate">Create a new workout</a></div>
	<br/> -->
	<h1>MeetUps</h1>
        <table class="table table-striped">
            <tr>
                <th>MeetUpId</th>
                <th>MeetUpName</th>
                <th>District</th>
                <th>Address</th>
                <th>Type</th>
            </tr>
            <c:forEach items="${meetups}" var="meetup" >
   		    	<tr>
                    <td><c:out value="${meetup.getMeetUpId()}" /></td>
                    <td><c:out value="${meetup.getMeetUpName()}" /></td>
                    <td><c:out value="${meetup.getDistrict()}" /></td>
                    <td><c:out value="${meetup.getAddress()}" /></td>
                    <td><c:out value="${meetup.getType()}" /></td>
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