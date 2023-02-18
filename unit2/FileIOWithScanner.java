package unit2;
import java.util.*;
import java.io.*;

//Daniel Yang
//Reading and outputting the first 15 lines of text using Scanner

public class FileIOWithScanner {

	public static void main(String[] args) {
		Scanner sc = null;
		try {
			File fortnite = new File("words_alpha.txt");
			sc = new Scanner(fortnite);
		} catch (FileNotFoundException e) {
			System.out.println("Kill program!");
			e.printStackTrace();
			System.exit(0);
		}
		for (int i = 0; i < 15; i++) {
			String line = sc.nextLine();
			System.out.println(line);
		}
		sc.close();
	}
}
