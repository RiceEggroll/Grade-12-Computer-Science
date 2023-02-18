package unit3;

class Toyota {
	//global variables are set to be 0 or null
	
	//1. static variables (and constants)
	//there is only one copy of these
	static String company = "Car Rentals";
	
	//2. instance variables (not static)
	//each object gets its own copy of these
	
	final String model;		//final means it cannot be changed
							//however it can be initialized in a constructor
	private int odometer; 	//private means it does not let other classes mess with the variable
	String colour = "Blue"; 
	double fuel = 40.0;
	
	//3. constructor(s)	
	Toyota (String m) {
		model = m;
		odometer = 25;
	}
	Toyota (String model, int odometer ){
		//run the other constructor
		this(model);
		this.odometer = odometer;
	}
	//4. static methods

	//5. instance methods
	int getOdo() {
		return odometer;
	}
	
	void drive(int km) {
		odometer += Math.abs(km);
	}
	
	//this method must return one line of code describing the object
	@Override
	public String toString() {
		String s = String.format("Model = %s. Odo = %dkm", model, odometer);
		return s;
	}
}
