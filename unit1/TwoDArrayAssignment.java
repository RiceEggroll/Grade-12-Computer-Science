package unit1;

//Daniel Yang 
//2D Array Assignment

public class TwoDArrayAssignment {
	public static void main(String[]args) {

		//Question 1
		String [][] names = new String [5][4];		
		//20 elements

		//Question 2
		double [][] numbers = new double [10][5];

		//Question 3
		int [][] array = new int [3][5];
		int k=0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				k = k + 1;
				array[i][j] = k;
			}
		}

		//3a
		for (int j = 4; j > 0; j--) {
			System.out.print(array[1][j]);
		}
		//Outputs 10987

		//3b
		for (int i = 0; i < 3; i++) {
			System.out.print(array[i][4]);
		}
		//Outputs 51015

		//3c
		for (int i = 1; i < 2; i++) {
			for (int j = 4; j > 0; j--) {
				System.out.print(array[i][j]);
			}	
			System.out.println();
		}
		//Outputs 10987

		//3d
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j <3; j++) {
				System.out.print(array[j][i+j]);
			}
			System.out.println();
		}
		//Output is 1713
		//			2814
		//			3915
		
		//Question 4

		int grandTotal = 0, row2Sum = 0, poorSales = 0, largest = 0;
		//4a
		int [][] sales = new int [5][4];

		//4b and 4c
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				sales[i][j] = (int) (Math.random()*250)+51;
				grandTotal += sales[i][j];
			}
		}

		//4d
		for (int j = 0; j < 4; j++) {
			row2Sum += sales[1][j];
		}

		//4e
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (sales[i][j] < 60) {
					poorSales += sales[i][j];
				}
			}
		}

		//4f
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (largest < sales[i][j]) largest = sales[i][j];
			}
		}

		//Question 5
		int[][] data = { 
				{3, 2, 5},
				{1, 4, 4, 8, 13},
				{9, 1, 0, 2},
				{0, 2, 6, 3, -1, -8} };
		
		for (int i = 0; i < 4; i++) {
			int sums = 0;
			for (int j = 0; j < data[i].length; j++) {
				sums += data[i][j];
			}
		System.out.println(sums);
		}
		
		//Question 6
		int[][] scores = { {20, -5, 90, 22, 32},
                {34, 29, -3, 44, 2},
                {100, 0, 96, 37, -0} };
		
		for (int j = 0; j < 5; j++) {
			double average = 0.0;
			for (int i = 0; i < 3; i++) {
				average += scores[i][j];
			}
			average = average/3.0;
			System.out.printf("%.2f%n",average);
		}
	}
}
