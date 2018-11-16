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
<title>User MeetUps</title>
</head>
<body>
	<div class ="container theme-showcase" role="main">
	<form action="usermeetup" method="post">
	<div class="jumbotron">
		<a href="usershome"><img src="FitFriendsHome.jpg" alt="FitFriends Home" width="175" height="50"></a>
		<font color="blue"><h1>User MeetUps</h1></font>
		</div>
		<p>
			<label for="username"> Username</label>
			<input id="username" name="username" value="${fn:escapeXml(param.intensity)}">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="usermeetupcreate"><a href="usermeetupcreate">Add a new User MeetUp</a></div>
	<br/>
	<h1>User MeetUps</h1>
        <table class="table table-striped">
            <tr>
            	<th>Leader</th>
                <th>MeetUp Name</th>
                <th>MeetUp Time</th>
                <th>Edit MeetUp Time</th>
                <th>Join the MeetUp</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${usermeetups}" var="userMeetup" >
   		    	<tr>
                    <td><c:out value="${userMeetup.getUserName()}" /></td>
                    <td><c:out value="${userMeetup.getMeetUpName()}" /></td>
                    <td><c:out value="${userMeetup.getMeetUpTime()}" /></td>
                    <td><a href="usermeetupupdatetime?meetupid=<c:out value="${userMeetup.getMeetUpId().toString()}"/>&memberid=<c:out value="${userMeetup.getMemberId().toString()}"/>&meetuptime=<c:out value="${userMeetup.getMeetUpTime().toString()}"/>">Update Time</a></td>
                    <td><a href="usermeetupjoin?meetupid=<c:out value="${userMeetup.getMeetUpId().toString()}"/>&memberid=<c:out value="${userMeetup.getMemberId().toString()}"/>&meetuptime=<c:out value="${userMeetup.getMeetUpTime().toString()}"/>">Join</a></td>
                     <td><a href="usermeetupdelete?meetupid=<c:out value="${userMeetup.getMeetUpId().toString()}"/>&memberid=<c:out value="${userMeetup.getMemberId().toString()}"/>&meetuptime=<c:out value="${userMeetup.getMeetUpTime().toString()}"/>">Delete</a></td>
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