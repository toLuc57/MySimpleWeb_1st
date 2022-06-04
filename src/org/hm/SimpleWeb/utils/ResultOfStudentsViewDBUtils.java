package org.hm.SimpleWeb.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hm.SimpleWeb.beans.ResultOfStudentsView;
import org.hm.SimpleWeb.jdbc.MySQLConnUtils;

public class ResultOfStudentsViewDBUtils {
	private static final String viewQuery = "ResultOfStudents";
	private static final String idStudent = "MaSinhVien";
	private static final String idSubject = "MaMonHoc";
	private static final String subjectName = "TenMonHoc";
	private static final String numberOfTheoryLesson = "SoTietLyThuyet";
	private static final String numberOfPracticeLesson = "SoTietThucHanh";
	private static final String numberOfTests = "LanThi";
	private static final String point = "Diem";
	
	private static final Map<String,List<ResultOfStudentsView>> mapInfoStudent 
		= new HashMap<String,List<ResultOfStudentsView>>();

	static {
		try {
			initView();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private static void initView() {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select " + idStudent + ", " + idSubject + ", " + subjectName 
					+ ", " + numberOfTheoryLesson + ", " + numberOfPracticeLesson 
					+ ", " + numberOfTests + ", " + point 
					+ " from " + viewQuery + " order by " + idStudent;
			PreparedStatement pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			// Store each studuent's result into list
			// Then store into map with: key - IDStudent, value - studuent's result
			List<ResultOfStudentsView> list = new ArrayList<ResultOfStudentsView>();
			String eachIdStudent = "";
			while(rs.next()) {
				String _idStudent = rs.getString(idStudent);
				String _idSubject = rs.getString(idSubject);
				String _subjectName = rs.getString(subjectName);
				int _numberOfTheoryLesson = rs.getInt(numberOfTheoryLesson);
				int _numberOfPracticeLesson = rs.getInt(numberOfPracticeLesson);
				int _numberOfTest = rs.getInt(numberOfTests);
				double _point = rs.getDouble(point);
				
				ResultOfStudentsView newRecord = 
						new ResultOfStudentsView(_idStudent,_idSubject, _subjectName, 
								_numberOfTheoryLesson, _numberOfPracticeLesson, _numberOfTest, _point);
				if(!eachIdStudent.equals(_idStudent) || rs.isLast()) {
					mapInfoStudent.put(_idStudent, list);
					list.clear();
					eachIdStudent = _idStudent;
				}
				else {
					list.add(newRecord);
				}
			}
			
		}
		catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
	}
	/*
	private static void initQuery() {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = query;
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String _idSubject = rs.getString(idSubject);
				String _subjectName = rs.getString(subjectName);
				int _numberOfTheoryLesson = rs.getInt(numberOfTheoryLesson);
				int _numberOfPracticeLesson = rs.getInt(numberOfPracticeLesson);
				int _numberOfTest = rs.getInt(numberOfTest);
				double _point = rs.getDouble(point);
				
				ResultOfStudentsView newRecords = 
						new ResultOfStudentsView(_idSubject, _subjectName, _numberOfTheoryLesson,
								_numberOfPracticeLesson, _numberOfTest, _point);
				mapInfoStudent.put(newRecords.getIdSubject(), newRecords);
			}
		}
		catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
	}*/
	public static List<ResultOfStudentsView> getMapInfoStudent(String _idStudent){
		return mapInfoStudent.get(_idStudent);
	}
	public static void delete(String _idStudent, String _idSubject, int _numberOfTests) {
		List <ResultOfStudentsView> list = mapInfoStudent.get(_idStudent);
		if(list == null || list.size() == 0) {
			return;
		}
		ResultOfStudentsView obj = new ResultOfStudentsView(_idStudent,_idSubject,_numberOfTests);
		if(list.contains(obj)) {
			list.remove(obj);
		}
	}
}
