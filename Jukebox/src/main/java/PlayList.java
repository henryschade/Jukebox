import java.util.ArrayList;

/**
 * Uses an ArrayList of Song objects to keep track of a user's songs in a play list.
 *
 * Here is an example of how a play list can be created.
 * <pre>
 *     
 * </pre>
 *
 * @author Henry J. Schade
 */
public class PlayList
{
	//Member variables
	private String strPlayListName;
	private Song objCurSong;
	private ArrayList<Song> objPlayList;

	//Constructor(s)
	public PlayList(String strPlayListName){
		this.strPlayListName = strPlayListName;
		this.objCurSong = null;
		this.objPlayList = new ArrayList<Song>();
	}

	//Methods - Getters and Setters
	/**
	* getPlayListName
	 * @param nothing
	 * @return the play list name
	*/
	public String getPlayListName(){
		return this.strPlayListName;
	}
	public String getName(){
		//Needed so the default Jukebox code will work, and probably the tester too.
		return getPlayListName();
	}

	/**
	* setPlayListName
	 * @param strTitle song's title
	 * @return the play list name
	*/
	public String setPlayListName(String strNewPlayListName){
		this.strPlayListName = strNewPlayListName;
		return this.strPlayListName;
	}
	public String setName(String strNewPlayListName){
		//Needed so the default Jukebox code will work, and probably the tester too.
		return setPlayListName(strNewPlayListName);
	}

	/**
	* getPlaying
	 * @param nothing
	 * @return the current song playing
	*/
	public Song getPlaying(){
		return this.objCurSong;
	}

	/**
	* getPlayList
	 * @param nothing
	 * @return the play list
	*/
	public ArrayList<Song> getPlayList(){
		return this.objPlayList;
	}

	//Methods - That do work
	/**
	* addSong
	 * @param a song
	 * @return nothing
	*/
	public void addSong(Song objNewSong){
		//Takes a song as an argument and returns nothing. Adds it to the songList.

		this.objPlayList.add(objNewSong);
	}

	/**
	* removeSong
	 * @param integer index
	 * @return the removed song (null if the index is out of range)
	*/
	public Song removeSong(int intIndex){
		//Takes an integer index as an argument and returns the removed song. 
		//Removes the song from the songList. Returns null if the index is out of range.

		//Song objSong = new Song("","",0,"");
		//if ((intIndex >= this.objPlayList.size()) || (intIndex < 0)){
		//	//return null;
		//	objSong = null;
		//}
		//else{
		//	objSong = this.objPlayList.get(intIndex);
		//	this.objPlayList.Remove(intIndex);
		//}
		Song objSong = getSong(intIndex);
		if (objSong != null){
			this.objPlayList.Remove(intIndex);
		}

		return objSong;
	}

	/**
	* getNumSongs
	 * @param nothing
	 * @return the number of songs in the playlist.
	*/
	public int getNumSongs(){
		//Returns the number of songs in the playlist.
		return this.objPlayList.size();
	}

	/**
	* getTotalPlayTime
	 * @param nothing
	 * @return the total playtime of all songs in the playlist (in seconds)
	*/
	public int getTotalPlayTime(){
		//Returns the total playtime of all songs in the playlist (in seconds).

		int intTotalTime = 0;
		for (Song objSong : this.objPlayList){
			intTotalTime = intTotalTime + objSong.getPlayTime();
		}

		return intTotalTime;
	}

	/**
	* getSong
	 * @param integer index 
	 * @return the song at that index. (null if song not exist)
	*/
	public Song getSong(int intIndex){
		//Takes an integer index as an argument and returns the song at that index, if it exists. Returns null if it does not exist.

		Song objSong = null;
		if ((intIndex >= this.objPlayList.size()) || (intIndex < 0)){
			//return null;
			objSong = null;
		}
		else{
			objSong = this.objPlayList.get(intIndex);
		}

		return objSong;
	}

	/**
	* playSong
	 * @param  integer index
	 * @return nothing
	*/
	public void playSong(int intIndex){
		//Takes an integer index as an argument and plays the corresponding song in the play list. If the index is out of range, do nothing.

		Song objSong = getSong(intIndex);
		if (objSong == null){
			System.out.println("Failed to get the song.");
		}
		else{
			objCurSong = objSong;
			objSong.play();
		}
	}

	/**
	* getInfo
	 * @param nothing
	 * @return Assorted stats about the play list
	*/
	public String getInfo(){
		//Returns a String that will display the following stats
			//The average play time is: 129.00 seconds
			//The shortest song is: An awesome song      Coolio Jo            sounds/westernBeat.wav             5
			//The longest song is: Eighties Jam         Some 80's band       sounds/eightiesJam.wav           201
			//Total play time: 387 seconds

		int intPlayTime = 0;
		double dblAvgPlayTime = 0.0;
		String strShortest = "";
		int intShortest = -1;
		String strLongest = "";
		int intLongest = -1;
		int intTotalPlayTime = 0;
		String strRet = "";

		intTotalPlayTime = getTotalPlayTime();
		dblAvgPlayTime = (intTotalPlayTime / getNumSongs());

		for (Song objSong : this.objPlayList){
			intPlayTime = objSong.getPlayTime();

			if ((intShortest == -1) || (intPlayTime < intShortest)){
				intShortest = intPlayTime;
				//strShortest = objSong.getTitle();
				strShortest = objSong.toString();
			}
			if ((intLongest == -1) || (intPlayTime > intLongest)){
				intLongest = intPlayTime;
				//strLongest = objSong.getTitle();
				strLongest = objSong.toString();
			}
		}

		strRet = "The average play time is: " + dblAvgPlayTime + " seconds" + "\r\n";
		strRet = strRet + "The shortest song is " + strShortest + "\r\n";
		strRet = strRet + "The longest song is: " + strLongest + "\r\n";
		strRet = strRet + "Total play time: " + intTotalPlayTime + " seconds" + "\r\n";

		return strRet;
	}

	/**
	* toString
	 * @param nothing
	 * @return the playlist list/display
	*/
	public String toString(){
		//Sample output:
			//------------------
			//Sample Playlist (3 songs)
			//------------------
			//(0) Classical            A Classical Artist   sounds/classical.wav             181
			//(1) Eighties Jam         Some 80's band       sounds/eightiesJam.wav           201
			//(2) New Age              Javya                sounds/newAgeRhythm.wav          132
			//------------------

		String strRet = "";
		Song objSong = new Song("","",0,"");

		strRet = "------------------" + "\r\n";
		//strRet = strRet + getPlayListName() + "(" + getNumSongs() + ") songs)";
		strRet = strRet + getName() + "(" + getNumSongs() + ") songs" + "\r\n";
		strRet = strRet + "------------------" + "\r\n";

//		int intX = 0;
//		for (Song objSong : this.objPlayList){
//			strRet = strRet + "(" + intX + ") " + objSong.toString();
//			intX++;
//		}
		for (int intX = 0; intX < this.objPlayList.size(); intX++){
			objSong = this.objPlayList.get(intX);
			strRet = strRet + "(" + intX + ") " + objSong.toString() + "\r\n";
		}

		strRet = strRet + "------------------" + "\r\n";

		return strRet;
	}

}
