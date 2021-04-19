<%@page import="com.kosta.model.JobVO"%>
<%@page import="com.kosta.model.ManagerVO"%>
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

</head>
<body>
<h1>직원정보 상세보기.....${myname }</h1>
<h1>${appInfo}</h1>

<jsp:include page="../common/header.jsp"></jsp:include>


${emp}<br>
<hr>
<%-- <%=request.getAttribute("emp") %> --%>
<!-- <form action="empDetail" method="post"> -->
<form id="myfrm" action="empUpdate" method="post">
직원번호 : <input type="text" value="${emp.employee_id }" name="employee_id"><br>
직원성 : <input type="text" value="${emp.first_name }" name="first_name"><br>
직원이름 : <input type="text" value="${emp.last_name }" name="last_name"><br>
직업코드 :
<select name="job_id">
<%
String myJob=((EmpVO)request.getAttribute("emp")).getJob_id();
List<JobVO> jlist = (List<JobVO>)request.getAttribute("jlist");
for(JobVO j:jlist){
	String s= myJob.equals(j.getJob_id())?"selected":"";
	out.print("<option "+s+" value='"+j.getJob_id()+"'>"+j.getJob_title()+"</option>");
}
%>
</select>
<br>
커미션 : <input type="text" value="${emp.commission_pct }" name="commission_pct"><br>
이메일 : <input type="text" value="${emp.email }" name="email"><br>
매니저아이디 : 
<select name="manager_id">
<%
int myManager=((EmpVO)request.getAttribute("emp")).getManager_id();
List<ManagerVO> mlist = (List<ManagerVO>)request.getAttribute("mlist");
for(ManagerVO m:mlist){
	String s= myManager==m.getManager_id()?"selected":"";
	out.print("<option "+s+" value='"+m.getManager_id()+"'>"+m.getFullname()+"</option>");
}
%>

</select>


<br>
전화번호 : <input type="text" value="${emp.phone_number }" name="phone_number"><br>
연봉 : <input type="number" value="${emp.salary }" name="salary"><br>
입사일 : <input type="text" value="${emp.hire_date }" name="hire_date"><br>
부서번호 :
<select name="department_id">
<%
	int mydept =((EmpVO)request.getAttribute("emp")).getDepartment_id();

		List<DeptVO> dlist = (List<DeptVO>)request.getAttribute("dlist");
			for(DeptVO dept:dlist){
				int d = dept.getDepartment_id();
	%>
<option <%=mydept==d?"selected":"" %> value="<%=d%>"><%=dept.getDepartment_name() %></option>
<% }%>
</select>
<br>


<input type="submit" value="수정하기">
<input type="button" id="btnUpdate" value="수정하기2">
<input type="button" id="btnRetrieve" value="조회하기">
<input type="button" id="btnDelete" value="삭제하기" mydata="${emp.employee_id }">

</form>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
 <script>
$(function(){
	$("#btnUpdate").on("click",function(){
		$("#myfrm").submit();
	});
	$("#btnRetrieve").on("click",function(){
		location.href="emplist";
	});
	$("#btnDelete").on("click",function(){
		alert($(this).attr("mydata"));
		location.href="empDelete?empid="+$(this).attr("mydata");
	});
});
</script>
</body>
</html>

		
	
	
	
		
		
	