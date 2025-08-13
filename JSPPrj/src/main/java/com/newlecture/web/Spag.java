 package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/spag")
public class Spag extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = 0;
		String num_ = request.getParameter("n");
		if(num_ != null && !num_.equals(""))
			num = Integer.parseInt(num_);
		
		String result;
		
		if(num%2 != 0) 
			result = "홀수";
		else 
			result = "짝수";
// result값을 jsp페이지로 옮겨야 한다. 둘 사이에 연결고리로 사용되는
// 저장소가 필요하다
		request.setAttribute("result", result);
		// jsp에서 result로 꺼낸다
		String names[] = {"newlec", "drago"};
		request.setAttribute("names", names);
		
		Map<String, Object> notice = new HashMap<String, Object>();
		notice.put("id", 1);
		notice.put("title", "El은 좋아요");
		request.setAttribute("notice", notice);
		
		//redirect : 현재 작업헀던 내용과 상관없이 새로 요청하게 한다.
		//forward : 현재 작업한 작업을 이어갈 수 있게 해준다.
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("spag.jsp");
		dispatcher.forward(request, response);
	
	}
}
