package com.douzone.bookmall.vo;

public class MemberVo {
	private long memberNo;
	private String memberName;
	private String memberTel;
	private String memberEmail;
	private String memberPw;
	private String memberAddress;
	
	public long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(long memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	
	@Override
	public String toString() {
		return memberNo + ". " + memberName + ", " + memberTel
				+ ", " + memberEmail + ", " + memberPw + ", " + memberAddress;
	}
	
}
