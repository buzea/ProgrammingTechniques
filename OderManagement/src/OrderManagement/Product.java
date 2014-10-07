package OrderManagement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;

public class Product implements Comparator<Product>,Comparable<Product>,Serializable,Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3207547416321696485L;
	private  int ID;
	private String name,producerName;
	private double price;
	private static int count;
	private String observations;
	
	/**
	 * Creates a new product with specified name, producer name and price.
	 * A product ID will also be generated automatically
	 * @param name - Product name
	 * @param producerName - Producer name
	 * @param price - product price in $
	 * Price can be fractional (ex. 0.99$)
	 */
	public Product(String name,String producerName,double price){
		try{
            FileInputStream fileOut =new FileInputStream("Product.bin");
            ObjectInputStream in1 =new ObjectInputStream(fileOut);
            count=(int)in1.readObject();
            in1.close();
            fileOut.close();
       }catch(IOException i){
           i.printStackTrace();
       }catch(ClassNotFoundException c)
       {
           System.out.println("BST class not found");
           c.printStackTrace();
       }
		this.setName(name);
		this.setProducerName(producerName);
		this.setPrice(price);
		count++;
		ID=count;
		try{
            FileOutputStream fileOut =new FileOutputStream("Product.bin");
            ObjectOutputStream out1 =new ObjectOutputStream(fileOut);
            out1.writeObject(count);
            out1.close();
            fileOut.close();
       }catch(IOException i){
           i.printStackTrace();
       }

	}
	/**
	 * Creates a new Product with no producer name
	 * @param name - Product name
	 * @param price - Product price
	 */
	public Product(String name,double price){
		this(name,"",price);
	}
	/**Creates a new Product with no producer name and non fractional price
	 * @param name - product name
	 * @param price - product price
	 */
	public Product(String name,int price){
		this(name,"",(double)price);
	}
	
	/**Gets the product ID
	 * @return the ID of the product
	 */
	public int getID() {
		return ID;
	}
	private void setID(int ID){
		this.ID=ID;
	}
	/**Gets the name of the product
	 * @return the name of the product
	 */
	public String getName() {
		return name;
	}
	/**Edits the name of the product
	 * @param name = the new name 
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**Gets the producer name for a product
	 * @return the producerName
	 */
	public String getProducerName() {
		return producerName;
	}
	/**Edits the producer name for a product
	 * @param producerName : the new producerName
	 */
	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}
	/**Retrieves the price of a product
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**Edits the price of a product
	 * @param price : the new price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * Returns product info in String format
	 * @return ID: name (producer name) - price$
	 */
	public String toString(){
		String x=""+ID+": "+name;
		if(!producerName.equals(""))
			x+=(" ("+producerName+")");
		x+=" - "+price+"$";
		return x;
	}
	/**	Returns observations recorded, regarding this product
	 * @return the observations
	 */
	public String getObservations() {
		return observations;
	}
	/**Creates a new log of observations
	 * @param observations : Text of new log
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}
	
	/**
	 * Appends observations to end of log
	 * @param obs : new observations
	 */
	public void addObs(String obs){
		this.observations+=obs;
	}
	/**
	 * Compares product the two product parameters
	 * @return 0 if equal, 1 if P1.ID > P2.ID -1, if P1.ID < P2.ID
	 */
	public int compare(Product p1, Product p2) {
		return p1.getID()-p2.getID();
	}
	/**
	 * Performs compare(this.p);
	 */
	public int compareTo(Product p) {
		return this.ID-(p.getID());
	}
	
	public boolean equals(Object p){
		Product temp=(Product)p;
		if(!this.name.equals(temp.getName()))
			return false;
		if(!this.producerName.equals(temp.getProducerName()))
			return false;
		if(this.price!=(temp.getPrice()))
			return false;
		
		
		return true;
	}
	public Product clone(){
		Product temp=new Product("0",0);
		temp.setID(ID);
		temp.setName(name);
		temp.setProducerName(producerName);
		temp.setObservations(observations);
		temp.setPrice(price);
		return temp;
	}
	
	
}
