package unit1;

//Daniel Yang
//Programs that uses Scanner to get input

import java.util.*;
public class ScannerPrograms {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.print("What food do you like? ");
		String food = sc.next();
		sc.nextLine();
		System.out.println("I like " + food + " too.");
		
		System.out.print("How many pieces do you have? ");
		int num = getInt();
		
		if (num > 1) {
			System.out.println("Can I have one?");
		}
	}
	static int getInt() {
		while(!sc.hasNextInt()) {
			System.out.println("Please enter an integer");
			sc.nextLine();
		}
		return sc.nextInt();
	}
}