package com.kosta.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.model.EmpDAO;

/**
 * Servlet implementation class EmpDeleteServlet
 */
@WebServlet("/emp/empDelete")
public class EmpDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String obj =request.getParameter("empid");
		if(obj==null ||obj=="")throw new ServletException("아이디가 없음");
		int empid = Integer.parseInt(obj);
		EmpDAO dao = new EmpDAO();
		int result=dao.deleteEmp(empid);
		request.setAttribute("message", result>0?"삭제성공":"삭제실패");
		RequestDispatcher rd = request.getRequestDispatcher("resultInfo.jsp");
			rd.forward(request, response);
	
	}



}
