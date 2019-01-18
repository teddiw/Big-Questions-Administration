import java.util.ArrayList;
/*Input: CSV of partners, no errors
 * What it does: 
 * Partner list --> team list 
 * Orders teams: 6th general, 6th State, 7th general, 7th State, 6th sports, 7th sports
 * Prints notification if a team is both sports and State
 * Gives each team a code "Team #"
 * Prints # ranges for 6th general, 6th State, 7th general, 7th State, 6th sports, 7th sports
 * Writes/Exports CSV: Code, Name1, Name2, Period, Sports?, State?
 */
public class OrderAndNumber {
	public static void main(String[] args) {
		ArrayList<String[]> partnerList = new ArrayListFromFile(9, "Check45.csv").list; 
		MyArrayList utl = new MyArrayList(partnerList);
		utl.statePreferenceReduce(4, 3, 7, 8); //assuming that yes/no for State is in the 5th column
		ArrayList<String[]> unsortedTeamList = utl.list;
		
		String[] rosterOfIDs = new RearrangeRoster("FullRoster4.csv", 262).studentIDs;
		finalTest(unsortedTeamList, rosterOfIDs);
		
		String[][][] sortedTeamLists = sortTeamList(unsortedTeamList, 2, 3, 4, 8);
		ArrayList<String[]> numberedTeamList = numberTeamList(sortedTeamLists);
		
		writeCSV w = new writeCSV(); //creates CSV, placed into file directory (see Finder)
		w.writeCSV(numberedTeamList, "NumberedTeamList.csv", "Code, ID1, ID2, Period, Sports?, State?, Name1, Name2, Schedule, Comfortable?");
		
		/* for(int i=0; i<6; i++) { //use to check if sortedTeamList works correctly
			for(int j=0; j<sortedTeamLists.length; j++) {
				for(int k=0; k<5; k++) {
					System.out.print(sortedTeamLists[j][k][i]+" ");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();
			System.out.println();
		}*/
		
		/*String s = ""; //use to check if numberTeamList works correctly
		for(int i=0; i<numberedTeamList.size(); i++) {
			for(int j=0; j<numberedTeamList.get(0).length; j++) {
				s+=numberedTeamList.get(i)[j]+" ";
			}
			s+="\n";
		}
		System.out.print(s);*/
		
		
	}
	public static String[][][] sortTeamList(ArrayList<String[]> unsortedTeamList, int periodIndex, int sportsIndex, int stateIndex, int comfortIndex){
		ArrayList<String[]> general6thC = new ArrayList<String[]>();
		ArrayList<String[]> general6thNC = new ArrayList<String[]>();
		ArrayList<String[]> state6th = new ArrayList<String[]>();
		ArrayList<String[]> general7thC = new ArrayList<String[]>();
		ArrayList<String[]> general7thNC = new ArrayList<String[]>();
		ArrayList<String[]> state7th = new ArrayList<String[]>();
		ArrayList<String[]> sports6th = new ArrayList<String[]>();
		ArrayList<String[]> sports7th = new ArrayList<String[]>();
		
		for(int i=0; i<unsortedTeamList.size(); i++) {
			if(unsortedTeamList.get(i)[periodIndex].equals("6")) { //sorts 6th period out
				
				if(unsortedTeamList.get(i)[sportsIndex].equals("Yes")) { //sorts out Sports in 6th
					sports6th.add(unsortedTeamList.get(i));
					if(unsortedTeamList.get(i)[stateIndex].equals("Yes")) { //prints notification if both Sports and State
						System.out.println("Sports and State in 6th: "+unsortedTeamList.get(i)[5]+" and "+unsortedTeamList.get(i)[6]);
					}
				}else if(unsortedTeamList.get(i)[stateIndex].equals("Yes")) { //sorts out State in 6th
					state6th.add(unsortedTeamList.get(i));
				}else { //catches all the non-state, non-sport in 6th
					if(unsortedTeamList.get(i)[comfortIndex].equals("Yes")) {
						general6thC.add(unsortedTeamList.get(i));
					}else {
						general6thNC.add(unsortedTeamList.get(i));
					}
				}
			
			}else { //sorts 7th period out
				
				if(unsortedTeamList.get(i)[sportsIndex].equals("Yes")) { //sorts out Sports in 7th
					sports7th.add(unsortedTeamList.get(i));
					if(unsortedTeamList.get(i)[stateIndex].equals("Yes")) { //prints notification if both Sports and State
						System.out.println("Sports and State in 7th: "+unsortedTeamList.get(i)[5]+" and "+unsortedTeamList.get(i)[6]);
					}
				}else if(unsortedTeamList.get(i)[stateIndex].equals("Yes")) { //sorts out State in 7th
					state7th.add(unsortedTeamList.get(i));
				}else { //catches all the non-state, non-sport in 7th
					if(unsortedTeamList.get(i)[comfortIndex].equals("Yes")) {
						general7thC.add(unsortedTeamList.get(i));
					}else {
						general7thNC.add(unsortedTeamList.get(i));
					}
				}
			}
		}
		
		String[][][] lists = new String[unsortedTeamList.size()][9][8]; //the following code loads the arrayLists used for sorting onto the 3D array that's returned
		
		for(int i=0; i<general6thC.size(); i++) { 
			for(int j=0; j<9; j++) {
				lists[i][j][0] = general6thC.get(i)[j];
			}
		}
		
		for(int i=0; i<general6thNC.size(); i++) { 
			for(int j=0; j<9; j++) {
				lists[i][j][1] = general6thNC.get(i)[j]; 
			}
		}
		
		for(int i=0; i<state6th.size(); i++) {
			for(int j=0; j<9; j++) {
				lists[i][j][2] = state6th.get(i)[j];
			}
		}
		
		for(int i=0; i<general7thC.size(); i++) {
			for(int j=0; j<9; j++) {
				lists[i][j][3] = general7thC.get(i)[j];
			}
		}
		
		for(int i=0; i<general7thNC.size(); i++) {
			for(int j=0; j<9; j++) {
				lists[i][j][4] = general7thNC.get(i)[j];
			}
		}
		
		for(int i=0; i<state7th.size(); i++) {
			for(int j=0; j<9; j++) {
				lists[i][j][5] = state7th.get(i)[j];
			}
		}
		
		for(int i=0; i<sports6th.size(); i++) {
			for(int j=0; j<9; j++) {
				lists[i][j][6] = sports6th.get(i)[j];
			}
		}
		
		for(int i=0; i<sports7th.size(); i++) {
			for(int j=0; j<9; j++) {
				lists[i][j][7] = sports7th.get(i)[j];
			}
		}
		System.out.println();
		return lists;
	}
	
