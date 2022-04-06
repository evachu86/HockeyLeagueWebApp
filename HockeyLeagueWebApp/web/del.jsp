<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="BIG5">
		<title>Hockey League</title>
	</head>
	
	<body>
		<h1>HOCKEY LEAGUE</h1>
		<h2>Delete Player ${player.name}</h2>
		
		<p>Are you sure you want to delete this player?</p>
		
		<form action="Players" method="post">
			<input type="hidden" name="id" value="${player.id}"/>
			<input type="hidden" name="team" value="${player.teamId}"/>
			<input type="submit" name="actBtn" value="Delete"/>&nbsp;&nbsp;&nbsp;
			<input type="submit" name="actBtn" value="Cancel"/>
		</form>
	</body>
</html>