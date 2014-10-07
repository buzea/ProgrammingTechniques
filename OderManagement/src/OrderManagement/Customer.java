package OrderManagement;

import java.io.Serializable;

public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8651928823117853212L;
	private String firstName,lastName,address,email,phone,UID;

/**
 * Builds a new Customer with the minimum data fields
 * @param UID	: Customer unique identification number
 * @param firstName : Customer first name
 * @param lastName : Customer last name
 * @param address : Customer address
 */
	public Customer(String UID,String firstName,String lastName,String address){
		this.setName(firstName,lastName);
		this.setAddress(address);
		setPhone(null);
		setEmail(null);
		this.UID=UID;
	}
	/**
	 * Builds a new Customer with the specified fields
	 * @param UID	: Customer unique identification number
	 * @param firstName : Customer first name
	 * @param lastName : Customer last name
	 * @param address : Customer address
	 * @param email	  : Customer email
	 * @param phone	  : Customer phone number
	 */
	public Customer(String UID,String firstName,String lastName,String address,String email,String phone){
		this.setName(firstName,lastName);
		this.setAddress(address);
		this.setPhone(phone);
		this.setEmail(email);
		this.UID=UID;
	}
	/**
	 * Builds a new Customer with the specified fields
	 * @param UID	: Customer unique identification number
	 * @param firstName : Customer first name
	 * @param lastName : Customer last name
	 * @param address : Customer address
	 * @param email	  : Customer email
	 */
	public Customer(String UID,String firstName,String lastName,String address,String email){
		this(UID,firstName,lastName,address,email,null);
	}
	/**
	 * Returns ID: Name, address phone number and email of caller customer in a String
	 *@return String containing customer info
	 */
	public String toString(){
		String x=""+UID+": "+firstName+" "+lastName+", "+address;
		if(phone!=null)
			x+=", telephone:"+phone;
		if(email!=null)
			x+=", email:"+email;
		return x;
	}
	/**
	 * Checks for equality between caller Customer and parameter Customer
	 * @return true if UIDs match, false otherwise
	 */
	public boolean equals(Object o){
		Customer temp=(Customer)o;
		if(temp.getUID()==this.getUID())
			return true;
		return false;
	}
	
	/**Getter method for Customer phone number
	 * @return the phone number of the customer in String format
	 */
	public String getPhone() {
		return phone;
	}
	/**Modifies the phone number associated to the caller Customer
	 * @param phone = new phone number
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**Getter method for the Customer's first name
	 * @return the firstName of the customer in String format
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Renames a customer
	 * @param firstName = new first name
	 * @param lastName 	= new last name
	 */
	public void setName(String firstName,String lastName) {
		this.firstName = firstName;
		this.lastName  = lastName;
	}
	/**Retrieves the address of a customer
	 * @return the address of the caller customer
	 */
	public String getAddress() {
		return address;
	}
	/**Edits the address corresponding to a customer
	 * @param address = the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**Retrieves the last name of a customer
	 * @return the lastName in String format
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**Retrieves the email of a customer
	 * @return the email of the customer is String format
	 */
	public String getEmail() {
		return email;
	}
	/**Edits the email of a customer
	 * @param email = the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**Returns the unique identification of the customer
	 * @return the UID of the caller customer
	 */
	public String getUID() {
		return UID;
	}
	
	
	
}
