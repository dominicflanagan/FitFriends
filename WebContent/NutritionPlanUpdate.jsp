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
		<form action="nutritionplanupdate" method="post">
	<div class="jumbotron">
		<h1>${messages.title}</h1>
	</div>
		<p>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
  				<label for="username">UserName</label>
				<input id="username" name="username" value="${fn:escapeXml(param.username)}">
				<p>
				<label for="nutritionid">NutritionId</label>
				<input id="nutritionid" name="nutritionid" value="${fn:escapeXml(param.nutritionid)}">
				</p>
				<p>
				<label for="newnumberservings">Change the Number of Servings to </label>
				<input id="newnumberservings" name="newnumberservings" value="${fn:escapeXml(param.newnumberservings)}">
				</p>
			</div>
		</p>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
		</p>
	</form>
		<h1>Nutrition Plan Details</h1>
        <table class="table table-striped">
            <tr>
                <th>UserName</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Food Item</th>
                <th>Portion</th>
                <th style="text-align:right">Number Servings</th>
                <th style="text-align:right">Solid Fats</th>
                <th style="text-align:right">Saturated Fats</th>
                <th style="text-align:right">Added Sugars</th>
                <th style="text-align:right">Calories</th>
                <th>Delete Nutrition Plan Item</th>
            </tr>
            <c:forEach items="${nutritionplans}" var="nutrition" >
                <tr>
                    <td><c:out value="${nutrition.getUserName()}" /></td>
                    <td><c:out value="${nutrition.getFirstName()}" /></td>
                    <td><c:out value="${nutrition.getLastName()}" /></td>
                    <td><c:out value="${nutrition.getDisplayName()}" /></td>
                    <td><c:out value="${nutrition.getPortionAmount()} x ${nutrition.getPortionDisplayName()}" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${nutrition.getNumberServings()}" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${nutrition.getSolidFats()}" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${nutrition.getSaturatedFats()}" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${nutrition.getAddedSugars()}" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${nutrition.getCalories()}" /></td>
                  <td><a href="nutritionplandelete?memberid=<c:out value="${nutrition.getMemberId()}"/>&nutritionid=<c:out value="${nutrition.getNutritionId()}"/>">Delete</a></td>
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