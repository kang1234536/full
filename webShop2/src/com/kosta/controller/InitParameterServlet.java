package com.kosta.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitParameterServlet
 */
@WebServlet(
		urlPatterns = { 
				"/InitParameterServlet", 
				"/param1", 
				"/param2"
		}, 
		initParams = { 
				@WebInitParam(name = "email", value = "kha0202kha@naver.com"), 
				@WebInitParam(name = "phone", value = "010-2043-6458")
		})
public class InitParameterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	
	/**
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getInitParameter("email"));
		System.out.println(getInitParameter("phone"));
		
	}

}
