package com.kosta.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ScopeTestServlet
 */
@WebServlet("/ScopeTestServlet")
public class ScopeTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
  

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Scope:context,session,request
		ServletContext app = getServletContext();
		HttpSession session = request.getSession();
		
		app.setAttribute("scopeMessage1", "ServletContext메시지");
		session.setAttribute("scopeMessage2", "session메시지");
		request.setAttribute("scopeMessage3", "request메시지");
		
		app.setAttribute("myname", "app길동");
		session.setAttribute("myname", "session길동");
		request.setAttribute("myname", "req김길동");
		
		
		RequestDispatcher rd = request.getRequestDispatcher("scopetest.jsp");
		rd.forward(request, response);
	}


	

}
