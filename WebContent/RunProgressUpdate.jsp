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
<title>Update Nutrition Plan</title>
</head>
<body>
<!--  	<h1>${messages.title}</h1> -->
	<div class ="container theme-showcase" role="main">
		<form action="runprogressupdate" method="post">
	<div class="jumbotron">
		<h1>${messages.title}</h1>
	</div>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<p>
  				<label style="width:175px"for="username">UserName</label>
				<input id="username" name="username" value="${fn:escapeXml(param.username)}">
				</p>
				<p>
				<label style="width:175px" for="created">Run Date</label>
				<input id="created" name="created" value="${fn:escapeXml(param.created)}" >
				</p>
				<p>
				<label style="width:175px" for="distance">Distance</label>
				<input id="distance" name="distance" value="${fn:escapeXml(param.distance)}">
				</p>
				<p>
				<label style="width:175px" for="runtime">New Run Time in Seconds</label>
 				<input id="runtime" name="runtime"> 
<!-- 				<input id="runtime" name="runtime" value="${fn:escapeXml(param.runtime)}">  -->
				</p>
			</div>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit" class="btn btn-lg btn-primary">
			</span>
		</p>
	</form>
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
                <th>Delete Run Time</th>
            </tr>
            <c:forEach items="${runprogress}" var="runtime" >
                <tr>
                    <td><c:out value="${runtime.getUserName()}" /></td>
                    <td><c:out value="${runtime.getFirstName()}" /></td>
                    <td><c:out value="${runtime.getLastName()}" /></td>
                    <td style="text-align:right"><fmt:formatDate value="${runtime.getCreated()}" pattern="MM-dd-YYYY" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${runtime.getDistanceMeters()}" /></td>
		            <td style="text-align:right"><fmt:formatDate value="${runtime.getRunTime()}" pattern="mm:ss" /></td>
                    <td><a href="nutritionplanhome?username=<c:out value="${runtime.getUserName()}"/>">Nutrition Plan</a></td>
                    <td><a href="runtimedelete?username=<c:out value="${runtime.getUserName()}"/>&created=<c:out value="${runtime.getCreated()}"/>&distance=<c:out value="${runtime.getDistanceMeters()}"/>">Delete</a></td>                 
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