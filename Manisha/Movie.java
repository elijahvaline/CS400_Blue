import java.util.List;

public class Movie implements MovieInterface{
	
	private String title;
	private Integer year;
	private List<String> genres;
	private String director;
	private String description;
	private Float votes;
	
	public Movie(String title, Integer year, List<String> genres, String director,
			String description, Float votes) {
		this.title = title;
		this.year = year;
		this.genres = genres;
		this.director = director;
		this.description = description;
		this.votes = votes;
	}

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

	@Override
	public int compareTo(MovieInterface otherMovie) {
		// TODO Auto-generated method stub
		if(votes>otherMovie.getAvgVote())
			return -1;
		if(votes<otherMovie.getAvgVote())
			return 1;
		return 0;
	}

}
