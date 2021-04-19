<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
application.setAttribute("myAge1", 10);
session.setAttribute("myAge2", 20);
request.setAttribute("myAge3", 30);
pageContext.setAttribute("myAge4", 40);


String dbname = application.getInitParameter("dbname");
String userid =application.getInitParameter("userid");


RequestDispatcher rd=null;
rd= request.getRequestDispatcher("second.jsp");
rd.include(request, response);
%>
<h1>first페이지에서 저장후 가져오기</h1>
<p>myAge1:${myAge1}</p>
<p>myAge2:${myAge2}</p>
<p>myAge3:${myAge3}</p>
<p>myAge4:${myAge4}</p>
<p><%=dbname %></p>
<p><%=userid %></p>
</body>
</html>