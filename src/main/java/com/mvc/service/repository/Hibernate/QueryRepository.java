package com.mvc.service.repository.Hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.mvc.dao.Cart;

import util.HibernateUtil;

public class QueryRepository {

	/**
	 * 取得Cart所有資料
	 */
	public List<Cart> getCartAllInfo() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Cart.class);
		// 查詢user所有欄位
		Iterator cart = criteria.list().iterator();
		List<Cart> cartList = new ArrayList();
		while (cart.hasNext()) {
			Cart cartInfo = (Cart) cart.next();
			cartList.add(cartInfo);
		}
		session.close();
		return cartList;
	}
	
	/**
	 * 取得Cart 指定資料
	 */
	public Cart getCartByCartNumber(String cart_number) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Cart.class);
		// 查詢指定cart_number欄位
		criteria.add(Expression.eq("cart_number", cart_number));
		Iterator cartIterator = criteria.list().iterator();
		Cart cartInfo = new Cart() ;
		while (cartIterator.hasNext()) {
			 cartInfo = (Cart) cartIterator.next();
		}
		session.close();
		return cartInfo;
	}
}
