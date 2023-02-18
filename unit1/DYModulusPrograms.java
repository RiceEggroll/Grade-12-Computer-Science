package unit1;

//Daniel Yang
//Programs that utilize the modulus operator

public class DYModulusPrograms {

	public static void main(String[] args) {
		
		//Program 1
		for (int i=0;i<101;i++) {
			int secondColumn = i%8;
			System.out.println(i + "\t" + secondColumn);
		}
		
		//Program 2
		for (int i=1;i<201;i++){
			if (i%13 == 0) {
				System.out.print(i + " ");
			}
		}
		System.out.println(" ");
		
		//Program 3
		for (int i=1;i<200;i++){
			System.out.printf("%3d ", i);

			if (i%12 == 0) {
				System.out.println("");
			}
		}
	}
}