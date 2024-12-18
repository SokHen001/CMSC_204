import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * This class will implements the TownGraphManagerInterface. There are methods to
 * populate the graph (reading from a text file), add a town (vertices), add a
 * road (edge), list all towns and all roads, and list towns adjacent to a given
 * town.
 * 
 * @author Sokha Heng
 * Project 6: Town Population
 * Due date: 12/18/2024
 */
public class TownGraphManager implements TownGraphManagerInterface {

	private Graph graph;

	// constructor
	public TownGraphManager() {
		graph = new Graph();
	}

	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town t1 = getTown(town1);
		Town t2 = getTown(town2);
		if (t1 == null) {
			t1 = new Town(town1);
			graph.addVertex(t1);
		}
		if (t2 == null) {
			t2 = new Town(town2);
			graph.addVertex(t2);
		}
		return graph.addEdge(t1, t2, weight, roadName) != null;
	}

	@Override
	public String getRoad(String town1, String town2) {
		Town t1 = getTown(town1);
		Town t2 = getTown(town2);
		if (t1 == null || t2 == null) {
			return null;
		}
		Road road = graph.getEdge(t1, t2);
		return road != null ? road.getName() : null;
	}

	@Override
	public boolean addTown(String v) {
		if (getTown(v) != null) {
			return false;
		}
		Town newTown = new Town(v);
		return graph.addVertex(newTown);
	}

	@Override
	public Town getTown(String name) {
		for (Town town : graph.vertexSet()) {
			if (town.getName().equals(name)) {
				return town;
			}
		}
		return null;
	}

	@Override
	public boolean containsTown(String v) {
		return getTown(v) != null;
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town t1 = getTown(town1);
		Town t2 = getTown(town2);
		if (t1 == null || t2 == null) {
			return false;
		}
		return graph.containsEdge(t1, t2);
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> roads = new ArrayList<>();
		for (Road road : graph.edgeSet()) {
			roads.add(road.getName());
		}
		Collections.sort(roads);
		return roads;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town t1 = getTown(town1);
		Town t2 = getTown(town2);
		if (t1 == null || t2 == null) {
			return false;
		}
		Road roadToDelete = graph.getEdge(t1, t2);
		if (roadToDelete != null && roadToDelete.getName().equals(road)) {
			graph.removeEdge(t1, t2, roadToDelete.getWeight(), road);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteTown(String v) {
		Town town = getTown(v);
		if (town == null) {
			return false;
		}
		return graph.removeVertex(town);
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> towns = new ArrayList<>();
		for (Town town : graph.vertexSet()) {
			towns.add(town.getName());
		}
		Collections.sort(towns);
		return towns;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		Town t1 = getTown(town1);
		Town t2 = getTown(town2);
		if (t1 == null || t2 == null) {
			return null;
		}
		return graph.shortestPath(t1, t2);
	}

	/**
	 * populate town from selected file into the graph
	 * 
	 * @param selectedFile - file to read from
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void populateTownGraph(File selectedFile) throws FileNotFoundException, IOException {
		Scanner in = new Scanner(selectedFile);
		Town town1, town2;
		String line = "";
		String[] splitLine;
		while (in.hasNext()) {
			line = in.nextLine();
			splitLine = line.split("[,;]");
			town2 = new Town(splitLine[3]);
			town1 = new Town(splitLine[2]);
			graph.addVertex(town1);
			graph.addVertex(town2);
			graph.addEdge(town1, town2, Integer.parseInt(splitLine[1]), splitLine[0]);

		}

	}
}
