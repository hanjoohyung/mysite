package com.douzone.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;

public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("a");
		

		if ("deleteform".equals(action)) {
			String no1 = request.getParameter("no");
			request.setAttribute("no", no1);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
			rd.forward(request, response);
			
		} else if ("add".equals(action)) {
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String message = request.getParameter("message");
			String reg_date = request.getParameter("reg_date");

			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setMessage(message);
			vo.setReg_date(reg_date);

			new GuestbookDao().insert(vo);

			response.sendRedirect("/guestbook02/db");
			
		} else if ("delete".equals(action)) {
			
			String no = request.getParameter("no");
			System.out.println(no+"\n");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String message = request.getParameter("message");
			String reg_date = request.getParameter("reg_date");

			GuestbookVo vo = new GuestbookVo();
			
			long y = Long.parseLong(no);
			
			vo.setNo(y);
			vo.setPassword(password);

			new GuestbookDao().delete(vo);

			response.sendRedirect("/guestbook02/db");
			
		} else {
			
			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> list = dao.findAll();

			request.setAttribute("list", list);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/list.jsp");
			rd.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
