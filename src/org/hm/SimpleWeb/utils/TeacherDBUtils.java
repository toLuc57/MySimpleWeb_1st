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

import org.hm.SimpleWeb.beans.Teacher;
import org.hm.SimpleWeb.jdbc.MySQLConnUtils;

public class TeacherDBUtils {	
	private static final String table ="tgiaovien";
	private static int amountRowsLimit = 10;
	private static int amountRowsOffset = 10;
	
	private static final String id = "MaGiaoVien";
	private static final String name = "TenGiaoVien";
	private static final String degree = "HocVi";
	private static final String teplephone = "SoDienThoai";
	private static final String idDepartment = "MaKhoa";
		
	private static List<String> listColumnName = new ArrayList<String>();
	private static Map<String,String> mapColumn = new HashMap<String,String>();
	
	public static final String className = "TeacherDBUtils";
	
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
	
	public static List<Teacher> query(Connection conn, int x) 
		throws SQLException {
		String sql = "select " + id + ", " + name + ", " 
				+ degree + ", " + teplephone + ", " + idDepartment 
				+ " from " + table 
				+" limit " + amountRowsLimit + " offset " + x*amountRowsOffset;
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs = pstm.executeQuery();
		List<Teacher> list = new ArrayList<Teacher>();
		while(rs.next()) {
			Teacher mh = new Teacher();
			mh.setId(rs.getString(id));
			mh.setName(rs.getString(name));
			mh.setTelephone(rs.getString(teplephone));
			mh.setDegree(rs.getString(degree));
			mh.setIdDepartment(rs.getString(idDepartment));
			list.add(mh);
		}
		return list;
	}
	public static Teacher find(String findRowById) 
			throws SQLException {
		Connection conn = null;
		Teacher mh = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select *from " + table + " where " + id + " =?";
			PreparedStatement pstm = conn.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			pstm.setString(1, findRowById);
			
			ResultSet rs = pstm.executeQuery();
			System.out.println(sql);
			System.out.println("id: " + findRowById );
			if(rs.next()) {
				mh = new Teacher();
				mh.setId(rs.getString(id));
				mh.setName(rs.getString(name));
				mh.setTelephone(rs.getString(teplephone));
				mh.setDegree(rs.getString(degree));
				mh.setIdDepartment(rs.getString(idDepartment));
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
	public static void insert(Teacher insertRow) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "insert into " + table 
					+ "(" + name + ", " + degree
					+ ", " + teplephone + ", " + idDepartment + ") "
					+ " values (?,?,?,?)" ;
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, insertRow.getName());
			pstm.setString(2, insertRow.getDegree());
			pstm.setString(3, insertRow.getTelephone());
			pstm.setString(4, insertRow.getIdDepartment());
			
			pstm.executeUpdate();
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
			String sql = "delete from " + table + " where " + id + "=?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1,deleteRowById);
			
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
	public static void update(Teacher updateRow) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "update " + table + " set " 
					+ name + "=?, " + degree +  "=?, "
					+ teplephone + "=?, " + idDepartment +  "=? "
					+ " where " + id + "=?";
				
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, updateRow.getName());
			pstm.setString(2, updateRow.getDegree());
			pstm.setString(3, updateRow.getTelephone());
			pstm.setString(4, updateRow.getIdDepartment());
			pstm.setString(5, updateRow.getId());
			
			pstm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}finally {
			MySQLConnUtils.closeQuietly(conn);
		}
	}
	public static void deleteIdDepartment( String deleteRowById) {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "delete from " + table + " where " + idDepartment + "=?";
			
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
