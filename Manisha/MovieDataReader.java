import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.zip.DataFormatException;
import java.util.Collections;

public class MovieDataReader implements MovieDataReaderInterface{
	private List<MovieInterface> movies;
	
	public MovieDataReader() {
		movies = new ArrayList<MovieInterface>();
	}

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
	
	private String[] separateQuotes(String[] str) {
		String[] toReturn = new String[str.length];
		int j=0;
		for(int i=0;i<str.length;) {
			if(str[i].startsWith("\"")) {
				String temp=str[i]+",";
				i++;

				while(!str[i].endsWith("\"")) {
					temp+=str[i]+",";
					i++;
				}
				temp+=str[i];
				i++;
				toReturn[j] = temp;
				j++;
			}
			else {
				toReturn[j] = str[i];
				j++;
				i++;
			}
		}

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
