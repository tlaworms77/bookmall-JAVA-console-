package com.douzone.bookmall.main;

import com.douzone.bookmall.test.BookDaoTest;
import com.douzone.bookmall.test.CartDaoTest;
import com.douzone.bookmall.test.CategoryDaoTest;
import com.douzone.bookmall.test.MemberDaoTest;
import com.douzone.bookmall.test.OderBookDaoTest;
import com.douzone.bookmall.test.OrderDaoTest;

public class BookMall {

	public static void main(String[] args) {
		// insert하는 코드들
		unitInsert();
		
		getList();
		
	}

	private static void getList() {
		System.out.println("==========================================================================");
		MemberDaoTest.getMemberList();
		CategoryDaoTest.getCategoryList();
		BookDaoTest.getListBook();
		CartDaoTest.getCartList();
		OrderDaoTest.getOrderList();
		OderBookDaoTest.getOderBookList();
	}

	private static void unitInsert() {
		System.out.println("====== unitInsert ... =======");
		MemberDaoTest.insertMember();
		MemberDaoTest.insertMember();

		CategoryDaoTest.insertCategory("소설");
		CategoryDaoTest.insertCategory("수필");
		CategoryDaoTest.insertCategory("컴퓨터/IT");
		CategoryDaoTest.insertCategory("예술");

		BookDaoTest.insertBook("이것이 자바다", 20000, "컴퓨터/IT");
		BookDaoTest.insertBook("서양 미술사", 13000, "예술");
		BookDaoTest.insertBook("안드로이드 완전정복", 40000, "컴퓨터/IT");
		BookDaoTest.insertBook("아큐정전", 10000, "소설");
		
		CartDaoTest.insertCart(1, 1, 2); // member_no, book_no, count
		CartDaoTest.insertCart(1, 2, 1);

		OrderDaoTest.insertOrder(2); // member_no
		
		System.out.println("====== init finish =======");

	}

}
