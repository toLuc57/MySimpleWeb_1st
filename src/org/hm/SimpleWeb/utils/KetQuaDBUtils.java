package org.hm.SimpleWeb.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hm.SimpleWeb.beans.GiaoVien;
import org.hm.SimpleWeb.beans.KetQua;
import org.hm.SimpleWeb.jdbc.MySQLConnUtils;

public class KetQuaDBUtils {
	private static final String table ="tketqua";
	private static final String idStudent = "MaSinhVien";
	private static final String subjects = "idHoc";
	private static final String numberOfTests = "LanThi";
	private static final String point = "Diem";
		
	public static List<KetQua> query(Connection conn) 
		throws SQLException {
		String sql = "select " + idStudent + ", " + subjects + ", " 
				+ numberOfTests + ", " + point 
				+ " from " + table;
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs = pstm.executeQuery();
		List<KetQua> list = new ArrayList<KetQua>();
		while(rs.next()) {
			KetQua mh = new KetQua();
			mh.setMaSinhVien(rs.getString(idStudent));
			mh.setMaKhoaHoc(rs.getString(subjects));
			mh.setLanThi(rs.getString(numberOfTests));
			mh.setDiem(rs.getDouble(point));
			list.add(mh);
		}
		return list;
	}
	public static KetQua find(String findRowById) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select " + idStudent + ", " + subjects + ", " 
					+ numberOfTests + ", " + point 
					+ "from " + table + "where " + idStudent + " = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, findRowById);
			
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				KetQua mh = new KetQua();
				mh.setMaSinhVien(rs.getString(idStudent));
				mh.setMaKhoaHoc(rs.getString(subjects));
				mh.setLanThi(rs.getString(numberOfTests));
				mh.setDiem(rs.getDouble(point));
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
	
	public static void insert(KetQua insertRow) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "insert into " + table + " values (?,?,?,?)" ;
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, insertRow.getMaSinhVien());
			pstm.setString(2, insertRow.getMaKhoaHoc());
			pstm.setString(3, insertRow.getLanThi());
			pstm.setDouble(4, insertRow.getDiem());
			
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
			String sql = "delete from " + table + " where " + idStudent + " = ?";
			
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
	public static void update(KetQua updateRow) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "update " + table + " set " 
					+ subjects + " = ?, " + numberOfTests +  " = ?, " + point +  " = ?, "
					+ " where " + idStudent + " = ?";
				
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, updateRow.getMaKhoaHoc());
			pstm.setString(2, updateRow.getLanThi());
			pstm.setDouble(3, updateRow.getDiem());
			pstm.setString(4, updateRow.getMaSinhVien());
			
			pstm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}	
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}	
	}
	
}
