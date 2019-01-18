import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/*Input: 
 * CSV of responses from google form... only the columns (in this order!): ID, Partner ID, period, sports?, state?, Name, Partner Name 
 * CSV of Roster with legal names NOTE: EDIT GOOGLE FORM TO MATCH INFINITE CAMPUS ROSTER FORMAT
 * What it does:
 * Prints out errors in partner, period, sports status
 * Prints out the IDs of people on the roster who have not signed up on the Google Form
 */
public class CheckPartnerList {
	public static void main(String[] args) {
		//String[][] currentList = arrayFromFile(32, 7, "LargerTest.csv");
		String[][] currentList = arrayFromFile(260, 9, "Check45.csv");
		String[] rosterOfIDs = new RearrangeRoster("FullRoster4.csv", 262).studentIDs; //loads array of IDs.. This is all set to go (for 2018), don't touch
		checkPartners(currentList);
		checkAttendance(currentList, rosterOfIDs);
	}
	
	public static String[][] arrayFromFile(int numPeople, int numAnswers, String f){
		File file = new File(f);
		String[][] list = new String[numPeople][numAnswers];
		try {
			Scanner inputStream = new Scanner(file);
			
			int counter = 0;
			while(inputStream.hasNextLine()) {
				String line = inputStream.nextLine(); //fixed
				Scanner lineScanner = new Scanner(line);
				lineScanner.useDelimiter(",");
				for(int i=0; i<numAnswers; i++) {
					list[counter][i] = lineScanner.next();
				}
				counter++;
				
			}
			inputStream.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void checkPartners(String[][] partnerList) {
		ArrayList<String[]> partnerMismatch = new ArrayList<String[]>();
		ArrayList<String[]> periodMismatch = new ArrayList<String[]>();
		ArrayList<String[]> sportsMismatch = new ArrayList<String[]>();
		
		for(int i = 0; i<partnerList.length; i++) {
			for(int j = 0; j<partnerList.length; j++) {
				if(partnerList[i][1].equals(partnerList[j][0])) {
					if(!partnerList[i][0].equals(partnerList[j][1])) { //checks if IDs don't match up & if so, adds to corresponding arrayList
						partnerMismatch.add(partnerList[i]);
						partnerMismatch.add(partnerList[j]);
					}else {
						if(!partnerList[i][2].equals(partnerList[j][2])) { //checks if period doesn't match up & if so, adds to corresponding arrayList
							periodMismatch.add(partnerList[i]);
						}
						if(!partnerList[i][3].equals(partnerList[j][3])) { //checks if sports status doesn't match up & if so, adds to corresponding arrayList
							sportsMismatch.add(partnerList[i]);
						}
					}
				}
			}
		}
		System.out.println("PARTNER MISMATCH:"); //prints partner mismatches
		if(!partnerMismatch.equals(null)) {
			for(int i=0; i<partnerMismatch.size(); i+=2) {
				System.out.println(partnerMismatch.get(i)[5]+" and "+partnerMismatch.get(i)[6]+", "+partnerMismatch.get(i+1)[5]+" and "+partnerMismatch.get(i+1)[6]);
			}	
		}else {
			System.out.println("None");
		}
		System.out.println();
		
		MyArrayList pmr = new MyArrayList(periodMismatch); //eliminates redundancy 
		pmr.reduce();
		ArrayList<String[]> PeriodMismatchReduced = pmr.list;
		
		System.out.println("PERIOD MISMATCH:"); //prints period mismatches
		if(!PeriodMismatchReduced.equals(null)) {
			for(int i=0; i<PeriodMismatchReduced.size(); i++) {
				System.out.println(PeriodMismatchReduced.get(i)[5]+" and "+PeriodMismatchReduced.get(i)[6]);
			}	
		}else {
			System.out.println("None");
		}
		
		System.out.println();
		
		MyArrayList smr = new MyArrayList(sportsMismatch); //eliminates redundancy 
		smr.reduce();
		ArrayList<String[]> sportsMismatchReduced = smr.list;
		
		System.out.println("SPORTS STATUS MISMATCH:"); //prints sports status mismatches
		if(!sportsMismatchReduced.equals(null)) {
			for(int i=0; i<sportsMismatchReduced.size(); i++) {
				System.out.println(sportsMismatchReduced.get(i)[5]+" and "+sportsMismatchReduced.get(i)[6]);
			}	
		}else {
			System.out.println("None");
		}
		System.out.println();
	}
	
	public static void checkAttendance(String[][] partnerList, String[] rosterofIDs) {
		System.out.println("MISSING STUDENTS:");
		int j;
		//System.out.println(rosterofIDs.length);
		for(int i = 0; i<rosterofIDs.length; i++) {
			boolean b = false;
			j = 0;
			while(j<partnerList.length && b==false) {
				if(partnerList[j][0].equals(rosterofIDs[i])) {
					b = true;
				}
				j++;
			}
			if(b==false) {
				System.out.println(rosterofIDs[i]);
			}
		}
		
		System.out.println("STUDENTS NOT ON ROSTER:");
		int y;
		//System.out.println(rosterofIDs.length);
		ArrayList<String> rosterofIDsList = new ArrayList<String>();
		for(int i = 0; i<rosterofIDs.length; i++) {
			rosterofIDsList.add(rosterofIDs[i]);
		}
		for(int i = 0; i<partnerList.length; i++) {
			boolean b = false;
			y = rosterofIDsList.size()-1;
			while(y>=0 && b==false) {
				if(rosterofIDsList.get(y).equals(partnerList[i][0])) {
					b = true;
					rosterofIDsList.remove(y);
				}
				y--;
			}
			if(b==false) {
				System.out.println(partnerList[i][0]); 
			}
		}
		
		ArrayList<String> rosterofIDsList2 = new ArrayList<String>();
		for(int i = 0; i<rosterofIDs.length; i++) {
			rosterofIDsList2.add(rosterofIDs[i]);
		}
		
		for(int i = 0; i<partnerList.length; i++) {
			boolean b = false;
			y = rosterofIDsList2.size()-1;
			while(y>=0 && b==false) {
				if(rosterofIDsList2.get(y).equals(partnerList[i][1])) {
					b = true;
					rosterofIDsList2.remove(y);
				}
				y--;
			}
			if(b==false) {
				System.out.println(partnerList[i][1]); 
			}
		}
	}

}
