<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete a Nutrition Plan Item</title>
</head>
<body>
	<a href="usershome"><img src="FitFriendsHome.jpg" alt="FitFriends Home" width="175" height="50"></a>
	<font color="blue"><h1>${messages.title}</h1></font>
	<form action="nutritionplandelete" method="post">
		<p>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label style="width:85px" for="memberid">Member Id</label>
				<input id="memberid" name="memberid" value="${fn:escapeXml(param.memberid)}">
			</div>
		</p>
		<p>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label style="width:85px" for="nutritionid">Nutrition Id</label>
				<input id="nutritionid" name="nutritionid" value="${fn:escapeXml(param.nutritionid)}">
			</div>
		</p>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
		</p>
	</form>
	<br/><br/>
	
</body>
</html>