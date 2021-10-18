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

public class ModifyAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String reg_date = request.getParameter("regdate");

		BoardVo vo = new BoardDao().findWhe(title,contents);
		HttpSession session = request.getSession(true);

		request.setAttribute("title", vo.getTitle());
		request.setAttribute("contents",vo.getContents());
		request.setAttribute("reg_date", vo.getReg_date());
	
		MvcUtil.forword("board/modify", request, response);
	}
}
