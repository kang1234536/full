package com.kosta.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class URLPatternTestServlet
 */
@WebServlet("/sample/*")
public class URLPatternTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("ContextPath:"+request.getContextPath()); 
		System.out.println("URi :" +request.getRequestURI());
		System.out.println("URL :" +request.getRequestURL());
		System.out.println("get?post? :" +request.getMethod());
		System.out.println("Servlet path: :" +request.getServletPath());
		
		int pos=request.getContextPath().length();
		
		String uri=request.getRequestURI().substring(pos);
		String page="default.jsp";
		if(uri.equals("/sample/aa")) {
			page="../sample2/test1.jsp";
		}else if(uri.equals("/sample/bb")) {
			page="../sample2/test2.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	
	

}
