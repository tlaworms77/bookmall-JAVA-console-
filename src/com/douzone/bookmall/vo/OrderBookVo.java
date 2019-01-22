package com.douzone.bookmall.vo;

public class OrderBookVo {
	private int count;
	private long bookNo;
	private long orderNo;
	
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return orderNo + ". 도서번호(" + bookNo + "), 주문수량(" + count + "권)";
	}

	
}
