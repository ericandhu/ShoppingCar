package com.mvc.service.impl.Hibernate;

import java.util.List;

import com.mvc.dao.Cart;
import com.mvc.service.repository.Hibernate.QueryRepository;

public class ShoppingCarServiceImpl_Hibernate {

	QueryRepository queryRepository = new QueryRepository();

	/**
	 * 取得全部資料
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Cart> getCart() throws Exception {
		return queryRepository.getCartAllInfo();
	}
	
	/**
	 * 取得指定搜尋資料
	 * 
	 * @return
	 * @throws Exception
	 */
	public Cart getCartByCartNumber(String cart_number) throws Exception {
		return queryRepository.getCartByCartNumber(cart_number);
	}

}