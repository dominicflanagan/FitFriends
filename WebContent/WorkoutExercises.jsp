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
<title>Workouts Exercises</title>
</head>
<body>
	<div class ="container theme-showcase" role="main">
	<form action="workoutexerciseadd" method="get">
	<div class="jumbotron">
		<a href="usershome"><img src="FitFriendsHome.jpg" alt="FitFriends Home" width="175" height="50"></a>
		<font color="blue"><h1>Workout Excercises</h1></font>
		</div>
		<p>
			<label for="exerciseid"> Add the exercise to the workout</label>	
			<input id="exerciseid" name="exerciseid" value="${fn:escapeXml(param.exerciseid)}">
			<input id="workoutid" name="workoutid" value="${workoutid}">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
			<span id="workoutid"><b>${workoutid}</b></span>
		</p> 
	</form>
	<!-- <br/>
	<div id="workoutcreate"><a href="workoutcreate">Create a new workout</a></div>
	<br/> -->
	<h1>Exercises</h1>
        <table class="table table-striped">
            <tr>
                <th>ExerciseId</th>
                <th>MuscleGroup</th>
                <th>Exercise</th>
                <th>Remove</th>
            </tr>
            <c:forEach items="${workoutexercises}" var="exercise" >
   		    	<tr>
                    <td><c:out value="${exercise.getExercise().getExerciseId()}" /></td>
                    <td><c:out value="${exercise.getExercise().getMuscleGroup()}" /></td>
                    <td><c:out value="${exercise.getExercise().getExercise()}" /></td>
                    <td><a href="removeworkoutexercise?workoutid=<c:out value="${exercise.getWorkoutId().toString()}"/>&exerciseid=<c:out value="${exercise.getExercise().getExerciseId().toString()}"/>">Remove</a></td>
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