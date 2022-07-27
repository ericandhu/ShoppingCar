package com.mvc.service.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.dao.Cart;
import com.mvc.dao.CommodityPoolMain;

/**
 * 查詢類別
 * 
 * @author User
 *
 */
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
			stmt.close();
			conn.close();
			throw e;
		}

		return cartList;
	}

	public List<CommodityPoolMain> getCPI() throws Exception {
		Statement stmt1 = null;
		Connection conn1 = null;
		List<CommodityPoolMain> cPIList = new ArrayList();

		try {
			conn1 = jd.getConnection();
			System.out.println(conn1);

			stmt1 = conn1.createStatement();
			String sql1;
			sql1 = "SELECT * FROM commodity_pool_main";
			ResultSet rs1 = stmt1.executeQuery(sql1);

			while (rs1.next()) {
				CommodityPoolMain cPI = new CommodityPoolMain();
				cPI.setCart_number(rs1.getString("cart_number"));
				cPI.setCommodity_pool_id(rs1.getString("commodity_pool_id"));
				cPI.setCommodity_pool_name(rs1.getString("commodity_pool_name"));
				cPI.setCommodity_pool_type(rs1.getString("commodity_pool_type"));
				cPI.setLog_id(rs1.getString("log_id"));
				cPI.setStop_check(rs1.getString("stop_check"));
				cPI.setStop_desc(rs1.getString("stop_desc"));

				cPIList.add(cPI);
			}
		} catch (Exception e) {
			stmt1.close();
			conn1.close();
			throw e;
		}

		return cPIList;
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
			stmt.close();
			conn.close();
			throw e;
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
			stmt.close();
			conn.close();
			throw e;
		}
		return cartList;
	}

	public CommodityPoolMain getCPM_By_Number(String cpm_number) throws Exception {
		Statement stmt2 = null;
		Connection conn2 = null;
		try {
			conn2 = jd.getConnection();
			// 準備對DB幹嘛的物件
			stmt2 = conn2.createStatement();

			String sql2;
			sql2 = "SELECT * FROM commodity_pool_main Where cart_number=" + cpm_number;
			ResultSet rs2 = stmt2.executeQuery(sql2);
			CommodityPoolMain cPM = new CommodityPoolMain();
			while (rs2.next()) {
				cPM.setCart_number(rs2.getString("cart_number"));
				cPM.setCommodity_pool_id(rs2.getString("commodity_pool_id"));
				cPM.setCommodity_pool_name(rs2.getString("commodity_pool_name"));
				cPM.setCommodity_pool_type(rs2.getString("commodity_pool_type"));
				cPM.setLog_id(rs2.getString("log_id"));
				cPM.setStop_check(rs2.getString("stop_check"));
				cPM.setStop_desc(rs2.getString("stop_desc"));
			}
			return cPM;
		} catch (Exception e) {
			stmt2.close();
			conn2.close();
			throw e;
		}
	}

}