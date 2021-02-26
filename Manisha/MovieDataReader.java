// --== CS400 File Header Information ==--
// Name: Manisha Pillai
// Email: mvpillai@wisc.edu 
// Team: Blue
// Group: HA
// TA: Hang Yin
// Lecturer: Gary Dahl

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/*This class implements the MoveDataReaderInterface and reads the given file of movie 
 * data and stores it in movie objects.
 * */
public class MovieDataReader implements MovieDataReaderInterface{
	private List<MovieInterface> movies;
	
	/*This method constructs a MovieDataReader object
	 * */
	public MovieDataReader() {
		movies = new ArrayList<MovieInterface>();
	}

	/*This method reads the file given and stores that data in movie objects
	 * @param inputFileReader
	 * @return list of movie objects
	 * */
	@Override
	public List<MovieInterface> readDataSet(FileReader inputFileReader)
			throws FileNotFoundException, IOException, DataFormatException {
		try {
			inputFileReader.read();
			Scanner scan = new Scanner(inputFileReader);
			scan.nextLine();			//get rid of header line
			while(scan.hasNext()) {
				String line = scan.nextLine();
				String[] str = line.split(",");
				str = separateQuotes(str);
				if(str.length!=13) {
					throw new DataFormatException();
				}
				String title = str[1];
				Integer year = Integer.parseInt(str[2]);
				List<String> genre = new ArrayList<String>();
				str[3].replace("\"", "");
				String[] gen = str[3].split(",");
				for(int i=0;i<gen.length;i++) {
					genre.add(gen[i]);
				}
				String director = str[7];
				String description = str[11];
				Float vote = Float.valueOf(str[12]);
				Movie movie = new Movie(title,year,genre,director,description,vote);
				movies.add(movie);
			}
			Collections.sort(movies);
			return movies;
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException();
		}
		catch(IOException e) {
			throw new IOException();
		}
	}
	
	/*This method is a helper method to separate the data by the commas, while keeping
	 * the data that are lists, represented by "", together.
	 * */
	private String[] separateQuotes(String[] str) {
		String[] toReturn = new String[str.length];
		int j=0;
		for(int i=0;i<str.length;) {
			//checks if there's quotations --> if the data is a list
			if(str[i].startsWith("\"")) {
				String temp=str[i]+",";
				i++;
				//keeps the data within the "" together
				while(!str[i].endsWith("\"")) {
					temp+=str[i]+",";
					i++;
				}
				//adds the final list of data as one string value to array
				temp+=str[i];
				i++;
				toReturn[j] = temp;
				j++;
			}
			//when there are no quotations
			else {
				toReturn[j] = str[i];
				j++;
				i++;
			}
		}
		//reduces size of array so that there are no empty spaces
		int count=0;
		for(int k=0;k<toReturn.length;k++) {
			if(toReturn[k]!=null)
				count++;
		}
		String[] toReturn2 = new String[count];
		for(int i=0;i<count;i++) {
			toReturn2[i]=toReturn[i];
		}

		return toReturn2;
	}

}
