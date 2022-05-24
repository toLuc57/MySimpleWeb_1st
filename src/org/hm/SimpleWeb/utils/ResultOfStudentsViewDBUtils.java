package org.hm.SimpleWeb.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.hm.SimpleWeb.beans.ResultOfStudentsView;
import org.hm.SimpleWeb.jdbc.MySQLConnUtils;

public class ResultOfStudentsViewDBUtils {
	private static final String query = "SELECT" + 
										"    tketqua.MaSinhVien," + 
										"    tmonhoc.*," + 
										"    tketqua.LanThi, " + 
										"    tketqua.Diem " + 
										"    FROM " + 
										"    qlhtsv_28.tketqua " + 
										"    INNER JOIN " + 
										"    qlhtsv_28.tkhoahoc USING (MaKhoaHoc)" + 
										"    INNER JOIN" + 
										"    qlhtsv_28.tmonhoc USING (MaMonHoc)" ;
	private static final String viewQuery = "ResultOfStudents";
	private static final String idSubject = "MaMonHoc";
	private static final String subjectName = "TenMonHoc";
	private static final String numberOfTheoryLesson = "SoTietLyThuyet";
	private static final String numberOfPracticeLesson = "SoTietThucHanh";
	private static final String numberOfTest = "LanThi";
	private static final String point = "Diem";
	
	private static final Map<String,ResultOfStudentsView> mapInfoStudent 
		= new HashMap<String,ResultOfStudentsView>();
	
	static {
		String error = initView();
		if(error != null) {
			initQuery();
		}
		
	}
	private static String initView() {
		Connection conn = null;
		String errorMessage = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select " + idSubject + ", " + subjectName + ", " + numberOfTheoryLesson 
					+ numberOfPracticeLesson + numberOfTest + point 
					+ " from " + viewQuery;
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
			errorMessage = e.getMessage();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
		return errorMessage;
	}
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
	}
	public static Map<String,ResultOfStudentsView> getMapInfoStudent(){
		return mapInfoStudent;
	}
}
