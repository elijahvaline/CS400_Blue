// --== CS400 File Header Information ==--
// Name: Theodore Louis Peters
// Email: tlpeters3@wisc.edu
// Team: Blue
// Group: HA
// TA: Hang Yin
// Lecturer: Gary Dahl
// Notes to Grader: N.A.


import java.io.StringReader;

/**
 * This class contains a set of tests for the back end of the Movie Mapper project.
 */
public class TestBackend {
        
        public static void main(String[] args) {
                (new TestBackend()).runTests();
        }


        public void runTests() {
                // add all tests to this method
                if (this.testInitialNumberOfMovies()) {
                        System.out.println("Test initial number of movies: PASSED");
                } else {
                        System.out.println("Test initial number of movies: FAILED");
                }
                if (this.testGetAllGenres()) {
                        System.out.println("Test get all genres: PASSED");
                } else {
                        System.out.println("Test get all genres: FAILED");
                }
                if (this.testGetThreeMoviesInitial()) {
                        System.out.println("Test get three movies sorted by rating: PASSED");
                } else {
                        System.out.println("Test get three movies sorted by rating: FAILED");
                }
                if (this.testAddAvgRating()) {
                        System.out.println("Test adding a new average rating parameter: PASSED");
                } else {
                        System.out.println("Test adding a new average rating parameter: FAILED");
                }
                 if (this.testAddGenre()) {
                        System.out.println("Test adding a new genre parameter: PASSED");
                } else {
                        System.out.println("Test adding a new genre parameter: FAILED");
                }
        }
        
        /**
         * This test instantiates the back end with three movies and tests whether the
         * initial selection is empty (getNumberOfMovies() returns 0). It passes when
         * 0 is returned and fails in all other cases, including when an exception is
         * thrown.
         * @return true if the test passed, false if it failed
         */
        public boolean testInitialNumberOfMovies() {
                try {
                  // instantiate once BackendInterface is implemented
                  BackendInterface backendToTest = new Backend(new StringReader(                                                                                                             
                      "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"                                        
                      + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"                                                                    
                      + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"                                                                                                                             
                      + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n" )); 

        if (backendToTest.getNumberOfMovies() == 0) {
                // test passed
                return true;
        } else {
                // test failed
                return false;
        }
} catch (Exception e) {
        e.printStackTrace();
        // test failed
        return false;
}
}

/**
* This test instantiates the back end with three movies and tests whether
* the getAllGenres method return the correct set of genres for those three
* movies.
* @return true if the test passed, false if it failed
*/
public boolean testGetAllGenres() {
try {
  // instantiate once BackendInterface is implemented
  BackendInterface backendToTest = new Backend(new StringReader(                                                                                                             
      "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"                                        
      + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"                                                                    
      + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"                                                                                                                             
      + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n" )); 

        if (backendToTest.getAllGenres().size() == 5
                        && backendToTest.getAllGenres().contains("Horror")
                        && backendToTest.getAllGenres().contains("Action")
                        && backendToTest.getAllGenres().contains("Comedy")
                        && backendToTest.getAllGenres().contains("Musical")
                        && backendToTest.getAllGenres().contains("Romance")) {
                // test passed
                return true;
        } else {
                // test failed
                return false;
        }
} catch (Exception e) {
        e.printStackTrace();
        // test failed
        return false;
}
}

/**
* This test instantiates the back end with three movies and tests whether the
* initial list returned by getThreeMovies starting with the first movie (0)
* is empty. It passes when 0 is returned and fails in all other cases, including
* when an exception is thrown.
* @return true if the test passed, false if its failed
*/
public boolean testGetThreeMoviesInitial() {
try {
  // instantiate once BackendInterface is implemented
  BackendInterface backendToTest = new Backend(new StringReader(                                                                                                             
      "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"                                        
      + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"                                                                    
      + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"                                                                                                                             
      + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n" )); 

        if (backendToTest.getThreeMovies(0).size() == 0) {
                // test passed
                return true;
        } else {
                // test failed
                return false;
        }
} catch (Exception e) {
        e.printStackTrace();
        // test failed
        return false;
}
}

// TODO: Back End Developer, add at least 2 more tests
public boolean testAddAvgRating() {
try {
  // instantiate once BackendInterface is implemented
  BackendInterface backendToTest = new Backend(new StringReader(                                                                                                             
      "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"                                        
      + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"                                                                    
      + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"                                                                                                                             
      + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n" )); 

        
        backendToTest.addAvgRating("3");
        backendToTest.addAvgRating("5");
        if (backendToTest.getNumberOfMovies() == 2) {
          // AddAvgRating test passed
          return true;
  } else {
          // test failed
          return false;
  }
  

} catch (Exception e) {
  e.printStackTrace();
  // test failed
  return false;
}
}
/**
* TestAddGenre instantiates the back end with two movies, each with different genres. The test method
* then adds a new horror genre. Subsequently checking to see if the number of movies with said genre is correctly 1.
* @return true if the test passed, false if its failed
*/
public boolean testAddGenre() {
try {
  // instantiate once BackendInterface is implemented
  BackendInterface backendToTest = new Backend(new StringReader(                                                                                                             
      "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"                                        
      + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"                                                                    
      + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"                                                                                                                             
      + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n" )); 

  
  backendToTest.addGenre("Horror");
  if (backendToTest.getNumberOfMovies() == 1) {
          // AddGenre test passed
          return true;
  } else {
          // test failed
          return false;
  }
  

} catch (Exception e) {
  e.printStackTrace();
  // test failed
  return false;
}
}

}


