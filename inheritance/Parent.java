package inheritance;

class Parent extends Ancestor{
	
	//NEVER DO THIS. You are just creating a new 
	//variable that shadows the original
	String hair = "pink"; 
	
	//toes show up here. Ancestor has no toes.
	int toes = 7;

	Parent() {
		hair = "green";
	}
	
	@Override
	void swim() {
		System.out.println("floating down the river");
	}
	
	@Override
	void fish() {
		System.out.println("using a fishing rod");
	}
	
	@Override
	public String toString() {
		return String.format("%s hair, %d toes", hair, toes);
	}
	
	void ancientFishing() {
		super.fish();
	}
}
