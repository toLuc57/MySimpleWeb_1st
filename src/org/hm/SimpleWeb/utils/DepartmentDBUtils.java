package org.hm.SimpleWeb.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hm.SimpleWeb.beans.Department;
import org.hm.SimpleWeb.jdbc.MySQLConnUtils;

public class DepartmentDBUtils {
	
	private static final String table ="tkhoa";
	private static final String id = "MaKhoa";
	private static final String name = "TenKhoa";
	private static final String address = "DiaChi";
	private static final String telephone = "DienThoai";
		
	public static List<Department> query(Connection conn) 
		throws SQLException {
		String sql = "select " + id + ", " + name + ", " 
				+ address + ", " + telephone 
				+ " from " + table;
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs = pstm.executeQuery();
		List<Department> list = new ArrayList<Department>();
		while(rs.next()) {
			Department mh = new Department();
			mh.setId(rs.getString(id));
			mh.setName(rs.getString(name));
			mh.setAddress(rs.getString(address));
			mh.setTelephone(rs.getString(telephone));
			list.add(mh);
		}
		return list;
	}
	public static Department find(String findRowById) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select " + id + ", " + name + ", " 
					+ address + ", " + telephone 
					+ " from " + table + " where " + id + "=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, findRowById);
			
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				Department mh = new Department();
				mh.setId(rs.getString(id));
				mh.setName(rs.getString(name));
				mh.setAddress(rs.getString(address));
				mh.setTelephone(rs.getString(telephone));
				return mh;
			}
		} catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
		return null;
	}
	public static void insert(Department insertRow) 
			throws SQLException {
		Connection conn = null;
		try{
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "insert into " + table 
					+ " (" + name + ", " + address + ", " + telephone + ") "
					+ " values (?,?,?)" ;
		
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, insertRow.getName());
			pstm.setString(2, insertRow.getAddress());
			pstm.setString(3, insertRow.getTelephone());
			
			pstm.executeUpdate();
		} 
		catch(ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}finally {
			MySQLConnUtils.closeQuietly(conn);
		}		
	}
	public static void delete(String deleteRowById) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "delete from " + table + " where " + id + " = ?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1,deleteRowById);
			
			pstm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		} finally {
			MySQLConnUtils.closeQuietly(conn);
		}
		
	}
	public static void update( Department updateRow) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "update " + table + " set " 
					+ name + " = ?, " + address +  " = ?, " + telephone +  " = ?, "
					+ " where " + id + " = ?";
				
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, updateRow.getName());
			pstm.setString(2, updateRow.getAddress());
			pstm.setString(3, updateRow.getTelephone());
			pstm.setString(4, updateRow.getId());
			
			pstm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		} finally {
			MySQLConnUtils.closeQuietly(conn);
		}
		
	}

}
