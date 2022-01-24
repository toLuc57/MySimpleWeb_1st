package org.hm.SimpleWeb.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hm.SimpleWeb.beans.SinhVien;

public class SinhVienDBUtils {
	private static int autoIncrement = 1;
	private static String charMa = "SV";
	
	private static final String table = "tSinhVien";
	private static final String id = "MaSinhVien";
	private static final String lastName = "HoSinhVien";
	private static final String firstName = "TenSinhVien";
	private static final String birthday = "NgaySinh";
	private static final String sex = "Phai";
	private static final String telephone = "DienThoai";
	private static final String address = "DiaChi";
	private static final String idDepartment = "MaKhoa"; 
	
	public static List<SinhVien> query(Connection conn) 
			throws SQLException {
		String query = "select "+ id +" ," + lastName +" ," + firstName
				+ " ," + birthday + " ," + sex + " ," + telephone
				+ " ," + address + " ," + idDepartment + " from " + table;
		PreparedStatement pstm = conn.prepareStatement(query);
		
		ResultSet rs = pstm.executeQuery();
		List<SinhVien> list = new ArrayList<SinhVien>();
		while(rs.next()) {
			SinhVien sv = new SinhVien();
			sv.setMaSV(rs.getString(id));
			sv.setHoSV(rs.getString(lastName));
			sv.setTenSV(rs.getString(firstName));
			sv.setNgaySinh(rs.getDate(birthday));
			sv.setPhai(rs.getString(sex));
			sv.setDienThoai(rs.getString(telephone));
			sv.setDiaChi(rs.getString(address));
			sv.setMaKhoa(rs.getString(idDepartment));
			list.add(sv);
		}
		return list;
		
	}
	public static SinhVien find(Connection conn,
			String findRowById) throws SQLException {
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
			SinhVien sv = new SinhVien();
			sv.setMaSV(rs.getString(id));
			sv.setHoSV(rs.getString(lastName));
			sv.setTenSV(rs.getString(firstName));
			sv.setNgaySinh(rs.getDate(birthday));
			sv.setPhai(rs.getString(sex));
			sv.setDienThoai(rs.getString(telephone));
			sv.setDiaChi(rs.getString(address));
			sv.setMaKhoa(rs.getString(idDepartment));
			return sv;
		}
		return null;
	}
	public static void insert(Connection conn, 
			SinhVien insertRow) throws SQLException{
		String sql ="insert into " + table + " values(?,?,?,?,?,?,?,?)";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		String newMa = charMa.toString() + "-" + String.valueOf(autoIncrement);
		
		pstm.setString(1, newMa);
		pstm.setString(2,insertRow.getHoSV());
		pstm.setString(3,insertRow.getTenSV());
		pstm.setDate(4,insertRow.getNgaySinh());
		pstm.setString(5,insertRow.getPhai());
		pstm.setString(6,insertRow.getDienThoai());
		pstm.setString(7,insertRow.getDiaChi());
		pstm.setString(8,insertRow.getMaKhoa());
		
		pstm.executeUpdate();
	}
	public static void delete(Connection conn, 
			String deleteRowById) throws SQLException{
		String sql = "delete from " + table + " where " + id + " = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setString(1,deleteRowById);
		
		pstm.executeUpdate();
		
	}
	public static void update(Connection conn, 
			SinhVien updateRow) throws SQLException{
		String sql = "update " + table + " set " 
			+ lastName + "= ?, " + firstName + " = ?, "
			+ birthday + "= ?, " + sex + "= ?, " 
			+ telephone + "= ?, " + address + "= ?, " 
			+ idDepartment +  "= ? " 
			+ " where " + id + " = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setString(1, updateRow.getHoSV());
		pstm.setString(2, updateRow.getTenSV());
		pstm.setDate(3, updateRow.getNgaySinh());
		pstm.setString(4, updateRow.getPhai());
		pstm.setString(5, updateRow.getDienThoai());
		pstm.setString(6, updateRow.getDiaChi());
		pstm.setString(7, updateRow.getMaKhoa());
		pstm.setString(8, updateRow.getMaSV());
		
		pstm.executeUpdate();
	}
}
