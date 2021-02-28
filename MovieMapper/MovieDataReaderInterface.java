import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.DataFormatException;


public interface MovieDataReaderInterface {
	
	public List<MovieInterface> readDataSet(FileReader inputFileReader) 
			throws FileNotFoundException, IOException, DataFormatException; 
	
}
