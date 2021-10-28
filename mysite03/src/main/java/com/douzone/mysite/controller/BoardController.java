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
	@RequestMapping(value = "/view/{boardVo.no}", method=RequestMethod.GET)
	public String view(@PathVariable("boardVo.no") Long no, Model model, BoardVo vo) {
		vo= boardService.serach(no);
		
		BoardVo boardVo = boardService.viewBoard(no);
		
		model.addAttribute("boardVo", boardVo);
		return "board/view";
	}
	@Auth
	@RequestMapping(value="/modify/{boardVo.no}", method=RequestMethod.GET)
	public String modify(@PathVariable("boardVo.no") Long no, Model model, @AuthUser UserVo authUser) {
		BoardVo vo= boardService.serach(no);
		
		if(vo.getUserNo() != authUser.getNo()) {
			return "redirect:/board/pageNo/1/blockNo/1";
		}
		BoardVo boardVo = boardService.viewBoard(no);
		
		model.addAttribute("vo", vo);
		
		return "board/modify";
	}
	@Auth
	@RequestMapping(value="/update1/{boardVo.no}", method=RequestMethod.POST)
	public String update1( Model model, @PathVariable("boardVo.no") Long no) {
		BoardVo boardVo = new BoardVo();
		
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
		
		vo= boardService.serach(no);
		
		if(authUser.getNo() != vo.getUserNo()) {
			return "redirect:/board/pageNo/1/blockNo/1";
		}
		
		boardService.deleteBoard(no, title);
	 
		return "redirect:/board/pageNo/1/blockNo/1";
	}
	@Auth
	@RequestMapping(value="/rewrite/{boardVo.no}", method=RequestMethod.GET)
	public String rewrite(@PathVariable("boardVo.no") Long no, BoardVo vo, @AuthUser UserVo authUser ,Model model) {
		vo= boardService.serach(no);
		
		if(vo.getUserNo() != authUser.getNo()) {
			return "redirect:/board/pageNo/1/blockNo/1";
		}
		model.addAttribute("vo", vo);
		
		return "board/readd";
	}
	@Auth
	@RequestMapping(value="/readd/{boardVo.no}", method=RequestMethod.POST)
	public String readd(Model model,BoardVo vo,@AuthUser UserVo authUser,@PathVariable("boardVo.no") Long no) {
		
		vo= boardService.serach(no);
		
		if(authUser.getNo() != vo.getUserNo()) {
			return "redirect:/board/pageNo/1/blockNo/1";
		}
		
		boardService.readdBoard(vo);
		model.addAttribute("vo",vo);
		
		return "redirect:/board/pageNo/1/blockNo/1";
	}
}
