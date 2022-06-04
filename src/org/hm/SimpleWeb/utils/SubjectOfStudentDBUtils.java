package org.hm.SimpleWeb.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hm.SimpleWeb.beans.Subject;
import org.hm.SimpleWeb.jdbc.MySQLConnUtils;

public class SubjectOfStudentDBUtils {
	private static final String table ="tmonhoc";
	private static final String viewPassedSubjects = "PassedSubjects";
	private static final String viewCompulsorySubjects ="CompulsorySubjects";
	
	private static final String idSubject = "MaMonHoc";
	private static final String idStudent = "MaSinhVien";
	private static final String name = "TenMonHoc";
	private static final String theoryLesson = "SoTietLyThuyet";
	private static final String practiceLesson = "SoTietThucHanh";
	
	public static List<Subject> query(String _idStudent){
		List<Subject> list = new ArrayList<Subject>();
		String sql = "select " + idSubject + ", " + name + ", " 
				+ theoryLesson + ", " + practiceLesson 
				+ " from " + table 
				+ " where " + idSubject + " not in (select " + idSubject 
				+ " from " + viewPassedSubjects + " where " + idStudent +" = ?) " 
				+ " and " + idSubject + " in (select " + idSubject 
				+ " from " + viewCompulsorySubjects + " where " + idStudent + " = ?)";
		try {
			Connection conn = MySQLConnUtils.getMySQLConUtils();
			PreparedStatement pstm = conn.prepareStatement(sql);
	        ResultSet rs = pstm.executeQuery();
	        while(rs.next()) {
    			Subject mh = new Subject();
    			mh.setId(rs.getString(idSubject));
    			mh.setName(rs.getString(name));
    			mh.setNumberOfTheoryLesson(rs.getInt(theoryLesson));
    			mh.setNumberOfPracticeLesson(rs.getInt(practiceLesson));
    			list.add(mh);
	    	}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		return list;
	}
}
