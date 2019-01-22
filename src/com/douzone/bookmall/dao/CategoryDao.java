package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.CategoryVo;

public class CategoryDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<CategoryVo> getCategoryList() {
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		
		try {
			conn = getConnection();
			String sql = "select * from category";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CategoryVo cVo = new CategoryVo();
				cVo.setCategoryNo(rs.getLong(1));
				cVo.setCategoryName(rs.getString(2));
				list.add(cVo);
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
	
	public boolean insertCategory(String name){
		boolean result = false;
		try {
			conn = getConnection();
			String sql = "insert into category values(null, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
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

	public String getCategoryName(long categoryNo) {
		String categoryName = null;
		try {
			conn = getConnection();
			String sql = "select category_name from category where category_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, categoryNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				categoryName = rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return categoryName;
		
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
