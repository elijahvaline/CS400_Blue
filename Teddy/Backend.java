// --== CS400 File Header Information ==--
// Name: Theodore Louis Peters
// Email: tlpeters3@wisc.edu
// Team: Blue
// Group: HA
// TA: Hang Yin
// Lecturer: Gary Dahl
// Notes to Grader: N.A.

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.io.Reader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Backend implements the BackendInterface. This allows the Frontend, and ultimately the application
 * user to retrieve data as well as do work on that data. Backend has two constructors, that through
 * interaction with the implementation of MovieDataReaderInterface, allow for the hash table to be
 * instantiated with a list of Movie objects. Backend allows for the ultimate user to select movies
 * to view by adding and removing both genres and ratings. Users can then select a list of three
 * movies ordered by rating to get a recommendation.
 */
public class Backend implements BackendInterface {

  private List<Movie> selectedMovies;
  private List<String> selectedGenres;
  private List<String> selectedRatings;
  private List<String> allGenres;
  private List<MovieInterface> allMovieObjects;
  private HashTableMap<String, List<Movie>> hashMap;

  /**
   * Backend constructor accepts a FileReader argument which is then passed to the MovieDataReader
   * class in order to obtain a list of all movie objects.
   * 
   * @param movieFile - Object of type FileReader that will be passed to the MovieDataReader class
   */
  public Backend(FileReader movieFile) {
    MovieDataReader getMovies = new MovieDataReader();

    // Try catch passes the FileReader to the MovieDataReader instance, catches an exception if the
    // FileReader is null.
    try {
      allMovieObjects = getMovies.readDataSet(movieFile);
      // A call is then made to the private method hashFiller to populate the hashtable
      hashFiller();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * Backend constructor accepts a Reader argument which is then scanned, turned into a file, passed
   * to the FileReader class, and ultimately passed back to the MovieDataReaderInterface
   * implementation.
   * 
   * @param s - Object of type Reader that will be scanned and used to retrieve movie objects.
   */
  public Backend(Reader s) {
    String line = "";
    Scanner scanner = new Scanner(s);

    // scanner scans the Reader object and creates a string of all movie data.
    while (scanner.hasNextLine()) {
      line += (scanner.nextLine() + "\n");
    }
    scanner.close();

    // A new File is created and the String of movie data is written in.
    try {
      File file = new File("Movies");
      FileWriter writer = new FileWriter(file);
      writer.write(line);
      writer.close();

      // The updated file is then used to construct a FileReader object which is passed to the
      // MovieDataReader class.
      FileReader read = new FileReader(file);
      MovieDataReader getMovies = new MovieDataReader();
      allMovieObjects = getMovies.readDataSet(read);
      hashFiller();
    } catch (Exception e) {
    }
  }

  /**
   * hashFiller is a private method that uses the instance variable allMovieObjects in order to
   * populate the hashTable. hashFiller additionally instantiates the remaining instance variables.
   */
  private void hashFiller() {
    hashMap = new HashTableMap<String, List<Movie>>();
    selectedGenres = new ArrayList<String>();
    selectedRatings = new ArrayList<String>();
    selectedMovies = new ArrayList<Movie>();

    // Iterates through movie object ratings and creates new key value pairs in hashMap if the pair
    // does not already exist. If it exists, the movie object is added to the to pairs value array.
    for (MovieInterface x : allMovieObjects) {
      String avgRate = "" + (int) Math.floor(x.getAvgVote());
      if (!(hashMap.put(avgRate, new ArrayList<Movie>() {
        {
          add((Movie) x);
        }
      }))) {
        hashMap.get(avgRate).add((Movie) x);
      }

      // Iterates through movie object genres and creates new key value pairs in hashMap if the pair
      // does not already exist. If it exists, the movie object is added to the to pairs value
      // array.
      for (String genre : x.getGenres()) {
        if (!(hashMap.put(genre, new ArrayList<Movie>() {
          {
            add((Movie) x);
          }
        }))) {
          hashMap.get(genre).add((Movie) x);
        }
      }
    }
  }

  /**
   * addGenre adds a genre to the specification of movies. It then updates the movie set to include
   * the added genre.
   * 
   * @param genre - String that represents the genre to be added.
   */
  @Override
  public void addGenre(String genre) {

    if (selectedGenres.contains(genre)) {
    } else {

      selectedGenres.add(genre);

      if (selectedMovies.isEmpty()) {
        selectedMovies = hashMap.get(genre);
      } else {
        // Every movie in the selected movie set that does not contain the new genre is removed.

        List<Movie> toBeRemoved = new ArrayList<Movie>();
        for (Movie x : selectedMovies) {
          if (!(x.getGenres().contains(genre)))
            toBeRemoved.add(x);
        }
        selectedMovies.removeAll(toBeRemoved);
      }
    }
  }

  /**
   * addAvgRating adds a new rating specification for the desired movies.
   * 
   * @param rating - String that represents the new rating to be added.
   */
  @Override
  public void addAvgRating(String rating) {
    if (selectedRatings.contains(rating)) {
    } else {

      // Try catch is necessary in case no movie in the hashmap has the added rating. In which case,
      // the exception is caught and the function continues without changing the selected movies.
      try {
        selectedRatings.add(rating);

        // If selected genres is empty, then every movie with the rating can be added.
        if (selectedGenres.isEmpty())
          selectedMovies.addAll(hashMap.get(rating));

        // If this is the first rating, all movies in the selected set that don't contain the rating
        // are removed.
        else if (selectedRatings.size() == 1) {
          List<Movie> toBeRemoved = new ArrayList<Movie>();
          for (Movie x : selectedMovies) {
            String avgRate = "" + (int) Math.floor(x.getAvgVote());
            if (!(avgRate.equals(rating)))
              toBeRemoved.add(x);
          }
          selectedMovies.removeAll(toBeRemoved);
        }

        // Else selectedGenres is not empty, in which case movies that contain the rating are only
        // added if they contain the list of selected genres.
        else {
          for (Movie x : hashMap.get(rating)) {
            Boolean contains = true;
            for (String genre : selectedGenres) {
              if (!(x.getGenres().contains(genre)))
                contains = false;
            }
            if (contains == true)
              selectedMovies.add(x);
          }
        }
      } catch (NoSuchElementException e) {
      }
    }
  }

  /**
   * removeGenre removes a genre from selected genres and updates the selected movie list. Because
   * removing a genre means that all other selected genres need to be reviewed, for the sake of
   * simplicity the removeGenre method clears the selectedMovies list and rebuilds it with all the
   * genres and ratings minus the genre to be removed.
   * 
   * @param genre - String that represents the genre to be removed.
   */
  @Override
  public void removeGenre(String genre) {

    if (selectedGenres.contains(genre)) {
      selectedGenres.remove(genre);
      // Placeholder arrays that store the selected genres and ratings.
      List<String> placeG = new ArrayList<String>();
      List<String> placeR = new ArrayList<String>();
      placeG.addAll(selectedGenres);
      placeR.addAll(selectedRatings);

      // Instance arrays are cleared in order for them to be repopulated using Backend methods.
      selectedGenres.clear();
      selectedRatings.clear();
      selectedMovies = new ArrayList<Movie>();

      // Lists are populated with all original genres and ratings minus the genre that was removed.
      for (String r : placeR) {
        this.addAvgRating(r);
      }
      for (String g : placeG) {
        this.addGenre(g);
      }
    }
  }

  /**
   * removeAvgRating removes a rating from the selected ratings and updates the selected movie list
   * accordingly. Every movie in the selected movie list that contains the rating to be removed is
   * removed from the list.
   * 
   * @param rating - String that represents the rating to be removed.
   */
  @Override
  public void removeAvgRating(String rating) {
    if (selectedRatings.contains(rating)) {
      selectedRatings.remove(rating);

      // If removing the rating empties the selected ratings, the selected movies are rebuilt by
      // adding all selected genres.
      if (selectedRatings.size() == 0 && selectedGenres.size() != 0) {
        selectedMovies = new ArrayList<Movie>();
        for (String x : selectedGenres) {
          try {
            selectedMovies.addAll(hashMap.get(x));
          } catch (NoSuchElementException e) {
          }
        }
      } else {
        // Else every movie that contains the rating is removed from the selectedMovies list.
        try {
          selectedMovies.removeAll(hashMap.get(rating));
        } catch (NoSuchElementException e) {
        }
      }
    }
  }

  /**
   * getGenres return a list of all selected genres.
   * 
   * @return a list of type string representing currently selected genres.
   */
  @Override
  public List<String> getGenres() {
    // TODO Auto-generated method stub
    return selectedGenres;
  }

  /**
   * getRatings return a list of all selected ratings.
   * 
   * @return a list of type string representing currently selected ratings.
   */
  @Override
  public List<String> getAvgRatings() {

    return selectedRatings;
  }

  /**
   * getGenres returns the number of movies that meet the current parameters.
   * 
   * @return an int representing the number of selected movies.
   */
  @Override
  public int getNumberOfMovies() {
    return selectedMovies.size();
  }

  /**
   * getAllGenres return a list of all genres in the entire data set.
   * 
   * @return a list of type string representing all genres in the data set.
   */
  @Override
  public List<String> getAllGenres() {
    // On first call, the method instantiates and updates the instance variable allGenres. If the
    // method has already been called, the allGenres variable has already been made and can be
    // returned without need for any other actions.
    if (allGenres == null) {
      allGenres = new ArrayList<String>();
      for (MovieInterface movie : allMovieObjects) {
        for (String genre : movie.getGenres()) {
          if (!allGenres.contains(genre))
            allGenres.add(genre);
        }
      }
    }
    return allGenres;
  }

  /**
   * getThreeMovies orders selected in moves in descending order based on avg rating. It then
   * returns a list of up to three movies beginning at the passed index. If three movies are not
   * available it will return as many as available, or none at all.
   * 
   * @return a list of type MovieInterface whose objects are in descending order of avg rating.
   */
  @Override
  public List<MovieInterface> getThreeMovies(int startingIndex) {
    List<MovieInterface> descendRate = new ArrayList<>();

    // Try catch necessary in case the passed starting index is out of bounds.
    try {
      // selectedMovies are added to a new list. As each movie is added it is compared to the others
      // using the avgRating. It is then placed in the proper location so that the new array
      // maintains descending order.
      if (selectedMovies.size() > 0) {
        descendRate.add(selectedMovies.get(0));
        for (int x = 1; x < selectedMovies.size(); x++) {
          int size = descendRate.size();
          int mover = 0;
          while (selectedMovies.get(x).compareTo(selectedMovies.get(mover)) <= 0 && mover < size) {
            mover++;
          }
          if (mover == size)
            descendRate.add(selectedMovies.get(x));
          else
            descendRate.add(mover, selectedMovies.get(x));
        }

        if (startingIndex + 3 < descendRate.size())
          return descendRate.subList(startingIndex, (startingIndex + 3));
        // If three movies are not available, the sublist from the starting index to the end of the
        // list is returned.
        else
          return descendRate.subList(startingIndex, descendRate.size());
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Index out of bounds");
    }
    return new ArrayList<MovieInterface>();
  }
}
