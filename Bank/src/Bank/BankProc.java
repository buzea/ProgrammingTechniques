package Bank;

public interface BankProc {

	/**
	 * Adds a new account to the bank
	 * @pre a!=null
	 * @param a account to be added
	 * @post hashtable.size=hashtable.size+1
	 */
	public void addAccount(Account a);
	
	/**
	 * Returns the account with the specified ID.
	 * @pre true
	 * @param ID ID of the Account to be retrieved
	 * @return the Account if valid ID, null otherwise
	 * @post @inv 
	 */
	public Account getAccount(String ID);
	
	
	/**
	 * Removes an account from the bank
	 * @pre getAccount(ID)!=null
	 * @param ID The ID of the account to be removed
	 * @post size--
	 * @post @inv
	 */
	public void removeAccount(String ID);
	
	/**
	 * Returns information regarding the requested account
	 * @pre getAccount(ID!=null)
	 * @param ID ID of the requested account
	 * @return String with information
	 * @post @inv
	 */
	public String readAccount(String ID);
	
	/**
	 * Edits the properties of an account
	 * @pre getAccount(ID!=null)
	 * @pre newOwner!=null
	 * @param ID ID of the account to be edited
	 * @param newOwner new owner of the account
	 * @param balance new balance of the account
	 * @post@inv
	 */
	public void writeAccount(String ID,Person newOwner,double balance);
	
	
	/**
	 * Transfers the specified amount from source account to destination account
	 * @pre getAccount(destID!=null)
	 * @pre getAccount(sourceID!=null)
	 * @pre amount>0
	 * @pre source.getBalance() >= amount
	 * @param destID ID of the destination account
	 * @param sourceID ID of the source account
	 * @param amount amount of money to be transfered from source to destination
	 * @post destination.getBalance()+=amount
	 * @inv isWellFormed
	 */
	public void transfer(String destID,String sourceID,double amount);
	
	/**
	 * Generates report regarding an Account
	 * @pre getAccount(ID)!=null
	 * @param ID ID of the Account
	 * @return The log of events associated to that account
	 * @post @inv
	 */
	public String getLog(String ID);
	
	/**
	 * Calculates the total amount of money stored in the bank
	 * @pre true
	 * @return the amount of money stored in the bank
	 * @post true
	 */
	public double getTotalDeposit();
	
	/**
	 * Empties the bank of all accounts
	 * @pre true
	 * @post @inv
	 */
	public void clearAllAcounts();
}
