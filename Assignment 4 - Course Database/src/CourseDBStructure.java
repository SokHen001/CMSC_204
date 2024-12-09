import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * CDS that implements CDSInterface
 * 
 * 
 * @author Sokha Heng
 */
public class CourseDBStructure implements CourseDBStructureInterface{

	private LinkedList<CourseDBElement>[] hashTable;
	private int tableSize = 500;
	private double loadFactor;
	
	/**
	 * Constructor 1
	 * 
	 * @param n - the estimated number of courses
	 */
	@SuppressWarnings("unchecked")
	public CourseDBStructure(int n) {
		this.loadFactor = 1.5;
		int determineSize = (int) Math.ceil(n/loadFactor);
		tableSize = find4kPlus3Prime(determineSize);
		hashTable = new LinkedList[tableSize];
	} // end constructor

	/**
	 * find the next prime number of a given number
	 * 
	 * @param i - number given
	 * @return the next prime number of a given number
	 */
	private int find4kPlus3Prime(int i) {
		int number = (i % 2 == 0) ? i + 1 : i;
		while(true) {
			if (isPrime(number) && number % 4 == 3) {
				return number;
			}
			
			number += 2;
		}
	}

	/**
	 * determine if is prime
	 * 
	 * @param num 
	 * @return true if num is prime, false otherwise
	 */
	private boolean isPrime(int num) {
		if (num <= 1)
			return false;
		if (num == 2 || num == 3)
			return true;
		if (num % 2 == 0 || num % 3 == 0)
			return false;
		for (int i = 5; i * i <= num; i += 6) {
			if (num % i == 0 || num % (i+2) == 0)
				return false;
		}
		
		return true;
	}

	public CourseDBStructure() {
		
	} // end constructor
	
	/**
	 * Constructor 2: for testing purposes
	 * 
	 * @param Testing
	 * @param HashTableSize
	 */
	@SuppressWarnings("unchecked")
	public CourseDBStructure(String Testing, int HashTableSize) {
		this.tableSize = HashTableSize;
		hashTable = new LinkedList[tableSize];
	} // end testing contructor

	@Override
	public void add(CourseDBElement element) {
		int index = Math.abs(String.valueOf(element.getCRN()).hashCode()) % tableSize;
		
		if (hashTable[index] == null) {
			hashTable[index] = new LinkedList<>();
		}
		
		LinkedList<CourseDBElement> bucket = hashTable[index];
		
		for (CourseDBElement dupeElement : bucket) {
			if (dupeElement.getCRN() == element.getCRN()) {
				
				bucket.remove(dupeElement);
				bucket.add(element);
				return;
			}
		}
		
		bucket.add(element);
	}

	@Override
	public CourseDBElement get(int crn) throws IOException {
		int index = Math.abs(String.valueOf(crn).hashCode()) % tableSize;
		LinkedList<CourseDBElement> bucket = hashTable[index];
		
		if (bucket != null) {
			for (CourseDBElement element : bucket) {
				if (element.getCRN() == crn) {
					return element;
				}
			}
		}
		throw new IOException("Course with CRN " + crn + " not found in the database.");
	}

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> listOfcourses = new ArrayList<>();
		
		for (LinkedList<CourseDBElement> bucket : hashTable) {
			if (bucket != null) {
				for (CourseDBElement element : bucket) {
					
					String course = "\nCourse:" + element.getID() +
							" CRN:" + element.getCRN() +
							" Credits:" + element.getCredits() + 
							" Instructor:" + element.getInstructor() +
							" Room:" + element.getRoomNum();
					listOfcourses.add(course);
				}
			}
		}

		
		return listOfcourses;
		
	}

	@Override
	public int getTableSize() {
		return tableSize;
	}
	
	
}
