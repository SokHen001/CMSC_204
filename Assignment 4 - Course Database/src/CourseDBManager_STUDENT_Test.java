import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Student Test for CourseDBManager
 * which implements CourseDBManagerInterface
 * 
 * @author Sokha Heng
 */
public class CourseDBManager_STUDENT_Test {
    
    private CourseDBManagerInterface dataMgr; 
    private File inputFile;

    @Before
    public void setUp() throws Exception {
        dataMgr = new CourseDBManager();  
        inputFile = new File("testfile.txt"); 
    }

    @After
    public void tearDown() throws Exception {
        dataMgr = null;  
        if (inputFile.exists()) {
            inputFile.delete();  
        }
    }

    @Test
    public void testAddToDB() {
        try {
            dataMgr.add("PHYS161", 21590, 4, "LB032", "Dr. Max Nam");
            dataMgr.add("MATH282", 32190, 3, "SW205", "Penn Richard");
        } catch (Exception e) {
            fail("This should not have caused an Exception");
        }
    }

    @Test
    public void testShowAll() {
    	dataMgr.add("MATH282", 32190, 3, "SW205", "Penn Richard");
    	dataMgr.add("PHYS161", 21590, 4, "LB032", "Dr. Max Nam");
        dataMgr.add("ENEE244", 23531, 4, "SC231", "Kam Yee");
  
        ArrayList<String> list = dataMgr.showAll();
        assertNotNull("The course list should not be null", list);
        assertTrue("There should be at least one course in the list", list.size() > 0);
        
        assertEquals(list.get(0),"\nCourse:PHYS161 CRN:21590 Credits:4 Instructor:Dr. Max Nam Room:LB032");
        assertEquals(list.get(1),"\nCourse:ENEE244 CRN:23531 Credits:4 Instructor:Kam Yee Room:SC231");
        assertEquals(list.get(2),"\nCourse:MATH282 CRN:32190 Credits:3 Instructor:Penn Richard Room:SW205");
    }

    @Test
    public void testGet() {
        dataMgr.add("PHYS161", 21590, 4, "LB032", "Dr. Max Nam");

        CourseDBElement course = dataMgr.get(21590);
        assertNotNull("Course should be found for existing CRN", course);
        assertEquals("PHYS161", course.getID());

        CourseDBElement nonExistentCourse = dataMgr.get(99999);
        assertNull("Course should be null for non-existing CRN", nonExistentCourse);
    }

    @Test
    public void testReadFile() {
        try (PrintWriter writer = new PrintWriter(inputFile)) {
            writer.println("MATH282 32190 3 SW205 Penn Richard");
            writer.println("ENEE244 23531 4 SC231 Kam Yee");
        } catch (FileNotFoundException e) {
            fail("Test file could not be created");
        }
        
        try {
            dataMgr.readFile(inputFile);
            assertEquals("MATH282", dataMgr.get(32190).getID());
            assertEquals("ENEE244", dataMgr.get(23531).getID());
        } catch (Exception e) {
            fail("Should not have thrown an exception");
        }
    }
}
