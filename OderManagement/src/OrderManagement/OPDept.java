package OrderManagement;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import javax.swing.JOptionPane;

public class OPDept {

	private TreeSet<Order> tree;
	private Warehouse warehouse;
	
	/**
	 * Creates a new Order Processing department that contains the orders saved in it's predefined file.
	 * So it continues the work already begun
	 */
	public OPDept(){
		tree=new TreeSet<Order>();
		warehouse=new Warehouse();
		init();
	}
	/**
	 * Creates a new Order Processing Department that has no orders stored if parameter is true.
	 * @param Empty : true for no orders, false for loading orders from file
	 */
	public OPDept(boolean Empty){
		this();
		if(Empty)
			this.clear();
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * loads order tree from file
	 */
	private void init(){

        tree = null;

        try
        {
           FileInputStream fileIn = new FileInputStream("AllOrders.bin");
           ObjectInputStream in = new ObjectInputStream(fileIn);
           tree = (TreeSet<Order>) in.readObject();
           
           in.close();
           fileIn.close();
       }catch(IOException i)
       {
           i.printStackTrace();
           return;
       }catch(ClassNotFoundException c)
       {
           System.out.println("BST not found");
           c.printStackTrace();
           return;
       }
   }
	
	
	/**
     * Adds a new order to the tree
     * After the addition to the tree, the method updates the serializable file
     * @param o Order
     */
    public void addOrder(Order o){
      
       
    
        if(tree.contains(o)){
        	JOptionPane.showMessageDialog(null,"Order already in system");
        }
        else{
            try{
                tree.add(o);
                warehouse.deliver(o.getProduct(), o.getQuantity());
                try{
                     FileOutputStream fileOut = new FileOutputStream("AllOrders.bin");
                     ObjectOutputStream out1 = new ObjectOutputStream(fileOut);
                     out1.writeObject(tree);
                     out1.close();
                     fileOut.close(); 
                     //JOptionPane.showMessageDialog(null,"Order added succesfully");
                }catch(IOException i){
                   System.out.println("IOException"); i.printStackTrace();
                }
            }catch (Exception e){
                System.err.println("AllOrders error: " + e.getMessage());
            }
           
        }
    }
    /**
     * Clears the order tree and saves changes in the AllOrders.bin file
     */
    public void clear(){
    	try{
    		tree.clear();
    		//warehouse=new Warehouse(true);
            FileOutputStream fileOut = new FileOutputStream("AllOrders.bin");
            ObjectOutputStream out1 = new ObjectOutputStream(fileOut);
            out1.writeObject(tree);
            out1.close();
            fileOut.close(); 
       }catch(IOException i){
           i.printStackTrace();
       }
    }
    
    /**
	 * Returns a string representation of the orders BST. The string representation consists of a list of the collection's elements in the order they are returned by its iterator, enclosed in square brackets ("[]"). Adjacent elements are separated by the characters ", " (comma and space). Elements are converted to strings as by String.valueOf(Order).
	 *
	 *@return String representation of orders
	 */
    public String toString(){
    	return tree.toString();
    }
    
    /**
     * Notifies warehouse to deliver the product in the specified amount
     * @param o Order to ship
     * @return true if operation was successful, false otherwise
     */
    public boolean shipOrder(Order o){
    	if(!tree.contains(o))
    		return false;
    	return warehouse.deliver(o.getProduct(), o.getQuantity());
    	
    }
    /**
     * Allows customer to check if received order
     * @param o Order received
     */
    public void recievedOrder(Order o){
    	Iterator<Order> i=tree.iterator();
    	Order temp;
    	while(i.hasNext()){
    		temp=i.next();
    		if(temp.equals(o))
    		{
    			temp.setDelivered(true);
    			try{
                    FileOutputStream fileOut = new FileOutputStream("AllOrders.bin");
                    ObjectOutputStream out1 = new ObjectOutputStream(fileOut);
                    out1.writeObject(tree);
                    out1.close();
                    fileOut.close(); 
                    //JOptionPane.showMessageDialog(null,"Order recieved succesfully");
               }catch(IOException i1){
                  System.out.println("IOException"); i1.printStackTrace();
               }
    			break;
    		}
    	}
    }
    /**
     * Returns the delivered orders in String format
     * @return String with orders already delivered
     */
    public String deliveredOrders(){
    	Iterator<Order> i=tree.iterator();
    	Order temp;
    	String x="";
    	while(i.hasNext()){
    		temp=i.next();
    		if(temp.isDelivered())
    			x+=""+temp+"\r\n";
    		
    }
    	
    	return x;
    }
    
    /**
     * Returns an ArrayList of the delivered orders
     * @return ArrayList<Order> containing the delivered orders
     */
    public ArrayList<Order> deliveredOrdersList(){
    	Iterator<Order> i=tree.iterator();
    	Order temp;
    	ArrayList<Order> ret=new ArrayList<Order>();
    	while(i.hasNext()){
    		temp=i.next();
    		if(temp.isDelivered())
    			ret.add(temp);
    		
    }
    	
    	return ret;
    }
    
    /**
     * Returns an ArrayList of the undelivered orders
     * @return ArrayList<Order> containing the undelivered orders
     */
    public ArrayList<Order> unDeliveredOrdersList(){
    	Iterator<Order> i=tree.iterator();
    	Order temp;
    	ArrayList<Order> ret=new ArrayList<Order>();
    	while(i.hasNext()){
    		temp=i.next();
    		if(!temp.isDelivered())
    			ret.add(temp);
    		
    }
    	
    	return ret;
    }
    /**
     * Returns the undelivered orders in String format
     * @return String with orders undelivered
     */
    public String unDeliveredOrders(){
    	Iterator<Order> i=tree.iterator();
    	Order temp;
    	String x="";
    	while(i.hasNext()){
    		temp=i.next();
    		if(!temp.isDelivered())
    			x+=""+temp+"\r\n";
    		
    }
    	
    	return x;
    }
    /**
     * Returns the tree containing the orders
     * @return TreeSet<Order> with all orders
     */
    public TreeSet<Order> getOrderTree(){
    	return tree;
    }
    
    Object[][] orderTable(){
		Object[][] temp=new Object[tree.size()][6];
		Iterator<Order>i=tree.iterator();
		int j=0;
		Order p;
		while(i.hasNext()&&j<tree.size()){
			p=i.next();
			
			
			{
						temp[j][0]=p.isDelivered();
						temp[j][1]=p.getCustomer();
						temp[j][2]=p.getProduct();
						temp[j][3]=p.getDate();
						temp[j][4]=p.getQuantity();
						temp[j][5]=(p.getProduct().getPrice() * p.getQuantity());
						j++;
			}
		}
		
		return temp;
	}
    
    Object[][] orderTable(boolean delivered){
		Object[][] temp=new Object[tree.size()][6];
		Iterator<Order>i=tree.iterator();
		int j=0;
		Order p;
		while(i.hasNext()&&j<tree.size()){
			p=i.next();
			
			if(p.isDelivered()==delivered)
			{
						temp[j][0]=p.isDelivered();
						temp[j][1]=p.getCustomer();
						temp[j][2]=p.getProduct();
						temp[j][3]=p.getDate();
						temp[j][4]=p.getQuantity();
						temp[j][5]=(p.getProduct().getPrice() * p.getQuantity());
						j++;
			}
		}
		
		return temp;
	}
    /*
     * Sets the Order as being delivered, in the BST
     * 
     * @param o Order
     *
    public void markDelivered(Order o){
    	Iterator<Order> i=tree.iterator();
    	Order temp;
    	while(i.hasNext()){
    		temp=i.next();
    		if(o.equals(temp))
    				{temp.setDelivered(true);
    				break;
    				}
    		
    		
    }
    	
    }
	*/
}
