package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hi") 
public class Nana extends HttpServlet{
	@Override // override 어노테이션은 부모 클래스의 메서드를 오버라이드(재정의) 했다는 뜻이다.
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset= UTF-8");
		
		PrintWriter out = response.getWriter();
			
		String cnt_ = request.getParameter("cnt");
		
		int cnt = 10;
		if(cnt_ != null && cnt_.equals(""))
			cnt = Integer.parseInt(cnt_);
		for(int i=0; i<cnt; i++)
			out.println((i+1)+":안녕 servlet!!<br/>");
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		
//		String cnt_ = request.getParameter("cnt");
//		
//		int cnt = 100;
//		
//		if(cnt_ != null && !cnt_.equals(""))
//			cnt = Integer.parseInt(cnt_);
//		
//		for(int i = 0; i <cnt; i++)
//			out.println("안녕 Servlet<br />");
	}
}	
