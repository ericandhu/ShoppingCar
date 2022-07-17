package com.mvc.service.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

		try {
			conn = jd.getConnection();
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

	public Cart getCart_By_Number(String cart_number) throws Exception {
		// 準備執行SQL的物件
		Statement stmt = null;
		// 連線的物件
		Connection conn = null;
		//
		Cart cart = new Cart();
		try {
			conn = jd.getConnection();
			// 準備對DB幹嘛的物件
			stmt = conn.createStatement();

			String sql;
			sql = "SELECT * FROM cart Where cart_number=" + cart_number;
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				cart.setAmount(rs.getInt("Amount"));
				cart.setCart_number(rs.getString("Cart_number"));
				cart.setCreated_by(rs.getString("Created_by"));
				cart.setCreated_date(rs.getDate("Created_date"));
				cart.setCustomer(rs.getString("Customer"));
				cart.setLast_modified_by(rs.getString("Last_modified_by"));
				cart.setLast_modified_date(rs.getDate("Last_modified_date"));
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			stmt.close();
			conn.close();
		}

		return cart;
	}

	public List<Cart> getCart_By_Number_List(List cart_number_list) throws Exception {
		Statement stmt = null;
		Connection conn = null;
		List<Cart> cartList = new ArrayList();

		try {
			conn = jd.getConnection();

			stmt = conn.createStatement();
			String c2 = cart_number_list.toString().replace("[", "").replace("]", "");
			String sql;
			sql = "SELECT * FROM cart Where cart_number in(" + c2 + ")";
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

	/**
	 * 新增一筆資料 參考網站:
	 * http://rightthewaygeek.blogspot.com/2014/02/java-jdbc-sql-database.html
	 * 
	 * @param cartInfo
	 * @throws Exception
	 */
	public void insertCartInfo(Cart cartInfo) throws Exception {
		Statement stmt = null;
		Connection conn = null;

		try {
			conn = jd.getConnection();
			stmt = conn.createStatement();
			// 這個寫法是 prepare statement 可以查insertCartInfo上面的參考網站
			// cart 後面先接 table的表欄位名稱, values的話則是有幾個欄位則給幾個問號
			String sql = "INSERT INTO cart "
					+ "(cart_number, customer, amount, created_by, created_date, last_modified_by, last_modified_date) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

			// 這邊跟查詢不一樣的是 需要新增下面的PreparedStatement作為跟db互動的物件查詢是Statement
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartInfo.getCart_number());// 第一個?要插入的值
			pstmt.setString(2, cartInfo.getCustomer());// 第二個?要插入的值
			pstmt.setInt(3, cartInfo.getAmount());// 第三個?要插入的值
			pstmt.setString(4, cartInfo.getCreated_by());// 第四個?要插入的值
			pstmt.setDate(5, cartInfo.getCreated_date());// 第五個?要插入的值
			pstmt.setString(6, cartInfo.getLast_modified_by());// 第六個?要插入的值
			pstmt.setDate(7, cartInfo.getLast_modified_date());// 第七個?要插入的值

			// 查詢的是用executeQuery 新增資料是executeUpdate
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			stmt.close();
			conn.close();
		}
	}
}
