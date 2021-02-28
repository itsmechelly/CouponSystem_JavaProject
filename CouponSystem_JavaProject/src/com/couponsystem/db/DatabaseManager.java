package com.couponsystem.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class DatabaseManager {
	
	private static final String url = "jdbc:mysql://localhost:3306"
									+ "?createDatabaseIfNotExist=TRUE&"
									+ "serverTimezone=Israel";
//									+ "useTimezone&"
//									+ "serverTimezone=UTC";
	private static String username = "root";
	private static String password = "1234";
	
	public static String getUsername() {
		return username;
	}
	public static String getPassword() {
		return password;
	}
	public static String getUrl() {
		return url;
	}

	public static void queryExecutive(String sql) {
		
		Connection connection = null;
		
		try {
//			The first(hidden) option here is dangerous - because we will prefer to use singleton in our connection to the database(connectionPool class).
//			connection = DriverManager.getConnection(url, username, password);
//			Here is the Bats Practice:
			connection = ConnectionPool.getInstance().getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
	}
}
