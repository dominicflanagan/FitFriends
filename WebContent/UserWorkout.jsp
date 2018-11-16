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
<title>User Workouts</title>
</head>
<body>
	<div class ="container theme-showcase" role="main">
	<form action="userworkout" method="post">
	<div class="jumbotron">
		<a href="usershome"><img src="FitFriendsHome.jpg" alt="FitFriends Home" width="175" height="50"></a>
		<font color="blue"><h1>User Workouts</h1></font>
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
	<div id="userworkoutcreate"><a href="userworkoutcreate">Add a new user workout</a></div>
	<br/>
	<h1>User Workouts</h1>
        <table class="table table-striped">
            <tr>
                <th>WorkoutId</th>
                <th>WorkoutDescription</th>
                <th>Intensity</th>
                <th>Exercises</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${userworkouts}" var="workout" >
   		    	<tr>
                    <td><c:out value="${workout.getWorkoutId()}" /></td>
                    <td><c:out value="${workout.getWorkoutDescription()}" /></td>
                    <td><c:out value="${workout.getIntensity()}" /></td>
                    <td><a href="workoutexercises?workoutid=<c:out value="${workout.getWorkoutId()}"/>">Exercises</a></td>
                    <td><a href="userworkoutdelete?workoutid=<c:out value="${workout.getWorkoutId()}"/>">Delete</a></td>
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