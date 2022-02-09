package org.hm.SimpleWeb.beans;

public class LearningOutcomes {
	private String idStudent;
	private String idCourse;
	private int numberOfTest;
	private double point;
	public LearningOutcomes() {
		
	}
	public LearningOutcomes(String idStudent,String idCourse,int numberOfTest,
			double point) {
		this.idStudent = idStudent;
		this.idCourse = idCourse;
		this.numberOfTest = numberOfTest;
		this.point = point;
	}
	public String getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(String idStudent) {
		this.idStudent = idStudent;
	}
	public String getIdCourse() {
		return idCourse;
	}
	public void setIdCourse(String idCourse) {
		this.idCourse = idCourse;
	}
	public int getNumberOfTest() {
		return numberOfTest;
	}
	public void setNumberOfTest(int numberOfTest) {
		this.numberOfTest = numberOfTest;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
}
