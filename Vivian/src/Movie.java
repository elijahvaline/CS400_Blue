import java.util.List;

public class Movie {
    String title;
    List<String> genres;
    Float avgVote;

    public Movie(String title, List<String> genres, Float avgVote) {
        this.title = title;
        this.genres = genres;
        this.avgVote = avgVote;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return 0;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getDirector() {
        return null;
    }

    public String getDescription() {
        return null;
    }

    public Float getAvgVote() {
        return avgVote;
    }

    public int compareTo(Movie otherMovie) {
        return 0;
    }
}
