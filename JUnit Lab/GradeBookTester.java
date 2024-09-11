import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GradebookTester {
	
	private GradeBook g1;
	private GradeBook g2;
	
	@Before
	public void setUp() {
		
		// create at least two objects of Gradebook of size 5.
		
		// object 1 
		g1 = new GradeBook(5);
		g1.addScore(50);
		g1.addScore(75);
		
		// object 2
		g2 = new GradeBook(5);
		g2.addScore(82);
		g2.addScore(87);
		g2.addScore(93);
		g2.addScore(78);
	}
	
	@After
	public void teardown() {
		
		// set the two objects of GradeBook to null.
		g1 = null;
		g2 = null;
	}
	
	@Test
	public void addScoreTest() {
		
		// Use the toString method to compare the  
		// contents of what is in the scores array vs. what  
		// is expected to be in the scores array assertTrue(...)
		assertTrue(g1.toString().equals("50.0 75.0"));
		assertTrue(g2.toString().equals("82.0 87.0 93.0 78.0"));
		
		// Compare the scoreSize to the 
		// expected number of scores entered.
		assertEquals(2, g1.getScoreSize(), 0.01);
		assertEquals(4, g2.getScoreSize(), 0.01);
		
	}
	
	@Test
	public void testSum() {
		
		// Compare what is returned by sum() to 
		// the expected sum of the scores entered.
		assertEquals(125, g1.sum(), .0001);
		assertEquals(340, g2.sum(), .0001);
	}
	
	@Test
	public void testMinimum() {
		
		// Compare what is returned by minimum() to
		//  the expected minimum of the scores entered.
		assertEquals(50, g1.minimum(), .001);
		assertEquals(78, g2.minimum(), .001);
	}
	
	@Test
	public void testFinalScore() {
		
		// Compare what is returned by finalScore() to  
		// the expected finalscore of the scores entered.
		assertEquals(75, g1.finalScore(), .0001);
		assertEquals(262, g2.finalScore(), .0001);
	}
	
	
	

}
