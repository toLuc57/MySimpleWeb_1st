package org.hm.SimpleWeb.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
		
		public static UserAccount find(String findUserName) 
				throws SQLException {
			UserAccount u = mapUsers.get(findUserName);
			return u;
		}
		public static void insert(UserAccount user) {
			Connection conn = null;
			try {
				conn = MySQLConnUtils.getMySQLConUtils();
				String sql = "insert into " + table 
						+ " (" + userName + ", " + password + ", " 
						+ id + ") " + " values (?,?,?)" ;
				
				PreparedStatement pstm = conn.prepareStatement(sql);
				
				pstm.setString(1, user.getUserName());
				pstm.setString(2, user.getPassword());
				pstm.setString(3, user.getID());
				pstm.executeUpdate();
				
				mapUsers.put(user.getUserName(), user);
			} catch (ClassNotFoundException | SQLException e) {
				MySQLConnUtils.rollbackQuietly(conn);
				e.printStackTrace();
			}finally {
				MySQLConnUtils.closeQuietly(conn);
			}
		}
		
		public static void update(String _userName ,String newPassword) {
			if (!mapUsers.containsKey(_userName)) {
				return;
			}
			Connection conn = null;
			try {
				conn = MySQLConnUtils.getMySQLConUtils();
				String sql = "update " + table + " set " 
						 + password +  " = ? "
						 + " where " + userName + " = ? ";
					
				PreparedStatement pstm = conn.prepareStatement(sql);
				
				pstm.setString(1, newPassword);
				pstm.setString(2, _userName);				
				pstm.executeUpdate();
				
				UserAccount u = mapUsers.get(_userName);
				u.setPassword(newPassword);
				mapUsers.replace(_userName, u);
			} catch (ClassNotFoundException | SQLException e) {
				MySQLConnUtils.rollbackQuietly(conn);
				e.printStackTrace();
			}	
			finally {
				MySQLConnUtils.closeQuietly(conn);
			}	
		}
		
		public static Set<String> getAllUserName() {
			return mapUsers.keySet();
		}
}
