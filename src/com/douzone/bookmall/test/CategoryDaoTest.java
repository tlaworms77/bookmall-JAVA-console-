package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.CategoryDao;
import com.douzone.bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		insertCategory("novel");
		insertCategory("컴퓨터/IT");
		insertCategory("예술");
		getCategoryList();
	}

	public static void getCategoryList() {
		List<CategoryVo> list = new CategoryDao().getCategoryList();
		System.out.println("CategoryList");
		System.out.println();
		for(CategoryVo cVo : list) {
			System.out.println(cVo);
		}
		System.out.println("==========================================================================");
	}

	@SuppressWarnings("unused")
	public static void insertCategory(String categoryName) {
		boolean result = new CategoryDao().insertCategory(categoryName);
		System.out.println("======TestInsertCategory======");
		if(result)
			System.out.println("[" + categoryName + "] 추가성공");
		else {
			System.out.println("항목 추가 실패");
		}
	}

}