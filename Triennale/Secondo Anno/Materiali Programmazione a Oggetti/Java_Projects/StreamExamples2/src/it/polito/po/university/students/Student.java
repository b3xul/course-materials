package it.polito.po.university.students;

import java.util.Collection;
import java.util.LinkedList;

public class Student {
	public enum Gender {
		F, M
	}
	public final static Gender F = Gender.F;
	public final static Gender M = Gender.M;
	
	private int id;
	private String first;
	private String last;
	private Gender gender;
	private Collection<Course> courses;


	public Student(int id, String first, String last, Gender gender) {
		this.id = id;
		this.first = first;
		this.last = last;
		this.gender = gender;
		courses = new LinkedList<>();
	}

	public boolean isFemale(){
		return gender == F;
	}

	public boolean isMale(){
		return gender == M;
	}

	public int getId() {
		return id;
	}
	
	public Gender getGender(){
		return gender;
	}


	public String getFirst() {
		return first;
	}


	public String getLast() {
		return last;
	}
	
	public String toString(){
		return "(" + id + ") " + last + ", " + first;
	}
	
	public Collection<Course> enrolledIn(){
		return courses;
	}

	public void enroll(Course c) {
		courses.add(c);
	}
}
