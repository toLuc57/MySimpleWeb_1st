package org.hm.SimpleWeb.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.hm.SimpleWeb.beans.UserAccount;
import org.hm.SimpleWeb.jdbc.MySQLConnUtils;

	public class UserAccountDBUtils {
		private static final String table = "useraccount";
		private static final String userName = "username";
		private static final String password = "password_";
		private static final String id = "_id";
		
		private static final Map<String, UserAccount> mapUsers = new HashMap<String, UserAccount>();

		static {
			try {
				initUsers();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private static void initUsers() throws IOException{
			Connection conn = null;
			try {
				conn = MySQLConnUtils.getMySQLConUtils();
				String sql = "select " + userName + ", " + password + ", " + id 
						+ " from " + table;
				PreparedStatement pstm = conn.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();
				while(rs.next()) {
					String _userName = rs.getString(userName);
					String _password = rs.getString(password);
					String _id = rs.getString(id);
					UserAccount newUser = new UserAccount(_userName,_password,_id);
					mapUsers.put(newUser.getUserName(), newUser);
				}
			}
			catch (ClassNotFoundException | SQLException e) {
				MySQLConnUtils.rollbackQuietly(conn);
				e.printStackTrace();
			}
			finally {
				MySQLConnUtils.closeQuietly(conn);
			}
		}

		
		public static UserAccount find(String findUserName, String findPassword) 
				throws SQLException {
			UserAccount u = mapUsers.get(findUserName);
			if (u != null && u.getPassword().equals(findPassword)) {
				return u;
			}
			return null;
		}
		
		public static boolean find(String findUserName) 
				throws SQLException {
			UserAccount u = mapUsers.get(findUserName);
			if (u != null) {
				return true;
			}
			return false;
		}
}
