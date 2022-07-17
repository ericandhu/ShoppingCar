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
		// �ǳư���SQL������
		Statement stmt = null;
		// �s�u������
		Connection conn = null;
		//
		Cart cart = new Cart();
		try {
			conn = jd.getConnection();
			// �ǳƹ�DB�F��������
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
	 * �s�W�@����� �ѦҺ���:
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
			// �o�Ӽg�k�O prepare statement �i�H�dinsertCartInfo�W�����ѦҺ���
			// cart �᭱���� table�������W��, values���ܫh�O���X�����h���X�Ӱݸ�
			String sql = "INSERT INTO cart "
					+ "(cart_number, customer, amount, created_by, created_date, last_modified_by, last_modified_date) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

			// �o���d�ߤ��@�˪��O �ݭn�s�W�U����PreparedStatement�@����db���ʪ�����d�߬OStatement
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartInfo.getCart_number());// �Ĥ@��?�n���J����
			pstmt.setString(2, cartInfo.getCustomer());// �ĤG��?�n���J����
			pstmt.setInt(3, cartInfo.getAmount());// �ĤT��?�n���J����
			pstmt.setString(4, cartInfo.getCreated_by());// �ĥ|��?�n���J����
			pstmt.setDate(5, cartInfo.getCreated_date());// �Ĥ���?�n���J����
			pstmt.setString(6, cartInfo.getLast_modified_by());// �Ĥ���?�n���J����
			pstmt.setDate(7, cartInfo.getLast_modified_date());// �ĤC��?�n���J����

			// �d�ߪ��O��executeQuery �s�W��ƬOexecuteUpdate
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			stmt.close();
			conn.close();
		}
	}
}
