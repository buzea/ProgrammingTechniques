package Bank;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = -8251348432102973442L;
	private String firstName,lastName,SSID,email,address;
	private long phone;
	
	public Person(String SSID,String firstName,String lastName,String address,String email,long phone){
		this.setSSID(SSID);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setSSID(SSID);
		this.setEmail(email);
		this.setAddress(address);
		this.setPhone(phone);
		
	}
	
	
	public Person(String SSID,String firstName,String lastName,String address)
	{
		this(SSID,firstName,lastName,address,null,-1);
	}
	@Override
	public String toString(){
		String x=new String();
		x=SSID+"; "+firstName+" "+lastName+"; email:"+email+"; address:"+address+"; phone number:"+phone+"; ";
		return x;
	}

	/**Getter method for the address
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**Setter method for the address
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**Getter method for SSID
	 * @return the SSID
	 */
	public String getSSID() {
		return SSID;
	}

	/**Setter method for SSID
	 * @param sSID the sSID to set
	 */
	public void setSSID(String sSID) {
		SSID = sSID;
	}

	/**Getter method for Person's FirstName
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**Setter method for Person's first name
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**Getter method for the phone number
	 * @return the phone
	 */
	public long getPhone() {
		return phone;
	}

	/**Setter method for the phone
	 * @param phone the phone to set
	 */
	public void setPhone(long phone) {
		this.phone = phone;
	}

	/**Getter method for the last name
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**Setter method for Person's last name
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**Getter method for the email address
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**Setter method for the email address
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Verifies if two Persons are equal
	 * @param person to verify equality with the caller
	 * @return true if the caller and parameter are equal, false otherwise
	 */
	@Override
	public boolean equals(Object person){
		Person p=(Person)person;
		return SSID.equals(p.getSSID());
	}
}
