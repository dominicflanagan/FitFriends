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
<title>Run Progress</title>
</head>
<body>
	<div class ="container theme-showcase" role="main">
	<form action="runprogresshome" method="post">
	<div class="jumbotron">
		<a href="usershome"><img src="FitFriendsHome.jpg" alt="FitFriends Home" width="175" height="50"></a>
		<font color="blue"><h1>Run Progress</h1></font>
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
 		<div id="runtimeadd"><a href="runtimeadd?username=<c:out value="${fn:escapeXml(param.username)}"/>">Add Run Time</a></div> 								
	<br/>
	<h1>Run Times</h1>
        <table class="table table-striped">
            <tr>
                <th>UserName</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th style="text-align:right">Date of Run</th>
                <th style="text-align:right">Distance</th>
                <th style="text-align:right">Run Time</th>
                <th>Nutrition Plan</th>
                <th>Update Run Time</th>
                <th>Delete Run Time</th>
            </tr>
            <c:forEach items="${runtimeprogress}" var="runtime" >
                <tr>
                    <td><c:out value="${runtime.getUserName()}" /></td>
                    <td><c:out value="${runtime.getFirstName()}" /></td>
                    <td><c:out value="${runtime.getLastName()}" /></td>
                    <td style="text-align:right"><fmt:formatDate value="${runtime.getCreated()}" pattern="MM-dd-YYYY" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${runtime.getDistanceMeters()}" /></td>
		            <td style="text-align:right"><fmt:formatDate value="${runtime.getRunTime()}" pattern="mm:ss" /></td>
                    <td><a href="nutritionplanhome?username=<c:out value="${runtime.getUserName()}"/>">Nutrition Plan</a></td>
                    <td><a href="runprogressupdate?username=<c:out value="${runtime.getUserName()}"/>&created=<fmt:formatDate value="${runtime.getCreated()}" pattern="YYYY-MM-dd" />&distance=<c:out value="${runtime.getDistanceMeters()}"/>&runtime=<fmt:formatDate value="${runtime.getRunTime()}" pattern="mm:ss" />">Update Run Time</a></td>                
                    <td><a href="runprogressdelete?username=<c:out value="${runtime.getUserName()}"/>&created=<fmt:formatDate value="${runtime.getCreated()}" pattern="YYYY-MM-dd" />&distance=<c:out value="${runtime.getDistanceMeters()}"/>">Delete</a></td>                 
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
