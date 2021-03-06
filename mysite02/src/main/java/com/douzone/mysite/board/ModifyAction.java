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

public class ModifyAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String no = request.getParameter("no");
		
		Long no1 = Long.parseLong(no);
		BoardVo vo = new BoardDao().findWhe(title,contents);
		HttpSession session = request.getSession(true);
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		BoardVo Vo = new BoardVo();
		
		if(Vo.getUser_no() != authUser.getNo()) {
			MvcUtil.redirect(request.getContextPath() + "/board?pageNo=1&blockNo=1", request, response);
			return;
		}
		
		
		request.setAttribute("title", vo.getTitle());
		request.setAttribute("contents",vo.getContents());
		request.setAttribute("no1", vo.getNo());
			
		MvcUtil.forword("board/modify", request, response);
	}
}
