import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Student test class for TownGraphManager
 * 
 * @author Sokha Heng
 * Project 6: Town Population
 * Due date: 12/18/2024
 */
public class TownGraphManager_STUDENT_Test {
	private TownGraphManagerInterface graph;
	private String[] town;

	@Before
	public void setUp() throws Exception {
		graph = new TownGraphManager();
		town = new String[8];

		for (int i = 1; i < 8; i++) {
			town[i] = "Town_" + i;
			graph.addTown(town[i]);
		}

		graph.addRoad(town[1], town[2], 5, "Road_1");
		graph.addRoad(town[1], town[3], 3, "Road_2");
		graph.addRoad(town[2], town[4], 4, "Road_3");
		graph.addRoad(town[3], town[4], 7, "Road_4");
		graph.addRoad(town[4], town[5], 2, "Road_5");
		graph.addRoad(town[5], town[6], 1, "Road_6");
		graph.addRoad(town[5], town[7], 8, "Road_7");
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		Collections.sort(roads);
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_2", roads.get(1));
		assertEquals("Road_3", roads.get(2));
		graph.addRoad(town[6], town[7], 4, "Road_8");
		roads = graph.allRoads();
		Collections.sort(roads);
		assertEquals("Road_8", roads.get(7));
	}

	@Test
	public void testGetRoad() {
		assertEquals("Road_1", graph.getRoad(town[1], town[2]));
		assertEquals("Road_4", graph.getRoad(town[3], town[4]));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Town_8"));
		graph.addTown("Town_8");
		assertEquals(true, graph.containsTown("Town_8"));
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Town_5"));
		assertEquals(false, graph.containsTown("Town_8"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[2], town[4]));
		assertEquals(false, graph.containsRoadConnection(town[1], town[6]));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		Collections.sort(roads);
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_6", roads.get(5));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[2], town[4]));
		graph.deleteRoadConnection(town[2], town[4], "Road_3");
		assertEquals(false, graph.containsRoadConnection(town[2], town[4]));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		graph.deleteTown("Town_2");
		assertEquals(false, graph.containsTown("Town_2"));
	}

	@Test
	public void testAllTowns() {
		ArrayList<String> towns = graph.allTowns();
		Collections.sort(towns);
		assertEquals("Town_1", towns.get(0));
		assertEquals("Town_7", towns.get(6));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(town[1], town[6]);
		assertNotNull(path);
		assertTrue(path.size() > 0);
		assertEquals("Town_1 via Road_1 to Town_2 5 mi", path.get(0).trim());
		assertEquals("Town_2 via Road_3 to Town_4 4 mi", path.get(1).trim());
		assertEquals("Town_4 via Road_5 to Town_5 2 mi", path.get(2).trim());
		assertEquals("Town_5 via Road_6 to Town_6 1 mi", path.get(3).trim());
	}
}
