package Bank;

public class SpendingAccount extends Account {
	
	private static final long serialVersionUID = -9198334397307131285L;
	public static final int TAXES = 5;
	public SpendingAccount(Person owner,double balance){
		super(Account.autoGenerateID(owner),owner,balance);
	}
	public SpendingAccount(String ID,Person owner,double balance){
		super(ID,owner,balance);
	}
	public SpendingAccount(Person owner){
		super(Account.autoGenerateID(owner),owner,0);
	}
	/**
	 * Subtracts taxes from account balance
	 */
	public void chargeTaxes(){
		this.setBalance(this.getBalance()-TAXES);
	}
	
	public String toString(){
		String x=super.toString()+"\r\n";
		x+="Spending\r\n";
		x+=dt.format(this.getStartDate());
		return x;
	}

}
