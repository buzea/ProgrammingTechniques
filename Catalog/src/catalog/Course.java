package catalog;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled Catalog
//  @ File Name : Course.java
//  @ Date : 5/27/2014
//  @ Author :  Buzea Vlad-Calin
//
//




public class Course {
	private String idCourse;
	private String name;
	private String day;
	private String hour;
	private Group group;
	private Professor professor;
	private Room room;
	public Course(String idCourse,String name,String zi,String ora,Group group,Professor professor,Room room){
		this.setIdCourse(idCourse);
		this.setDay(zi);
		this.setHour(ora);
		this.setGroup(group);
		this.setProfessor(professor);
		this.setRoom(room);
		this.setName(name);
	}
	/**
	 * Returns the information of the Course in String format.
	 */
	public String toString(){
		String x="\n";
		x+=name+" "+room.getIdRoom()+" "+group.getIdg()+" "+day+":"+hour;
		return x;
	}
	
	/**
	 * Getter method.
	 * @return the idCourse
	 */
	public String getIdCourse() {
		return idCourse;
	}
	/**
	 * Setter method.
	 * @param idCourse the idCourse to set
	 */
	public void setIdCourse(String idCourse) {
		this.idCourse = idCourse;
	}
	
	/**
	 * Getter method.
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}
	/**
	 * Setter method.
	 * @param group the group to set
	 */
	public void setGroup(Group group) {
		this.group = group;
	}
	/**
	 * Getter method.
	 * @return the professor
	 */
	public Professor getProfessor() {
		return professor;
	}
	/**
	 * Setter method.
	 * @param professor the professor to set
	 */
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	/**
	 * Getter method.
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}
	/**
	 * Setter method.
	 * @param room the room to set
	 */
	public void setRoom(Room room) {
		this.room = room;
	}
	/**
	 * Getter method.
	 * @return the day
	 */
	public String getDay() {
		return day;
	}
	/**
	 * Setter method.
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}
	/**
	 * Getter method.
	 * @return the hour
	 */
	public String getHour() {
		return hour;
	}
	/**
	 * Setter method.
	 * @param hour the hour to set
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}
	/**
	 * Getter method.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Setter method.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
