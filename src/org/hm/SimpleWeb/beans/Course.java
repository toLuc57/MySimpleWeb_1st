package org.hm.SimpleWeb.beans;

import java.sql.Date;

public class Course {
	private String id;
	private String idTeacher;
	private String idSubject;
	private Date fromDate;
	private Date toDate; 
	public Course() {
		
	}
	public Course(String id,String idTeacher,String idSubject,
			Date fromDate, Date toDate ) {
		this.id = id;
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
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date date) {
		fromDate = date;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date date) {
		toDate = date;
	}

}
