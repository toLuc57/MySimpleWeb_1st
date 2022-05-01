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
		private static final String isStudent = "isStudent";
		
		private static final Map<String, UserAccount> mapUsers = new HashMap<String, UserAccount>();

		static {
			try {
				initUsers();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private static void initUsers() throws IOException{
			Connection conn = null;
			try {
				conn = MySQLConnUtils.getMySQLConUtils();
				String sql = "select " + userName + ", " + password + ", " + isStudent 
						+ " from " + table;
				PreparedStatement pstm = conn.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();
				while(rs.next()) {
					String _userName = rs.getString(userName);
					String _password = rs.getString(password);
					boolean _isStudent = rs.getBoolean(isStudent);
					UserAccount newUser = new UserAccount(_userName,_password,_isStudent);
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
}
