package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String list(Model model) {
		List<BoardVo> list = boardService.getBoardList();
		model.addAttribute("list", list);		
		return "board/list";
	}
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(Model model) {
		return "board/write";
	}
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(BoardVo vo) {
		boardService.addBoard(vo);
		return "redirect:/board";
	}
}
