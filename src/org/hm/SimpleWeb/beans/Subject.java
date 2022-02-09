package org.hm.SimpleWeb.beans;

public class Subject {
	private String id;
	private String name;
	private int numberOfTheoryLesson;
	private int numberOfPracticeLesson;
	public Subject() {
		
	}
	public Subject(String name,int numberOfTheoryLesson, 
			int numberOfPracticeLesson) {
		id = null;
		this.name = name;
		this.numberOfTheoryLesson = numberOfTheoryLesson;
		this.numberOfPracticeLesson = numberOfPracticeLesson;
	}
	public Subject(String id,String name,int numberOfTheoryLesson, 
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
	public int getNumberOfTheoryLesson() {
		return numberOfTheoryLesson;
	}
	public void setNumberOfTheoryLesson(int numberOfTheoryLesson) {
		this.numberOfTheoryLesson = numberOfTheoryLesson;
	}
	public int getNumberOfPracticeLesson() {
		return numberOfPracticeLesson;
	}
	public void setNumberOfPracticeLesson(int numberOfPracticeLesson) {
		this.numberOfPracticeLesson = numberOfPracticeLesson;
	}
}
