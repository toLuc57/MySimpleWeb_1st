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

import org.hm.SimpleWeb.beans.Department;
import org.hm.SimpleWeb.jdbc.MySQLConnUtils;

public class DepartmentDBUtils {
	
	private static final String table ="tkhoa";
	private static int amountRowsLimit = 10;
	private static int amountRowsOffset = 10;
	private static final String textInID = "K";
	
	private static final String id = "MaKhoa";
	private static final String name = "TenKhoa";
	private static final String address = "DiaChi";
	private static final String telephone = "DienThoai";
	
	private static List<String> listColumnName = new ArrayList<String>();
	private static List<String> listID = new ArrayList<String>(); 
	private static Map<String,String> mapColumn = new HashMap<String,String>();
	
	public static final String className = "DepartmentDBUtils";
	
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
			
            PreparedStatement pstm = conn.prepareStatement("select * from " + table );
            ResultSet rs = pstm.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            for(int i = 1; i <= rsmd.getColumnCount();++i) {
            	listColumnName.add(rsmd.getColumnName(i));
            	mapColumn.put(rsmd.getColumnName(i), rsmd.getColumnTypeName(i));
            }
            while(rs.next()) {
            	listID.add(rs.getString(id));
            }
        } catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        }
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
	}
	
	public static List<Department> query(Connection conn,int x,String queryWhere) 
		throws SQLException {
		String sql = "select " + id + ", " + name + ", " 
				+ address + ", " + telephone 
				+ " from " + table + queryWhere
				+" limit " + amountRowsLimit + " offset " + x*amountRowsOffset;
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs = pstm.executeQuery();
		List<Department> list = new ArrayList<Department>();
		while(rs.next()) {
			Department mh = new Department();
			mh.setId(rs.getString(id));
			mh.setName(rs.getString(name));
			mh.setAddress(rs.getString(address));
			mh.setTelephone(rs.getString(telephone));
			list.add(mh);
		}
		return list;
	}
	public static Department find(String findRowById) 
			throws SQLException {
		Connection conn = null;
		Department mh = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select " + id + ", " + name + ", " 
					+ address + ", " + telephone 
					+ " from " + table + " where " + id + "=?";
			PreparedStatement pstm = conn.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			pstm.setString(1, findRowById);
			
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				mh = new Department();
				mh.setId(rs.getString(id));
				mh.setName(rs.getString(name));
				mh.setAddress(rs.getString(address));
				mh.setTelephone(rs.getString(telephone));
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
	public static void insert(Department insertRow) 
			throws SQLException {
		Connection conn = null;
		try{
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "insert into " + table 
					+ " (" + id + ", " + name + ", " 
					+ address + ", " + telephone + ") "
					+ " values (?,?,?,?)" ;
		
			PreparedStatement pstm = conn.prepareStatement(sql);
			String newID = getNewID();
			pstm.setString(1, newID);
			pstm.setString(2, insertRow.getName());
			pstm.setString(3, insertRow.getAddress());
			pstm.setString(4, insertRow.getTelephone());
			
			pstm.executeUpdate();
			listID.add(newID);
		} 
		catch(ClassNotFoundException | SQLException e) {
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
			
			//TeacherDBUtils.deleteIdDepartment(deleteRowById);
			//StudentDBUtils.deleteIdDepartment(deleteRowById);
			
			String sql = "delete from " + table + " where " + id + " = ?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1,deleteRowById);
			
			pstm.executeUpdate();
			listID.remove(deleteRowById);
		} catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			errorMessage = e.getMessage();
			e.printStackTrace();
		} finally {
			MySQLConnUtils.closeQuietly(conn);
		}
		return errorMessage;
	}
	public static void update( Department updateRow) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "update " + table + " set " 
					+ name + " = ?, " + address +  " = ?, " + telephone +  " = ? "
					+ " where " + id + " = ?";
			System.out.println(sql);
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, updateRow.getName());
			pstm.setString(2, updateRow.getAddress());
			pstm.setString(3, updateRow.getTelephone());
			pstm.setString(4, updateRow.getId());
			
			pstm.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		} finally {
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
