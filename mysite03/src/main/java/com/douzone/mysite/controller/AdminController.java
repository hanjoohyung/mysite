package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Controller
@RequestMapping("/admin")
@Auth(role="ADMIN")
public class AdminController {
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	SiteService siteService;
	
	@RequestMapping("")
	public String main() {
		return "admin/main";
	}
	@RequestMapping("/main/update")
	public String main(SiteVo vo) {
		siteService.update(vo);
		
		return "admin/main";
	}
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
}
