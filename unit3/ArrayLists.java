package unit3;
import java.util.*;

//Daniel Yang
//This program allows users to add and remove songs into their own playlist. 
//They can also view their playlist at any time. 
//Use Song.java for objects

public class ArrayLists {
	static ArrayList<Song> songList = new ArrayList<Song>();
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.println("Enter your playlist's name");
		Song.playlist = sc.nextLine();
		
		while (true) {
			System.out.println("What would you like to do?");
			System.out.println("1. Add a song \n2. Remove a song \n3. View your playlist \n4. Exit");
			int choice = Integer.parseInt(sc.nextLine());
			if (choice == 1) {
				addSong();
			}
			
			if (choice == 2) {
				if (songList.size() > 0) removeSong();
				else System.out.println("You currently have no songs, add some first.");
			}
			if (choice == 3) {
				if (songList.size() > 0) {
					System.out.printf("%s%n", Song.playlist);
					System.out.printf("   %12s\t%12s\t%12s\t%12s %n","Song","Artist","Album","Year");
					viewPlaylist(songList);
				}
				else System.out.println("You currently have no songs, add some first."); 
			}
			if (choice == 4) {
				System.out.print("Goodbye!");
				System.exit(0);
			}
		}
	}


	static void addSong() {
		System.out.println("Enter in the name of the song, artist, album then year of release");

		String name = sc.nextLine();
		String artist = sc.nextLine();
		String album = sc.nextLine();
		String year = sc.nextLine();

		songList.add(new Song(name, artist, album, year));
	}

	static void removeSong() {
		System.out.println("Which song would you like to remove?");
		viewPlaylist(songList);
		int removal = Integer.parseInt(sc.nextLine());
		songList.remove(removal-1);
	}
	static void viewPlaylist(ArrayList<Song> songList) {
		int i = 0;
		for (Song s : songList) {
			i++;
			System.out.printf("%d. %12s\t%12s\t%12s\t%12s %n",i,s.name,s.artist,s.album,s.year);
		}
		System.out.println();
	}
}
