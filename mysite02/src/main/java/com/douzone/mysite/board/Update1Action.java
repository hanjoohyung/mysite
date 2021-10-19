package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class Update1Action implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String no = request.getParameter("no");
		
		BoardVo vo = new BoardVo();
		
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setNo(Long.parseLong(no));
		
		new BoardDao().update(vo);
		
		MvcUtil.redirect(request.getContextPath() +"/board", request, response);
	}

}
