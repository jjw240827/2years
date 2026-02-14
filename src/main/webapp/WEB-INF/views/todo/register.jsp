<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/todo/register" method="post">
		<div>
			Title: <input type="text" name="title">
		</div>
		<div>
			DueDate: <input type="date" name="dteDate" value="2026-02-12">
		</div>
		<div>
			Writer: <input type="text" name="writer">
		</div>
		<div>
			Finished: <input type="checkbox" name="finished">
		</div>
		<div>
			<button type="submit">Register</button>
		</div>
	</form>
</body>
</html>