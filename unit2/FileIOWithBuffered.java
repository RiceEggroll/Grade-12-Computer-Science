package unit2;
import java.io.*;

//Daniel Yang
//Reading and outputting the first 15 lines of text using BufferedReader

public class FileIOWithBuffered {

	public static void main(String[] args) {
		File f = new File("words_alpha.txt");
		BufferedReader brFile = null;
		try {
			brFile = new BufferedReader (new FileReader(f));
		} catch (FileNotFoundException e){
			System.out.println("Kill program!");
			e.printStackTrace();
			System.exit(0);
		}
		for (int i = 0; i < 15; i++) {
			String line = null;
			try {
				line = brFile.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(line);
		}
	}
}
