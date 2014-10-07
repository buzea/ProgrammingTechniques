/**
 * 
 */
package OrderManagement;

/**
 * @author Vlad
 *
 */
public abstract class CustomerDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Customer probe=new Customer("1","Vlad","Buzea","Cluj-Napoca");
		System.out.println(probe.toString());
		probe=new Customer("2","Vlad","Buzea","Cluj-Napoca","buzea.vlad@gmail.com");
		System.out.println(probe.toString());
		probe=new Customer("3","Vlad","Buzea","Cluj-Napoca","buzea.vlad@gmail.com","0740469868");
		System.out.println(probe.toString());
		
		System.out.println(probe.equals(new Customer("3","Vlad","Buzea","Cluj-Napoca")));
	}

}
