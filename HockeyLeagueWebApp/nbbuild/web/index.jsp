<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import='com.hockeyleague.dao.TeamDao' %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="BIG5">
		<title>Hockey League</title>
		
		<style>
			select {
				width:100px;
			}
		</style>
	</head>
	
	<%
		TeamDao teamDao = new TeamDao();
		request.setAttribute("teams", teamDao.query(null));
	%>
	
	
	<body>
		<h1>HOCKEY LEAGUE</h1>
		<img src="img/ice-hockey-4285440_1280.jpg" width="600"/>
		<form action="Players" method="post">
			<label for="team">Team:</label>
			<select name="team">
			<c:forEach var="team" items="${teams}">
				<option value="${team.id}">${team.name}</option>
			</c:forEach>
			</select>
			<input type="submit" name="actBtn" value="View Team"/>
		</form>
	</body>
</html>