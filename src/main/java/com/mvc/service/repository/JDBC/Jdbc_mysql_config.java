package com.mvc.service.repository.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc_mysql_config {

	static final String url = "jdbc:mysql://localhost:3306/shoppingcar?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

	static final String user = "root";
	static final String password = "admin";

	public static Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		// Class.forName("com.mysql.jdbc.Driver");
		// Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

}
