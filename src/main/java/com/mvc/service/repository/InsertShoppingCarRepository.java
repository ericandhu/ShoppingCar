package com.mvc.service.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import com.mvc.dao.Cart;
import com.mvc.dao.CommodityPoolMain;

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

	// 新增單筆資料至poolmain
	public void insertCommodityPoolInfo(CommodityPoolMain cPI) throws Exception {
		Statement stmt1 = null;
		Connection conn1 = null;

		try {
			conn1 = jd.getConnection();
			stmt1 = conn1.createStatement();
			String sql1 = "INSERT INTO commodity_pool_main"
					+ "(commodity_pool_id,cart_number,commodity_pool_name,commodity_pool_type,stop_check,stop_desc,log_id)";
			PreparedStatement pstmt = conn1.prepareStatement(sql1);
			pstmt.setString(1, cPI.getCommodity_pool_id());// 第一個?要插入的值
			pstmt.setString(2, cPI.getCart_number());// 第二個?要插入的值
			pstmt.setString(3, cPI.getCommodity_pool_name());// 第三個?要插入的值
			pstmt.setString(4, cPI.getCommodity_pool_type());// 第四個?要插入的值
			pstmt.setString(5, cPI.getStop_check());// 第五個?要插入的值
			pstmt.setString(6, cPI.getStop_desc());// 第六個?要插入的值
			pstmt.setString(7, cPI.getLog_id());
			pstmt.executeUpdate();
		} catch (Exception e) {
			stmt1.close();
			conn1.close();
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