<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
ServletContext : ${scopeMessage1}<br> 
session : ${scopeMessage2}<br> 
request : ${scopeMessage3}<br> 
<h1>나의이름은 ${myname}</h1>
<h1>나의이름은 ${applicationScope.myname}</h1>
<h1>나의이름은 ${sessionScope.myname}</h1>
<h1>나의이름은 ${requestScope.myname}</h1>
</body>
</html>