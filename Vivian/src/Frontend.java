// --== CS400 File Header Information ==--
// Name: Vivian Lacson
// Email: vlacson@wisc.edu
// Team: Blue
// Group: HA
// TA: Hang Yin
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the Frontend interface of the Movie Mapper program. Users can view their top three movies
 * and scroll through their movie recommendations in Base mode. They can select genres and ratings to filter out
 * their movies recommendations.
 */
public class Frontend {
    private static Backend backend;
    private static final Scanner READIN = new Scanner(System.in);

    /**
     * This method selects all the ratings, and runs the program.
     * @param args takes a path to a CSV file
     */
    public static void main(String[] args) {
        String path = args[0];
        File CSVFile = new File(path);
        (new Frontend()).run(new Backend(CSVFile));
    }

    public void run(Backend backend) {
        this.backend = backend;

        // all ratings 0-10 are selected
        for (int i = 0; i < 11; i++) {
            backend.addAvgRating("" + i);
        }

        baseMode();
    }

    /**
     * This method represents the Base mode. Initially, no movies are displayed, and the user is told to select at
     * least one genre to get their movie recommendations. If a genre is selected, users will get their top 3 movies
     * ordered by rating. They can scroll through the list by typing in numbers corresponding to the movie rank. (If
     * they type in 7, the list will scroll down to movies ranked 7, 8, 9.) The user will be continuously prompted to
     * enter in a valid integer that pertains to a movie rank or a valid command of "x", "g", and "r." If "g" or "r"
     * is inputted, the mode will switch. This method ends when the user inputs "x" to exit the program.
     */
    private static void baseMode() {
        System.out.println("WELCOME TO MOVIE MAPPER!");

        // loops base mode until user inputs "g", "r", or "x"
        String input = "";
        int startingIndex = 0;
        boolean isDone = false;
        while (!isDone) {
            // lists the top 3 (by avg rating) selected movies
            List<Movie> threeMovies = backend.getThreeMovies(startingIndex);
            if (threeMovies == null || threeMovies.isEmpty()) {
                // initially, no movies are listed until a genre is chosen
                System.out.println("Select at least one genre to get your movie recommendations!");
            } else {
                System.out.println("Recommended Movies:");
                for (int i = startingIndex; i < startingIndex + 3; i++) {
                    System.out.println((i+1) + ". " + threeMovies.get(i).getTitle());
                }
            }

            System.out.println("*-----------------COMMANDS-----------------*\n" +
                    "| Enter # corresponding to rank to scroll. |\n" +
                    "| Press 'g' to go to Genre Selection.      |\n" +
                    "| Press 'r' to go to Ratings Selection.    |\n" +
                    "| Press 'x' to Exit.                       |\n" +
                    "*------------------------------------------*");

            System.out.print("Enter a command: ");
            boolean isValid = false;
            while (!isValid) {
                input = READIN.nextLine();
                if (input.equalsIgnoreCase("g")
                        || input.equalsIgnoreCase("r")
                        || input.equalsIgnoreCase("x")) {
                    isDone = true;
                    break;
                }
                // ensures input is an integer
                try {
                    // ensures input pertains to a movie rank
                    if (Integer.parseInt(input) >= 1 && Integer.parseInt(input) <= backend.getNumberOfMovies()) {
                        startingIndex = Integer.parseInt(input) - 1;
                        System.out.println("");
                        isValid = true;
                    } else {
                        System.out.print("Enter a # corresponding to a rank: ");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Please enter in a valid command: ");
                }
            }
        }

        System.out.println("");
        // switch to other modes
        if (input.equalsIgnoreCase("g")) {
            genreSelectionMode();
        } else if (input.equalsIgnoreCase("r")) {
            ratingsSelectionMode();
        } else {
            System.out.println("See you again!");
            // program ends
        }
    }

    /**
     * This method represents the Genre Selection mode. Initially, all genres are displayed and unselected. To select
     * or deselect a genre, the user can input the corresponding number of that genre. The user is continuously
     * prompted to enter a valid integer if the input does not pertain to a genre or is not "x." The list of genres
     * will be updated after every selection/deselection. Selected genres will be marked with an *. This method ends
     * when the user inputs "x" to return to the base mode.
     */
    private static void genreSelectionMode() {
        System.out.println("GENRE SELECTION");

        // loops until user inputs "x" to exit to base mode
        String input;
        boolean isDone = false;
        while (!isDone) {
            List<String> allGenres = backend.getAllGenres();
            List<String> selected = backend.getGenres();

            // prints out list of genres, selected genres are marked with an *
            System.out.println("[Selected genres are marked with an *.]");
            for (int i = 0; i < allGenres.size(); i++) {
                if (selected == null || selected.isEmpty() || !selected.contains(allGenres.get(i))) {
                    System.out.println("  " + (i+1) + ". " + allGenres.get(i));
                } else {
                    System.out.println("* " + (i+1) + ". " + allGenres.get(i));
                }
            }

            System.out.println( "*-----------------------COMMANDS------------------------*\n" +
                    "| Enter # corresponding to genre to select or deselect. |\n" +
                    "| Press 'x' to return to home screen.                   |\n" +
                    "*-------------------------------------------------------*");
            System.out.print("Enter a command: ");
            boolean isValid = false;
            while (!isValid) {
                input = READIN.nextLine();
                if (input.equalsIgnoreCase("x")) {
                    isDone = true;
                    break;
                }
                // ensures input is an integer
                try {
                    // ensures input pertains to a genre
                    if (Integer.parseInt(input) > 0 && Integer.parseInt(input) < allGenres.size()) {
                        if (selected == null || selected.isEmpty() || !selected.contains(allGenres.get(Integer.parseInt(input) - 1))) {
                            // if there are no genres selected, or if the genre is not selected, select it
                            backend.addGenre(allGenres.get(Integer.parseInt(input) - 1));
                        } else {
                            // if the genre is already selected, deselect it
                            backend.removeGenre(allGenres.get(Integer.parseInt(input) - 1));
                        }
                        System.out.println("");
                        isValid = true;
                    } else {
                        // if the input is a number that doesn't correspond to a genre, try again
                        System.out.print("Please enter a number corresponding to a genre: ");
                    }
                } catch (NumberFormatException e) {
                    // if the input is a String but is not "x", try again
                    System.out.print("Please enter a valid command: ");
                }
            }
        }
        System.out.println("");
        baseMode(); // exits to base mode
    }

    /**
     * This method represents the Ratings Selection mode. Initially, all ratings are selected. To select or deselect
     * a rating, the user can input an integer 0-10. The user is continuously prompted to enter a valid command if
     * the input is not 0-10 or "x." The list of selected ratings will be updated whenever a rating is selected or
     * deselected. This method ends when the user enters "x" to exit to base mode.
     */
    private static void ratingsSelectionMode() {
        System.out.println("RATINGS SELECTION");

        // loops until user inputs "x" to exit to base mode
        String input;
        boolean isDone = false;
        while (!isDone) {
            // displays selected ratings in one line, all separated by commas
            System.out.println("Selected Rating(s): ");
            List<String> selected = backend.getAvgRatings();
            if (selected.size() >= 1) {
                System.out.print(selected.get(0));
            }
            for (int i = 1; i < selected.size(); i++) {
                System.out.print(", " + selected.get(i));
            }

            System.out.println( "\n*-----------------COMMANDS-----------------*\n" +
                    "| Enter rating 0-10 to select or deselect. |\n" +
                    "| Press 'x' to return to home screen.      |\n" +
                    "*------------------------------------------*");
            System.out.print("Enter a command: ");

            // loops until user inputs a valid command
            boolean isValid = false;
            while (!isValid) {
                input = READIN.nextLine();
                if (input.equalsIgnoreCase("x")) {
                    isDone = true;
                    break;
                }

                // ensures input is an integer
                try {
                    // ensures input is 0-10
                    if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) <= 10) {
                        if (selected.contains(input)) {
                            // if inputted rating is already selected, then deselect it
                            backend.removeAvgRating(input);
                        } else {
                            // if inputted rating is not already selected, then select it
                            backend.addAvgRating(input);
                        }
                        isValid = true;
                    } else {
                        // if the input is not 0-10, try again
                        System.out.print("Please enter a number 0-10: ");
                    }
                } catch (NumberFormatException e) {
                    // if the input is a String but is not "x", try again
                    System.out.print("Please enter a valid command: ");
                }
            }
        }

        System.out.println("");
        baseMode(); // exit to base mode
    }
}
