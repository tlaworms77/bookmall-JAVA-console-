package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.BookDao;
import com.douzone.bookmall.dao.CartDao;
import com.douzone.bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		insertCart(2,16,7);
		insertCart(4,18,8);
		getCartList();
	}

	public static void getCartList() {
		List<CartVo> list = new CartDao().getCartList();
		System.out.println("Cart List");
		System.out.println();
		int cnt = 0;
		for(CartVo cVo : list) {
			String strtmp[] = new BookDao().getBookNameAndPrice(cVo.getBookNo());
			String bookName = strtmp[0];
			int bookPrice = Integer.parseInt(strtmp[1]);
			int bookCount = cVo.getGoodsCount();
			System.out.print((++cnt) + ". ");
			System.out.println(bookName + ", "
							 	+ bookPrice + "원, "
							 	+ cVo + ", 합계: " 
							 	+ (bookCount*bookPrice) + "원");
		}
		System.out.println("==========================================================================");
	}

	public static void insertCart(long memberNo, long bookNo, int cnt) {
		boolean result = new CartDao().insertCart(memberNo, bookNo, cnt);
		System.out.println("======Insert Cart======");
		if(result) {
			System.out.println("카트 추가 성공");
		} else {
			System.out.println("카트 추가 실패");
		}
		
	}

}
