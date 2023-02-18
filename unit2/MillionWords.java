package unit2;
import java.io.*;

//Daniel Yang
//This program reads a text file and finds "million" words
//million words are words with letter that have a product of one million (more or less 500)

public class MillionWords {

	public static void main(String[] args) {
		File f = new File("words_alpha.txt");
		BufferedReader brFile = null;
		try {
			brFile = new BufferedReader (new FileReader(f));
		} catch (FileNotFoundException e){
			System.out.println("that file does not exist");
			e.printStackTrace();
			System.exit(0);
		}
		String line = "";
		while (true) {
			try {
				line = brFile.readLine();
			} catch (IOException e) {
				System.out.println("lol IO exception error");
				e.printStackTrace();
			}
			if (line == null) {
				break;
			}
			
//			 a = 1, b = 2, c = 3 ... z = 26
			int count = 1;
			for (int i = 0; i < line.length(); i++) {
				count *= line.charAt(i)-96;
			}
			
//			z = 1, y = 2, x = 3 ... a = 26
//			int count = 1;
//			for (int i = 0; i < line.length(); i++) {
//				int value = line.charAt(i)-121;
//				if (value <= 0) value = Math.abs(value-2);
//				count *= value;	
//			}
			
			if (count >= 999500 && count <= 1000500) {
				System.out.println(line + " " + count);
			}
		}
		try {
			brFile.close();
		} catch (IOException e) {
			System.out.println("IO exception error");
			e.printStackTrace();
		}
	}
}
