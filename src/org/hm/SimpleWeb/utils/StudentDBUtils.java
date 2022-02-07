package org.hm.SimpleWeb.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hm.SimpleWeb.beans.Student;
import org.hm.SimpleWeb.jdbc.MySQLConnUtils;

public class StudentDBUtils {
	private static final String table = "tSinhVien";
	private static final String id = "MaSinhVien";
	private static final String lastName = "HoSinhVien";
	private static final String firstName = "TenSinhVien";
	private static final String birthday = "NgaySinh";
	private static final String sex = "Phai";
	private static final String telephone = "DienThoai";
	private static final String address = "DiaChi";
	private static final String idDepartment = "MaKhoa"; 
	
	public static List<Student> query(Connection conn) 
			throws SQLException {
		String query = "select "+ id +" ," + lastName +" ," + firstName
				+ " ," + birthday + " ," + sex + " ," + telephone
				+ " ," + address + " ," + idDepartment + " from " + table;
		PreparedStatement pstm = conn.prepareStatement(query);
		
		ResultSet rs = pstm.executeQuery();
		List<Student> list = new ArrayList<Student>();
		while(rs.next()) {
			Student sv = new Student();
			sv.setId(rs.getString(id));
			sv.setLastName(rs.getString(lastName));
			sv.setFirstName(rs.getString(firstName));
			sv.setBirthday(rs.getDate(birthday));
			sv.setSex(rs.getString(sex));
			sv.setTelephone(rs.getString(telephone));
			sv.setAddress(rs.getString(address));
			sv.setIdDepartment(rs.getString(idDepartment));
			list.add(sv);
		}
		return list;
		
	}
	public static Student find(String findRowById) throws SQLException {
		Connection conn = null;
		Student sv = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String query = "select "
					+ id +" ," + lastName +" ," + firstName + " ," 
					+ birthday + " ," + sex + " ," + telephone + " ," 
					+ address + " ," + idDepartment 
					+ " from " + table
					+ " where " + id + " = ?";
			PreparedStatement pstm = conn.prepareStatement(query);
			
			pstm.setString(1, findRowById);
			
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				sv = new Student();
				sv.setId(rs.getString(id));
				sv.setLastName(rs.getString(lastName));
				sv.setFirstName(rs.getString(firstName));
				sv.setBirthday(rs.getDate(birthday));
				sv.setSex(rs.getString(sex));
				sv.setTelephone(rs.getString(telephone));
				sv.setAddress(rs.getString(address));
				sv.setIdDepartment(rs.getString(idDepartment));
			}
		} catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		} finally {
			MySQLConnUtils.closeQuietly(conn);
		}
		return sv;
	}
	
	public static void insert(Student insertRow) throws SQLException{
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql ="insert into " + table 
					+ " (" + lastName + ", " + firstName +", " 
					+ birthday + ", " + sex + ", " +telephone + ", " 
					+ address + ", " + idDepartment + ") "
					+ " values(?,?,?,?,?,?,?)";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1,insertRow.getLastName());
			pstm.setString(2,insertRow.getFirstName());
			pstm.setDate(3,insertRow.getBirthday());
			pstm.setString(4,insertRow.getSex());
			pstm.setString(5,insertRow.getTelephone());
			pstm.setString(6,insertRow.getAddress());
			pstm.setString(7,insertRow.getIdDepartment());
			
			pstm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		} finally {
			MySQLConnUtils.closeQuietly(conn);
		}
		
	}
	public static void delete(String deleteRowById) throws SQLException{
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			LearningOutcomesDBUtils.delete(deleteRowById);
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
	public static void update(Student updateRow) throws SQLException{
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "update " + table + " set " 
					+ lastName + "= ?, " + firstName + " = ?, "
					+ birthday + "= ?, " + sex + "= ?, " 
					+ telephone + "= ?, " + address + "= ?, " 
					+ idDepartment +  "= ? " 
					+ " where " + id + " = ?";
				
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, updateRow.getLastName());
			pstm.setString(2, updateRow.getFirstName());
			pstm.setDate(3, updateRow.getBirthday());
			pstm.setString(4, updateRow.getSex());
			pstm.setString(5, updateRow.getTelephone());
			pstm.setString(6, updateRow.getAddress());
			pstm.setString(7, updateRow.getIdDepartment());
			pstm.setString(8, updateRow.getId());
			
			pstm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		} finally {
			MySQLConnUtils.closeQuietly(conn);
		}		
	}
	public static void deleteIdDepartment(String deleteRowById) throws SQLException{
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "delete from " + table + " where " + idDepartment + " = ?";
			
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
}
