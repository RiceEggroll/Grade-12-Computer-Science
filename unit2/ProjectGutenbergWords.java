package unit2;
import java.util.*;
import java.io.*;
import java.net.*;

//Daniel Yang
//Find the most common word that occurs in a book, excluding articles

public class ProjectGutenbergWords {

	public static void main(String[] args) {
		HashMap <String, Integer> data = new HashMap<String,Integer>();
		BufferedReader brWeb = null;
		try {
			URL book = new URL("https://www.gutenberg.org/files/1661/1661-0.txt");
			brWeb = new BufferedReader(new InputStreamReader(book.openStream()));
		} catch (MalformedURLException e){
			System.out.println("Bad url");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Exception Error");
			e.printStackTrace();
		}
		String line = null;
		while (true) {
			
			try {
				line = brWeb.readLine();
			} catch (IOException e) {
				System.out.println("IO Exception error");
				e.printStackTrace();
			}
			if (line == null) {
				break;
			}
			
			line = line.toLowerCase().replaceAll("\\p{Punct}","");
			line = line.replaceAll(" the "," ");
			line = line.replaceAll(" a "," ");
			line = line.replaceAll(" an "," ");
			
			String[] words = line.split(" ",0);
				
			/*for (int i = 0; i < words.length; i++) {
				System.out.print(words[i]+ " ");
			}
			System.out.println("");*/
			
			for (int i = 0; i < words.length; i++) {
				if (data.get(words[i]) == null) {
					data.put(words[i], 1);
				} else {
					data.put(words[i], data.get(words[i])+1);
				}
			}
		}
		data.remove("");
		int max = 0;
		String biggest = null;
		for (String tempWord : data.keySet()) {
			
			if (data.get(tempWord) > max) {
				max = data.get(tempWord);
				biggest = tempWord;
			}
		}
		System.out.println(biggest + " " + max);
	}
}
