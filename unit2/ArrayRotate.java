package unit2;

//Daniel Yang
//This program takes the first two elements of an array and puts them to the back

public class ArrayRotate {

	public static void main(String[] args) {
		int[] marks = {9,18,5,11,15,0,10};
		
		int temp1 = marks[0],temp2 = marks[1];
		
		int[] newmarks = new int[marks.length];
		for (int i = 2; i < newmarks.length; i++) {
			newmarks[i-2] = marks[i];
		}
		
		newmarks[newmarks.length-1] = temp2;
		newmarks[newmarks.length-2] = temp1;
		
		for (int i = 0; i < newmarks.length; i++) {
			System.out.print(newmarks[i]+ " ");
		}
	}
}
