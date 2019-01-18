/*Run this to get names of missing students
 * the second fileName must be of an uploaded CSV of a spreadsheet of just ID #s
 * Also, remember to update the last number, which represents the number of missing students*/
public class IdentifyStudentClient {
	public static void main(String[] args) {
		IdentifyStudent s = new IdentifyStudent("FullRoster6.csv", 262, "missingIDsSun.csv", 13);
	}
}
