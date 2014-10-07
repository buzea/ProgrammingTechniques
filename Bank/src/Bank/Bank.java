package Bank;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Bank implements Serializable,BankProc {
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
	private Hashtable<String,Account> hashtable;
	
	
	@SuppressWarnings("unchecked")
	/**
	 * Creates a new bank with initial accounts extracted from Accounts.bin file if parameter is true.
	 * If parameter is false, then an empty bank is created
	 * @param fromFile true if you want to load from .bin file, false if you want an empty bank
	 */
	public Bank(boolean fromFile){
		if(!fromFile)
			hashtable=new Hashtable<String,Account>();
		else{
		try {
			FileInputStream f=new FileInputStream("bank.bin");
			ObjectInputStream in=new ObjectInputStream(f);
			hashtable=(Hashtable<String,Account>) in.readObject();
			in.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		}
	}
	/**
	 * Creates an empty bank
	 */
	public Bank(){
		hashtable=new Hashtable<String,Account>();
	}
	
	/**
	 * Creates a new bank that loads it's account from specified txt file
	 * @param pathname path to .txt file
	 */
	public Bank(String pathname){
		hashtable=new Hashtable<String,Account>();
		try {
			File file=new File(pathname);
			BufferedReader in =new BufferedReader(new FileReader(file));
			Account temp;
			Person owner;
			double balance;
			String ID,firstName,lastName,email="",address="",phone="0",type,data,date,SSID;
			String[] tokens,t2;
			ID=in.readLine();
			data=in.readLine();
			type=in.readLine();
			date=in.readLine();
			while(ID!=null && data!=null && type !=null&& date !=null){
				tokens=data.split("; ");
				SSID=tokens[0];
				t2=tokens[1].split(" ");
				firstName=t2[0];
				lastName=t2[1];
				for(int i=2;i<tokens.length-1;i++)
				{
					t2=tokens[i].split(":");
					if(i==2)
						email=t2[1];
					if(i==3)
						address=t2[1];
					if(i==4)
						phone=t2[1];
					
					
				}
				balance=0;
				char[] chars=tokens[5].toCharArray();
				int i;
				double f,div;
				for(i=0;i<chars.length && chars[i]!='.';i++)
					if(Character.isDigit(chars[i]))
						{balance*=10; balance+= chars[i]-'0';}
				for(div=10;i<chars.length;i++)
					if(Character.isDigit(chars[i]))
						{f=(chars[i]-'0')/div; div*=10; balance+=f;}
				owner=new Person(SSID,firstName,lastName,address,email,Long.parseLong(phone));
				if(type.equals("Spending"))
					{
						temp=new SpendingAccount(ID,owner,balance);
						try {
							temp.setStartDate(dt.parse(date));
							addAccount(temp);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				if(type.equals("Savings"))
				{
					String[] d=date.split(" ");
					Calendar dueDate=new GregorianCalendar();
					try {
						dueDate.setTime(dt.parse(d[1]));
						temp=new SavingsAccount(ID,owner,balance,dueDate);
						temp.setStartDate(dt.parse(d[0]));
						
						addAccount(temp);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
				}
				
				
				
				ID=in.readLine();
				data=in.readLine();
				type=in.readLine();
				date=in.readLine();
				
				
				
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds account to bank storage
	 * @param a Account to add;
	 */
	public void addAccount(Account a){
		assert a!=null;
		hashtable.put(a.getID(), a);
		try {
			FileOutputStream f=new FileOutputStream("bank.bin");
			ObjectOutputStream o=new ObjectOutputStream(f);
			o.writeObject(hashtable);
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assert isWellFormed();
	}
	
	public void clearAllAcounts(){
		hashtable.clear();
		try {
			FileOutputStream f=new FileOutputStream("bank.bin");
			ObjectOutputStream o=new ObjectOutputStream(f);
			o.writeObject(hashtable);
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assert isWellFormed();
	}
	
	public Account getAccount(String ID){
		if(!hashtable.containsKey(ID))
			return null;
		Account temp=hashtable.get(ID);
		assert isWellFormed();
		return temp;
	}
	
	public String readAccount(String ID){
		assert getAccount(ID)!=null;
		return getAccount(ID).toString();
	}
	
	public void writeAccount(String ID,Person newOwner,double balance){
		assert getAccount(ID)!=null;
		Account a=hashtable.get(ID);
		a.setBalance(balance);
		a.setOwner(newOwner);
		assert isWellFormed();
	}
	
	
	public void removeAccount(String ID){
		assert getAccount(ID)!=null;
		hashtable.remove(ID);
		assert isWellFormed();
	}
	
	@Override
	/**
	 * Returns information about all the account stored in the bank
	 */
	public String toString(){
		Account temp;
		String x="";
		for(String iter : hashtable.keySet())
			{
				temp=hashtable.get(iter);
				x+= temp+"\r\n";
			}
		return x;
	}
	
	public void transfer(String destID,String sourceID,double amount){
		assert getAccount(destID)!=null;
		assert getAccount(sourceID)!=null;
		assert amount>0;
		Account dest=hashtable.get(destID);
		Account source=hashtable.get(sourceID);
		dest.setBalance(dest.getBalance()+amount);//use setBalance to maintain startDate if savings account
		dest.logAppend(dt.format(new Date())+": "+amount+"$ wired to account from account "+sourceID+"\r\n");
		source.withdraw(amount);
	}
	
	public String getLog(String ID){
		Account a=hashtable.get(ID);
		assert isWellFormed();
		return a.getLog();
	}
	
	public double getTotalDeposit(){
		double total=0;
		Account temp;
		for(String i: hashtable.keySet())
		{
			temp=hashtable.get(i);
			total+=temp.getBalance();
		}
		return total;
	}
	
	private boolean isWellFormed(){
		Account temp;
		for(String i:hashtable.keySet())
			{
				temp=hashtable.get(i);
				if(!temp.getID().equals(i))
					return false;
			}
		return true;
	}
	
	Object[][] getAccountsTable(){
		Object[][] m=new Object[hashtable.size()][4];
		int row=0;
		for(String i:hashtable.keySet())
		{
			m[row][0]=hashtable.get(i).getID();
			m[row][1]=hashtable.get(i).getOwner();
			m[row][2]=hashtable.get(i).getBalance();
			if(hashtable.get(i).isSavings())
				m[row][3]="Savings";
			else
				m[row][3]="Spending";
			row++;
		}
		return m;
	}
	
}
