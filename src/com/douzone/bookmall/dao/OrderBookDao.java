package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.OrderBookVo;

public class OrderBookDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<OrderBookVo> getOrderBookList() {
		List<OrderBookVo> list = new ArrayList<OrderBookVo>();
		
		try {
			conn = getConnection();
			String sql = "select * from order_book";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderBookVo obVo = new OrderBookVo();
				obVo.setBookNo(rs.getLong(1));
				obVo.setOrderNo(rs.getLong(2));
				obVo.setCount(rs.getInt(3));
				list.add(obVo);
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
		
		return list;
	}
	
	public boolean insertOrderBook(long bookNo, long orderNo, int cnt){
		boolean result = false;
		try {
			
			conn = getConnection();
			String sql = "insert into order_book values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, bookNo);
			pstmt.setLong(2, orderNo);
			pstmt.setInt(3, cnt);
			
			int count = pstmt.executeUpdate();
			
			result = (count == 1);
			
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
