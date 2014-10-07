package OrderManagement;


import java.io.*;
import java.util.*;

public class Warehouse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6166758524318430229L;
	/**
	 * 
	 */
	public static final int MAX_PRODUCTS=1000,underStock=10,overStock=100;
	private TreeSet<Product> tree;
	private int[] stock;
	
	/**
	 * Creates a new warehouse that loads the products tree from file
	 */
	public Warehouse(){
		tree=new TreeSet<Product>();
		stock=new int[MAX_PRODUCTS];
		init();
	}
	/**
	 * Creates a new warehouse with empty product tree if empty=true
	 * @param empty true for empty product tree, false to load from file
	 */
	public Warehouse(boolean empty){
		this();
		if(empty)
			this.clear();
			
	}
	

	@SuppressWarnings("unchecked")
	/**
	 * load tree
	 */
	private void init(){
        
        tree = null;
   
        try
        {
           FileInputStream fileIn =
                         new FileInputStream("Warehouse.bin");
           ObjectInputStream in = new ObjectInputStream(fileIn);
           tree = ((TreeSet<Product>)(in.readObject()));
           stock= (int[]) in.readObject();
           in.close();
           fileIn.close();
       }catch(IOException i)
       {
           i.printStackTrace();
           return;
       }catch(ClassNotFoundException c)
       {
           System.out.println("BST class not found");
           c.printStackTrace();
       }
	}
	
	
	/**
	 * Adds a product to the warehouse, with the corresponding stock. If the product already exists, the stock will be added to the existing one
	 * @param p : product to add to warehouse
	 * @param stockVal : quantity to add;
	 */
	public void addProduct(Product p,int stockVal){
		
		if(!this.has(p)) //if NEW product
			{
			//Product temp=p.clone();
			tree.add(p);
			stock[p.getID()]=stockVal;
			try{
	             FileOutputStream fileOut =new FileOutputStream("Warehouse.bin");
	             ObjectOutputStream out1 =new ObjectOutputStream(fileOut);
	             out1.writeObject(tree);
	             out1.writeObject(stock);
	             out1.close();
	             fileOut.close();
	        }catch(IOException i){
	            i.printStackTrace();
	        }
			}
		else //if product already in the list, update stock
			{
			//Product temp=tree.floor(p);
			int tempID=this.getCatalogID(p);
			stock[tempID]+=stockVal; // we can use floor because we compare by ID, and for sure p.ID is the greatest smaller or equal to p.ID
			try{
	             FileOutputStream fileOut =new FileOutputStream("Warehouse.bin");
	             ObjectOutputStream out1 =new ObjectOutputStream(fileOut);
	             out1.writeObject(tree);
	             out1.writeObject(stock);
	             out1.close();
	             fileOut.close();
	        }catch(IOException i){
	            i.printStackTrace();
	        }
			}
	}
	/**
	 * Adds product to warehouse, with implicit stock =1 
	 * @param p Product
	 */
	public void addProduct(Product p){
		addProduct(p,1);
	}
	
	/**
	 * Returns a string representation of the warehouse products. The string representation consists of a list of the collection's elements in the order they are returned by its iterator, enclosed in square brackets ("[]"). Adjacent elements are separated by the characters ", " (comma and space). Elements are converted to strings as by String.valueOf(Object).
	 *
	 *@return String representation of warehouse products
	 */
	public String toString(){
		String x="";
		Product temp;
		Iterator<Product> i=tree.iterator();
		while(i.hasNext()){
			temp=i.next();
			if(temp!=tree.last())
				x+=""+temp+" (Stock="+stock[temp.getID()]+"), \r\n";
			else
				x+=""+temp+" (Stock="+stock[temp.getID()]+") \r\n"; // no ','
			
		}
		return x;
	}
	
	/**
	 * Extracts product from warehouse, in the desired quantity
	 * @param p Product
	 * @param cant : Quantity to extract
	 * @return true if operation successful, false otherwise
	 */
	public boolean deliver(Product p,int cant){
		assert cant>0;
		int CID=this.getCatalogID(p);
		if(stock[CID]>=cant) //  stock[0]=0 always! thus if product not in warehouse return false.(0!>cant)
		{
			stock[CID]-=cant;
			try{
	             FileOutputStream fileOut =new FileOutputStream("Warehouse.bin");
	             ObjectOutputStream out1 =new ObjectOutputStream(fileOut);
	             out1.writeObject(tree);
	             out1.writeObject(stock);
	             out1.close();
	             fileOut.close();
	        }catch(IOException i){
	            i.printStackTrace();
	        }
			
			return true;
		}
		return false;
	}
	/**
	 * Resets the Product Search tree and Stock
	 */
	public void clear(){
		tree.clear();
		stock=new int[MAX_PRODUCTS];
		try{
            FileOutputStream fileOut =new FileOutputStream("Warehouse.bin");
            ObjectOutputStream out1 =new ObjectOutputStream(fileOut);
            out1.writeObject(tree);
            out1.writeObject(stock);
            out1.close();
            fileOut.close();
       }catch(IOException i){
           i.printStackTrace();
       }
		
	}
	/**
	 * Checks if a product was registered in the warehouse. Does not checks if available on stock
	 * @param p Product to check
	 * @return true if available, false otherwise
	 */
	public boolean has(Product p){
		if(tree.isEmpty())
			return false;
		Iterator<Product> i=tree.iterator();
		Product temp;
		while(i.hasNext())
		{
			temp=i.next();
			if(temp.equals(p))
				return true;
			
		}
		return false;
	}
	/**
	 * Returns the id of the product, from the warehouse product BST.
	 * @param p Product
	 * @return ID in Warehouse if found, 0 if not found
	 * 
	 */
	public int getCatalogID(Product p){
		if(tree.isEmpty())
			return 0;
		Iterator<Product> i=tree.iterator();
		Product temp;
		while(i.hasNext())
		{
			temp=i.next();
			if(temp.equals(p))
				return temp.getID();
			
		}
		return 0;
		
	}
	/**
	 * Returns the products that are under-stocked from the warehouse in an ArrayList
	 * @return ArrayList containing the under-stocked products
	 */
	public ArrayList<Product> underStocked(){
		ArrayList<Product> temp=new ArrayList<Product>();
		Iterator<Product> i=tree.iterator();
		Product aux;
		while(i.hasNext()){
			aux=i.next();
			if(stock[aux.getID()]<underStock)
				temp.add(aux);
		}
		return temp;
	}
	
	/**
	 * Returns the products that are over-stocked from the warehouse in an ArrayList
	 * @return ArrayList containing the over-stocked products
	 */
	public ArrayList<Product> overStocked(){
		ArrayList<Product> temp=new ArrayList<Product>();
		Iterator<Product> i=tree.iterator();
		Product aux;
		while(i.hasNext()){
			aux=i.next();
			if(stock[aux.getID()]>overStock)
				temp.add(aux);
		}
		return temp;
	}
	/**
	 * Computes the value a stock of a specific product has in the warehouse
	 * @param p Product to compute value in stock
	 * @return value of stock in $
	 */
	public double totalValue(Product p){
		int id=this.getCatalogID(p);
		return (stock[id]*p.getPrice());
		
	}
	/**
	 * Computes the total value of products stored in the warehouse
	 * @return the total value
	 */
	public double totalValue(){
		double total=0.0;
		Iterator<Product> i=tree.iterator();
		Product temp;
		while(i.hasNext()){
			temp=i.next();
			total+=(temp.getPrice()*stock[temp.getID()]);
		}
		
		return total;
	}
	/**
	 * Returns an array with products stored in the warehouse
	 * @return array with products
	 */
	public Product[] productArray(){
		Product[] result = tree.toArray(new Product[tree.size()]);
		return result;
	}
	
	Object[][] productTable(){
		Object[][] temp=new Object[tree.size()][5];
		Iterator<Product>i=tree.iterator();
		int j=0;
		Product p;
		while(i.hasNext()&&j<tree.size()){
			p=i.next();
			temp[j][0]=p.getID();
			temp[j][1]=p.getName();
			temp[j][2]=p.getProducerName();
			temp[j][3]=p.getPrice();
			temp[j][4]=stock[p.getID()];
			
			j++;
		}
		
		return temp;
	}
	
	/////
	Object[][] productTable(String name,String producer){
		Object[][] temp=new Object[tree.size()][5];
		Iterator<Product>i=tree.iterator();
		int j=0;
		Product p;
		while(i.hasNext()&&j<tree.size()){
			p=i.next();
			if(p.getName().startsWith(name) && p.getProducerName().startsWith(producer)){
						temp[j][0]=p.getID();
						temp[j][1]=p.getName();
						temp[j][2]=p.getProducerName();
						temp[j][3]=p.getPrice();
						temp[j][4]=stock[p.getID()];
						j++;
			}
		}
		
		return temp;
	}
	
	Object[][] productTable(String name,String producer,double maxPrice,double minPrice){
		Object[][] temp=new Object[tree.size()][5];
		Iterator<Product>i=tree.iterator();
		int j=0;
		Product p;
		if(maxPrice==0)
			maxPrice=Double.MAX_VALUE;
		while(i.hasNext()&&j<tree.size()){
			p=i.next();
			if(p.getPrice()<=maxPrice && p.getPrice()>=minPrice)
			if(p.getName().startsWith(name) && p.getProducerName().startsWith(producer)){
						temp[j][0]=p.getID();
						temp[j][1]=p.getName();
						temp[j][2]=p.getProducerName();
						temp[j][3]=p.getPrice();
						temp[j][4]=stock[p.getID()];
						j++;
			}
		}
		
		return temp;
	}
	
	Object[][] statsArray(){
		Object[][] temp=new Object[tree.size()][5];
		Iterator<Product>i=tree.iterator();
		Product p;
		int row=0;
		while(i.hasNext()){
			p=i.next();
			temp[row][0]=p.getName();
			temp[row][1]=p.getProducerName();
			temp[row][2]=stock[p.getID()];
			temp[row][3]=stock[p.getID()]*p.getPrice();
			if(stock[p.getID()]<underStock)
				temp[row][4]="UNDERSTOCKED";
			else
				if(stock[p.getID()]>overStock)
					temp[row][4]="OVERSTOCKED";
				else
					temp[row][4]="Balanced stock";
			row++;
		}
		
		return temp;
	}
	
	
}
