package com.douzone.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String actionName = request.getParameter("a");
		
		ActionFactory af = new UserActionFactory();
		Action action = af.getAction(actionName);
		action.execute(request,response);
//		if ("joinform".equals(action)) {
//			MvcUtil.forword("/WEB-INF/views/user/joinform.jsp", request, response);
//		} else if ("join".equals(action)) {
//			MvcUtil.forword("/WEB-INF/views/user/joinsuccess.jsp", request, response);
//		} else if ("login".equals(action)) {
//			MvcUtil.forword("/WEB-INF/views/user/loginform.jsp", request, response);
//		} else {
//			MvcUtil.redirect("mysite02", request, response);
//		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
