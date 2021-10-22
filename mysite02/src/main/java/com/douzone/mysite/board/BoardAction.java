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
		List<BoardVo> list = new BoardDao().findAll();
		
		HttpSession session = request.getSession(true);
		
		if (session.getAttribute("authUser") == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('로그인 후 이용이 가능합니다.'); location.href='/mysite02" + "';</script>");
		} else {
			int blockNo; // 블록 개수
			int pageNo = 1; // 페이지 번호
			int pagerNo = 5; // 페이지 목록 개수
			int pageCount ;
			int viewNo = 5; // 페이지에 보이는 게시물 개수
			
			int count = new BoardDao().pageCount();
			
			request.setAttribute("list", list);
			request.setAttribute("count",count);
			
			if(count%5 == 0) {
				if(count < 25) {
				pageCount = count/viewNo;
				blockNo = pageCount/pagerNo+1;
				} else {
					pageCount = count/viewNo;
					blockNo = pageCount/pagerNo;
				}
			}	else {
				pageCount = (count/viewNo)+1;
				blockNo = (pageCount/pagerNo)+1;
			}
		
			PagingVo vo = new PagingVo();
			
			vo.setPageNo(pageNo);
			vo.setBlockNo(blockNo);
					
			String start = Integer.toString(blockNo*5-4);
			String end = Integer.toString(blockNo*5);
			
			request.setAttribute("blockNo",blockNo);
			request.setAttribute("start",start);
			request.setAttribute("end",end);
			System.out.println(blockNo);
			System.out.println(count);
			System.out.println(start);
			System.out.println(end);
			
			MvcUtil.forword("board/list", request, response);
		}
		
		
	}
}
