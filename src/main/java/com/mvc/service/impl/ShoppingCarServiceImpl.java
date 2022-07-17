package com.mvc.service.impl;

import java.util.List;

import com.mvc.dao.Cart;
import com.mvc.service.repository.ShoppingCarRepository;

public class ShoppingCarServiceImpl {

	ShoppingCarRepository ShoppingCarRepository = new ShoppingCarRepository();

	/**
	 * 取得全部資料
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Cart> getCart() throws Exception {
		return ShoppingCarRepository.getCart();
	}

	/**
	 * 取得指定資料
	 * 
	 * @param cart_number
	 * @return
	 * @throws Exception
	 */
	public Cart getCart_By_Number(String cart_number) throws Exception {
		return ShoppingCarRepository.getCart_By_Number(cart_number);
	}

	/**
	 * 取得指定資料_多筆
	 * 
	 * @param cart_number_list
	 * @return
	 * @throws Exception
	 */
	public List<Cart> getCart_By_Number_List(List cart_number_list) throws Exception {
		return ShoppingCarRepository.getCart_By_Number_List(cart_number_list);
	}

	/**
	 * 新增一筆資料
	 * 
	 * @param cartInfo
	 * @throws Exception
	 */
	public void insertCartInfo(Cart cartInfo) throws Exception {
		ShoppingCarRepository.insertCartInfo(cartInfo);
	}
}
