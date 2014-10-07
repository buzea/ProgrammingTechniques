package Bank;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SavingsAccount extends Account {

	private static final long serialVersionUID = 1407584978103935389L;
	/**
	 * The standard interest(%) for savings accounts
	 */
	public static final double INTEREST =8.9;
	/**
	 * The standard penalties applied when contract conditions are not met
	 */
	public static final double PENALTIES =10;
	
	private Calendar dueDate;
	private int maxWithdraws;
	
	/**
	 * Creates a new savings account that must have an owner and deposited amount
	 * @param owner the account owner
	 * @param balance the deposited amount
	 */
	public SavingsAccount(Person owner,Double balance,Calendar dueDate) {
		super(Account.autoGenerateID(owner),owner,balance);
		Calendar currentDate=new GregorianCalendar();
		currentDate.clear();
		currentDate.setTime(new Date());
		//assert dueDate.after(currentDate);
		if(dueDate.before(currentDate))
			dueDate=currentDate;
		this.dueDate=dueDate;
		maxWithdraws=6;
	}
	public SavingsAccount(String ID,Person owner,Double balance,Calendar dueDate) {
		super(ID,owner,balance);
		Calendar currentDate=new GregorianCalendar();
		currentDate.clear();
		currentDate.setTime(new Date());
		//assert dueDate.after(currentDate);
		if(dueDate.before(currentDate))
			dueDate=currentDate;
		this.dueDate=dueDate;
		maxWithdraws=6;
	}
	/**
	 * Getter method for the accounts due date
	 * @return the dueDate
	 */
	public Calendar getDueDate() {
		return dueDate;
	}
	
	/**
	 * Deposits money in the Savings Account, but with losing of any cumulated interest
	 * @param amount amount to deposit
	 * @return true if possible, false otherwise
	 */
	public boolean deposit(double amount){
		if(amount<0)
			return false;
		if(Double.MAX_VALUE- this.getBalance() <amount) // if overflow
			return false;
		//if(dueDate.after(new Date()))
			this.setStartDate(new Date());
		return super.deposit(amount);
	}
	
	/**
	 * Withdraws money from account
	 * @param amount amount to withdraw
	 * @return true if possible, false otherwise
	 */
	public boolean withdraw(double amount){
		if(amount<0)
			return false;
		Calendar currentDate=new GregorianCalendar();
		currentDate.setTime(new Date());
		if(currentDate.before(dueDate)){
		if(this.getBalance()<= amount+PENALTIES)
			return false;
		if(maxWithdraws==0)
			return false;
		this.setBalance(this.getBalance()-PENALTIES);
		this.logAppend("PENALTY "+ PENALTIES +"$ : Early withdraw:");
		maxWithdraws--;
		return super.withdraw(amount);}
		else
			return super.withdraw(amount);
		
	}
	/**
	 * Adds interest to account
	 */
	private void addInterest(){
		double b=this.getBalance();
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(this.getStartDate());
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(new Date());

		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		//int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		b+=b*(INTEREST/100)*diffYear;
		
		this.setBalance(b);
		
	}
	/**
	 * Collects the money in the savings account
	 * @return the amount in balance
	 */
	public double collect(){
		Calendar currentDate=new GregorianCalendar();
		currentDate.setTime(new Date());
		if(currentDate.before(dueDate))
			return 0;
		addInterest();
		double money=this.getBalance();
		this.setBalance(0);
		return money;
	}
	
	public String toString(){
		String x=super.toString()+"\r\n";
		x+="Savings\r\n";
		x+=dt.format(this.getStartDate())+" "+dt.format(dueDate.getTime());
		return x;
	}
	
	public boolean isSavings(){
		return true;
	}

}
