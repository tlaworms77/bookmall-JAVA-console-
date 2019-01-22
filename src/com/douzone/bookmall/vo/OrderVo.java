package com.douzone.bookmall.vo;

public class OrderVo {
	private long orderNo;
	private String defaultOrder;
	private long orderPrice;
	private String orderAddress;
	private long memberNo;

	public long getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(long memberNo) {
		this.memberNo = memberNo;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public String getDefaultOrder() {
		return defaultOrder;
	}

	public void setDefaultOrder(String defaultOrder) {
		this.defaultOrder = defaultOrder;
	}

	public long getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(long orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	@Override
	public String toString() {
		return orderNo + ". " + defaultOrder + ", " + orderPrice + ", " + orderAddress;
	}

}
