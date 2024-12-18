
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Student test class for Graph
 * 
 * @author Sokha Heng
 * Project 6: Town Population
 * Due date: 12/18/2024
 */
public class Graph_STUDENT_Test {
    private GraphInterface<Town, Road> graph;
    private Town[] town;

    @Before
    public void setUp() throws Exception {
        graph = new Graph();
        town = new Town[8];

        for (int i = 1; i < 8; i++) {
            town[i] = new Town("Town_" + i);
            graph.addVertex(town[i]);
        }

        graph.addEdge(town[1], town[2], 5, "Road_1");
        graph.addEdge(town[1], town[3], 3, "Road_2");
        graph.addEdge(town[2], town[4], 4, "Road_3");
        graph.addEdge(town[3], town[4], 7, "Road_4");
        graph.addEdge(town[4], town[5], 2, "Road_5");
        graph.addEdge(town[5], town[6], 1, "Road_6");
        graph.addEdge(town[5], town[7], 8, "Road_7");
    }

    @After
    public void tearDown() throws Exception {
        graph = null;
    }

    @Test
    public void testGetEdge() {
        assertEquals(new Road(town[1], town[2], 5, "Road_1"), graph.getEdge(town[1], town[2]));
        assertEquals(new Road(town[3], town[4], 7, "Road_4"), graph.getEdge(town[3], town[4]));
    }

    @Test
    public void testAddEdge() {
        assertFalse(graph.containsEdge(town[6], town[7]));
        graph.addEdge(town[6], town[7], 4, "Road_8");
        assertTrue(graph.containsEdge(town[6], town[7]));
    }

    @Test
    public void testAddVertex() {
        Town newTown = new Town("Town_8");
        assertFalse(graph.containsVertex(newTown));
        graph.addVertex(newTown);
        assertTrue(graph.containsVertex(newTown));
    }

    @Test
    public void testContainsEdge() {
        assertTrue(graph.containsEdge(town[2], town[4]));
        assertFalse(graph.containsEdge(town[1], town[6]));
    }

    @Test
    public void testContainsVertex() {
        assertTrue(graph.containsVertex(town[5]));
        assertFalse(graph.containsVertex(new Town("Town_8")));
    }

    @Test
    public void testEdgeSet() {
        Set<Road> roads = graph.edgeSet();
        ArrayList<String> roadArrayList = new ArrayList<>();
        for (Road road : roads)
            roadArrayList.add(road.getName());
        Collections.sort(roadArrayList);
        assertEquals("Road_1", roadArrayList.get(0));
        assertEquals("Road_5", roadArrayList.get(4));
    }

    @Test
    public void testEdgesOf() {
        Set<Road> roads = graph.edgesOf(town[4]);
        ArrayList<String> roadArrayList = new ArrayList<>();
        for (Road road : roads)
            roadArrayList.add(road.getName());
        Collections.sort(roadArrayList);
        assertEquals("Road_3", roadArrayList.get(0));
        assertEquals("Road_4", roadArrayList.get(1));
        assertEquals("Road_5", roadArrayList.get(2)); 
    }


    @Test
    public void testRemoveEdge() {
        assertTrue(graph.containsEdge(town[2], town[4]));
        graph.removeEdge(town[2], town[4], 4, "Road_3");
        assertFalse(graph.containsEdge(town[2], town[4]));
    }

    @Test
    public void testRemoveVertex() {
        assertTrue(graph.containsVertex(town[6]));
        graph.removeVertex(town[6]);
        assertFalse(graph.containsVertex(town[6]));
    }

    @Test
    public void testVertexSet() {
        Set<Town> towns = graph.vertexSet();
        assertTrue(towns.contains(town[1]));
        assertTrue(towns.contains(town[5]));
    }

    @Test
    public void testShortestPath() {
        ArrayList<String> path = graph.shortestPath(town[1], town[6]);
        assertNotNull(path);
        assertTrue(path.size() > 0);
    }
}
