import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Student test class for Road
 * 
 * @author Sokha Heng 
 * Project 6: Town Population 
 * Due date: 12/18/2024
 */
public class Road_STUDENT_Test {

	private Town townA;
	private Town townB;
	private Town townC;

	private Road roadA;
	private Road roadB;
	private Road roadC;
	private Road roadD;

	@Before
	public void setUp() throws Exception {
		townA = new Town("Bethesda");
		townB = new Town("Silver Spring");
		townC = new Town("Rockville");

		roadA = new Road(townA, townB, 10, "Georgia Avenue");
		roadB = new Road(townB, townA, 10, "Georgia Avenue");
		roadC = new Road(townA, townC, 5, "Rockville Pike");
		roadD = new Road(townC, townB, 8, "I-270"); //
	}

	@After
	public void tearDown() throws Exception {
		townA = townB = townC = null;
		roadA = roadB = roadC = roadD = null;
	}

	@Test
	public void testConstructor() {
		assertEquals(10, roadB.getWeight()); // Default weight should be 1
		assertEquals("Georgia Avenue", roadB.getName()); // Road name should be the same
	}

	@Test
	public void testConstructorWithWeight() {
		assertEquals(10, roadA.getWeight());
		assertEquals("Georgia Avenue", roadA.getName());

		assertEquals(5, roadC.getWeight());
		assertEquals(8, roadD.getWeight());
	}

	@Test
	public void testToString() {
		String expected = "Bethesda and Silver Spring is connected by the road Georgia Avenue and the distance is 10 miles";
		assertEquals(expected, roadA.toString());
	}

	@Test
	public void testGetters() {
		assertEquals("Georgia Avenue", roadA.getName());
		assertEquals(townB, roadA.getDestination());
		assertEquals(townA, roadA.getSource());
	}

	@Test
	public void testContains() {
		assertTrue(roadA.contains(townA));
		assertTrue(roadA.contains(townB));
		assertFalse(roadA.contains(townC));
	}

	@Test
	public void testEquals() {
		assertTrue(roadA.equals(roadB));
		assertFalse(roadA.equals(roadC));
		assertFalse(roadA.equals(roadD));
	}

	@Test
	public void testCompareTo() {
		assertEquals(0, roadA.compareTo(roadB));
		assertTrue(roadA.compareTo(roadC) > 0);
		assertTrue(roadC.compareTo(roadD) < 0);
	}

}
