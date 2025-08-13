 package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();

		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");

		String exp = "";
		if (cookies != null)
			for (Cookie c : cookies) {
				if (c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
			}

		if (operator != null && operator.equals("=")) { // 계산 담당 -> 최신버전에서는 사용안되서 코드가 조금 바뀜
			if (operator != null && operator.equals("=")) {
				try {
					exp = String.valueOf(eval(exp));
				} catch (Exception e) {
					exp = "Error";
				}
			}
		} 
		else if (operator != null && operator.equals("C")) {
			exp = "";
		}
		else {
			exp += (value == null) ? "" : value;
			exp += (operator == null) ? "" : operator;
			exp += (dot == null) ? "" : dot;
			// 어차피 value, operator, dot 3가지 중 1가지만 cookie로 오게 된다. cookie로 온 값만 누적하는 방식
			// 모든 값을 누적만 하지 않고 operator가 =이면 계산을 해야 한다.
		}

		Cookie expCookie = new Cookie("exp", exp);
		if (operator != null && operator.equals("C")) 
			expCookie.setMaxAge(0); //쿠키 지우는 방법
		expCookie.setPath("/calculator");
		response.addCookie(expCookie);
		response.sendRedirect("calculator");
	}

	private double eval(String exp) {
		java.util.Stack<Double> numbers = new java.util.Stack<>();
		java.util.Stack<Character> operators = new java.util.Stack<>();

		int i = 0;
		while (i < exp.length()) {
			char ch = exp.charAt(i);

			if (Character.isDigit(ch) || ch == '.') {
				StringBuilder sb = new StringBuilder();
				while (i < exp.length() && (Character.isDigit(exp.charAt(i)) || exp.charAt(i) == '.')) {
					sb.append(exp.charAt(i++));
				}
				numbers.push(Double.parseDouble(sb.toString()));
				continue;
			}

			if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
				while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(ch)) {
					calculate(numbers, operators);
				}
				operators.push(ch);
			}
			i++;
		}

		while (!operators.isEmpty()) {
			calculate(numbers, operators);
		}

		return numbers.isEmpty() ? 0 : numbers.pop();
	}

	private int precedence(char op) {
		if (op == '+' || op == '-') return 1;
		if (op == '*' || op == '/') return 2;
		return 0;
	}

	private void calculate(java.util.Stack<Double> numbers, java.util.Stack<Character> operators) {
		if (numbers.size() < 2) return;
		double b = numbers.pop();
		double a = numbers.pop();
		char op = operators.pop();

		switch (op) {
			case '+': numbers.push(a + b); break;
			case '-': numbers.push(a - b); break;
			case '*': numbers.push(a * b); break;
			case '/': numbers.push(b == 0 ? 0 : a / b); break;
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		
		String exp = "0";
		if(cookies != null)
			for(Cookie c : cookies) {
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
			}// 만약 cookies가 null이 아니라면 exp는 cookie에서 읽어온 exp로 바뀐다.
			
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");	 
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("<style>");
		out.write("input{");
		out.write("width:50px;");
		out.write("height:50px;");
		out.write("}");
		out.write(".output{");
		out.write("height:50px;");
		out.write("background: #e9e9e9;");
		out.write("font-size:24px;");
		out.write("font-weight:bold;");
		out.write("text-align: right;");
		out.write("padding: 0px 5px;");
		out.write("}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		out.write("<form method=\"post\">");
		out.write("<table>");
		out.write("<tr>");
		out.printf("<td class=\"output\" colspan=\"4\">%s</td>", exp);
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type=\"submit\" name = \"operator\" value=\"CE\"/></td>");
		out.write("<td><input type=\"submit\" name = \"operator\" value=\"C\"/></td>");
		out.write("<td><input type=\"submit\" name = \"operator\" value=\"BS\"/></td>");
		out.write("<td><input type=\"submit\" name = \"operator\" value=\"/\"/></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type=\"submit\" name = \"value\" value=\"7\"/></td>");
		out.write("<td><input type=\"submit\" name = \"value\" value=\"8\"/></td>");
		out.write("<td><input type=\"submit\" name = \"value\" value=\"9\"/></td>");
		out.write("<td><input type=\"submit\" name = \"operator\" value=\"*\"/></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type=\"submit\" name = \"value\" value=\"4\"/></td>");
		out.write("<td><input type=\"submit\" name = \"value\" value=\"5\"/></td>");
		out.write("<td><input type=\"submit\" name = \"value\" value=\"6\"/></td>");
		out.write("<td><input type=\"submit\" name = \"operator\" value=\"-\"/></td>");
		out.write("</tr>");
		out.write("<tr>");
					out.write("<td><input type=\"submit\" name = \"value\" value=\"1\"/></td>");
					out.write("<td><input type=\"submit\" name = \"value\" value=\"2\"/></td>");
					out.write("<td><input type=\"submit\" name = \"value\" value=\"3\"/></td>");
					out.write("<td><input type=\"submit\" name = \"operator\" value=\"+\"/></td>");
					out.write("</tr>");
					out.write("<tr>");
					out.write("<td><input type=\"submit\" name = \"value\" value=\"0\"/></td>");
					out.write("<td><input type=\"submit\" name = \"dot\" value=\".\"/></td>");
					out.write("<td><input type=\"submit\" name = \"operator\" value=\"=\"/></td>");
					out.write("</tr>");
					out.write("	</table>");
					out.write("</form>");
					out.write("</body>");
					out.write("</html>");	
	}
	

	}

