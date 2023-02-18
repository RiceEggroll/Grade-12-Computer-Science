package unit1;

//Daniel Yang
//Programs that utilize printf

public class MyPrintf {
	public static void main(String[]args) {
		
		//Program 1
		double x = 1.0/7;
		System.out.printf("%.5f\n", x);
		
		//Program 2
		String name = "Bessy";
		String colour = "brown";
		int weight = 1200;
		
		System.out.printf("The cow's name is %s, she is %s and and weights %d\n",name,colour,weight);
		
		//Program 3
		int xx = 123;
		System.out.println("| 12345678 |");
		System.out.println("| ======== |");
		System.out.printf("| %8d |%n", xx);
		System.out.printf("| %08d |%n", xx);
		System.out.printf("| %+8d |%n", xx);
		System.out.printf("| %-8d |%n", xx);
		System.out.printf("| %8.1f |%n", (double)xx);	
	}
}
