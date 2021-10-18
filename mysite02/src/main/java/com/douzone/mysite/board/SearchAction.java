package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String reg_date = request.getParameter("contents");
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setReg_date(reg_date);
		
		new BoardDao().findSearch(title);
		
		MvcUtil.redirect(request.getContextPath() + "/board", request, response);
	}
}
