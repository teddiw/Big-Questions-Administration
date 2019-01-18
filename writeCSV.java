
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class writeCSV {
	private String COMMA_DELIMITER = ",";
	private String NEW_LINE_SEPARATOR = "\n";
	
	//public String FILE_HEADER = "Code, Name1, Name2, Period, Sports?, State?";
	public void writeCSV(ArrayList<String[]> list, String fileName, String fileHeader) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
			fileWriter.append(fileHeader.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);
			for(int i=0; i<list.size(); i++) {
				fileWriter.append(String.valueOf(list.get(i)[0]));
				for(int j=1; j<list.get(i).length; j++) {
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(list.get(i)[j]));
				}
				fileWriter.append(NEW_LINE_SEPARATOR);
			} 
		} catch (Exception e) {
				System.out.println("Error in writeCSV");
				e.printStackTrace();
			} finally {
				try {
					fileWriter.flush();
					fileWriter.close();
				} catch(IOException e) {
					System.out.println("Erro while flushing/closing fileWriter");
					e.printStackTrace();
				}
			}
			
		}
		
}

