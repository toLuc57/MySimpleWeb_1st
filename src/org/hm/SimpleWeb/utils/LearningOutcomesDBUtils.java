package org.hm.SimpleWeb.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hm.SimpleWeb.beans.LearningOutcomes;
import org.hm.SimpleWeb.jdbc.MySQLConnUtils;

public class LearningOutcomesDBUtils {
	private static final String table ="tketqua";
	private static int amountRowsLimit = 10;
	private static int amountRowsOffset = 10;
	
	private static final String idStudent = "MaSinhVien";
	private static final String idCourse = "MaKhoaHoc";
	private static final String numberOfTests = "LanThi";
	private static final String point = "Diem";
		
	public static List<LearningOutcomes> query(Connection conn, int x) 
		throws SQLException {
		String sql = "select " + idStudent + ", " + idCourse + ", " 
				+ numberOfTests + ", " + point 
				+ " from " + table
				+" limit " + amountRowsLimit + " offset " + x*amountRowsOffset;
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs = pstm.executeQuery();
		List<LearningOutcomes> list = new ArrayList<LearningOutcomes>();
		while(rs.next()) {
			LearningOutcomes mh = new LearningOutcomes();
			mh.setIdStudent(rs.getString(idStudent));
			mh.setIdCourse(rs.getString(idCourse));
			mh.setNumberOfTest(rs.getInt(numberOfTests));
			mh.setPoint(rs.getDouble(point));
			list.add(mh);
		}
		return list;
	}
	public static LearningOutcomes find(String findRowById) 
			throws SQLException {
		Connection conn = null;
		LearningOutcomes mh = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select " + idStudent + ", " + idCourse + ", " 
					+ numberOfTests + ", " + point 
					+ "from " + table + "where " + idStudent + " = ?";
			PreparedStatement pstm = conn.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);			
			pstm.setString(1, findRowById);
			
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				mh = new LearningOutcomes();
				mh.setIdStudent(rs.getString(idStudent));
				mh.setIdCourse(rs.getString(idCourse));
				mh.setNumberOfTest(rs.getInt(numberOfTests));
				mh.setPoint(rs.getDouble(point));
			}
		} catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}		
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
		return mh;
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
			pstm.setInt(3, insertRow.getNumberOfTest());
			pstm.setDouble(4, insertRow.getPoint());
			
			pstm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}finally {
			MySQLConnUtils.closeQuietly(conn);
		}
	}
	// delete all student's information
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
					+ numberOfTests +  " = ?, " + point +  " = ? "
					+ " where " + idStudent + " = ? and " + idCourse + " = ?";
				
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, updateRow.getNumberOfTest());
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
	public static void deleteIdCourse(String deleteRowById) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "delete from " + table + " where " + idCourse + " = ?";
			
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
	public static int getTotalRow() {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) from " + table);
            int totalRow = 0;
            if(rs.next()) {
            	totalRow = rs.getInt(1);
            }
            System.out.println("So dong (trong DepartmentDBUtils): " + totalRow);
            return totalRow;
        } catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        }
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
		return 0;
	}
}
