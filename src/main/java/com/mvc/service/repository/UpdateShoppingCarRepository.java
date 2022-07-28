package com.mvc.service.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.mvc.dao.Cart;
import com.mvc.dao.CommodityPoolMain;

public class UpdateShoppingCarRepository {

	static Jdbc_mysql_config jd = new Jdbc_mysql_config();

	/**
	 * 更新一筆資料 參考網站:
	 * http://rightthewaygeek.blogspot.com/2014/02/java-jdbc-sql-database.html
	 * 
	 * @param cartInfo
	 * @throws Exception
	 */
	public void updateCartInfo(Cart cartInfo) throws Exception {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = jd.getConnection();
			stmt = conn.createStatement();
			// 這個寫法是 prepare statement 可以查insertCartInfo上面的參考網站
			// cart 後面先接 table的表欄位名稱, values的話則是有幾個欄位則給幾個問號
			String sql = "UPDATE cart SET customer=?, amount=?, last_modified_by= ?, last_modified_date=? "
					+ "WHERE cart_number=?";

			// 這邊跟查詢不一樣的是 需要新增下面的PreparedStatement作為跟db互動的物件查詢是Statement
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartInfo.getCustomer());// 第二個?要插入的值
			pstmt.setInt(2, cartInfo.getAmount());// 第三個?要插入的值
			pstmt.setString(3, cartInfo.getLast_modified_by());// 第4個?要插入的值
			pstmt.setDate(4, cartInfo.getLast_modified_date());// 第5個?要插入的值
			pstmt.setString(5, cartInfo.getCart_number());// 第一個?要插入的值

			// 查詢的是用executeQuery 新增資料是executeUpdate
			pstmt.executeUpdate();
		} catch (Exception e) {
			stmt.close();
			conn.close();
			throw e;
		}
	}

	public void updateCommodityPoolMain(CommodityPoolMain cPM) throws Exception {
		Statement stmt1 = null;
		Connection conn1 = null;
		try {
			conn1 = jd.getConnection();
			stmt1 = conn1.createStatement();
			// 這個寫法是 prepare statement 可以查insertCartInfo上面的參考網站
			// cart 後面先接 table的表欄位名稱, values的話則是有幾個欄位則給幾個問號
			String sql1 = "UPDATE commodity_pool_main SET commodity_pool_id=?,commodity_pool_name=?,commodity_pool_type=?,log_id=? ,stop_check=?,stop_desc=? "
					+ "WHERE cart_number=?";

			// 這邊跟查詢不一樣的是 需要新增下面的PreparedStatement作為跟db互動的物件查詢是Statement
			// 要照上方順序排序以下
			PreparedStatement pstmt = conn1.prepareStatement(sql1);
			pstmt.setString(1, cPM.getCommodity_pool_id());
			pstmt.setString(2, cPM.getCommodity_pool_name());
			pstmt.setString(3, cPM.getCommodity_pool_type());
			pstmt.setString(4, cPM.getLog_id());
			pstmt.setString(5, cPM.getStop_check());
			pstmt.setString(6, cPM.getStop_desc());
			pstmt.setString(7, cPM.getCart_number());

			// 查詢的是用executeQuery 新增資料是executeUpdate
			pstmt.executeUpdate();
		} catch (Exception e) {
			stmt1.close();
			conn1.close();
			throw e;
		}
	}

}