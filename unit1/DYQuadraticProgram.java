package unit1;
import java.util.*;

//Daniel Yang
//This program takes in three user input values
//and uses the quadratic formulas

public class DYQuadraticProgram {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[]args) {
		
		double a, b, c, discriminant;
		
		System.out.print("Input a value = ");
		a = sc.nextDouble();
		
		System.out.print("Input b value = ");
		b = sc.nextDouble();
		
		System.out.print("Input c value = ");
		c = sc.nextDouble();
		
		sc.close();
		
		discriminant = (b*b) - (4*a*c);
		
		if (discriminant < 0){
			System.out.println("No roots ");

		} if (discriminant == 0) {
			System.out.printf("one root \t x = %.4f",-b /(2.0*a));

		} if (discriminant > 0) {
			System.out.printf("x1 = %.4f\t",(-b + Math.sqrt(discriminant))/(2.0*a));
			System.out.printf("x2 = %.4f", (-b - Math.sqrt(discriminant))/(2.0*a));

		}
	}
}
