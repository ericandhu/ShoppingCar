package com.mvc.service.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import com.mvc.dao.Cart;

public class InsertShoppingCarRepository {

	static Jdbc_mysql_config jd = new Jdbc_mysql_config();

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
			stmt.close();
			conn.close();
			throw e;
		}
	}

	/**
	 * 新增多筆資料
	 * 
	 * @param cartInfo list
	 * @throws Exception
	 */
	public void insertCartInfoList(List<Cart> cartInfoList) throws Exception {
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

			for (int i = 0; i < cartInfoList.size(); i++) {
				Cart cartInfo = new Cart();
				// 這邊跟查詢不一樣的是 需要新增下面的PreparedStatement作為跟db互動的物件查詢是Statement
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cartInfo.getCart_number());// 第一個?要插入的值
				pstmt.setString(2, cartInfo.getCustomer());// 第二個?要插入的值
				pstmt.setInt(3, cartInfo.getAmount());// 第三個?要插入的值
				pstmt.setString(4, cartInfo.getCreated_by());// 第四個?要插入的值
				pstmt.setDate(5, cartInfo.getCreated_date());// 第五個?要插入的值
				pstmt.setString(6, cartInfo.getLast_modified_by());// 第六個?要插入的值
				pstmt.setDate(7, cartInfo.getLast_modified_date());// 第七個?要插入的值
				pstmt.executeUpdate();
			}

		} catch (Exception e) {
			stmt.close();
			conn.close();
			throw e;
		}
	}

}