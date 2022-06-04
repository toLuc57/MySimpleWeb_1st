package org.hm.SimpleWeb.beans;

public class ResultOfStudentsView {
	private String idStudent;
	private String idSubject;
	private String subjectName;
	private int numberOfTheoryLesson;
	private int numberOfPracticeLesson;
	private int numberOfTest;
	private double point;

	public ResultOfStudentsView(String idStudent,String idSubject,int numberOfTest) {
		this.idStudent = idStudent;
		this.idSubject = idSubject;
		this.numberOfTest = numberOfTest;
	}	
	public ResultOfStudentsView(String idStudent,String idSubject, String subjectName,
			int numberOfTheoryLesson, int numberOfPracticeLesson, 
			int numberOfTest, double point) {
		this.idStudent = idStudent;
		this.idSubject = idSubject;
		this.subjectName = subjectName;
		this.numberOfTheoryLesson = numberOfTheoryLesson;
		this.numberOfPracticeLesson = numberOfPracticeLesson;
		this.numberOfTest = numberOfTest;
		this.point = point;
	}
	public String getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(String idStudent) {
		this.idStudent =  idStudent;
	}
	public String getIdSubject() {
		return idSubject;
	}
	public void setIdSubject(String idSubject) {
		this.idSubject =  idSubject;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName =  subjectName;
	}
	public int getNumberOfTheoryLesson() {
		return numberOfTheoryLesson;
	}
	public void setNumberOfTheoryLesson(int numberOfTheoryLesson) {
		this.numberOfTheoryLesson =  numberOfTheoryLesson;
	}
	public int getNumberOfPracticeLesson() {
		return numberOfPracticeLesson;
	}
	public void setNumberOfPracticeLesson(int numberOfPracticeLesson) {
		this.numberOfPracticeLesson =  numberOfPracticeLesson;
	}
	public int getNumberOfTest() {
		return numberOfTest;
	}
	public void numberOfTest(int numberOfTest) {
		this.numberOfTest =  numberOfTest;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point =  point;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		// Nếu obj không thuộc lớp ResultOfStudentsView
		// trả về false
		if (!(obj instanceof ResultOfStudentsView)) {
			return false;
		}
		ResultOfStudentsView _obj = (ResultOfStudentsView) obj;
		return idStudent.equals(_obj.idStudent) && idSubject.equals(_obj.idSubject)
				&& point == _obj.point;
	}
	
}
