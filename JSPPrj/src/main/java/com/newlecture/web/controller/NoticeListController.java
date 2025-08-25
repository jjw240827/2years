package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// controller에서 중요한 것은 모델을 만들어서 view에게 전달하는 것이다. 이 페이지에서 가지고 있는 모델은 목록이다. 
		// 목록을 위한 list를 만든다.
		
		//list?f=title&q=a
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		
		String field = "title";
		if(field_ != null) {
			field = field_;
		}
		String query= "";
		if(query_ != null) {
			query = query_;
		}
		
		NoticeService service = new NoticeService();
		List<Notice> list = service.getNoticeList(field, query, 1);
		
		
		request.setAttribute("list",list);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/list2.jsp")
		.forward(request, response);
		
}
	
	}
