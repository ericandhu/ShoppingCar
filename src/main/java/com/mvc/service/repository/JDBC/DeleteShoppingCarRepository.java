package com.mvc.service.repository.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DeleteShoppingCarRepository {

	static Jdbc_mysql_config jd = new Jdbc_mysql_config();

	/**
	 * 刪除一筆資料 參考網站:
	 * 
	 * @param cartInfo
	 * @throws Exception
	 */
	public void deleteCartInfo(String cartNumber) throws Exception {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = jd.getConnection();
			stmt = conn.createStatement();
			// 這個寫法是 prepare statement 可以查insertCartInfo上面的參考網站
			// cart 後面先接 table的表欄位名稱, values的話則是有幾個欄位則給幾個問號
			String sql = "DELETE FROM cart WHERE cart_number=?";
			// 這邊跟查詢不一樣的是 需要新增下面的PreparedStatement作為跟db互動的物件查詢是Statement
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartNumber);// 第二個?要插入的值
			pstmt.executeUpdate();
		} catch (Exception e) {
			stmt.close();
			conn.close();
			throw e;
		}
	}

}