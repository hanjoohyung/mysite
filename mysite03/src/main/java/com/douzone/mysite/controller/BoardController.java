package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

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
	@RequestMapping(value = "/write", method=RequestMethod.GET)
	public String write(Model model) {
		return "board/write";
	}
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(BoardVo vo) {
		boardService.addBoard(vo);
		return "redirect:/board";
	}
	@RequestMapping(value = "/view/{boardVo.no}/{boardVo.title}/{boardVo.contents}/{boardVo.regDate}", method=RequestMethod.GET)
	public String view(@PathVariable("boardVo.no") Long no, @PathVariable("boardVo.title") String title, @PathVariable("boardVo.contents") String contents, 
		@PathVariable("boardVo.regDate") String regDate ,Model model) {
		BoardVo boardVo = boardService.viewBoard(no);
		
		return "board/view";
	}
	@Auth
	@RequestMapping(value="/modify/{no+1}", method=RequestMethod.GET)
	public String modify(@RequestParam(value="no", required=true, defaultValue="") Long no, Model model) {
		
		model.addAttribute("no", no);
		
		return "board/modify";
	}	
	@Auth
	@RequestMapping(value="/update1", method=RequestMethod.POST)
	public String update1(BoardVo boardVo) {
		boardVo.setNo(boardVo.getNo());
		boardService.updateBoard(boardVo);
		
		boardVo.setTitle(boardVo.getTitle());
		boardVo.setContents(boardVo.getContents());
		
		return "redirect:/board";
	}
	
	@RequestMapping(value="/delete1/{boardVo.no}/{boardVo.title}")
	public String delete1(@PathVariable("boardVo.no") Long no, @PathVariable("boardVo.title") String title , Model model) {
		boardService.deleteBoard(no, title);
	
		return "redirect:/board";
	}
}
