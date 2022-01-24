package org.hm.SimpleWeb.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hm.SimpleWeb.beans.MonHoc;

public class MonHocDBUtils {
	private static int autoIncrement = 1;
	private static String charMa = "MH";
	
	private static final String table ="tmonhoc";
	private static final String id = "MaMonHoc";
	private static final String name = "TenMonHoc";
	private static final String theoryLesson = "SoTietLyThuyet";
	private static final String practiceLesson = "SoTietThucHanh";
		
	public static List<MonHoc> query(Connection conn) 
		throws SQLException {
		String sql = "select " + id + ", " + name + ", " 
				+ theoryLesson + ", " + practiceLesson 
				+ "from " + table;
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs = pstm.executeQuery();
		List<MonHoc> list = new ArrayList<MonHoc>();
		while(rs.next()) {
			MonHoc mh = new MonHoc();
			mh.setMaMonHoc(rs.getString(id));
			mh.setTenMonHoc(rs.getString(name));
			mh.setSoTietLT(rs.getInt(theoryLesson));
			mh.setSoTietTH(rs.getInt(practiceLesson));
			list.add(mh);
		}
		return list;
	}
	public static MonHoc find(Connection conn, String findRowById) 
			throws SQLException {
		String sql = "select " + id + ", " + name + ", " 
				+ theoryLesson + ", " + practiceLesson 
				+ "from " + table + "where " + id + " = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setString(1, findRowById);
		
		ResultSet rs = pstm.executeQuery();
		if(rs.next()) {
			MonHoc mh = new MonHoc();
			mh.setMaMonHoc(rs.getString(id));
			mh.setTenMonHoc(rs.getString(name));
			mh.setSoTietLT(rs.getInt(theoryLesson));
			mh.setSoTietTH(rs.getInt(practiceLesson));
			return mh;
		}
		return null;
	}
	public static void insert(Connection conn, MonHoc insertRow) 
			throws SQLException {
		String sql = "insert into " + table + " values (?,?,?,?)" ;
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		String newMa = charMa.toString() + "-" + String.valueOf(autoIncrement);
		
		pstm.setString(1, newMa);
		pstm.setString(2, insertRow.getTenMonHoc());
		pstm.setInt(3, insertRow.getSoTietLT());
		pstm.setInt(4, insertRow.getSoTietTH());
		
		pstm.executeUpdate();
	}
	public static void delete(Connection conn, String deleteRowById) 
			throws SQLException {
		String sql = "delete from " + table + " where " + id + " = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setString(1,deleteRowById);
		
		pstm.executeUpdate();
	}
	public static void update(Connection conn, MonHoc updateRow) 
			throws SQLException {
		String sql = "update " + table + " set " 
			+ name + " = ?, " + theoryLesson +  " = ?, " + practiceLesson +  " = ?, "
			+ " where " + id + " = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setString(1,updateRow.getTenMonHoc());
		pstm.setInt(2,updateRow.getSoTietLT());
		pstm.setInt(3, updateRow.getSoTietTH());
		
		pstm.executeUpdate();
	}
}
