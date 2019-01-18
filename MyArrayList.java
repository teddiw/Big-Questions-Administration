import java.util.ArrayList;
//Class with methods to eliminate redundancies in ArrayLists
public class MyArrayList {
	public ArrayList<String[]> list = new ArrayList<String[]>();
	public MyArrayList(ArrayList<String[]> l) {	//creates a copy of the inputted ArrayList
		for(int i=0; i<l.size(); i++) {
			list.add(l.get(i));
		}
	}
	
	public void reduce() {	//deletes the second partner listed (no regard for state status)
		for(int i=0; i<list.size(); i++) {
			for(int j=0; j<list.size(); j++) {
				if(list.get(j)[1].equals(list.get(i)[0])) {
					list.remove(j);
					break;	
				}
				
			}
		}
	}
	//be sure to update method calls to statePreferenceReduce with the added parameters
	public void statePreferenceReduce(int stateIndex, int sportIndex, int scheduleIndex, int comfortIndex) { //reduces, but if one partner is going to state, that is the entry that is kept to represent the team
		for(int i=0; i<list.size(); i++) {
			for(int j=0; j<list.size(); j++) {
				if(list.get(j)[1].equals(list.get(i)[0])) {
					if(list.get(j)[stateIndex].equals("No") && list.get(i)[stateIndex].equals("No")) {
						scheduleComfortSmush(i, j, sportIndex, scheduleIndex, comfortIndex);
						list.remove(j);
						break;	
					}else if(list.get(j)[stateIndex].equals("Yes") && list.get(i)[stateIndex].equals("Yes")) {
						scheduleComfortSmush(i, j, sportIndex, scheduleIndex, comfortIndex);
						list.remove(j);
						break;
					}else if(list.get(j)[stateIndex].equals("No") && list.get(i)[stateIndex].equals("Yes")) {
						scheduleComfortSmush(i, j, sportIndex, scheduleIndex, comfortIndex);
						list.remove(j);
						break;
					}else if(list.get(j)[stateIndex].equals("Yes") && list.get(i)[stateIndex].equals("No")) {
						scheduleComfortSmush(i, j, sportIndex, scheduleIndex, comfortIndex);
						list.remove(i);
						i--;
						break;
					}
				}
				
			}
		}
	}
	
	public void scheduleComfortSmush(int i, int j, int sportIndex, int scheduleIndex, int comfortIndex) {
		//if j is sports, smush j schedule to i schedule and if j comfort is "yes", set i comfort to "yes"
		//if(list.get(j)[sportIndex].equals("Yes")) {
		if(!(list.get(i)[scheduleIndex].equals("") && list.get(j)[scheduleIndex].equals(""))) {	
			list.get(i)[scheduleIndex] += " **AND** "+list.get(j)[scheduleIndex];
		}
		//}
		if(list.get(j)[comfortIndex].equals("Yes")) {
			list.get(i)[comfortIndex] = "Yes";
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
}
