import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArrayFromFile {
	public String[][] list;
	public ArrayFromFile(int numPeople, int numAnswers, String f) {
		File file = new File(f);
		list = new String[numPeople][numAnswers];
		try {
			Scanner inputStream = new Scanner(file);
			
			int counter = 0;
			while(inputStream.hasNextLine()) {
				String line = inputStream.next();
				Scanner lineScanner = new Scanner(line);
				lineScanner.useDelimiter(",");
				for(int i=0; i<numAnswers; i++) {
					list[counter][i] = lineScanner.next();
					//System.out.println(line);
				}
				counter++;
				
			}
			inputStream.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
