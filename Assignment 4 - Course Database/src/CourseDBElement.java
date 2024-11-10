import java.util.Objects;

/**
 * This class implement Comparable
 * 
 * @author Sokha Heng
 */
public class CourseDBElement implements Comparable<CourseDBElement>{

	private String courseID;
	private int CRN;
	private int credits;
	private String roomNum;
	private String instructor;
	
	/**
	 * Constructor to assign values
	 * 
	 * @param id         course id
	 * @param crn        course crn
	 * @param credits    number of credits
	 * @param roomNum    course room number
	 * @param instructor name of the instructor
	 */
	public CourseDBElement(String id, int crn, int credits, String roomNum, String instructor) {
		this.courseID = id;
		this.CRN = crn;
		this.credits = credits;
		this.roomNum = roomNum;
		this.instructor = instructor;
	} 

	// Constructor
	public CourseDBElement() {
	}

	/**
	 * retrieve Course ID
	 * 
	 * @return id 
	 */
	public String getID() {
		return courseID;
	}
	
	/**
	 * retrieve Course CRN
	 * 
	 * @return CRN
	 */
	public int getCRN() {
		return CRN;
	}
	
	/**
	 * retrieve Course credit
	 * 
	 * @return credits
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * retrieve Course room number
	 * 
	 * @return room number
	 */
	public String getRoomNum() {
		return roomNum;
	}

	/**
	 * retrieve instructor of the course
	 * 
	 * @return instructor name
	 */
	public String getInstructor() {
		return instructor;
	}
	
	@Override
	public int compareTo(CourseDBElement o) {
		return Integer.compare(this.CRN, o.CRN);
	}

	@Override
	public int hashCode() {
		return Objects.hash(CRN);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		
		CourseDBElement that = (CourseDBElement) obj;
		return CRN == that.CRN;
		
	}

	/**
	 * set value to crn
	 * 
	 * @param crn
	 */
	public void setCRN(int crn) {
		this.CRN = crn;
	}

}
