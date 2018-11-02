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
<title>Add Items To Your Nutrition Plan</title>
</head>
<body>
	<h1>${messages.title}</h1>
		<div class ="container theme-showcase" role="main">
		<form action="nutritionhome" method="post">
			<div class="jumbotron">
		<h1>Add Items To Your Nutrition Plan</h1>
		</div>
		<p>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label for="username">UserName</label>
				<input id="username" name="username" value="${fn:escapeXml(param.username)}">
			</div>
		</p>
		<p>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label for="foodtype">Food Type</label>
				<input id="foodtype" name="foodtype" value="${fn:escapeXml(param.foodtype)}">
			</div>
		</p>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit" class="btn btn-lg btn-primary">
			</span>
		</p>
	</form>
	<h1>Nutrition Items</h1>
        <table class="table table-striped">
            <tr>
                <th>Food Item</th>
                <th>Portion</th>
                <th style="text-align:right">Solid Fats</th>
                <th style="text-align:right">Saturated Fats</th>
                <th style="text-align:right">Added Sugars</th>
                <th style="text-align:right">Calories</th>
                <th>Add to Nutrition Plan</th>
            </tr>
            <c:forEach items="${nutritions}" var="nutrition" >
                <tr>
                    <td><c:out value="${nutrition.getDisplayName()}" /></td>
                    <td><c:out value="${nutrition.getPortionAmount()} x ${nutrition.getPortionDisplayName()}" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${nutrition.getSolidFats()}" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${nutrition.getSaturatedFats()}" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${nutrition.getAddedSugars()}" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${nutrition.getCalories()}" /></td>
                    <td><a href="nutritionplanadd?username=<c:out value="${fn:escapeXml(param.username)}"/>&nutritionid=<c:out value="${nutrition.getNutritionId()}"/>">Add To Plan</a></td>                
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