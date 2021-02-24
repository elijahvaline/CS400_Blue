import java.util.List;
import java.util.ArrayList;
import java.io.Reader;
import java.io.FileReader;


public class Backend implements BackendInterface {

  private List<Movie> selectedMovies;
  private List<String> selectedGenres;
  private List<String> selectedRatings;

  private HashTableMap<String, List<Movie>> hashMap;

  public Backend(FileReader movieFile) {
    
    MovieDataReader getMovies = new MovieDataReader();
    try {
      List<Movie> movieList = getMovies.readDataSet(movieFile);
      hashFiller(movieList);

    } catch (Exception e) {
      System.out.println(e);
    }

  }

  public Backend(Reader s) {

  }
  
  // ORDER BY RATING
  private void hashFiller(List<Movie> movies) {
    hashMap = new HashTableMap<String, List<Movie>>();
    selectedGenres = new ArrayList<String>();
    selectedRatings = new ArrayList<String>();
    selectedMovies = new ArrayList<Movie>();

    for (Movie x : movies) {
      String avgRate = "" + (int) Math.floor(x.getAvgVote());
      if (!(hashMap.put(avgRate, new ArrayList<Movie>() {
        {
          add(x);
        }
      }))) {
        hashMap.get(avgRate).add(x);
      }
      for (String genre : x.getGenres()) {
        if (!(hashMap.put(genre, new ArrayList<Movie>() {
          {
            add(x);
          }
        }))) {
          hashMap.get(genre).add(x);
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
          selectedMovies.addAll(hashMap.get(x));
        }

      } else {
        selectedMovies.removeAll(hashMap.get(rating));
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
    List<String> allGenres = new ArrayList<String>();

    for (Movie x : selectedMovies) {
      for (String g : x.getGenres()) {
        if (!allGenres.contains(g))
          allGenres.add(g);
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
