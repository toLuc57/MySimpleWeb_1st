package org.hm.SimpleWeb.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hm.SimpleWeb.beans.UserAccount;

	public class UserAccountDBUtils {
		private static final String table = "useraccount";
		private static final String userName = "username";
		private static final String password = "password_";
		private static final String isStudent = "isStudent";
		public static UserAccount find(Connection conn, //
			String userName1, String password1) throws SQLException {

			String sql = "select " + userName + ", " + password + ", " + isStudent 
					+ " from " + table 
					+ " where " +userName +"= ? and " +  password + "= ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, userName1);
			pstm.setString(2, password1);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				boolean isStudent = rs.getBoolean("isStudent");
				UserAccount user = new UserAccount();
				user.setUserName(userName1);
				user.setPassword(password1);
				user.setPosition(isStudent);
				return user;
			}
			return null;
		}
	public static UserAccount find(Connection conn, //
			String userName1) throws SQLException {

		String sql = "select " + userName + ", " + password + ", " + isStudent 
				+ " from " + table 
				+ " where " +userName +"= ? ";


		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName1);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			boolean isStudent = rs.getBoolean("isStudent");
			String password = rs.getString("password_");
			UserAccount user = new UserAccount();
			user.setUserName(userName1);
			user.setPassword(password);
			user.setPosition(isStudent);
			return user;
		}
		return null;
	}
}
