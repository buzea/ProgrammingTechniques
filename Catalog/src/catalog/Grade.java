package catalog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled Catalog
//  @ File Name : Grade.java
//  @ Date : 5/27/2014
//  @ Author :  Buzea Vlad-Calin
//
//




public class Grade {
	private Student student;
	private Course course;
	private Calendar date;
	private double mark;
	private SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	
	public Grade(Student student,Course course,Calendar date,double mark){
		this.setStudent(student);
		this.setCourse(course);
		this.setDate(date);
		this.setMark(mark);
	}
	
	public boolean equals(Object o){
		Grade g=(Grade)o;
		if(!student.getSSID().equals(g.getStudent().getSSID()))
			return false;
		if(!course.getIdCourse().equals(g.getCourse().getIdCourse()))
			return false;
		if(!date.equals(g.getDate()))
			return false;
		return true;
	}
	
	/**
	 * Returns the information of the object in String format.
	 */
	public String toString(){
		String x="\n";
		x+=course.getName()+" | "+student.getFirstName()+" " +student.getLastName()+" | "+format1.format(date.getTime()) +" | "+mark;
		return x;
	}

	/**
	 * Getter method.
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * Setter method.
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * Getter method.
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * Setter method.
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * Getter method.
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * Setter method.
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * Getter method.
	 * @return the mark
	 */
	public double getMark() {
		return mark;
	}

	/**
	 * Setter method.
	 * @param mark the mark to set
	 */
	public void setMark(double mark) {
		this.mark = mark;
	}
	/**
	 * Returns the date in yyyy-MM-dd format
	 * @return String with the date
	 */
	public String gatFormatedDate(){
		return format1.format(date.getTime());
	}
}
