
package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet{
	@Override // override 어노테이션은 부모 클래스의 메서드를 오버라이드(재정의) 했다는 뜻이다.
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
	
		int v = 0;
		
		
		if(v_ != null && !v_.equals("")) v = Integer.parseInt(v_);
		// 만약 null값이나 빈 문자열 값이면 그냥 v = 0으로 계산된다.
		
		// 계산
		if(op!= null && op.equals("=")) {
			
			//int x = (Integer)application.getAttribute("value");
//			int x = (Integer)session.getAttribute("value");
			int x = 0;
			for(Cookie c : cookies) {
				if(c.getName().equals("value")){
					x = Integer.parseInt(c.getValue());
					break;
				}
//				op가 = 일때 게산하는 코드이다. value(나중에 쿠키에 값을 저장할 떄 value로 저장함)
//				와 cookie배열의 내가 원하는 값이 일치하면 그 값을 x에 넣는다.
			}
			int y = v;
			//String operator = (String)application.getAttribute("op");
//			String operator = (String)session.getAttribute("op");
			String operator = "";
			for(Cookie c : cookies) {
					if(c.getName().equals("op")){
						operator = c.getValue();
						break;
					}
			}
			int result = 0;
			
			if(operator.equals("+"))
				result = x + y;
			else 
				result = x - y;
			// x 값이 전에 쿠키에 넣은 값이고 y가 현재 받아온 값이다.
			response.getWriter().printf("result is %d\n", result);
			
		}
		else {
//			application.setAttribute("value", v);
//			application.setAttribute("op", op);
//			session.setAttribute("value", v);
//			session.setAttribute("op", op);
			
			Cookie valuecookie = new Cookie("value", String.valueOf(v));
			Cookie opcookie = new Cookie("op", op);
//			쿠키값으로 보낼 값은 반드시 문자열로 보내야 한다. v는 정수이므로 문자열로 변경해야 한다.
			valuecookie.setPath("/");
			valuecookie.setMaxAge(24*60*60);
			opcookie.setPath("/");
			response.addCookie(valuecookie);
			response.addCookie(opcookie);
			
			response.sendRedirect("calc2.html");
			
		}
			
		

	}
		
	
}	
	

