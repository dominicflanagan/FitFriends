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
	<title>Lift Progress</title>
</head>
<body>
	<div class ="container theme-showcase" role="main">
	<form action="liftprogresshome" method="post">
	<div class="jumbotron">
		<a href="usershome"><img src="FitFriendsHome.jpg" alt="FitFriends Home" width="175" height="50"></a>
		<font color="blue"><h1>Lift Progress</h1></font>
		</div>
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
	<br/>
 		<div id="liftprogressadd"><a href="liftprogressadd?username=<c:out value="${fn:escapeXml(param.username)}"/>">Add Lift Progress</a></div> 								
	<br/>
	<h1>Lift Progress</h1>
        <table class="table table-striped">
            <tr>
                <th>UserName</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th style="text-align:right">Date of Lift</th>
                <th style="text-align:right">Weight</th>
                <th style="text-align:right">Number Reps</th>
                <th>Nutrition Plan</th>
                <th>Update Lift Progress</th>
                <th>Delete Lift Progress</th>
            </tr>
            <c:forEach items="${liftprogress}" var="lift" >
                <tr>
                    <td><c:out value="${lift.getUserName()}" /></td>
                    <td><c:out value="${lift.getFirstName()}" /></td>
                    <td><c:out value="${lift.getLastName()}" /></td>
                    <td style="text-align:right"><fmt:formatDate value="${lift.getCreated()}" pattern="MM-dd-YYYY" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${lift.getWeightPounds()}" /></td>
		            <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${lift.getNumReps()}" /></td>
                    <td><a href="nutritionplanhome?username=<c:out value="${lift.getUserName()}"/>">Nutrition Plan</a></td>
                    <td><a href="liftprogressupdate?username=<c:out value="${lift.getUserName()}"/>&created=<fmt:formatDate value="${lift.getCreated()}" pattern="YYYY-MM-dd" />&weight=<c:out value="${lift.getWeightPounds()}"/>&numreps=<c:out value="${lift.getNumReps()}" />">Update Lift Item</a></td>                
                    <td><a href="liftprogressdelete?username=<c:out value="${lift.getUserName()}"/>&created=<fmt:formatDate value="${lift.getCreated()}" pattern="YYYY-MM-dd" />&weight=<c:out value="${lift.getWeightPounds()}"/>">Delete</a></td>                 
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
