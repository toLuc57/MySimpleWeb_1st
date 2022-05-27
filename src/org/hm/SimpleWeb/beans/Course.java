package org.hm.SimpleWeb.beans;

public class Course {
	private String id;
	private String idTeacher;
	private String idSubject;
	private String fromDate;
	private String toDate; 
	public Course() {
		
	}
	public Course(String idTeacher,String idSubject,
			String fromDate, String toDate ) {
		this.idTeacher = idTeacher;
		this.idSubject = idSubject;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	public String getIdCourse() {
		return id;
	}
	public void setIdCourse(String id) {
		this.id = id;
	}
	public String getIdTeacher() {
		return idTeacher;
	}
	public void setIdTeacher(String id) {
		idTeacher = id;
	}
	public String getIdSubject() {
		return idSubject;
	}
	public void setIdSubject(String id) {
		idSubject = id;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String date) {
		fromDate = date;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String date) {
		toDate = date;
	}

}
