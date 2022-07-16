package com.mvc.service.impl;

import java.util.List;

import com.mvc.dao.Cart;
import com.mvc.service.repository.ShoppingCarRepository;

public class ShoppingCarServiceImpl {

	// public static void main(String[] args) {
	//
	// }

	ShoppingCarRepository ShoppingCarRepository = new ShoppingCarRepository();

	public List<Cart> getCart() throws Exception {
		return ShoppingCarRepository.getCart();
	}

	public Cart getCart_By_Number(String cart_number) throws Exception {
		return ShoppingCarRepository.getCart_By_Number(cart_number);
	}

	public List<Cart> getCart_By_Number_List(List cart_number_list) throws Exception {
		return ShoppingCarRepository.getCart_By_Number_List(cart_number_list);
	}
}
