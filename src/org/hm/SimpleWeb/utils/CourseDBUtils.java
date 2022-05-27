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

import org.hm.SimpleWeb.beans.Course;
import org.hm.SimpleWeb.jdbc.MySQLConnUtils;

public class CourseDBUtils {
	private static final String table ="tkhoahoc";
	private static int amountRowsLimit = 10;
	private static int amountRowsOffset = 10;
	private static final String textInID = "KH";
	
	private static final String id = "MaKhoaHoc";
	private static final String idTeacher = "MaGiaoVien";
	private static final String idSubject = "MaMonHoc";
	private static final String fromDate = "NgayBatDau";
	private static final String toDate = "NgayKetThuc";
	
	private static List<String> listColumnName = new ArrayList<String>();
	private static List<String> listID = new ArrayList<String>(); 
	private static Map<String,String> mapColumn = new HashMap<String,String>();
	
	public static final String className = "CourseDBUtils";
	
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
			
            PreparedStatement pstm = conn.prepareStatement("select * from " + table);
            ResultSet rs = pstm.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            for(int i = 1; i <= rsmd.getColumnCount();++i) {
            	listColumnName.add(rsmd.getColumnName(i));
            	mapColumn.put(rsmd.getColumnName(i), rsmd.getColumnTypeName(i));
            }
            while(rs.next()) {
            	listID.add(rs.getString(id));
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        }
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
	}
	
	public static List<Course> query(Connection conn, int x, String queryWhere) 
		throws SQLException {
		String sql = "select " + id + ", " + idTeacher + ", " 
				+ idSubject + ", " + fromDate + ", " + toDate 
				+ " from " + table + queryWhere
				+ " limit " + amountRowsLimit + " offset " + x*amountRowsOffset;
		//System.out.println(sql);
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs = pstm.executeQuery();
		List<Course> list = new ArrayList<Course>();
		while(rs.next()) {
			Course mh = new Course();
			mh.setIdCourse(rs.getString(id));
			mh.setIdTeacher(rs.getString(idTeacher));
			mh.setFromDate(rs.getString(fromDate));
			mh.setIdSubject(rs.getString(idSubject));
			mh.setToDate(rs.getString(toDate));
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
				mh.setFromDate(rs.getString(fromDate));
				mh.setIdSubject(rs.getString(idSubject));
				mh.setToDate(rs.getString(toDate));
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
					+ "(" + id + ", " + idTeacher + ", " + idSubject
					+ ", " + fromDate + ", " + toDate + ") "
					+ " values (?,?,?,?,?)";
			PreparedStatement pstm = conn.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String newID = getNewID();
			pstm.setString(1, newID);
			pstm.setString(2, insertRow.getIdTeacher());
			pstm.setString(3, insertRow.getIdSubject());
			pstm.setString(4, insertRow.getFromDate());
			pstm.setString(5, insertRow.getToDate());
			
			pstm.executeUpdate();
			listID.add(newID);
		} catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}finally {
			MySQLConnUtils.closeQuietly(conn);
		}		
	}
	public static String delete(String deleteRowById) 
			throws SQLException {
		Connection conn = null;
		String errorMessage = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			
			//LearningOutcomesDBUtils.deleteIdCourse(deleteRowById);
			String sql = "delete from " + table + " where " + id + "=?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1,deleteRowById);
			
			pstm.executeUpdate();
			listID.remove(deleteRowById);
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
			pstm.setString(3, updateRow.getFromDate());
			pstm.setString(4, updateRow.getToDate());
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
	public static int getTotalRow(String queryWhere) {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) from " + table + queryWhere);
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

	public static List<String> getColumnName() {
		return listColumnName;
	}

	public static Map<String,String> getAllColumnNameAndTypeName() {
		return mapColumn;
	}
	private static String getNewID() {
		String numberZeroInID = "";
		if(listID == null || listID.size() == 0)
		{
			for(int i = 1; i < MyUtils.numberInID;++i) {
				numberZeroInID = numberZeroInID.concat("0");
			}
			return textInID + numberZeroInID + "1";
		}
		String lastID = listID.get(listID.size() - 1);
		int numberInID = Integer.parseInt(lastID.substring(textInID.length())) + 1;
		String numberInIDString = String.valueOf(numberInID);
		for(int i = numberInIDString.length(); i < MyUtils.numberInID; ++i) {
			numberZeroInID = numberZeroInID.concat("0");
		}
		// newID = textInID + numberZeroInID + numberInIDString
		String newID = textInID;
		newID = newID.concat(numberZeroInID);
		newID = newID.concat(numberInIDString);
		return newID;
	}
	public static List<String> getListID(){
		return listID;
	}
}
