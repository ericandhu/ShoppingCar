package com.mvc.service.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {
	// 設定檔
	private static Connection getConnection() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/shoppingcar?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		// 帳號和密碼
		String user = "root";
		String password = "admin";
		return DriverManager.getConnection(url, user, password);
	}

	public static void main(String[] args) throws Exception {
		Statement stmt = null;
		Connection conn = null;
		// 測試用try跟catch
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			 Class.forName("com.mysql.cj.jdbc.Driver");
			conn = getConnection();
			// 印出來表示連線效果是可以的
			System.out.println(conn);

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM cart";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.print(rs);
		} finally {
			stmt.close();
			conn.close();
		}

	}
}
