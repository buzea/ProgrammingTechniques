package catalog;




public class TestDriver {


	public static void main(String[] args) {
	
		   Catalog c=Catalog.getInstance();
		   System.out.println(c.getCourses().toString()+"\n");
		   
		  // Calendar date=new GregorianCalendar(2013,10,20);
		  
		   
		   
		    c.closeConnection();
		   
	}

}
