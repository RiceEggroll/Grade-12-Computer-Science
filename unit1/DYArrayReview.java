package unit1;

public class DYArrayReview {

	public static void main(String[] args) {

		int max = 0; 
		int min = 100;
		int[] numbers = new int[12];
		
		for (int i = 0; i <12; i++) {
			numbers[i] = (int)(Math.random()*100)+1;
			System.out.print(numbers[i] + " ");
			
			if (numbers[i] > max) {
				max = numbers[i];
			} 
			
			if (numbers[i] < min) {
				min = numbers[i];
			}
		}
		System.out.println("");

		System.out.printf("Biggest number is %d%n", max);
		System.out.printf("Smallest number is %d", min);
	}
}
