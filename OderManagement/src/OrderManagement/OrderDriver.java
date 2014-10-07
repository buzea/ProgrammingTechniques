package OrderManagement;

import java.util.Date;

public class OrderDriver {

	public static void main(String[] args) {
		Order o;
		Product p=new Product("Enzo","Ferrari",100000);
		Customer c=new Customer("1","Vlad","Buzea","Cluj-Napoca");
		//current date
		Date date = new Date();
		
		
		o=new Order(p,c,date);
		System.out.println(o);
		System.out.println(o.equals( new Order(p,c,date)));
		System.out.println(o.equals( new Order(p,c,new Date())));
	}

}
