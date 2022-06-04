package org.hm.SimpleWeb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {

	public static Connection getMySQLConUtils() throws SQLException,
			ClassNotFoundException {
		//jdbc:mysql://localhost:3306/qlhtsv_28
		String hostName = "localhost";
		String dbName = "qlhtsv_28_1";
		String userName = "root";
		String password = "VeLa";
		
		return getMySQLConnUtils(hostName, dbName, userName, password);
	}
	
	public static Connection getMySQLConnUtils(String hostName, String dbName,
			String userName, String password) throws SQLException,
			ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
			
			Connection conn = (Connection) DriverManager.getConnection(connectionURL,userName,password);
//			System.out.println("Suscessfully!");
			return conn;
		}
		catch (ClassNotFoundException | SQLException e) {
//			System.out.println("Fail!");
			e.printStackTrace();
		}
		return null;
	}
	public static void closeQuietly(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
		}
	}

	public static void rollbackQuietly(Connection conn) {
		try {
			conn.rollback();
		} catch (Exception e) {
		}
	}
}
