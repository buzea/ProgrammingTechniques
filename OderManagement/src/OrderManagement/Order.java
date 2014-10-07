/**
 * 
 */
package OrderManagement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * @author Vlad
 *
 */
public class Order implements Serializable,Comparator<Order>,Comparable<Order> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1800058641173905002L;
	private Product product;
	private int quantity;
	private Customer customer;
	private Date date;
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private boolean delivered;
	private int ID;
	private static int count=0;
	
	/**
	 * Creates a new Order with the specified quantity, associated to the specified product,by the transmitted Customer. The order also contains the date it was transmitted
	 * @param quantity - quantity of ordered product 
	 * @param p - Product ordered
	 * @param c	- Customer who ordered
	 * @param d	- date when ordered
	 */
	public Order(int quantity,Product p,Customer c,Date d){
		try{
            FileInputStream fileOut =new FileInputStream("Order.bin");
            ObjectInputStream in1 =new ObjectInputStream(fileOut);
            count=(int)in1.readObject();
            in1.close();
            fileOut.close();
       }catch(IOException i){
           i.printStackTrace();
       }catch(ClassNotFoundException cla){}
		this.setProduct(p);
		this.setCustomer(c);
		this.setDate(d);
		delivered=false;
		count++;
		ID=count;
		this.setQuantity(quantity);
		
		try{
            FileOutputStream fileOut =new FileOutputStream("Order.bin");
            ObjectOutputStream out1 =new ObjectOutputStream(fileOut);
            out1.writeObject(count);
            out1.close();
            fileOut.close();
       }catch(IOException i){
           i.printStackTrace();
       }
	}
	/**
	 * Creates a new order with quantity 1 associated to the product
	 * @param p - Product ordered
	 * @param c	- Customer who ordered
	 * @param d	- date when ordered
	 */
	public Order(Product p,Customer c,Date d){
		this(1,p,c,d);
	}
	/**
	 * Returns order information in String format
	 * @return String containing order info
	 */
	public String toString(){
		String x="["+dateFormat.format(date)+"]: "+customer+" - ("+product+")";
		return x;
	}
	/**
	 * Checks status of order delivering
	 * @return true if delivered, false if must be delivered
	 */
	public boolean isDelivered(){
		return delivered;
	}
	/**
	 * Sets the delivered field of the order
	 * @param status = deliver status (true or false)
	 */
	public void setDelivered(boolean status){
		delivered=status;
	}
	/**
	 * Checks order field by field for equality
	 * @return true if caller and parameter are equal, false otherwise
	 */
	public boolean equals(Object o){
		Order temp=(Order)o;
		
		if(!temp.getCustomer().equals(customer))
			return false;
		if(!temp.getDate().equals(date))
			return false;
		if(!temp.getProduct().equals(product))
			return false;
			
		//if(ID==temp.getID())
			//return true;
		return true;
	}
	/**Gets the product ordered
	 * @return the product ordered
	 */
	public Product getProduct() {
		return product;
	}
	/**Edits the product ordered
	 * @param product = the new product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**Gets the customer who placed the order
	 * @return the customer who placed the order
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**Edits the customer who placed the order
	 * @param customer = the new customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/**Gets the date the order took place
	 * @return the date the order took place
	 */
	public Date getDate() {
		return date;
	}
	/**Edits the date of order
	 * @param date = the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Compares the two products by date of order
	 * @return 0 if equal, 1 if caller was ordered first, -1 otherwise
	 */
	public int compareTo(Order otherOrder) {
		return date.compareTo(otherOrder.getDate());
	}
	
	/**
	 * Compares the two orders by date of order
	 * @return  0 if equal, 1 if o1(first parameter) was ordered first, -1 otherwise
	 */
	public int compare(Order o1, Order o2) {
		return o1.compareTo(o2);
	}
	/**Gets the ID
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**Gets the quantity associated with a product in an order
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**Edits the ordered quantity
	 * @param quantity = the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
