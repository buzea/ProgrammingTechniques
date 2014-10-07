package catalog;

public class AccountConflictException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3634932859630129595L;

	public AccountConflictException() {
		super("Account Conflict Exception Encountered");
	}

	public AccountConflictException(String arg0) {
		super(arg0);
	}

	public String getMessage(){
		return	super.getMessage();
	}
}
