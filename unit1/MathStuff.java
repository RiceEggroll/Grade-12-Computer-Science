package unit1;
import java.util.*;

public class MathStuff {
	public static void main(String[]args) {
		Scanner input = new Scanner(System.in);
		int number,number2;
		String operation;
		
		number = Integer.parseInt(input.nextLine());
		operation = input.nextLine();
		number2 = Integer.parseInt(input.nextLine());
		
		if (operation.equals("+")) {
			System.out.println(number+number2);
		}
		else if (operation.equals("-")) {
			System.out.println(number-number2);
		}
		else if (operation.equals("*")) {
			System.out.println(number*number2);
		}
		else if (operation.equals("/")) {
			System.out.println(number/number2);
		
		input.close();
		}
	}
}