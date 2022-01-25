package org.hm.SimpleWeb.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hm.SimpleWeb.beans.Subject;
import org.hm.SimpleWeb.jdbc.MySQLConnUtils;

public class SubjectDBUtils {
	private static final String table ="tmonhoc";
	private static final String id = "MaMonHoc";
	private static final String name = "TenMonHoc";
	private static final String theoryLesson = "SoTietLyThuyet";
	private static final String practiceLesson = "SoTietThucHanh";
		
	public static List<Subject> query(Connection conn) 
		throws SQLException {
		String sql = "select " + id + ", " + name + ", " 
				+ theoryLesson + ", " + practiceLesson 
				+ "from " + table;
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs = pstm.executeQuery();
		List<Subject> list = new ArrayList<Subject>();
		while(rs.next()) {
			Subject mh = new Subject();
			mh.setId(rs.getString(id));
			mh.setName(rs.getString(name));
			mh.setNumberOfTheoryLesson(rs.getInt(theoryLesson));
			mh.setNumberOfPracticeLesson(rs.getInt(practiceLesson));
			list.add(mh);
		}
		return list;
	}
	public static Subject find(String findRowById) 
			throws SQLException {
		Connection conn = null;
		Subject mh = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select " + id + ", " + name + ", " 
					+ theoryLesson + ", " + practiceLesson 
					+ "from " + table + "where " + id + " = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, findRowById);
			
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				mh = new Subject();
				mh.setId(rs.getString(id));
				mh.setName(rs.getString(name));
				mh.setNumberOfTheoryLesson(rs.getInt(theoryLesson));
				mh.setNumberOfPracticeLesson(rs.getInt(practiceLesson));
			}
		}
		catch(ClassNotFoundException | SQLException e ) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn); 
		}
		return mh;
	}
	public static void insert(Subject insertRow) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "insert into " + table 
					+ " (" + name + ", " + theoryLesson + ", " + practiceLesson + ")"
					+ " values (?,?,?,?)" ;
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, insertRow.getName());
			pstm.setInt(2, insertRow.getNumberOfTheoryLesson());
			pstm.setInt(3, insertRow.getNumberOfPracticeLesson());
			
			pstm.executeUpdate();
		}
		catch(ClassNotFoundException | SQLException e) {
			MySQLConnUtils.closeQuietly(conn); 
			e.printStackTrace();
		}
		finally {
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
			
		}
		catch(ClassNotFoundException | SQLException e) {
			MySQLConnUtils.closeQuietly(conn); 
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn); 
		}
	}
	public static void update(Subject updateRow) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			
			String sql = "update " + table + " set " 
				+ name + " = ?, " + theoryLesson +  " = ?, " + practiceLesson +  " = ?, "
				+ " where " + id + " = ?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1,updateRow.getName());
			pstm.setInt(2, updateRow.getNumberOfTheoryLesson());
			pstm.setInt(3, updateRow.getNumberOfPracticeLesson());
			
			pstm.executeUpdate();
		}
		catch(ClassNotFoundException | SQLException e) {
			MySQLConnUtils.closeQuietly(conn); 
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn); 
		}
	}
}
