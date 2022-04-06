<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="BIG5">
		<title>Hockey League</title>
		
		<style>
		label {
			width: 200px;
		}
		input[type="text"],select {
			width: 200px;
		}
		input[type="text"],select,input[type="submit"] {
			margin: 5px;
		}
		</style>
	</head>
	
	<body>
		<h1>HOCKEY LEAGUE</h1>
		<h2>${ empty player?'Add Player':'Edit Player: '.concat(player.name) }</h2>
		
		<form action="Players" method="post">
			<input type="hidden" name="id" value="${player.id}"/>

			<label for="name">Name:</label>
			<input type="text" name="name" value="${player.name}"/><br/>
			
			<label for="address">Address:</label>
			<input type="text" name="address" value="${player.address}"/><br/>

			<label for="teamId">Team:</label>
			<select name="teamId" >
			<c:forEach var="teamOpt" items="${teams}">
				<option ${ team==teamOpt.id || player.teamId==teamOpt.id? 'selected':''} 
					value="${teamOpt.id}">
					${teamOpt.name}
				</option>
			</c:forEach>
			</select>
			<br/>
			
			<label for="role">Role:</label>
			<select name="role" >
			<c:forEach var="roleOpt" items="${roles}">
				<option ${player.role eq roleOpt.name()? 'selected':''} value="${roleOpt}">
					${roleOpt.toString()}
				</option>
			</c:forEach>
			</select>
			<br/>
			
			<label for="active">Active:</label>
			<input type="checkbox" name="active" value=true ${player.active?'checked':''}/><br/>
			
			<input type="hidden" name="team" value="${team}"/>
			<input type="submit" name="actBtn" value="Save"/>&nbsp;&nbsp;&nbsp;
			<input type="submit" name="actBtn" value="Cancel"/>
		</form>
	</body>
</html>