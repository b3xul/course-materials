package it.polito.po.university.students;

public class Course {

	private String code;
	private String title;
	public Course(String code, String title) {
		this.code = code;
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public String getTitle() {
		return title;
	}
	public String toString(){
		return "(" + code + ") " + title;
	}
	
}
