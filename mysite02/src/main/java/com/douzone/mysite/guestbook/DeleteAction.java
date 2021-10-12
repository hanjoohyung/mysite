package com.douzone.mysite.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.mvc.Action;

public class DeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String message = request.getParameter("message");
		String reg_date = request.getParameter("reg_date");

		GuestbookVo vo = new GuestbookVo();
		
		long y = Long.parseLong(no);
		vo.setNo(y);
		vo.setPassword(password);

		new GuestbookDao().delete(vo);

		response.sendRedirect("/guestbook");
	}

}
