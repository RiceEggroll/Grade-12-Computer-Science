package unit1;

//Daniel Yang
//This program counts how many times "the" occurs in an user input

import java.util.*;

public class CountingThe {
	public static void main (String[]args) {
		Scanner sc = new Scanner(System.in);
		
		String sentence = sc.nextLine();
		sc.close();
		
		//iterates through the input three letters at a time
		//if it detects that the three letters is a "the", adds one to counter
		int counter = 0; 
		for (int i = 0; i < sentence.length()-2; i++) {
			if (sentence.substring(i, i+3).equalsIgnoreCase("the")) counter++;
		}
		
		System.out.println(counter);
	}
}
