package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.OrderVo;

public class OrderDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<OrderVo> getOrderList() {
		List<OrderVo> list = new ArrayList<OrderVo>();
		
		try {
			conn = getConnection();
			String sql = "select * from orders";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderVo oVo = new OrderVo();
				oVo.setOrderNo(rs.getLong(1));
				oVo.setDefaultOrder(rs.getString(2));
				oVo.setOrderPrice(rs.getLong(3));
				oVo.setOrderAddress(rs.getString(4));
				oVo.setMemberNo(rs.getLong(5));
				list.add(oVo);
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
	
	public boolean reUpdateOrder(int total, long orderNo) {
		boolean result = false;
		try {
			
			conn = getConnection();
			String sql = "update orders set order_price = ? where order_no = ?";
			pstmt = conn.prepareStatement(sql);
		
	    	pstmt.setInt(1, total);
			pstmt.setLong(2, orderNo);
			
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
	
	public long insertOrder(long memberNo){
		long orderNo = 0;
		try {
			String[] strArr = searchMemberInfo(memberNo);
			
			String defaultOrder = strArr[0];
			String orderAddress = strArr[1];
			
			conn = getConnection();
			String sql = "insert into orders values(null, ?, 0, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, defaultOrder);
			pstmt.setString(2, orderAddress);
			pstmt.setLong(3, memberNo);
			
			pstmt.executeUpdate();
			
			sql = "select order_no from orders where default_order = ? and order_address = ? and member_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, defaultOrder);
			pstmt.setString(2, orderAddress);
			pstmt.setLong(3, memberNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				orderNo = rs.getLong(1);
	
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
		return orderNo;
	}

	public int getTotalBill(long orderNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		
		try {
			conn = getConnection();
			String sql = "select sum(book_cnt * book_price) from order_book a, book b where a.book_no = b.book_no and a.order_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, orderNo);
		
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			
				total = (int)rs.getLong(1);;
				
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
		
		return total;
		
	}

	private String[] searchMemberInfo(long memberNo) {
		try {
			conn = getConnection();
			
			String sql = "select concat(a.member_name, '/', a.member_email), member_address from member a where member_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, memberNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String[] strArr= new String[2];
				strArr[0] = rs.getString(1);
				strArr[1] = rs.getString(2);
				return strArr;
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
		
		return null;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
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
