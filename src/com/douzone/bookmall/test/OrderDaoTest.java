package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.OrderDao;
import com.douzone.bookmall.vo.OrderVo;

public class OrderDaoTest {

	public static void main(String[] args) {
		insertOrder(7);
		getOrderList();
	}

	public static void insertOrder(long memberNo) {
		
		long orderNo = new OrderDao().insertOrder(memberNo);
		System.out.println("======Insert Order======");
		if(orderNo > 0) {
			System.out.println("주문 성공");
			
			/*OderBookDaoTest.insertOrderBook(1, 1, 1); // book_no, order_no, count 
			OderBookDaoTest.insertOrderBook(2, 1, 2);*/
			
			OderBookDaoTest.insertOrderBook(1, 1, 1); // book_no, order_no, count 
			OderBookDaoTest.insertOrderBook(2, 1, 1);
			OderBookDaoTest.insertOrderBook(4, 1, 2);
			
			int total = new OrderDao().getTotalBill(orderNo);
			boolean result = new OrderDao().reUpdateOrder(total, orderNo);
			
		}else {
			System.out.println("주문 실패");
		}
	}

	public static void getOrderList() {
		List<OrderVo> list = new OrderDao().getOrderList();
		System.out.println("Order List");
		System.out.println();
		for(OrderVo oVo : list) {
			System.out.println(oVo);
		}
		System.out.println("==========================================================================");
	}

}
