package org.hm.SimpleWeb.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hm.SimpleWeb.beans.Teacher;
import org.hm.SimpleWeb.jdbc.MySQLConnUtils;

public class TeacherDBUtils {	
	private static final String table ="tgiaovien";
	private static final String id = "MaTeacher";
	private static final String name = "TenTeacher";
	private static final String degree = "HocVi";
	private static final String teplephone = "SoDienThoai";
	private static final String idDepartment = "MaKhoa";
		
	public static List<Teacher> query(Connection conn) 
		throws SQLException {
		String sql = "select " + id + ", " + name + ", " 
				+ degree + ", " + teplephone + ", " + idDepartment 
				+ " from " + table ;
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs = pstm.executeQuery();
		List<Teacher> list = new ArrayList<Teacher>();
		while(rs.next()) {
			Teacher mh = new Teacher();
			mh.setId(rs.getString(id));
			mh.setName(rs.getString(name));
			mh.setTelephone(rs.getString(teplephone));
			mh.setDegree(rs.getString(degree));
			mh.setIdDepartment(rs.getString(idDepartment));
			list.add(mh);
		}
		return list;
	}
	public static Teacher find(String findRowById) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select *from " + table + " where " + id + " =?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, findRowById);
			
			ResultSet rs = pstm.executeQuery();
			System.out.println(sql);
			System.out.println(rs.first());
			if(rs.next()) {
				Teacher mh = new Teacher();
				mh.setId(rs.getString(id));
				mh.setName(rs.getString(name));
				mh.setTelephone(rs.getString(teplephone));
				mh.setDegree(rs.getString(degree));
				mh.setIdDepartment(rs.getString(idDepartment));
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
	public static void insert(Teacher insertRow) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "insert into " + table 
					+ "(" + name + ", " + degree
					+ ", " + teplephone + ", " + idDepartment + ") "
					+ " values (?,?,?,?)" ;
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, insertRow.getName());
			pstm.setString(2, insertRow.getDegree());
			pstm.setString(3, insertRow.getTelephone());
			pstm.setString(4, insertRow.getIdDepartment());
			
			pstm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
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
			String sql = "delete from " + table + " where " + id + "=?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1,deleteRowById);
			
			pstm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
	}
	public static void update(Teacher updateRow) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "update " + table + " set " 
					+ name + "=?, " + degree +  "=?, "
					+ teplephone + "=?, " + idDepartment +  "=? "
					+ " where " + id + "=?";
				
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, updateRow.getName());
			pstm.setString(2, updateRow.getDegree());
			pstm.setString(3, updateRow.getTelephone());
			pstm.setString(4, updateRow.getIdDepartment());
			pstm.setString(5, updateRow.getId());
			
			pstm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}finally {
			MySQLConnUtils.closeQuietly(conn);
		}
	}
}
