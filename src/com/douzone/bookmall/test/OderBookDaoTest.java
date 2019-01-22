package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.BookDao;
import com.douzone.bookmall.dao.OrderBookDao;
import com.douzone.bookmall.vo.OrderBookVo;

public class OderBookDaoTest {

	public static void main(String[] args) {

		insertOrderBook(2, 16, 2); // count, book_no, order_no
		insertOrderBook(1, 17, 2);
		getOderBookList();
	}

	public static void insertOrderBook(long bookNo, long orderNo, int cnt) {
		boolean result = new OrderBookDao().insertOrderBook(bookNo, orderNo, cnt);
		System.out.println("======Insert Order_Book======");
		if(result) {
			System.out.println("주문도서 추가 성공");
		}else {
			System.out.println("주문도서 추가 실패");
		}
	}

	public static void getOderBookList() {
		List<OrderBookVo> list = new OrderBookDao().getOrderBookList();
		System.out.println("Order_Book List");
		System.out.println();
		for (OrderBookVo obVo : list) {
			long bookNo = obVo.getBookNo();
			String strtmp[] = new BookDao().getBookNameAndPrice(bookNo);
			String bookName = strtmp[0];
			
			System.out.println(obVo.getBookNo() + ". " + bookName + ", " + obVo.getCount() + "권");
		}
		System.out.println("==========================================================================");
	}

}
