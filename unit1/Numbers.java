package unit1;

//Daniel Yang
//This program takes a string and for each digit in the string, it increases it by one

import java.util.*;

public class Numbers {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		
		String sentence = sc.nextLine();
		sc.close();
		
		//iterates through each letter in the sentence
		for (int i = 0; i < sentence.length(); i++) {
			int ascii = sentence.charAt(i); 	//converts the letter into ASCII 

			//determines if it is a number and if so, increases it by one
			//if the number is a 9, it sets it to the ASCII before 0 then increases it
			if (ascii >= 48 && ascii <= 57) {
				if (ascii == 57) ascii = 47;
				ascii++;
			}
			//convert back into a character then add it into the output
			System.out.print((char) ascii);
		}
	}
}
