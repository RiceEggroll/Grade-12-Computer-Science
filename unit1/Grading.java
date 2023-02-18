package unit1;

public class Grading {	
	//static String grade = "";
	public static void main (String[]args) {
		int mark = 65;
		String grade = "";
		if (mark >= 0 && mark <= 100) {
			System.out.printf("Mark = %d\t%s",mark,calc(mark, grade));
		}
	}
	
	static String calc(int mark, String grade) {
		if (mark < 50) grade = "F";
		if (mark >= 50 && mark < 60) grade = "D";
		if (mark >= 60 && mark < 70) grade = "C";
		if (mark >= 70 && mark < 80) grade = "B";
		if (mark >= 80 && mark < 90) grade = "A";
		if (mark >= 90) grade = "A+";
		
		return grade;
	}
}