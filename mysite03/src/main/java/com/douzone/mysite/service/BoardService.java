package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PagingVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;

	public Map<String, Object> getBoardList(int pageNo, int blockNo) {
		Map<String, Object> map = new HashMap<>();
		
		int begin = 0; // 각 페이지에 보일 시작 게시물 no
		int pageCount ; // 페이지 개수
		int viewNo = 5; // 페이지에 보이는 게시물 개수
		int count = 0; // 총 게시물 개수
		count = boardRepository.pageCount(count);
		System.out.println(count);
		pageCount = count/viewNo;
		 
		begin = (pageNo-1)*5;
		List<BoardVo> list = boardRepository.findAll(begin);
		map.put("list", list);
		map.put("count", count);
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
		
		map.put("pageNo", pageNo);
		map.put("pageCount", pageCount);	
		map.put("blockNo", blockNo);
		map.put("start", start);
		map.put("end", end);
		
		} else {
		String end = Integer.toString(blockNo*5);
			
		map.put("pageNo", pageNo);
		map.put("pageCount", pageCount);	
		map.put("blockNo", blockNo);
		map.put("start", start);
		map.put("end", end);
		
		}
		
		
		return map;
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

	public boolean readdBoard(BoardVo vo) {
		return boardRepository.reinsert(vo);
		
	}
	public boolean reupdate(BoardVo vo) {
		return boardRepository.reupdate(vo);
	}
	
	public BoardVo serach(Long no) {
		return boardRepository.findByNo(no);
	}
	
}
