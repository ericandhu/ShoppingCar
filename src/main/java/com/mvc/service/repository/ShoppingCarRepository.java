package com.mvc.service.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.dao.Cart;

public class ShoppingCarRepository {

	static Jdbc_mysql_config jd = new Jdbc_mysql_config();

	public List<Cart> getCart() throws Exception {
		Statement stmt = null;
		Connection conn = null;
		List<Cart> cartList = new ArrayList();
		// 測試用try跟catch
		try {
			conn = jd.getConnection();
			// 印出來表示連線效果是可以的
			System.out.println(conn);

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM cart";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Cart cart = new Cart();
				cart.setAmount(rs.getInt("Amount"));
				cart.setCart_number(rs.getString("Cart_number"));
				cart.setCreated_by(rs.getString("Created_by"));
				cart.setCreated_date(rs.getDate("Created_date"));
				cart.setCustomer(rs.getString("Customer"));
				cart.setLast_modified_by(rs.getString("Last_modified_by"));
				cart.setLast_modified_date(rs.getDate("Last_modified_date"));
				cartList.add(cart);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			stmt.close();
			conn.close();
		}

		return cartList;
	}

}
