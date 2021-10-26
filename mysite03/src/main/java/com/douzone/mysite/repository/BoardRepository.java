package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
		public List<BoardVo> findAll() {
			return sqlSession.selectList("board.findAll");	
	}
	
	public BoardVo findWhere(String title, String regDate) {
		Map<String, String> map = new HashMap<>();
		map.put("title", title);
		map.put("regDate", regDate);
		return  sqlSession.selectOne("board.findWhere", map);	
	}
	
	public boolean insert(BoardVo vo) {
		int count = sqlSession.delete("board.insert", vo);
		return count == 1;
	}

	public boolean update(BoardVo vo) {
		int count = sqlSession.delete("board.update", vo);
		return count == 1;
	}
	
	public BoardVo findByNo(Long no) {
		return sqlSession.selectOne( "board.findByNo", no );
	}

	public boolean delete(BoardVo vo) {
		int count = sqlSession.delete("board.delete", vo);
		return count == 1;
	}

	public BoardVo findWhe(String title, String contents) {
		Map<String, String> map = new HashMap<>();
		map.put("title", title);
		map.put("contents", contents);
		
		return sqlSession.selectOne( "board.findByWhe", map );
	}
	
	  public int updateHit(Long no) { // 조회수 올려주는 코드
		  return sqlSession.update( "board.updateHit", no );
	  }
}
