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

}
