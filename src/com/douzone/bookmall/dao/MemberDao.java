package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.MemberVo;

public class MemberDao {
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public List<MemberVo> getMemberList() {
		List<MemberVo> list = new ArrayList<MemberVo>();

		Connection conn = null;
		
		try {
			
			conn = getConnection();
			
			String sql = "select * from member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			boolean valueIsTrue = false;
			
			while(rs.next()) {
				valueIsTrue = true;
				long memberNo = rs.getLong(1);
				String memberName = rs.getString(2);
				String memberTel = rs.getString(3);
				String memberEmail = rs.getString(4);
				String memberPw = rs.getString(5);
				String memberAddress = rs.getString(6);
				
				MemberVo mVo = new MemberVo();
				mVo.setMemberNo(memberNo);
				mVo.setMemberName(memberName);
				mVo.setMemberTel(memberTel);
				mVo.setMemberEmail(memberEmail);
				mVo.setMemberPw(memberPw);
				mVo.setMemberAddress(memberAddress);
				
				list.add(mVo);
				
			}
			if(!valueIsTrue) {
				System.out.println("회원이 존재하지 않습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { // resource release...
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
	
	public boolean insertMember(MemberVo mVo) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = getConnection();
			
			String sql = "insert "
						+ "into member "
						+ "values(null, ?, ?, ?, password(?), ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getMemberName());
			pstmt.setString(2, mVo.getMemberTel());
			pstmt.setString(3, mVo.getMemberEmail());
			pstmt.setString(4, mVo.getMemberPw());
			pstmt.setString(5, mVo.getMemberAddress());
			int count = pstmt.executeUpdate();
			result = (count == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {	// Resource Release...
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
