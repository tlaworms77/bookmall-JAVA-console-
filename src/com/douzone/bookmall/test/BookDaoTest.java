package com.douzone.bookmall.test;

import java.sql.SQLException;
import java.util.List;

import com.douzone.bookmall.dao.BookDao;
import com.douzone.bookmall.dao.CategoryDao;
import com.douzone.bookmall.vo.BookVo;

public class BookDaoTest {
	public static int test_CNT = 0;

	public static void main(String[] args) {
		insertBook("불친절한 SQL", 43000, "컴퓨터/IT");
		insertBook("친절한 SQL", 45000, "예술");
		insertBook("토비 spring", 90000, "novel");
		getListBook();
	}

	public static void getListBook() {
		List<BookVo> list = new BookDao().getBookList();
		System.out.println("BookList");
		System.out.println();
		for (BookVo bVo : list) {
			String categoryName = new CategoryDao().getCategoryName(bVo.getCategoryNo());
			System.out.println(bVo + ", (category:" + categoryName + ")");
		}
		System.out.println("==========================================================================");
	}

	public static void insertBook(String name, int price, String category) {

		System.out.println("======Book Insert======");
		boolean result = new BookDao().insertBook(name, price, category);
		if (result) {
			System.out.println("[" + name + "] 추가 성공");
		} else {
			System.out.println("[" + name + "] 추가 실패");
		}
	}

}
