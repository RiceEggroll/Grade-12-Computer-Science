package inheritance;

public class Maininherit {
	public static void main(String[]args) {		

		//1. You cannot make an ancestor because it is abstract
		//Ancestor person = new Ancestor();

		Parent p = new Parent();
		System.out.println("--parent--");
		System.out.println(p.toString());
		p.fish();

		//2. Parent has learned the techniques of Ancient fishing
		p.ancientFishing();

		//make two children
		Child bill = new Child(5);
		Parent fred = new Child();

		System.out.println("\n --child--");
		System.out.println(bill.toString());
		System.out.println(fred.toString());
		bill.fish();
		fred.fish();
		bill.sleep();
		//		Does not work because parents can't sleep
		//		fred.sleep(); 

		Child c = (Child) fred;
		c.sleep(); // all of the child methods are now working

		//fake out the system, make the parent a child
		//this will not work
		//Child c2 = (Child) p; 
		//c2.sleep();
		
		c.parent_hair();
	}
}
