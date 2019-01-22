package com.douzone.bookmall.vo;

public class CartVo {
	private int goodsCount;
	private long bookNo;
	private long orderNo;
	
	public int getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
	public long getBookNo() {
		return bookNo;
	}
	public void setBookNo(long bookNo) {
		this.bookNo = bookNo;
	}
	public long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}
	
	@Override
	public String toString() {
		return goodsCount + "권";
	}
	
	
}
