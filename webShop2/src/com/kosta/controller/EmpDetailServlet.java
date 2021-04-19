package com.kosta.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.model.DeptDAO;
import com.kosta.model.DeptVO;
import com.kosta.model.EmpDAO;
import com.kosta.model.EmpVO;

/**
 * Servlet implementation class DeptDetailServlet
 */
@WebServlet("/emp/empDetail")
public class EmpDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//상세보기:조회목적
		String empid=request.getParameter("empid");
		if(empid==null)throw new ServletException("emid가 없음");
		int emp_id=Integer.parseInt(empid);
		EmpDAO dao = new EmpDAO();
		DeptDAO dao2 = new DeptDAO();
		EmpVO emp=dao.selectById(emp_id);
		request.setAttribute("emp", dao.selectById(emp_id));
		request.setAttribute("mlist", dao2.selectAllManager());
		request.setAttribute("dlist", dao2.selectAll());
		request.setAttribute("jlist", dao.selectAllJobs());
		//JSP에게 위임한다.
		RequestDispatcher rd= request.getRequestDispatcher("empDetail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
