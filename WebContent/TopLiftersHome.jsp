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
<title>Top Lifters</title>
</head>
<body>
	<div class ="container theme-showcase" role="main">
	<form action="topliftershome" method="post">
	<div class="jumbotron">
		<h1>Top Lifters</h1>
		</div>
		<p>
			<label style="width:235px" for="weight">Weight in Pounds</label>
			<input id="weight" name="weight" value="${fn:escapeXml(param.weight)}">
		</p>
		<p>
			<label style="width:235px" for="topN">Number of Top Lifters to Display</label>
			<input id="topN" name="topN" value="${fn:escapeXml(param.topN)}">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Top Lifters</h1>
        <table class="table table-striped">
            <tr>
                <th>UserName</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th style="text-align:right">Number Reps</th>
                <th style="text-align:right">Solid Fats</th>
                <th style="text-align:right">Saturated Fats</th>
                <th style="text-align:right">Sugars</th>
                <th style="text-align:right">Total Calories</th>
                <th>Nutrition Plan</th>
                <th>Lift Progress</th>
            </tr>
            <c:forEach items="${toplifters}" var="toplifter" >
                <tr>
                    <td><c:out value="${toplifter.getUserName()}" /></td>
                    <td><c:out value="${toplifter.getFirstName()}" /></td>
                    <td><c:out value="${toplifter.getLastName()}" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${toplifter.getNumReps()}" /></td>
			        <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${toplifter.getSolidFats()}" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${toplifter.getSatFats()}" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${toplifter.getSugars()}" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${toplifter.getTotalCalories()}" /></td>
                    <td><a href="nutritionplanhome?username=<c:out value="${toplifter.getUserName()}"/>">Nutrition Plan</a></td>
                    <td><a href="liftprogresshome?username=<c:out value="${toplifter.getUserName()}"/>">Lift Progress</a></td>
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