	public static ArrayList<String[]> numberTeamList(String[][][] sortedTeamLists){ 
		
		String[][][] sortedSpacedTeamLists = new String[sortedTeamLists.length][sortedTeamLists[0].length+1][sortedTeamLists[0][0].length]; //creates new matrix with extra column for team #
		for(int i=0; i<sortedTeamLists[0][0].length; i++) {
			for(int k=0; k<sortedTeamLists.length; k++) {
				for(int j=1; j<sortedTeamLists[0].length+1; j++) {
					sortedSpacedTeamLists[k][j][i] = sortedTeamLists[k][j-1][i];
				}
			}
		}
		
		
		ArrayList<String[]> numberedTeamList = new ArrayList<String[]>();
		int teamNumber = 1;
		String[] type = {"6th General Comfy", "6th General Not Comfy", "6th State", "7th General Comfy", "7th General Not Comfy", "7th State", "6th Sports", "7th Sports"};
		
		for(int i=0; i<8; i++) { //loads inputted data from array to arrayList
			int j = 0;
			System.out.print(type[i]+": "+teamNumber+" - ");
			while(j<sortedTeamLists.length && sortedTeamLists[j][1][i]!=null) {
				String[] bin = new String[sortedTeamLists[0].length+1]; //"creates" the 1D String matrix that fills the arrayList
				bin[0] = "Team "+teamNumber++;
				for(int k=1; k<sortedTeamLists[0].length+1; k++) {
					bin[k] = sortedSpacedTeamLists[j][k][i];
				}
				numberedTeamList.add(bin);
				j++;
			}
			System.out.println(teamNumber-1);
			
			
		}
		
		
		return numberedTeamList;
	}
	
	public static void finalTest(ArrayList<String[]> unsortedTeamList, String[] rosterOfIDs) {
		for(int i=0; i<rosterOfIDs.length; i++) { 
			int count = 0;
			for(int j=0; j<unsortedTeamList.size(); j++) {
				if(rosterOfIDs[i].equals(unsortedTeamList.get(j)[0]) || rosterOfIDs[i].equals(unsortedTeamList.get(j)[1])) {
					count++;
				}
			}
			if(count==0){
				System.out.println("Missing: "+rosterOfIDs[i]);
			}
			
		}
		
		for(int i=0; i<unsortedTeamList.size(); i++) {
			for(int j=0; j<2; j++) {
				int count = 0;
				for(int k=0; k<rosterOfIDs.length; k++) {
					if(rosterOfIDs[k].equals(unsortedTeamList.get(i)[j])) {
						count++;
					}
				}
				if(count==0) {
					System.out.println("Not on the roster: "+unsortedTeamList.get(i)[j]);
				}
				if(count>1) {
					System.out.println("Extra Appearances: "+rosterOfIDs[i]);
				}
			}
			
		}
		System.out.println();
	}
}
