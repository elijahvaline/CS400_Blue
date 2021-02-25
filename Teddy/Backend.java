import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.io.Reader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;


public class Backend implements BackendInterface {

  private List<Movie> selectedMovies;
  private List<String> selectedGenres;
  private List<String> selectedRatings;
  private List<String> allGenres;
  private List<MovieInterface> allMovieObjects;
  
  private HashTableMap<String, List<Movie>> hashMap;

  public Backend(FileReader movieFile) {

    MovieDataReader getMovies = new MovieDataReader();
    try {
      allMovieObjects = getMovies.readDataSet(movieFile);
      hashFiller();

    } catch (Exception e) {
      System.out.println(e);
    }

  }

  public Backend(Reader s) {
 String line = "";
    
    Scanner scanner = new Scanner(s);
   
    while(scanner.hasNextLine()) {
      line += (scanner.nextLine() + "\n");
    }  
    scanner.close();
    
    try {
    File file = new File("Movies");
    FileWriter writer = new FileWriter(file);
    writer.write(line);
    
    writer.close();
    FileReader read = new FileReader(file);
    
    MovieDataReader getMovies = new MovieDataReader();
    allMovieObjects = getMovies.readDataSet(read);
    hashFiller();
    }
    catch(Exception e){}
    
  }

  // ORDER BY RATING
  private void hashFiller() {
    hashMap = new HashTableMap<String, List<Movie>>();
    selectedGenres = new ArrayList<String>();
    selectedRatings = new ArrayList<String>();
    selectedMovies = new ArrayList<Movie>();

    for (MovieInterface x : allMovieObjects) {
      String avgRate = "" + (int) Math.floor(x.getAvgVote());
      if (!(hashMap.put(avgRate, new ArrayList<Movie>() {
        {
          add((Movie) x);
        }
      }))) {
        hashMap.get(avgRate).add((Movie) x);
      }
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

  @Override
  public void addGenre(String genre) {

    if (selectedGenres.contains(genre)) {

    } else {

      selectedGenres.add(genre);

      if (selectedMovies.isEmpty()) {

        selectedMovies = hashMap.get(genre);

      } else {
        List<Movie> toBeRemoved = new ArrayList<Movie>();
        for (Movie x : selectedMovies) {
          if (!(x.getGenres().contains(genre)))
            toBeRemoved.add(x);
        }
        selectedMovies.removeAll(toBeRemoved);
      }


    }
  }


  @Override
  public void addAvgRating(String rating) {
    if (selectedRatings.contains(rating)) {

    } else {
      try {
        selectedRatings.add(rating);
        if (selectedGenres.isEmpty())
          selectedMovies.addAll(hashMap.get(rating));

        // CONDITIONAL IF THIS IS THE FIRST RATING
        else if (selectedRatings.size() == 1) {
          List<Movie> toBeRemoved = new ArrayList<Movie>();
          for (Movie x : selectedMovies) {
            String avgRate = "" + (int) Math.floor(x.getAvgVote());
            if (!(avgRate.equals(rating)))
              toBeRemoved.add(x);
          }
          selectedMovies.removeAll(toBeRemoved);
        }


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
      } catch (NoSuchElementException e) {}
    }
  }

  @Override
  public void removeGenre(String genre) {

    if (selectedGenres.contains(genre)) {
      selectedGenres.remove(genre);

      List<String> placeG = new ArrayList<String>();
      List<String> placeR = new ArrayList<String>();

      placeG.addAll(selectedGenres);
      placeR.addAll(selectedRatings);


      selectedGenres.clear();
      selectedRatings.clear();
      selectedMovies = new ArrayList<Movie>();



      for (String r : placeR) {
        this.addAvgRating(r);
      }
      for (String g : placeG) {

        this.addGenre(g);
      }
    }
  }



  @Override
  public void removeAvgRating(String rating) {
    if (selectedRatings.contains(rating)) {
      selectedRatings.remove(rating);
      if (selectedRatings.size() == 0 && selectedGenres.size() != 0) {

        selectedMovies.clear();
        for (String x : selectedGenres) {
          try {
          selectedMovies.addAll(hashMap.get(x));
          }
          catch(NoSuchElementException e) {}
        }

      } else {
        try {
        selectedMovies.removeAll(hashMap.get(rating));
        }
        catch(NoSuchElementException e) {}
      }
    }
  }

  @Override
  public List<String> getGenres() {
    // TODO Auto-generated method stub
    return selectedGenres;
  }

  @Override
  public List<String> getAvgRatings() {

    return selectedRatings;
  }

  @Override
  public int getNumberOfMovies() {
    return selectedMovies.size();
  }

  @Override
  public List<String> getAllGenres() {
    if(allGenres == null) {
    allGenres = new ArrayList<String>();
      for(MovieInterface movie: allMovieObjects) {
        for(String genre: movie.getGenres()) {
        if(!allGenres.contains(genre)) allGenres.add(genre);
        }
    }
    }
   return allGenres;
  }

  @Override
  public List<MovieInterface> getThreeMovies(int startingIndex) {
    List<MovieInterface> descendRate = new ArrayList<>();
    try {
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
        else
          return descendRate.subList(startingIndex, descendRate.size());
      }
    } catch (IllegalArgumentException e) {
    }
    return new ArrayList<MovieInterface>();


  }
}
