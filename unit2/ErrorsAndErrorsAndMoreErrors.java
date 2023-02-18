package unit2;

public class ErrorsAndErrorsAndMoreErrors {
	
	static int n;
	public static void main(String[] args) {

		doNothing();
	}

	public static void doNothing() {
		System.out.println(n++);
		doNothing();
	}
}