package OrderManagement;

class ProductDriver {

	public static void main(String []args){
		Product p;
		p=new Product("Notepad",1.0);
		System.out.println(p);
		p=new Product("Pen",5);
		System.out.println(new Product("Eraser",0.05));
		System.out.println(p);
		System.out.println(p.equals( new Product("Pen",5)));
	}
}
