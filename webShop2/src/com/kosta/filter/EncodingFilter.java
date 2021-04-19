package com.kosta.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

  
    public EncodingFilter() {
    	System.out.println("EncodingFilter 생성자");
    }


	public void destroy() {
		System.out.println("EncodingFilter destory");
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		request.setCharacterEncoding("utf-8");
		
		
		// pass the request along the filter chain
		//서블릿으로가기
		System.out.println("EncodingFilter:서블릿으로가기전에 수행한다");
		long start=System.currentTimeMillis();
		chain.doFilter(request, response);
		System.out.println("EncodingFilter:서블릿 수행후");
		long end = System.currentTimeMillis();
		System.out.println("EncodingFilter:서블릿 수행후:(걸린시간)"+(end-start)+"ms");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("EncodingFilter init----------------");
		System.out.println(fConfig.getFilterName());
		System.out.println(fConfig.getServletContext());
		System.out.println(fConfig.getServletContext().getRealPath("."));
		
	}

}
