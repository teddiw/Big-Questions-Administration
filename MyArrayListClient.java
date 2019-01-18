

import java.util.ArrayList;

public class MyArrayListClient {
	public static void main(String[] args) {
		ArrayList<String[]> original = new ArrayList<String[]>();
		
		String[] a = {"Teddi","Rojeen","","","No"};
		String[] b = {"Faraz","Kathryn","","","Yes"};
		String[] c = {"Rojeen", "Teddi","","","Yes"};
		String[] d = {"Kathryn","Faraz","","","Yes"};
		String[] e = {"me","xad","","","Yes"};
		String[] f = {"xad","me","","","No"};
		
		original.add(a);
		original.add(b);
		original.add(c);
		original.add(d);
		original.add(e);
		original.add(f);
		
		MyArrayList reduced = new MyArrayList(original);
		//statePreferenceReduce(int stateIndex, int sportIndex, int scheduleIndex, int comfortIndex)
		reduced.statePreferenceReduce(4, 3, 7, 8);
		System.out.println(reduced);
		
		String s = "";
		for(int i=0; i<original.size(); i++) {
			for(int j=0; j<original.get(0).length; j++) {
				s+=original.get(i)[j]+" ";
			}
			s+="\n";
		}
		
		System.out.println(s);
		
	}
	
}
