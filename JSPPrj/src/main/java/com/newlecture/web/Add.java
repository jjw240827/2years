 
package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class Add extends HttpServlet{
	@Override // override 어노테이션은 부모 클래스의 메서드를 오버라이드(재정의) 했다는 뜻이다.
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String x_ = request.getParameter("first");
		String y_ = request.getParameter("second");
	
		int x = 0;
		int y = 0;
		
		if(x_ != null && !x_.equals("")) x = Integer.parseInt(x_);
		if(y_ != null && !y_.equals("")) y = Integer.parseInt(y_);
		
		int result = x + y;
		
		response.getWriter().printf("result is %d<br>", result);
		
		response.getWriter().println("히히 바보 멍충아");
		
	}
}	
