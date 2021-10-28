package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ReAddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		int order_no = Integer.parseInt(request.getParameter("order_no"));
		int depth = Integer.parseInt(request.getParameter("depth"));
		System.out.println(group_no);
		BoardVo Vo = new BoardVo();
		Vo.setTitle(title);
		Vo.setContents(contents);
		Vo.setGroup_no(group_no);
		Vo.setOrder_no(order_no);
		Vo.setDepth(depth);
		Vo.setUser_no(authUser.getNo());
		
		new BoardDao().reinsert(Vo);
		
		MvcUtil.redirect(request.getContextPath() +"/board?pageNo=1&blockNo=1", request, response);
	}

}
