package com.douzone.mysite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@Auth
	@RequestMapping({"","/pageNo/{pageNo}/blockNo/{blockNo}"})
	public String list(@PathVariable("pageNo") int pageNo, @PathVariable("blockNo") int blockNo, Model model) {
		
		Map<String, Object> map = boardService.getBoardList(pageNo, blockNo);
		model.addAttribute("map", map);
		model.addAttribute("pageNo",pageNo);
		model.addAttribute("blockNo", blockNo);
		return "board/list";
	}
	@Auth
	@RequestMapping(value = "/write", method=RequestMethod.GET)
	public String write(Model model) {
		
		return "board/write";
	}
	@Auth
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@AuthUser UserVo authUser, BoardVo vo) {
		vo.setUserNo(authUser.getNo());
		boardService.addBoard(vo);
		
		return "redirect:/board/pageNo/1/blockNo/1";
	}
	@Auth
	@RequestMapping(value = "/rewrite", method=RequestMethod.GET)
	public String rewrite(Model model) {
		return "board/rewrite";
	}
	@Auth
	@RequestMapping(value="/readd", method=RequestMethod.POST)
	public String readd(BoardVo vo) {
		boardService.addBoard(vo);
		return "redirect:/board/pageNo/1/blockNo/1";
	}
	@Auth
	@RequestMapping(value = "/view/{boardVo.no}/{boardVo.title}/{boardVo.contents}/{boardVo.regDate}", method=RequestMethod.GET)
	public String view(@PathVariable("boardVo.no") Long no, @PathVariable("boardVo.title") String title, @PathVariable("boardVo.contents") String contents, 
		@PathVariable("boardVo.regDate") String regDate ,Model model) {
		model.addAttribute("no", no);
		model.addAttribute("title", title);
		model.addAttribute("contents", contents);
		
		BoardVo boardVo = boardService.viewBoard(no);
		
		return "board/view";
	}
	@Auth
	@RequestMapping(value="/modify/{no}/{title}/{contents}", method=RequestMethod.GET)
	public String modify(@PathVariable("no") Long no, @PathVariable("title") String title,
			@PathVariable("contents") String contents, Model model,BoardVo vo, @AuthUser UserVo authUser) {
		if(vo.getUserNo() != authUser.getNo()) {
			return "redirect:/board/pageNo/1/blockNo/1";
		}
		
		model.addAttribute("no", no);
		model.addAttribute("title", title);
		model.addAttribute("contents", contents);
		
		return "board/modify";
	}
	@Auth
	@RequestMapping(value="/update1/{no}", method=RequestMethod.POST)
	public String update1(BoardVo boardVo) {
		boardVo.setNo(boardVo.getNo());
		boardService.updateBoard(boardVo);
		
		boardVo.setTitle(boardVo.getTitle());
		boardVo.setContents(boardVo.getContents());
		
		return "redirect:/board/pageNo/1/blockNo/1";
	}
	@Auth
	@RequestMapping(value="/delete1/{boardVo.no}/{boardVo.title}")
	public String delete1(@PathVariable("boardVo.no") Long no, @PathVariable("boardVo.title") String title , Model model,
			BoardVo vo,@AuthUser UserVo authUser) {
		if(vo.getUserNo() != authUser.getNo()) {
			return "redirect:/board/pageNo/1/blockNo/1";
		}
		
		boardService.deleteBoard(no, title);
	
		return "redirect:/board/pageNo/1/blockNo/1";
	}
}
