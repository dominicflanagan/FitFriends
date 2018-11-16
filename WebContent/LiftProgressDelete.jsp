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
	<title>Lift Progress Delete</title>
</head>
<body>
<!--  	<h1>${messages.title}</h1> -->
	<div class ="container theme-showcase" role="main">
		<form action="liftprogressdelete" method="post">
	<div class="jumbotron">
		<a href="usershome"><img src="FitFriendsHome.jpg" alt="FitFriends Home" width="175" height="50"></a>
		<font color="blue"><h1>${messages.title}</h1></font>
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
				<label style="width:175px" for="weight">Weight</label>
				<input id="weight" name="weight" value="${fn:escapeXml(param.weight)}">
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
                <th style="text-align:right">Date of Lift</th>
                <th style="text-align:right">Weight</th>
                <th style="text-align:right">Number Reps</th>
            </tr>
            <c:forEach items="${liftprogress}" var="lift" >
                <tr>
                    <td><c:out value="${lift.getUserName()}" /></td>
                    <td><c:out value="${lift.getFirstName()}" /></td>
                    <td><c:out value="${lift.getLastName()}" /></td>
                    <td style="text-align:right"><fmt:formatDate value="${lift.getCreated()}" pattern="MM-dd-YYYY" /></td>
                    <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value = "${lift.getWeightPounds()}" /></td>
		            <td align="right"><fmt:formatNumber type = "number" pattern = "0.00" value="${lift.getNumReps()}"  /></td>
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