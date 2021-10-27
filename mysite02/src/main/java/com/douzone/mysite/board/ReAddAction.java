package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ReAddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		BoardVo Vo = new BoardVo();
		Vo.setTitle(title);
		Vo.setContents(contents);
		
		new BoardDao().reinsert(Vo);
		
		MvcUtil.redirect(request.getContextPath() +"/board?pageNo=1&blockNo=1", request, response);
	}

}
