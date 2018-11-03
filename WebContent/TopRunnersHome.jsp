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
<title>Top Runners</title>
</head>
<body>
	<div class ="container theme-showcase" role="main">
	<form action="toprunnershome" method="post">
	<div class="jumbotron">
		<h1>Top Runners</h1>
		</div>
		<p>
			<label for="event">Event Distance in Meters</label>
			<input id="event" name="event" value="${fn:escapeXml(param.event)}">
		</p>
		<p>
			<label for="topN">Number of Top Runners to Display</label>
			<input id="topN" name="topN" value="${fn:escapeXml(param.topN)}">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Top Runners</h1>
        <table class="table table-striped">
            <tr>
                <th>UserName</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th style="text-align:right">Run Time</th>
                <th style="text-align:right">Solid Fats</th>
                <th style="text-align:right">Saturated Fats</th>
                <th style="text-align:right">Sugars</th>
                <th style="text-align:right">Total Calories</th>
                <th>Nutrition Plan</th>
                <th>Run Progress</th>
            </tr>
            <c:forEach items="${toprunners}" var="toprunner" >
                <tr>
                    <td><c:out value="${toprunner.getUserName()}" /></td>
                    <td><c:out value="${toprunner.getFirstName()}" /></td>
                    <td><c:out value="${toprunner.getLastName()}" /></td>
                    <td style="text-align:right"><fmt:formatDate value="${toprunner.getRunTime()}" pattern="mm:ss" /></td>
			        <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${toprunner.getSolidFats()}" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${toprunner.getSatFats()}" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${toprunner.getSugars()}" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${toprunner.getTotalCalories()}" /></td>
                    <td><a href="nutritionplanhome?username=<c:out value="${toprunner.getUserName()}"/>">Nutrition Plan</a></td>
                    <td><a href="runprogresshome?username=<c:out value="${toprunner.getUserName()}"/>">Run Progress</a></td>
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
