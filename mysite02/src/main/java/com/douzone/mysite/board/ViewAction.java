package com.douzone.mysite.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String reg_date = request.getParameter("regdate");
		String no = request.getParameter("no");
		String hit = request.getParameter("hit");
		
		Long no1 = Long.parseLong(no);
		Integer hit1 = Integer.parseInt(hit);
		BoardVo vo = new BoardDao().findWhere(title, reg_date);
		HttpSession session = request.getSession(true);
		
		request.setAttribute("no1", vo.getNo());
		request.setAttribute("title", vo.getTitle());
		request.setAttribute("contents",vo.getContents());
		request.setAttribute("reg_date", vo.getReg_date());
		request.setAttribute("hit1", vo.getHit());
		
		new BoardDao().updateHit(no1);
		
		MvcUtil.forword("board/view", request, response);
		
	}
}