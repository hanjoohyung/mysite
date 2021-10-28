package com.douzone.mysite.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PagingVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class BoardAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		if (session.getAttribute("authUser") == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('로그인 후 이용이 가능합니다.'); location.href='/mysite02" + "';</script>");
		} else {
			 
			int blockNo = Integer.parseInt(request.getParameter("blockNo"));
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
			int begin = 0;
			int pagerNo = 5; // 페이지 목록 개수
			int pageCount ;
			int viewNo = 5; // 페이지에 보이는 게시물 개수
			int count = new BoardDao().pageCount();
			pageCount = count/viewNo;
			
			begin = (pageNo-1)*5;
			List<BoardVo> list = new BoardDao().findAll(begin);
			request.setAttribute("list", list);
			request.setAttribute("count",count);
			if (blockNo == 0) {
			if(count%5 == 0) {
				if(count < 25) {
				pageCount = count/viewNo;
				blockNo = 1;
				} else {
					pageCount = count/viewNo;
					if(pageCount >= 5) {
						blockNo = 0;
						blockNo++;
					} else {
						blockNo=1;
					}
				}
			}	else {
					if(count < 25) {
						pageCount = (count/viewNo)+1;
						blockNo=1;
					} else {
						pageCount = (count/viewNo)+1;
						if(pageCount >= 5) {
							blockNo = 0;
							blockNo++;
						} else {
							blockNo=1;
						}
					}
				}
			}
			PagingVo vo = new PagingVo();
			
			vo.setBlockNo(blockNo);
			vo.setPageCount(pageCount);
			vo.setPageNo(pageNo);
			
			String start = Integer.toString(blockNo*5-4);
			if(blockNo == 1) {
			String end = Integer.toString(blockNo*5);
			
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("pageCount", pageCount);	
			request.setAttribute("blockNo",blockNo);
			request.setAttribute("start",start);
			request.setAttribute("end",end);
			
			} else {
			String end = Integer.toString(count/5+1);
				
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("pageCount", pageCount);	
			request.setAttribute("blockNo",blockNo);
			request.setAttribute("start",start);
			request.setAttribute("end",end);
			}
			
			MvcUtil.forword("board/list", request, response);
		}
		
		
	}
}
