package OrderManagement;

public abstract class WarehouseDriver {

	public static void main(String[] args) {
		Warehouse w=new Warehouse(true);
		//w.clear();
		
		Product p=new Product("laptop",999.99);
		
		w.addProduct(new Product("notepad",1));
		w.addProduct(new Product("notepad",1),6);
		w.addProduct(new Product("laptop",999.99),5);
		w.addProduct(p,9);
		w.addProduct(p);
		w.addProduct(new Product("notepad",1.0),3);
		System.out.println(w);
		if(w.deliver(new Product("notepad",1),5))
			System.out.println(w);
		System.out.println(w.underStocked());
		System.out.println("Value of nodepads"+ w.totalValue(new Product("notepad",1))+"$");
		System.out.println("Total value stored="+w.totalValue()+"$");
	}

}
