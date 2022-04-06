<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="BIG5">
		<title>Hockey League</title>
		
		<style>
			li {
				width: 500px;
			}
			div.nameDiv,li {
				height: 30px;
			}
			div.nameDiv {
				width: 200px;
				display: inline;
			}
			li button[name="editBtn"] {
				text-align: right;
			}
		</style>
	</head>
	
	<body>
		<h1>HOCKEY LEAGUE</h1>
		<h2>${team.name} Team</h2>

		<ul>
		<c:forEach var="player" items="${players}">
			<li>
			<form action="EditPlayer" method="post">
				<input type="hidden" name="id" value="${player.id}" />
				<div class="nameDiv">${player.name}</div>
				<button type="submit" name="editBtn" value="update">
					<img src="img/pen.png" width="10"/>
				</button>
				<button type="submit" name="editBtn" value="delete">
					<img src="img/trash.png" width="10"/>
				</button>
			</form>
			</li>
		</c:forEach>
		</ul>
			
		<form action="EditPlayer" method="post">
			<input type="hidden" name="team" value="${team.id}"/>
			<input type="submit" name="editBtn" value="Add Player"/>&nbsp;&nbsp;&nbsp;
			<input type="submit" name="editBtn" value="Cancel"/>
		</form>
	</body>
</html>