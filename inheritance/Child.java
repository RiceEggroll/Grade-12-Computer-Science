package inheritance;

class Child extends Parent {

	String hair;
	
	Child() {
		hair = "blue";
		toes = 2;
	}
	
	Child(int t) {
		this(); //This runs the other constructor that matches ()
		toes = t;
	}
	
	
	void sleep() {
		System.out.println("child object is sleeping");
	}
	
	void fish() {
		System.out.println("uses a net");
	}
	
	void parent_hair() {
		System.out.println(super.hair);
	}
}
