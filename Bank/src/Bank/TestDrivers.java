package Bank;

import java.util.*;

public abstract class TestDrivers extends Thread {

	public static void main(String[] args)
	{
		
		
		Calendar date=new GregorianCalendar();
		Calendar currentDate=new GregorianCalendar();
		currentDate.setTime(new Date());
		date.clear();
		date.set(2015,Calendar.APRIL, 22,23,56);
		Person p=new Person("1","Buzea","Vlad-Calin","Manastur","buzea.vlad@gmail.com",-1L);
		Account a=new SavingsAccount(p,1000.0,date);
		System.out.println(a.isSavings());
		//SavingsAccount Test
		/*
			SavingsAccount a;
			a=new SavingsAccount(p,1000.0,date);
			System.out.println(a.getBalance());
			a.withdraw(9.55);
			System.out.println(a.getBalance());
			a.withdraw(0.45);
			System.out.println(a.getBalance());
			a.deposit(30.0);
			System.out.println(a.getBalance());
			try {
				sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(a.collect());
		////////
		 
			Bank b=new Bank("C:\\Users\\Vlad\\Desktop\\An2 Sem 2\\PT\\Bank\\Accounts.txt");
	try {
			BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\Vlad\\Desktop\\An2 Sem 2\\PT\\Bank\\Accounts.txt"));
		Person p=new Person("1","Buzea","Vlad-Calin","Manastur","buzea.vlad@gmail.com",-1L);
		Account a=new SavingsAccount(p,1000.0,date);
		Account spend=new SpendingAccount(p,100.0);
		
		System.out.println(b.toString());
		//System.out.println(b.getAccount("USD12949BUZEAVLAD-CALIN628003988").collect());
		//b.addAccount(a);
		//b.addAccount(spend);
		//b.writeAccount("USD12949BUZEAVLAD-CALIN628003988", b.getAccount("USD12949BUZEAVLAD-CALIN628003988").getOwner(), 1000.51);
		//System.out.println(b.toString());
		out.write(b.toString());
		out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}

	
}
