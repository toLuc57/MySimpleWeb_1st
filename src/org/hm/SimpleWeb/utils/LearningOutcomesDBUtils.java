package org.hm.SimpleWeb.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hm.SimpleWeb.beans.LearningOutcomes;
import org.hm.SimpleWeb.jdbc.MySQLConnUtils;

public class LearningOutcomesDBUtils {
private static final String table ="tketqua";
	
	private static final String idStudent = "MaSinhVien";
	private static final String idCourse = "MaKhoaHoc";
	private static final String numberOfTests = "LanThi";
	private static final String point = "Diem";
		
	public static List<LearningOutcomes> query(Connection conn) 
		throws SQLException {
		String sql = "select " + idStudent + ", " + idCourse + ", " 
				+ numberOfTests + ", " + point 
				+ " from " + table;
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs = pstm.executeQuery();
		List<LearningOutcomes> list = new ArrayList<LearningOutcomes>();
		while(rs.next()) {
			LearningOutcomes mh = new LearningOutcomes();
			mh.setIdStudent(rs.getString(idStudent));
			mh.setIdCourse(rs.getString(idCourse));
			mh.setNumberOfTest(rs.getString(numberOfTests));
			mh.setPoint(rs.getDouble(point));
			list.add(mh);
		}
		return list;
	}
	public static LearningOutcomes find(String findRowById) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select " + idStudent + ", " + idCourse + ", " 
					+ numberOfTests + ", " + point 
					+ "from " + table + "where " + idStudent + " = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, findRowById);
			
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				LearningOutcomes mh = new LearningOutcomes();
				mh.setIdStudent(rs.getString(idStudent));
				mh.setIdCourse(rs.getString(idCourse));
				mh.setNumberOfTest(rs.getString(numberOfTests));
				mh.setPoint(rs.getDouble(point));
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
	
	public static void insert(LearningOutcomes insertRow) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "insert into " + table 
					+ " (" + idStudent + ", " + idCourse + ", " 
					+ numberOfTests + ", " + point + ") " 
					+ " values (?,?,?,?)" ;
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, insertRow.getIdStudent());
			pstm.setString(2, insertRow.getIdCourse());
			pstm.setString(3, insertRow.getNumberOfTest());
			pstm.setDouble(4, insertRow.getPoint());
			
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
	public static void update(LearningOutcomes updateRow) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "update " + table + " set " 
					+ numberOfTests +  " = ?, " + point +  " = ?, "
					+ " where " + idStudent + " = ? and " + idCourse + " = ?";
				
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, updateRow.getNumberOfTest());
			pstm.setDouble(2, updateRow.getPoint());
			pstm.setString(3, updateRow.getIdStudent());
			pstm.setString(4, updateRow.getIdCourse());
			
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