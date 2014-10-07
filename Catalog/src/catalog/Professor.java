package catalog;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled Catalog
//  @ File Name : Professor.java
//  @ Date : 5/27/2014
//  @ Author :  Buzea Vlad-Calin
//
//




public class Professor {
	private String SSID;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String departament;
	public Professor(String SSID,String firstName,String lastName,String email,String phone,String departament){
		this.setSSID(SSID);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setDepartament(departament);
	}
	/**
	 * Returns the information of the Professor in String format.
	 */
	public String toString(){
		String x="\n";
		x+=firstName+" "+lastName;
		return x;
	}
	/**
	 * Getter method.
	 * @return the sSID
	 */
	public String getSSID() {
		return SSID;
	}
	/**
	 * Setter method.
	 * @param sSID the sSID to set
	 */
	public void setSSID(String sSID) {
		SSID = sSID;
	}
	/**
	 * Getter method.
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Setter method.
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Getter method.
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Setter method.
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Getter method.
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Setter method.
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Getter method.
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * Setter method.
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * Getter method.
	 * @return the departament
	 */
	public String getDepartament() {
		return departament;
	}
	/**
	 * Setter method.
	 * @param departament the departament to set
	 */
	public void setDepartament(String departament) {
		this.departament = departament;
	}
	
}