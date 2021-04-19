package com.kosta.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosta.model.DeptDAO;
import com.kosta.model.DeptVO;
import com.kosta.model.EmpDAO;
import com.kosta.model.EmpVO;

/**
 * Servlet implementation class EmpListServlet
 */
@WebServlet("/emp/emplist")
public class EmpListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("emptlist요청...servlet");
		EmpDAO dao = new EmpDAO();
		List<EmpVO> elist= dao.selectAll();
		request.setAttribute("empall", elist);
		/*
		 * 서블리싱 요청을 받아서 JSP에게 위임한다.
		 */
		request.setAttribute("myname", "heewon");
		request.setAttribute("myscore", 100);
		EmpVO emp = new EmpVO();
		emp.setFirst_name("직원이름:홍길동");
		emp.setSalary(500);
		request.setAttribute("myemp", emp);
		
		/*
		 * servletcontext: application당 하나
		 * 
		 * 		 */
		ServletContext app= getServletContext();
		app.setAttribute("appInfo", "우리사이트에 오신것을 환영합니다.");
		
		
		
		
		
		System.out.println("info:"+request.getAttribute("info"));
		System.out.println("dbname:"+app.getAttribute("dbname"));
		System.out.println("userid:"+app.getAttribute("userid"));
		
		//1.쿠키얻기
		/*
		 * Cookie[] cs=request.getCookies(); for(Cookie c:cs) { String name=c.getName();
		 * String value=c.getValue(); if(name.equals("username")) {
		 * request.setAttribute("loginEmail",URLDecoder.decode(value,"utf-8")); }
		 * System.out.println(name+"====>"+value);
		 * System.out.println("====================="); }
		 */
		//2.session이용하기(더 많이 사용한다)why?보안, 사이즈 제한없음, 브라우져에서 쿠키차단과 무관
				//Session은 쿠키 기술을 이용 SessionID가 쿠키로 만들어져서 Browser에 저장된다.
				//정보는 서버 메모리에 저장된다.
				HttpSession session = request.getSession(true);//있으면 얻고 없으면 만든다.
				Object obj = session.getAttribute("empid");
				if(obj==null) {
					response.sendRedirect("../login/loginChk.kosta");
					return;
				}
				session.getAttribute("empid");

		
		
		
		//서블릿이 받은요청을 JSP에 넘기기 (위임)
	//	RequestDispatcher rd=null;
		RequestDispatcher rd = request.getRequestDispatcher("emp_retrieve.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
