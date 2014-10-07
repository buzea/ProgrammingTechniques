package Bank;

public class BankTestDriver {

	public static void main(String[] args) {
		Bank bank=new Bank("Accounts.txt");
		
		System.out.println(bank.toString());	//I prints the initial accounts found in the bank
		
		Person raul=new Person("3","Jantea","Raul","jantearaul@gmail.com","Baia de Aries",+40749690691L);
		Account accountRaul=new SpendingAccount(raul);
		bank.addAccount(accountRaul);
		System.out.println(bank.toString());   //II adds raul to the bank an prints
		
		bank.transfer(accountRaul.getID(), "USD12949BUZEAVLAD-CALIN628003988", 50.0);
		System.out.println(bank.toString());  //III transfers 50$ from vlad to raul and prints
		
		System.out.println(bank.getLog("USD8249BUZEAVLAD-CALIN965133325")); //IV prints the log of vlad
		System.out.println("Bank total deposits: "+bank.getTotalDeposit()+"$\r\n");//V prints the bank total deposits
		
		System.out.println("Account info:"+bank.getAccount("USD12949BUZEAVLAD-CALIN628003988").toString()); //VI prints vlad's account info
		System.out.println("Collected:"+bank.getAccount("USD12949BUZEAVLAD-CALIN628003988").collect()+"\r\n");// VII prints collected amount from account
		bank.removeAccount("USD12949BUZEAVLAD-CALIN628003988"); 
		System.out.println(bank.toString()); //VII removes account and prints
		
		
	}

}
