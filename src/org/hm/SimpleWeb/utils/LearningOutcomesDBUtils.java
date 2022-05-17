package org.hm.SimpleWeb.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private static List<String> listColumnName = new ArrayList<String>();
	private static Map<String,String> mapColumn = new HashMap<String,String>();
	
	public static final String className = "LearningOutcomesDBUtils";
	
	static {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void init() throws IOException{
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			
            PreparedStatement pstm = conn.prepareStatement("select * from " + table + " limit 1");
            ResultSet rs = pstm.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            for(int i = 1; i <= rsmd.getColumnCount();++i) {
            	listColumnName.add(rsmd.getColumnName(i));
            	mapColumn.put(rsmd.getColumnName(i), rsmd.getColumnTypeName(i));
            }
        } catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        }
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
	}
	
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
	public static LearningOutcomes find(String _idStudent,String _idCourse) 
			throws SQLException {
		Connection conn = null;
		LearningOutcomes mh = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select " + idStudent + ", " + idCourse + ", " 
					+ numberOfTests + ", " + point 
					+ "from " + table 
					+ " where " + idStudent + " = ?"
					+ " and " + idCourse + " = ?";
			PreparedStatement pstm = conn.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);			
			pstm.setString(1, _idStudent);
			pstm.setString(2, _idStudent);
			
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
	
	public static String delete(String _idStudent,String _idCourse) 
			throws SQLException {
		Connection conn = null;
		String errorMessage = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "delete from " + table 
					+ " where " + idStudent + " = ?"
					+ " and " + idCourse + " = ?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1,_idStudent);
			pstm.setString(2,_idCourse);
			
			pstm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			errorMessage = e.getMessage();
			e.printStackTrace();
		}	
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}	
		return errorMessage;
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
	public static List<String> getColumnName() {
		return listColumnName;
	}

	public static Map<String,String> getAllColumnNameAndTypeName() {
		return mapColumn;
	}	
}
