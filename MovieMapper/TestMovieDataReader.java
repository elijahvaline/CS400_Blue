// --== CS400 File Header Information ==--
// Name: Manisha Pillai
// Email: mvpillai@wisc.edu 
// Team: Blue
// Group: HA
// TA: Hang Yin
// Lecturer: Gary Dahl

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.List;
import java.util.ArrayList;

/* 
 * This class tests the MovieDataReader's readDataSet() method, and the Movie object methods
 */
public class TestMovieDataReader {
	
	/*
	 * This method tests that the readDataSet() method reads the file and saves the data
	 * properly.
	 */
	public static boolean testReader() {
		try {
			MovieDataReader read = new MovieDataReader();
			FileReader f = new FileReader("movies.csv");
			List<MovieInterface> movieList = read.readDataSet(f);
			//tests that all movies have been saved
			if(movieList.size()!=231)
				return false;
			//tests movies are in descending order by average votes
			if(movieList.get(5).getAvgVote()<movieList.get(60).getAvgVote())
				return false;
			if(movieList.get(130).getAvgVote()<movieList.get(200).getAvgVote())
				return false;
			if(movieList.get(200).getAvgVote()<movieList.get(230).getAvgVote())
				return false;
			return true;
		}
		catch(FileNotFoundException e){
			return false;
		}
		catch(IOException e){
			return false;
		}
		catch(DataFormatException e){
			return false;
		}
	}
	
	/*
	 * This method tests that the readDataSet() method throws an exception if any
	 * other file that doesn't exist is passed through the method.
	 */
	public static boolean testReaderExceptions() {
		try {
			MovieDataReader read = new MovieDataReader();
			FileReader f = new FileReader("something");
			return false;
		}
		catch(FileNotFoundException e){
			return true;
		}
	}
	
	/*
	 * This method tests that the get methods of the Movie class work fine, and that
	 * the right variables have been saved to the right spot.
	 */
	public static boolean testMovieMethods() {
		try {
			MovieDataReader read = new MovieDataReader();
			FileReader f = new FileReader("movies.csv");
			List<MovieInterface> movieList = read.readDataSet(f);
			if(!movieList.get(5).getTitle().equals("Stanley Stanton"))
				return false;
			if(movieList.get(5).getYear()!=2019)
				return false;
			//checks singular genre
			List<String> list = new ArrayList<String>();
			list.add("Drama");
			if(!movieList.get(5).getGenres().equals(list))
				return false;
			//checks with multiple genres, and that "" have been removed
			List<String> list2 = new ArrayList<String>();
			list2.add("Adventure");
			list2.add("Comedy");
			list2.add("Drama");
			if(!movieList.get(2).getGenres().equals(list2))
				return false;
			if(!movieList.get(5).getDirector().equals("Mars McCracken"))
				return false;
			if(!movieList.get(5).getDescription().equals("\"Forced to watch flashbacks "
					+ "of his life, a pious man comes to the realization he's dead and on "
					+ "his way to hell. Inspired by a true event.\""))
				return false;
			if(movieList.get(5).getAvgVote().compareTo((float)8.7)!=0)
				return false;
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	
	

	public static void main(String[] args) {
		System.out.println("testReader(): "+testReader());
		System.out.println("testReaderExceptions(): "+testReaderExceptions());
		System.out.println("testMovieMethods(): "+testMovieMethods());

		

	}

}
