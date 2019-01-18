import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//extracts list of student IDs
public class RearrangeRoster {
	String[] studentIDs;
	public RearrangeRoster(String fileName, int numStudents) {
		String[] uploadedRoster = arrayFromFile(numStudents, fileName);
		studentIDs = getIDs(uploadedRoster);
		
		
		/*String s = ""; //use to check if the reformatting process is correct
		for(int i=0; i<uploadedRoster.length; i++) {
				s+=uploadedRoster[i]+" "+studentIDs[i];
				s+="\n";
			}
		
		
		System.out.print(s);
		*/
		
	}
	
	public static String[] arrayFromFile(int numPeople, String f){
		File file = new File(f);
		String[] list = new String[numPeople];
		try {
			Scanner inputStream = new Scanner(file);
			
			int counter = 0;
			while(inputStream.hasNextLine()) {
				list[counter] = inputStream.nextLine();
				counter++;
			}
			inputStream.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String[] getIDs(String[] original) {
		String[] rearranged = new String[original.length];
		for(int i=0; i<rearranged.length; i++) {
			int index = 0;
			for(int j=0; j<original[i].length(); j++) {
				if(original[i].substring(j, j+1).equals("#")) {
					index = j;
					break;
				}
			}
			rearranged[i] = original[i].substring(index+1, original[i].length()-1);
		}
		return rearranged;
	}
	
}
