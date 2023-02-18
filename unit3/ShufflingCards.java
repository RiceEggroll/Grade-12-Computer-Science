package unit3;
import java.util.*;

//Daniel Yang
//This program outputs an array of cards, stored as an object
//Then the cards get shuffled and is output again

public class ShufflingCards {

	//I use hashmaps to convert the suits (hearts, diamonds, spades and clubs) from numbers
	//and to convert the honours (ace, jack, queen, king) from numbers
	static HashMap<Integer, Character> CONVERTSUITS = new HashMap<Integer, Character>();
	static HashMap<Integer, String> CONVERTVALUE = new HashMap<Integer, String>();

	//To store the cards
	static ArrayList<Cards> DECK = new ArrayList<Cards>();
	
	public static void main(String[] args) {
		CONVERTSUITS.put(1, 'H');
		CONVERTSUITS.put(2, 'D');
		CONVERTSUITS.put(3, 'S');
		CONVERTSUITS.put(4, 'C');
		
		CONVERTVALUE.put(0, "A");
		CONVERTVALUE.put(11, "J");
		CONVERTVALUE.put(12, "Q");
		CONVERTVALUE.put(13, "K");
		
		//inputs each card into the arraylist
		for (int i = 1; i < 5; i++) {
			for (int x = 0; x < 14; x++) {
				DECK.add(new Cards(i, x));
			}
		}
		
		System.out.print("Unshuffled: ");
		DeckOutput(DECK);
		
		//Gets a random number and uses that number and swaps the loop value with that value
		for (int i = 0; i < 52; i++) {
			int swap = (int) (Math.random()*52);
			
			Cards temp = DECK.get(swap);
			DECK.set(swap, DECK.get(i));
			DECK.set(i, temp);
		}
		
		System.out.print("Shuffled: ");
		DeckOutput(DECK);
	}

	private static class Cards {
		//Creates an object for the cards and converts the numbers into their respective suit/honour
		char suit;
		String value;
		
		Cards (int i, int x){
			suit = CONVERTSUITS.get(i);
				
			if (CONVERTVALUE.get(x) == null) value = Integer.toString(x);
			else value = CONVERTVALUE.get(x);
		}
	}
	
	//since I print out the deck more than once, I use a method for readability
	static void DeckOutput(ArrayList<Cards> DECK) {
		for (Cards a : DECK) {
			System.out.print(a.value + "" + a.suit +" ");
		}
		System.out.println();
	}
}
