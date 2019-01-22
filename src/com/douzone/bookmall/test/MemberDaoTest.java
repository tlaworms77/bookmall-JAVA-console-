package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.MemberDao;
import com.douzone.bookmall.vo.MemberVo;

public class MemberDaoTest {
	public static int Test_CNT = 0; 
	public static void main(String[] args) {
		insertMember();
		insertMember();
		getMemberList();
	}

	public static void getMemberList() {
		List<MemberVo> list = new MemberDao().getMemberList();
		System.out.println("MemberList");
		System.out.println();
		for(MemberVo mVo : list) {
			System.out.println(mVo);
		}
		System.out.println("==========================================================================");
	}

	@SuppressWarnings("unused")
	public static void insertMember() {
		//String testMember1 = "insert into member values(null,'chris','000-0000-0000','chris@test.com','1234','부산광역시 연제구 과정로 ㅁㄴㅇ')";
		//String testMember2 = "insert into member values(null,'mare','123-1234-1234','mare@test.com','1234','부산광역시 연제구 과정로 9999가길')";
		
		System.out.println("=====TestMemberInsert=====");
		
		String memberName = "";
		String memberTel = "";
		String memberEmail = "";
		String memberPw = "";
		String memberAddress = "";
		
		MemberVo mVo = new MemberVo();
		if(Test_CNT == 0) {
			memberName = "chris";
			memberTel = "000-0000-0000";
			memberEmail = "chris@test.com";
			memberPw = "1234";
			memberAddress = "부산광역시 연제구 과정로";
			Test_CNT ++;
		} else {
			memberName = "mare";
			memberTel = "123-1234-1234";
			memberEmail = "mare@test.com";
			memberPw = "1234";
			memberAddress = "부산광역시 연제구 과정로 11111번길";
		}
		
		mVo.setMemberName(memberName);
		mVo.setMemberTel(memberTel);
		mVo.setMemberEmail(memberEmail);
		mVo.setMemberPw(memberPw);
		mVo.setMemberAddress(memberAddress);
		
		boolean result = new MemberDao().insertMember(mVo);
		if(result)
			System.out.println("멤버등록 성공");
		else {
			System.out.println("멤버등록 실패");
		}
	}
	
	
	
}
