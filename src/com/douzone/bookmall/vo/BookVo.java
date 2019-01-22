package com.douzone.bookmall.vo;

public class BookVo {
	private long bookNo;
	private String bookTitle;
	private int bookPrice;
	private long categoryNo;
	
	public long getBookNo() {
		return bookNo;
	}

	public void setBookNo(long bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}
	
	public long getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(long categoryNo) {
		this.categoryNo = categoryNo;
	}

	@Override
	public String toString() {
		return bookNo + ". " + bookTitle + ", " + bookPrice + "원";
	}
	
}
