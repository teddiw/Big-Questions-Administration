import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*Identifies student from ID. 
 * Intended use: Identify "Missing Students"*/
public class IdentifyStudent {
	String[][] classRoster;
	String[] missingIDs;
	ArrayList<String[]> missingNames = new ArrayList<String[]>();
	
	public IdentifyStudent(String classRosterFileName, int numRosterStudents, String missingIDsFileName, int numMissingStudents) {
		classRoster = arrayFromRosterFile(numRosterStudents, 3, classRosterFileName);
		missingIDs = arrayFromMissingIDFile(numMissingStudents, missingIDsFileName);
		
		/*for(int i = 0; i<classRoster.length; i++) {
			System.out.println(classRoster[i][0]+" , "+classRoster[i][1]+" , "+classRoster[i][2]);
		}
		System.out.println("*******");
		for(int i = 0; i<missingIDs.length; i++) {
			System.out.println(missingIDs[i]);
		}*/
		
		for(int i = 0; i<missingIDs.length; i++) {
			for(int j = 0; j<classRoster.length; j++) {
				//System.out.println(j);
				if(missingIDs[i].equals(classRoster[j][1])) {
					String[] s = new String[3];
					s[0] = classRoster[j][0];
					s[1] = classRoster[j][1];
					s[2] = classRoster[j][2];
					missingNames.add(s);
				}
			}
		}
		writeCSV w = new writeCSV(); //creates CSV, placed into file directory (see Finder)
		w.writeCSV(missingNames, "missingStudents.csv", "Grade&Last Name, First Name, ID, Period");
	}
	
	public static String[][] arrayFromRosterFile(int numPeople, int numAnswers, String f){
		File file = new File(f);
		String[][] list = new String[numPeople][numAnswers];
		try {
			Scanner inputStream = new Scanner(file);
			
			int counter = 0;
			while(inputStream.hasNextLine()) {
				String line = inputStream.next();
				Scanner lineScanner = new Scanner(line);
				lineScanner.useDelimiter("#");
				for(int i=0; i<numAnswers; i++) {
					if(i==1 || i==2) {
						lineScanner.useDelimiter(",");
						if(i==1) {
							String s = lineScanner.next();
							list[counter][i] = s.substring(1, s.length()-1);
						}else {
							list[counter][i] = lineScanner.next();
						}
					}else {
						list[counter][i] = lineScanner.next().substring(1);
					}
				}
				counter++;
				
			}
			inputStream.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static String[] arrayFromMissingIDFile(int numPeople, String f){
		File file = new File(f);
		String[] list = new String[numPeople];
		try {
			Scanner inputStream = new Scanner(file);
			
			int counter = 0;
			while(inputStream.hasNextLine()) {
				String line = inputStream.next();
				Scanner lineScanner = new Scanner(line);
				lineScanner.useDelimiter(",");
				list[counter] = lineScanner.next();
				counter++;
				
			}
			inputStream.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
}
