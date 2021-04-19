package com.kosta.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.model.EmpDAO;
import com.kosta.model.EmpVO;
import com.kosta.util.ConvertUtil;

/**
 * Servlet implementation class EmpUpdateServlet
 */
@WebServlet("/emp/empUpdate")
public class EmpUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//수정목적
		//request.setCharacterEncoding("utf-8");
		
		Enumeration<String> paramNames =request.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String colname=paramNames.nextElement();
			String val=request.getParameter(colname);
		}
			EmpVO emp = new EmpVO();
			emp.setCommission_pct(ConvertUtil.convertDouble(request.getParameter("commission_pct")));
			emp.setDepartment_id(ConvertUtil.convertInt(request.getParameter("department_id")));
			emp.setEmail(request.getParameter("email"));
			emp.setEmployee_id(ConvertUtil.convertInt(request.getParameter("employee_id")));
			emp.setFirst_name(request.getParameter("first_name"));
			emp.setHire_date(ConvertUtil.convertDate(request.getParameter("hire_date")));
			emp.setJob_id(request.getParameter("job_id"));
			emp.setLast_name(request.getParameter("last_name"));
			emp.setManager_id(ConvertUtil.convertInt(request.getParameter("manager_id")));
			emp.setPhone_number(request.getParameter("phone_number"));
			emp.setSalary(ConvertUtil.convertInt(request.getParameter("salary")));
			
			EmpDAO dao = new EmpDAO();
			dao.updateEmp(emp);
		int result=dao.updateEmp(emp);
		String message=result>0?"수정성공":"수정실패";
		
		request.setAttribute("message", message);
		
		//response.setHeader("refresh", "3;url=emplist");
		//JSP에게 위임한다
//		RequestDispatcher rd = request.getRequestDispatcher("resultInfo.jsp");
//		rd.forward(request, response);
		
		//주소창이변경된다.
		//요청 재지정(변경)
		ServletContext app = getServletContext();
		app.setAttribute("info", "요청 재지정시 request는 전달안됨");
		response.sendRedirect("emplist");
	}
	

	
}
