package com.douzone.mysite.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	GuestbookService guestbookService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GuestbookVo> list = guestbookService.getMessageList();
		model.addAttribute("list", list);
		return "guestbook/index";
	}

	@RequestMapping("/spa")
	public String spa() {
		return "guestbook/index-spa";
	}
	
	@ResponseBody
	@RequestMapping("/spa/delete/{no}")
	public JsonResult ex3(@PathVariable("no") Long no, String password) {
		// result = guestbookService.deleteMessage(no,password)를 사용하여 삭제 작업
		Long data = 0L;
	
		// 1. 삭제가 되지않은 경우 
		data = -1L;
		
		// 2. 삭제 된 경우
		data = no;
		
		return JsonResult.success(data);
	}
	@ResponseBody
	@RequestMapping("/spa/list")
	public JsonResult ex2(@RequestParam(value="sn", required=true, defaultValue="-1")Long no) {
		// vo = guestbookService.findAll(no)를 사용하여 리스트 가져오기
		List<GuestbookVo> list = guestbookService.getMessageList();
		
		return JsonResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping("/spa/add")
	public JsonResult ex1(@RequestBody GuestbookVo vo) {
		// vo =gusetbookService.addMessage(vo)를 사용하여 등록 작업
		guestbookService.addMessage(vo);
		
		return JsonResult.success(vo);
	}
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(GuestbookVo vo) {
		guestbookService.addMessage(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		return "guestbook/delete";
	}

	@RequestMapping(value="/delete/{no}", method=RequestMethod.POST)
	public String delete(@PathVariable("no") Long no, @RequestParam(value="password", required=true, defaultValue="") String password) {
		guestbookService.deleteMessage(no, password);
		return "redirect:/guestbook";
	}
}