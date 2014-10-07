package Bank;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Account implements Serializable {
	private static final long serialVersionUID = 5148835236300045747L;
	private String ID;
	private Person owner;
	private double balance;
	private String log;
	private int count;
	private static int accountsNb;
	private Date startDate;
	protected SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd"); 
	
	public Account(String ID,Person owner,double balance){
		this.setID(ID);
		this.setOwner(owner);
		this.setBalance(balance);
		try {
			ObjectInputStream in=new ObjectInputStream(new FileInputStream("Accounts.bin"));
			count=in.read();
			in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		count++;
		startDate=new Date();//sets start date to current date;
		log="Created: "+dt.format(startDate)+"\r\n";
		accountsNb=count;
		try {
			ObjectOutputStream out;
			out = new ObjectOutputStream(new FileOutputStream("Accounts.bin"));
			out.write(count);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Account(String ID,Person owner){
		this(ID,owner,0);
	}
	public Account(Person owner){
		this(autoGenerateID(owner),owner,0);
	}
	/**
	 * Generates an account ID that is based on the owner's data and should not be duplicate
	 * @param owner
	 * @return generated ID for account
	 */
	public static String autoGenerateID(Person owner){
		try {
			ObjectInputStream in=new ObjectInputStream(new FileInputStream("Accounts.bin"));
			accountsNb=in.read();
			in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "USD"+accountsNb+owner.getSSID().hashCode()+owner.getFirstName().toUpperCase()+owner.getLastName().toUpperCase()+owner.hashCode();
		
	}
	
	public boolean equals(Account a){
		return ID.equalsIgnoreCase(a.getID());
	}

	/**Getter method for the account ID
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**Setter method for the account ID
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**Getter method for the account owner
	 * @return the owner
	 */
	public Person getOwner() {
		return owner;
	}

	/**Setter method for the account owner
	 * @param owner the owner to set
	 */
	public void setOwner(Person owner) {
		this.owner = owner;
	}

	/**Getter method for the account balance
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**Setter method for the account balance
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**Getter method for the account log
	 * @return the log
	 */
	public String getLog() {
		return log;
	}

	/**Setter method for the account log
	 * @param log the log to set
	 */
	public void setLog(String log) {
		this.log = log;
	}
	
	/**
	 * Appends info at end of log
	 * @param info the info to append
	 */
	public void logAppend(String info){
		log+=info;
	}

	/**Getter method for the account creation date
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * Setter method for the start date
	 * @param date new start date
	 */
	void setStartDate(Date date) {
		this.startDate=date;
	}
	
	/**
	 * Extracts from the account the requested amount, if enough balance
	 * @param amount amount to withdraw
	 * @return true if successful, false otherwise
	 */
	public boolean withdraw(double amount){
		if(balance>=amount)
			{
			balance-=amount;
			log+=dt.format(new Date())+": "+amount+"$ withdrawn \r\n";
			return true;
			}
		return false;
	}
	/**
	 * Adds the transmitted amount to the account balance
	 * @param amount the amount to add to the account balance
	 * @return true if possible, false if you are too bad ass for this bank and have way too much money
	 */
	public boolean deposit(double amount){
		if(Double.MAX_VALUE- balance < amount ) // if overflow
			return false;
		balance+=amount;
		log+=dt.format(new Date())+": "+amount+"$ deposited \r\n";
		return true;
		
	}
	
	/**
	 * Empties the account balance
	 * @return the account balance value
	 */
	public double collect(){
		double money=this.getBalance();
		this.setBalance(0);
		return money;
		
	}
	
	@Override
	public String toString(){
		String x=ID+"\r\n";
		x+=owner+" "+balance+"$";
	
		
		return x;
	}
	
	/**
	 * Checks if an account is a SavingsAccount
	 * @return true caller is SavingsAccount
	 */
	public boolean isSavings(){
		return false;
	}

}
