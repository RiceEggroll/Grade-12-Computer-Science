package unit3;

public class CarTest {
	
	public static void main(String[] args) {
		//Static variable. No need to redeclare
		System.out.println(Toyota.company);
		
		Toyota car1 = new Toyota("Yarris");
		//Toyota is a new variable type
		//new Toyota(); creates that new object
		//car1 is the variable name for the variable type
		
		//Cannot be changed due to final variable
		//car1.model = "Camry"; 
		//car1.odometer = 1000;
		//car1.odometer = 4;
		
		Toyota car2 = new Toyota("Corrola");
		
		System.out.println(car2.colour);
		System.out.println(car1.model);
		
		//Changing the static variable will change it for everything 
		//There is only one "copy' of static variables
		Toyota.company = "Rental Cars";
		System.out.println(car2.company);
		System.out.println(car1.toString());
	}
}
