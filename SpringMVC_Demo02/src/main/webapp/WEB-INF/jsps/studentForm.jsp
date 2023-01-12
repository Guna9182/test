<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="/SpringMVC_Demo02/frontcontroller/submitform" method="POST">

		<p>
			Student name: <input type="text" name="stuName">
		</p>
		<p>
			Subject: <input type="text" name="subject">
		</p>
		<input type="submit" value="Submit form">
	</form>


</body>