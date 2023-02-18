package unit1;

public class SplittingDigits {
	public static void main(String[]args) {
		
		int number = 90;
		
		//using modulus
		int a = number/10;
		int b = number%10;
		System.out.printf("a = %d\tb = %d%n", a, b);
		
		//using double
		double d = number/10.0;
		a = (int) d;
		b = number - a*10;
		System.out.printf("a = %d\tb = %d", a, b);

	}
}
