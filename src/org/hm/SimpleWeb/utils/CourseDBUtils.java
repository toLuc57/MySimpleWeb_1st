package org.hm.SimpleWeb.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hm.SimpleWeb.beans.Course;
import org.hm.SimpleWeb.jdbc.MySQLConnUtils;

public class CourseDBUtils {
	private static final String table ="tkhoahoc";
	private static int amountRowsLimit = 10;
	private static int amountRowsOffset = 10;
	
	private static final String id = "MaKhoaHoc";
	private static final String idTeacher = "MaGiaoVien";
	private static final String idSubject = "MaMonHoc";
	private static final String fromDate = "NgayBatDau";
	private static final String toDate = "NgayKetThuc";
		
	public static List<Course> query(Connection conn, int x) 
		throws SQLException {
		String sql = "select " + id + ", " + idTeacher + ", " 
				+ idSubject + ", " + fromDate + ", " + toDate 
				+ " from " + table 
				+" limit " + amountRowsLimit + " offset " + x*amountRowsOffset;
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs = pstm.executeQuery();
		List<Course> list = new ArrayList<Course>();
		while(rs.next()) {
			Course mh = new Course();
			mh.setIdCourse(rs.getString(id));
			mh.setIdTeacher(rs.getString(idTeacher));
			mh.setFromDate(rs.getDate(fromDate));
			mh.setIdSubject(rs.getString(idSubject));
			mh.setToDate(rs.getDate(toDate));
			list.add(mh);
		}
		return list;
	}
	public static Course find(String findRowById) 
			throws SQLException {
		Connection conn = null;
		Course mh = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select *from " + table + " where " + id + " =?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, findRowById);
			
			ResultSet rs = pstm.executeQuery();
			System.out.println(sql);
			System.out.println(rs.first());
			if(rs.next()) {
				mh = new Course();
				mh.setIdCourse(rs.getString(id));
				mh.setIdTeacher(rs.getString(idTeacher));
				mh.setFromDate(rs.getDate(fromDate));
				mh.setIdSubject(rs.getString(idSubject));
				mh.setToDate(rs.getDate(toDate));
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
	public static void insert(Course insertRow) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "insert into " + table 
					+ "(" + idTeacher + ", " + idSubject
					+ ", " + fromDate + ", " + toDate + ") "
					+ " values (?,?,?,?)" ;
			PreparedStatement pstm = conn.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			pstm.setString(1, insertRow.getIdTeacher());
			pstm.setString(2, insertRow.getIdSubject());
			pstm.setDate(3, insertRow.getFromDate());
			pstm.setDate(4, insertRow.getToDate());
			
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
			
			LearningOutcomesDBUtils.deleteIdCourse(deleteRowById);
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
	public static void update(Course updateRow) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "update " + table + " set " 
					+ idTeacher + "=?, " + idSubject +  "=?, "
					+ fromDate + "=?, " + toDate +  "=? "
					+ " where " + id + "=?";
				
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, updateRow.getIdTeacher());
			pstm.setString(2, updateRow.getIdSubject());
			pstm.setDate(3, updateRow.getFromDate());
			pstm.setDate(4, updateRow.getToDate());
			pstm.setString(5, updateRow.getIdCourse());
			
			pstm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}finally {
			MySQLConnUtils.closeQuietly(conn);
		}
	}
	public static void deleteIdTeacher(String deleteRowById) {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "delete from " + table + " where " + idTeacher + "=?";
			
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
	public static void deleteIdSubject(String deleteRowById) {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "delete from " + table + " where " + idSubject + "=?";
			
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
            System.out.println("So dong (trong CourseDBUtils): " + totalRow);
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
