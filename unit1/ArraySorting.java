package unit1;

public class ArraySorting {
	public static void main(String[]args) {
		//15 random numbers
		int array [] = new int [15];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random()*100)+1;
		}
		
		//Selection sort
		for (int i = 0; i < array.length; i++) {
			int minimum = 101;
			int position = 0;
			for (int j = i; j < array.length; j++) {
				if (array[j] < minimum) {
					minimum = array[j];
					position = j;
				}
				
			}
			int temp = array[i];
			array[i] = array[position];
			array[position] = temp;
		}
		
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}		
	}
}
