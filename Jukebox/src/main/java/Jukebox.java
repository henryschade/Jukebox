import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * This is a driver class for testing your <code>Song</code> and <code>PlayList</code> classes.
 *
 * This class will not compile until you have implemented both classes.
 * You should write your <code>Song</code> class first and test it using <code>SongTest.java</code>.
 * Then, write your <code>PlayList</code> class and test it using <code>PlayListTest.java</code>.
 * And finally, make sure everything works together and listen to your music using this class.
 *
 * @author CS121 Instructors
 * @author Henry J. Schade
 */
public class Jukebox
{
	/**
	 * Runs the program and handles all the menu options.
	 */
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);

		System.out.println("===========================");
		System.out.println("Welcome to the super jukebox!");
		System.out.println("===========================");

		PlayList playList = null;
		char option;
		do
		{
			System.out.println("You must create a playlist to get started.");
			System.out.println("---------------------------");
			System.out.println("(f) load playlist from file");
			System.out.println("(n) create new playlist");
			System.out.println("---------------------------");
			System.out.print("Choose an option: ");
			option = scan.nextLine().trim().charAt(0);
			switch(option)
			{
				case 'f':
					playList = loadPlayList("playlist.txt");
					break;
				case 'n':
					System.out.print("Playlist name: ");
					String name = scan.nextLine().trim();
					playList = new PlayList(name);
					break;
				default:
					System.out.println("Invalid option.");
					break;
			}
		}
		while(playList == null);

		System.out.println("Playlist added.");
		System.out.println(playList);

		do
		{
			System.out.print("What do you want to do (type 'm' to show menu)? ");
			option = scan.nextLine().trim().charAt(0);
			////http://stackoverflow.com/questions/12715246/how-to-check-if-a-character-in-a-string-is-a-digit-or-letter
			//if (!(Character.isLetter(option))){
			//	option = 'z';
			//}

			switch(option)
			{
				case 'm':
					//System.out.println("(p) play song");
					//System.out.println("(l) list songs");
					//System.out.println("(a) add song");
					//System.out.println("(d) delete song");
					//System.out.println("(i) info");
					//System.out.println("(s) search");
					//System.out.println("(q) quit");
					ShowMenu();
					break;
				case 'p':
					if(playList.getNumSongs() > 0) {
						System.out.println(playList);
						int id;
						do {
							System.out.print("Choose a valid song id: ");
							id = Integer.parseInt(scan.nextLine().trim());
						} while(id < 0 || id >= playList.getNumSongs());
						playList.playSong(id);
						System.out.println("Playing song: " + playList.getPlaying());
					} else {
						System.out.println("Playlist is empty.");
					}
					break;
				case 'a':
					Song song = readSong(scan);
					playList.addSong(song);
					System.out.println("Added song: " + song.getTitle());
					break;
				case 'd':
					if(playList.getNumSongs() > 0) {
						System.out.println(playList);
						int id;
						do {
							System.out.print("Choose a valid song id: ");
							id = Integer.parseInt(scan.nextLine().trim());
						} while(id < 0 || id >= playList.getNumSongs());
						playList.removeSong(id);
						System.out.println("Removed song.");
					} else {
						System.out.println("Playlist is empty.");
					}
					break;
				case 'l':
					System.out.println();
					System.out.println(playList);
					break;
				case 'i':
					System.out.println(playList.getInfo());
					System.out.println();
					break;
				case 's':
					System.out.println("Enter search string:");
					ArrayList<Song> objSearchList = playList.search(scan.nextLine().trim());

					if (objSearchList.size() > 0){
						//Display a list of songs found
						int intX = 0;
						int id;
						char chChar;

						do {
							for (intX = 0; intX < objSearchList.size(); intX++){
								System.out.println("(" + intX + ") " + objSearchList.get(intX).toString());
							}

							//System.out.print("Choose a song id to play (m for main menu): ");
							System.out.print("Choose a song id to play (m to go back to main menu): ");
							id = -1;
							chChar = scan.nextLine().trim().charAt(0);
							//http://stackoverflow.com/questions/12715246/how-to-check-if-a-character-in-a-string-is-a-digit-or-letter
							if (Character.isDigit(chChar)){
								id = Character.getNumericValue(chChar);
							}
							else{
								if (chChar == 'm'){
									//ShowMenu();
									break;
								}
							}
						} while(id < 0 || id >= objSearchList.size());
						if (chChar == 'm'){
							break;
						}

						Song objTempSong = objSearchList.get(id);

						for (intX = 0; intX < playList.getNumSongs(); intX++){
							if ((playList.getSong(intX).getArtist() == objTempSong.getArtist()) && (playList.getSong(intX).getTitle() == objTempSong.getTitle())){
								playList.playSong(intX);
								break;
							}
						}
						//System.out.println("Playing song: " + playList.getPlaying());
					}
					else{
						System.out.println("No songs found that match your search query.");
					}
					break;
				case 'q':
					System.out.println("Goodbye!");
					break;
				default:
					System.out.println("Invalid option.");
					break;
			}
		}
		while(option != 'q');
	}

	private static void ShowMenu(){
		System.out.println("(p) play song");
		System.out.println("(l) list songs");
		System.out.println("(a) add song");
		System.out.println("(d) delete song");
		System.out.println("(i) info");
		System.out.println("(s) search");
		System.out.println("(q) quit");
	}

	/**
	 * Reads song information from the user (via the scanner) and
	 * creates a new Song object.
	 *
	 * @param scan The input scanner to read from.
	 * @return The new song.
	 */
	public static Song readSong(Scanner scan)
	{
		System.out.print("Enter title: ");
		String title = scan.nextLine().trim();

		System.out.print("Enter artist: ");
		// TODO: read in the artist
		String artist = scan.nextLine().trim();

		System.out.print("Enter play time: ");
		// TODO: read in the playtime
		String playtime = scan.nextLine().trim();

		System.out.print("Enter file path: ");
		// TODO: read in the file path
		String songPath = scan.nextLine().trim();

		// TODO: replace null with instantiation of new song object
		//Song song = null;
		//Next code block copied from loadPlayList() routine below.
		int colon = playtime.indexOf(':');
		int minutes = Integer.parseInt(playtime.substring(0, colon));
		int seconds = Integer.parseInt(playtime.substring(colon+1));
		int playtimesecs = (minutes * 60) + seconds;

		Song song = new Song(title, artist, playtimesecs, songPath);

		return song;
	}

	/**
	 * Loads a new playlist from a given file. The file should have the following format:
	 * File Name
	 * Song 1 Title
	 * Song 1 Artist
	 * Song 1 Play time
	 * Song 1 File path
	 * Song 2 Title
	 * Song 2 Artist
	 * Song 2 Play time
	 * Song 2 File path
	 * etc.
	 * @param filename The name of the file to read the songs from.
	 * @return A new playlist containing songs with the attributes given in the file.
	 */
	public static PlayList loadPlayList(String filename)
	{
		PlayList list = null;
		try {
			Scanner scan = new Scanner(new File(filename));
			String playListName = scan.nextLine().trim();
			list = new PlayList(playListName);
			while(scan.hasNextLine()) {
				String title = scan.nextLine().trim();
				String artist = scan.nextLine().trim();
				String playtime = scan.nextLine().trim();
				String songPath = scan.nextLine().trim();

				int colon = playtime.indexOf(':');
				int minutes = Integer.parseInt(playtime.substring(0, colon));
				int seconds = Integer.parseInt(playtime.substring(colon+1));
				int playtimesecs = (minutes * 60) + seconds;

				Song song = new Song(title, artist, playtimesecs, songPath);
				list.addSong(song);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.err.println("Failed to load playlist. " + e.getMessage());
		}
		return list;
	}
}
