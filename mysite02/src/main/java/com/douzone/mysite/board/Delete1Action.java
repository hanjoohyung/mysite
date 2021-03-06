package com.douzone.mysite.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class Delete1Action implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		
		BoardVo vo = new BoardVo();
		vo.setNo(Long.parseLong(no));
		vo.setTitle(title);
		
		if(vo.getUser_no() != authUser.getNo()) {
			MvcUtil.redirect(request.getContextPath() + "/board?pageNo=1&blockNo=1", request, response);
			return;
		} 
		
		new BoardDao().delete(vo);
		
		MvcUtil.redirect(request.getContextPath() + "/board?pageNo=1&blockNo=1", request, response);
	}
}
