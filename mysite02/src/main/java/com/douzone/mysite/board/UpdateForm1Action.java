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

public class UpdateForm1Action implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Access Control(보안, 인증)
				HttpSession session = request.getSession();
				UserVo authUser = (UserVo)session.getAttribute("authUser");
				if(authUser==null) {
					MvcUtil.redirect(request.getContextPath(),request,response);
					return;
				}
				
				Long no = authUser.getNo();
				BoardVo boardVo = new BoardDao().findByNo(no);
				
				request.setAttribute("boardVo", authUser);
				MvcUtil.forword("/board/list", request, response);

	}

}
