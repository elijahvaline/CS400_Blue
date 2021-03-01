// --== CS400 File Header Information ==--
// Name: Manisha Pillai
// Email: mvpillai@wisc.edu 
// Team: Blue
// Group: HA
// TA: Hang Yin
// Lecturer: Gary Dahl

import java.util.List;

/*This class implements the MoveInterface and represents the movie object, which stores
 * and provides access to the movie's title, year, genres, director, description and 
 * average votes
 * */
public class Movie implements MovieInterface{
	
	private String title;
	private Integer year;
	private List<String> genres;
	private String director;
	private String description;
	private Float votes;
	
	/*This method constructs the movie object
	 * @param title, year, genres, director, description and votes
	 * */
	public Movie(String title, Integer year, List<String> genres, String director,
			String description, Float votes) {
		this.title = title;
		this.year = year;
		this.genres = genres;
		this.director = director;
		this.description = description;
		this.votes = votes;
	}

	/*The following methods all provide access to the information stored in the movie object
	 * */
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Integer getYear() {
		return year;
	}

	@Override
	public List<String> getGenres() {
		return genres;
	}

	@Override
	public String getDirector() {
		return director;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Float getAvgVote() {
		return votes;
	}

	/*This method overrides the compareTo method and compares the movies based on their
	 * average vote. It is designed so that the movies will be sorted in descending order
	 * of the average votes.
	 * */
	@Override
	public int compareTo(MovieInterface otherMovie) {
		if(votes>otherMovie.getAvgVote())
			return 1;
		if(votes<otherMovie.getAvgVote())
			return -1;
		return 0;
	}
	
	/*This method overrides the toString method and displays the movie's title, genres,
	 * directord, description and average votes.
	 * */
	@Override
	public String toString() {
		return "Title: "+getTitle()+" \nGenre(s): "+getGenres()+"\nDirector: "+getDirector()+
				"\nDescription: "+getDescription()+"\nAverage Vote: "+getAvgVote();
	}

}
