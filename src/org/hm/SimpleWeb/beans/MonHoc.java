package org.hm.SimpleWeb.beans;

public class MonHoc {
	private String id;
	private String name;
	private int numberOfTheoryLesson;
	private int numberOfPracticeLesson;
	public MonHoc() {
		
	}
	public MonHoc(String id,String name,int numberOfTheoryLesson, 
			int numberOfPracticeLesson) {
		this.id = id;
		this.name = name;
		this.numberOfTheoryLesson = numberOfTheoryLesson;
		this.numberOfPracticeLesson = numberOfPracticeLesson;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getnumberOfTheoryLesson() {
		return numberOfTheoryLesson;
	}
	public void setnumberOfTheoryLesson(int numberOfTheoryLesson) {
		this.numberOfTheoryLesson = numberOfTheoryLesson;
	}
	public int getnumberOfPracticeLesson() {
		return numberOfPracticeLesson;
	}
	public void setnumberOfPracticeLesson(int numberOfPracticeLesson) {
		this.numberOfPracticeLesson = numberOfPracticeLesson;
	}
}
