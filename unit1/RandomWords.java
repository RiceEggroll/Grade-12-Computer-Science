package unit1;

//Daniel Yang
//This program generates a random word with 1-2 vowels in random spots
//This program also considers 'y' as a vowel

public class RandomWords {

	public static void main(String[] args) {
		//A character array of all the letters categorized into vowels and consonants
		char[] vowels = {'a','e','i','o','u','y'};
		char[] consonants = {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','z'};
		
		//Determines the number of vowels in the word
		int numVowels = (int) (Math.random()*2)+1;
		int numConsonants = 6 - numVowels;
		
		StringBuffer output = new StringBuffer("");
		
		//chooses a random letter in the string buffer and appends the letter into the output
		for (int i = 0; i < numConsonants; i++) {
			output.append(consonants[(int)(Math.random()*20)]);
		}
		
		//chooses two random numbers, the first randomizer determines where the vowel will be
		//the second randomizer determines which vowel it will be using the character array
		for (int i = 0; i < numVowels; i++) {
			output.insert((int)(Math.random()*output.length()),vowels[(int)(Math.random()*6)]);
		}
		System.out.println(output);
	}
}
	