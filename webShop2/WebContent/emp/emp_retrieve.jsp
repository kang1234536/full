<%@page import="com.kosta.model.EmpVO"%>
<%@page import="com.kosta.model.DeptVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table,td{border: 1px solid black; border-collapse: collapse;}
td{padding: 5px;}
tr:first-of-type{background-color: yellow;}
</style>
<%
List<EmpVO> elist =(List<EmpVO>)request.getAttribute("empall");
%>
</head>
<body>
<h1>직원목록....로그인정보: ${loginEmail}</h1>
<jsp:include page="../common/header.jsp"></jsp:include>
<br>
<a href ="empInsert">신규등록</a>
<hr>
<ul>
	<li>문자값:${myname}</li>
	<li>숫자값:<%=request.getAttribute("myscore") %></li>
	<li>숫자값:emp객체:${myemp}</li>
	<li>info객체:${info}</li>
</ul>



<table>
<tr>
<td>직원코드</td><td>직원성</td><td>직원이름</td><td>직업코드</td><td>컴미션</td><td>이메일</td><td>매니저</td><td>전화번호</td><td>급여</td><td>입사일</td><td>부서번호</td><td></td>
</tr>
<%for(EmpVO emp:elist){%>
	<tr>
		<td><a href="empDetail?empid=<%=emp.getEmployee_id() %>"><%=emp.getEmployee_id() %></a></td>		
		<td><%=emp.getFirst_name() %></td>
		<td><%=emp.getLast_name() %></td>
		<td><%=emp.getJob_id() %></td>
		<td><%=emp.getCommission_pct() %></td>
		<td><%=emp.getEmail() %></td>
		<td><%=emp.getManager_id() %></td>
		<td><%=emp.getPhone_number() %></td>
		<td><%=emp.getSalary() %></td>
		<td><%=emp.getHire_date() %></td>
		<td><%=emp.getDepartment_id() %></td>
		<td><button onclick="location.href='empDelete?empid=<%=emp.getEmployee_id() %>'">삭제</button></td> 
	</tr>
<% }%>
</table>




</body>
</html>