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
 * Servlet Filter implementation class SecondFilter
 */
@WebFilter("*.kosta")
public class SecondFilter implements Filter {

   
    public SecondFilter() {
       System.out.println("SecondFilter생성자");
    }


	public void destroy() {
	       System.out.println("SecondFilter소멸자");
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
	     System.out.println(" SecondFilter에서 실제  요청가기전에 수행한다.");

		// pass the request along the filter chain
		chain.doFilter(request, response);
	     System.out.println(" SecondFilter에서 실제  요청후 수행한다.");

	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("SecondFilter init----------------");
		System.out.println(fConfig.getFilterName());
		System.out.println(fConfig.getServletContext());
		System.out.println(fConfig.getServletContext().getRealPath("."));
	}

}
