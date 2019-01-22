package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.BookVo;
import com.douzone.bookmall.vo.CategoryVo;

public class BookDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<BookVo> getBookList() {
		List<BookVo> list = new ArrayList<BookVo>();
		
		try {
			conn = getConnection();
			String sql = "select * from book";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookVo bVo = new BookVo();
				bVo.setBookNo(rs.getLong(1));
				bVo.setBookTitle(rs.getString(2));
				bVo.setBookPrice(rs.getInt(3));
				bVo.setCategoryNo(rs.getLong(4));
				list.add(bVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public String[] getBookNameAndPrice(long bookNo) {
		String strtmp[] = new String[2];
		try {
			conn = getConnection();
			String sql = "select book_name, book_price from book where book_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bookNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				strtmp[0] = rs.getString(1);
				strtmp[1] = rs.getInt(2) + "";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return strtmp;
		
	}
	
	public boolean insertBook(String name, int price, String categoryName){
		boolean result = false;
		try {
			long categoryNo = searchCategoryNo(categoryName);
			
			conn = getConnection();
			String sql = "insert into book values(null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, price);
			pstmt.setLong(3, categoryNo);
			int count = pstmt.executeUpdate();
			
			result = (count == 1);
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	private long searchCategoryNo(String categoryName) {
		long resultNo = 0;
		boolean isCategoryName = false;

		try {
			// 1. connection 연결
			conn = getConnection();
			// 2. 미리 sql 로드
			String sql = "select category_no from category where category_name = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, categoryName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				isCategoryName = true;
				resultNo = rs.getLong(1);
			}
			
			if(!isCategoryName) {
				System.out.println("해당 카테고리는 존재하지 않습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return resultNo;
	}

	private Connection getConnection() throws SQLException {
		try {
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 연결하기(Connection 객체 얻어오기)
			// jdbc:mysql = http://
			String url = "jdbc:mysql://localhost:3306/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

		} catch (ClassNotFoundException e) {
			System.out.println("Driver loading fail : " + e);
		}
		return conn;
	}

}
