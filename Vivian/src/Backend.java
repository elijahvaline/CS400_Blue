import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Backend {

    public Backend(FileReader reader) {

    }

    public void addGenre(String genre) {

    }

    public void addAvgRating(String rating) {

    }

    public void removeGenre(String genre) {

    }

    public void removeAvgRating(String rating) {

    }

    public List<String> getGenres() {
        List<String> genres = new ArrayList<String>();
        genres.add("Action");
        genres.add("Horror");
        genres.add("Romance");
        genres.add("Musical");
        return null;
    }

    public List<String> getAvgRatings() {
        List<String> ratings = new ArrayList<String>();
        for (int i = 0; i <= 10; i++) {
            ratings.add(i + "");
        }
        ratings.remove(5);
        return ratings;
    }

    public int getNumberOfMovies() {
        return 10;
    }

    public List<Movie> getThreeMovies(int startingIndex) {
        List<String> genres1 = new ArrayList<String>();
        genres1.add("Horror");
        List<String> genres2 = new ArrayList<String>();
        genres1.add("Action");
        List<String> genres3 = new ArrayList<String>();
        genres1.add("Comedy");
        genres1.add("Romance");
        genres1.add("Musical");

        Movie movie1 = new Movie("The Source of Shadows", genres1, 3.5F);
        Movie movie2 = new Movie("The Insurrection", genres2, 2.9F);
        Movie movie3 = new Movie("Valley Girl", genres3, 5.4F);
        List<Movie> threeMovies = new ArrayList<Movie>();
        threeMovies.add(movie1);
        threeMovies.add(movie2);
        threeMovies.add(movie3);
        return threeMovies;
    }

    public List<String> getAllGenres() {
        List<String> genres = new ArrayList<String>();
        genres.add("Action");
        genres.add("Horror");
        genres.add("Comedy");
        genres.add("Romance");
        genres.add("Musical");
        return genres;
    }
}