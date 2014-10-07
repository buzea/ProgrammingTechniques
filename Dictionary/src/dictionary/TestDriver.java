package dictionary;





/**
 * Class used to test functionality of implemented functions
 * @author Vlad
 *
 */

public class TestDriver {

	public static void main(String[] args) {
		Dictionary d=new Dictionary();
		//test add() and toString() methods
		d.add("pom", "copac");
		d.add("pom","arbore");
		d.add("broasca","brotac");
		d.add("broasca", "mioarca");
		d.add("cartof", "hiriba");
		d.add("cartof","picioc");
		d.add("cartof", "gota");
		System.out.println(d.toString());
		
		//test save() method
		d.save();
		
		//test remove() method
		d.remove("brotac");
		System.out.println(d.toString());
		
		//test populate() method and also see the results of the save method()
		d.populate();
		System.out.println(d.toString());
		
		//test search() method
		System.out.println(d.search("c*").toString());
		
		
		
		
	}

}
