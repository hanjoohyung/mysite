package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.douzone.mysite.vo.BoardVo;

public class BoardDao {
	public List<BoardVo> findAll(int begin) {
		
		List<BoardVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "select b.no,name,title,contents,hit,reg_date,group_no,order_no,depth,user_no	"
					+ "	from user a, board b	"
					+ "    where a.no = b.user_no order by reg_date desc limit ?,5";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)
			pstmt.setInt(1, begin);
			
			// 5. SQL 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String title = rs.getString(3);
				String contents = rs.getString(4);
				int hit = rs.getInt(5);
				String reg_date = rs.getString(6);
				int group_no = rs.getInt(7);
				int order_no = rs.getInt(8);
				int depth = rs.getInt(9);
				int user_no = rs.getInt(10);
				// System.out.println("테스트 " + reg_date);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setReg_date(reg_date);
				vo.setGroup_no(group_no);
				vo.setOrder_no(order_no);
				vo.setDepth(depth);
				vo.setUser_no(user_no);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
			e.printStackTrace();
		} finally {
			// clean up
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public Integer pageCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "select count(*) from board";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)
			
			// 5. SQL 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
			count = rs.getInt(1);
			}
		} catch (SQLException e) {
				System.out.println("error:" + e);
				e.printStackTrace();
			} finally {
				// clean up
				try {
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return count;

	}
	public BoardVo findWhere(String title, String reg_date) {
		BoardVo vo = new BoardVo();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "select *" + "  	from board" + "     where title = ? and reg_date=? ";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)
			pstmt.setString(1, title);
			pstmt.setString(2, reg_date);
		
			// 5. SQL 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {		
				Long no = rs.getLong(1);
				String titles = rs.getString(2);
				String contents = rs.getString(3);
				int hit = rs.getInt(4);
				String reg_dates = rs.getString(5);
				
				vo.setNo(no);
				vo.setTitle(titles);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setReg_date(reg_dates);
				
				return vo;
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
			e.printStackTrace();
		} finally {
			// clean up
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}
	

	public boolean insert(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "insert " + "into board " + "values(null, ?, ?, 0, now(), 1, 1, 1, 1)";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			
			// 5. SQL 실행
			int count = pstmt.executeUpdate();

			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// clean up
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}

	public boolean update(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = " update board 	" + "    set title=?, contents=?" + "  where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());
			
			int count = pstmt.executeUpdate();
			
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public BoardVo findByNo(Long no) {
		BoardVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = " select no, title, contents, hit, reg_date, group_no, order_no, depth, user_no "
					+ "   from user " + "  where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new BoardVo();
				
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContents(rs.getString(3));
				vo.setHit(rs.getInt(4));
				vo.setReg_date(rs.getString(5));
				vo.setGroup_no(rs.getInt(6));
				vo.setOrder_no(rs.getInt(7));
				vo.setDepth(rs.getInt(8));
				vo.setUser_no(rs.getInt(9));
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}

	public boolean delete(BoardVo vo) {

		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL 준비
			String sql = "delete from board where no=? and title=?";
			pstmt = conn.prepareStatement(sql);

			// 4. binding
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getTitle());

			// 5. SQL 실행
			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// clean up
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public BoardVo findWhe(String title, String contents) {
		BoardVo vo = new BoardVo();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "select *" + "  	from board" + "     where title = ? and contents = ?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)
			pstmt.setString(1, title);
			pstmt.setString(2, contents);

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {	
				Long no = rs.getLong(1);
				String titles = rs.getString(2);
				String contentss = rs.getString(3);	
				int hit = rs.getInt(4);	
				
				vo.setNo(no);
				vo.setTitle(titles);
				vo.setContents(contentss);				
				vo.setHit(hit);
				
				return vo;
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
			e.printStackTrace();
		} finally {
			// clean up
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}
	
	  public BoardVo updateHit(Long no) { // 조회수 올려주는 코드
	       	BoardVo vo = new BoardVo();
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	       
	        try {
	        	conn = getConnection();
	        	
	        	String sql = "update board set hit=hit+1 where no=?";
	            pstmt = conn.prepareStatement(sql);	  
	            
	            pstmt.setLong(1, no);	       
	            
	            pstmt.executeUpdate(); // 실행 -> 조회수 1증가
	        } catch (Exception e) {
	        	System.out.println("error:" + e);
				e.printStackTrace();
	        }finally {
				// clean up
				try {
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	       return vo;
	    }

	 
}
