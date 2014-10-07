package OrderManagement;

import java.util.Date;

public abstract class OPDeptDriver {

	public static void main(String[] args) {
		OPDept opd=new OPDept();
		Order o=new Order(new Product("notepad",1),new Customer("1930209","Vlad","Buzea","Cluj-Napoca"),new Date());
		System.out.println(opd.toString());
		opd.addOrder(o);
		System.out.println(opd.toString());
		opd.addOrder(o);
		
		if(opd.shipOrder(o))
			{System.out.println("Shipped");
			opd.recievedOrder(o);
			}
		else
			System.out.println("Product not available");
		System.out.println(opd.toString());
		System.out.println("Delivered Orders:\r\n"+opd.deliveredOrders());
		System.out.println("Undelivered Orders:\r\n"+opd.unDeliveredOrders());
		
	}

}
