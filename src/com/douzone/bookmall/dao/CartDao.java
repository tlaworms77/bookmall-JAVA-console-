package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.BookVo;
import com.douzone.bookmall.vo.CartVo;

public class CartDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public List<CartVo> getCartList() {
		List<CartVo> list = new ArrayList<CartVo>();

		try {
			conn = getConnection();
			String sql = "select member_no, book_no, cart_cnt from cart";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CartVo cVo = new CartVo();
				cVo.setOrderNo(rs.getLong(1));
				cVo.setBookNo(rs.getLong(2));
				cVo.setGoodsCount(rs.getInt(3));
				list.add(cVo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public boolean insertCart(long memberNo, long bookNo, int cnt) {
		boolean result = false;
		try {
			conn = getConnection();
			String sql = "insert into cart values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, memberNo);
			pstmt.setLong(2, bookNo);
			pstmt.setInt(3, cnt);
			int count = pstmt.executeUpdate();

			result = (count == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
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
