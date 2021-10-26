package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.mysite.vo.UserVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;

	public List<BoardVo> getBoardList() {
		return boardRepository.findAll();
	}

	public boolean addBoard(BoardVo vo) {
		return boardRepository.insert(vo);
	}

	public BoardVo viewBoard(Long no) {
		BoardVo boardVo = boardRepository.findByNo(no);
		
		if( boardVo != null ) {
			boardRepository.updateHit(no);
		}	
		return boardVo;
	}

	public boolean updateBoard(BoardVo vo) {
		return boardRepository.update(vo);
	}

	public boolean deleteBoard(Long no, String title) {
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setTitle(title);	
		
		return boardRepository.delete(vo);
	}
	
	public boolean getBoard(BoardVo boardVo) {
		return boardRepository.update(boardVo);
	}
	
}
