package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.GuestbookRepositroyException;
import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	@Autowired
	private DataSource dataSource;
	
	public List<GuestbookVo> findAll() throws GuestbookRepositroyException {
	
		List<GuestbookVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();

			// 3. SQL 준비
			String sql = "select *" + "  	from guestbook" + "      order by no asc";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String message = rs.getString(4);
				String reg_date = rs.getString(5);
				// System.out.println("테스트 " + reg_date);

				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setPassword(password);
				vo.setMessage(message);
				vo.setReg_date(reg_date);

				result.add(vo);
			}

		} catch (SQLException e) {
			throw new GuestbookRepositroyException(e.toString());
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

	public boolean insert(GuestbookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			// 3. SQL 준비
			String sql = "insert " + "into guestbook " + "values(null, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());

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

	public boolean delete(GuestbookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			
			conn = dataSource.getConnection();

			// 3. SQL 준비
			String sql = "delete from guestbook where no=? and password=?";
			pstmt = conn.prepareStatement(sql);

			// 4. binding
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());

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
}
