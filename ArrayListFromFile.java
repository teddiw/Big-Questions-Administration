

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListFromFile {
	public ArrayList<String[]> list;
	public ArrayListFromFile(int numAnswers, String f) {
		File file = new File(f);
		list = new ArrayList<String[]>();
		try {
			Scanner inputStream = new Scanner(file);
			int counter = 0;
			while(inputStream.hasNextLine()) {
				String line = inputStream.nextLine();
				Scanner lineScanner = new Scanner(line);
				lineScanner.useDelimiter(",");
				list.add(new String[numAnswers]);
				for(int i=0; i<numAnswers; i++) {
					list.get(counter)[i] = lineScanner.next();
				}
				counter++;
				
			}
			inputStream.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public String toString() {
		String s = "";
		for(int i=0; i<list.size(); i++) {
			for(int j=0; j<list.get(0).length; j++) {
				s+=list.get(i)[j]+" ";
			}
			s+="\n";
		}
		return s;
	}
	/*public void print(int numAnswers) {		//same result as toString
		for(int i=0; i<list.size(); i++) {
			for(int j=0; j<numAnswers; j++) {
				System.out.print(list.get(i)[j]+" ");
			}
			System.out.println();
		}
	}*/
}
