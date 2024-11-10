import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class implements the CourseDBManagerInterface that is provided.
 * 
 * @author Sokha Heng
 */
public class CourseDBManager implements CourseDBManagerInterface {
	private CourseDBStructure CDS;
	
	/**
	 * Constructor 
	 * 
	 * @param n - estimated number of courses 
	 */
	public CourseDBManager(int n) {
		CDS = new CourseDBStructure(n);
	}

	// Default Constructor
	public CourseDBManager() {
		this(500);
	}

	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);
		CDS.add(element);
	}

	@Override
	public void readFile(File input) throws FileNotFoundException {
		Scanner file = new Scanner(input);
		
		while(file.hasNextLine()) {
			String line = file.nextLine();
			@SuppressWarnings("resource")
			Scanner lineScan = new Scanner(line);
			
			String id = lineScan.next();
			int crn = lineScan.nextInt();
			int credits = lineScan.nextInt();
			String roomNum = lineScan.next();
			String instructor = lineScan.nextLine().trim();
			
			add(id, crn, credits, roomNum, instructor);
			
		}
		
		file.close();
		
	}

	@Override
	public ArrayList<String> showAll() {
		return CDS.showAll();
	}

	@Override
	public CourseDBElement get(int crn){
		try {
			return CDS.get(crn);
		} catch (IOException e) {
			System.out.print("Course with CRN " + crn + " not found in the database.");
			return null;
		}
	}
	
}
