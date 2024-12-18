import java.util.*;

/**
 * This class implements the GraphInterface.
 * 
 * @author Sokha Heng
 * Project 6: Town Population
 * Due date: 12/18/2024
 */
public class Graph implements GraphInterface<Town, Road> {

    private Map<Town, Set<Road>> adjacencyList;
    private Map<Town, Town> previousVertices;

    public Graph() {
        adjacencyList = new HashMap<>();
        previousVertices = new HashMap<>();
    }

    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
        if (!adjacencyList.containsKey(sourceVertex) || !adjacencyList.containsKey(destinationVertex)) {
            return null;
        }
        for (Road road : adjacencyList.get(sourceVertex)) {
            if (road.getDestination().equals(destinationVertex) || road.getSource().equals(destinationVertex)) {
                return road;
            }
        }
        return null;
    }

    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        if (!adjacencyList.containsKey(sourceVertex)) {
            addVertex(sourceVertex);
        }
        if (!adjacencyList.containsKey(destinationVertex)) {
            addVertex(destinationVertex);
        }

        Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
        adjacencyList.get(sourceVertex).add(newRoad);
        adjacencyList.get(destinationVertex).add(newRoad);

        return newRoad;
    }

    @Override
    public boolean addVertex(Town v) {
        if (v == null || adjacencyList.containsKey(v)) {
            return false;
        }
        adjacencyList.put(v, new HashSet<>());
        return true;
    }

    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
        if (!adjacencyList.containsKey(sourceVertex)) {
            return false;
        }
        for (Road road : adjacencyList.get(sourceVertex)) {
            if (road.getDestination().equals(destinationVertex) || road.getSource().equals(destinationVertex)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsVertex(Town v) {
        return adjacencyList.containsKey(v);
    }

    @Override
    public Set<Road> edgeSet() {
        Set<Road> edges = new HashSet<>();
        for (Set<Road> roads : adjacencyList.values()) {
            edges.addAll(roads);
        }
        return edges;
    }

    @Override
    public Set<Road> edgesOf(Town vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
    }

    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        if (!adjacencyList.containsKey(sourceVertex) || !adjacencyList.containsKey(destinationVertex)) {
            return null;
        }
        Road roadToRemove = null;
        for (Road road : adjacencyList.get(sourceVertex)) {
            if ((road.getDestination().equals(destinationVertex) || road.getSource().equals(destinationVertex))
                && road.getWeight() == weight && road.getName().equals(description)) {
                roadToRemove = road;
                break;
            }
        }
        if (roadToRemove != null) {
            adjacencyList.get(sourceVertex).remove(roadToRemove);
            adjacencyList.get(destinationVertex).remove(roadToRemove);
        }
        return roadToRemove;
    }

    @Override
    public boolean removeVertex(Town v) {
        if (!adjacencyList.containsKey(v)) {
            return false;
        }
        Set<Road> roadsToRemove = new HashSet<>(adjacencyList.get(v));
        for (Road road : roadsToRemove) {
            removeEdge(road.getSource(), road.getDestination(), road.getWeight(), road.getName());
        }
        adjacencyList.remove(v);
        return true;
    }

    @Override
    public Set<Town> vertexSet() {
        return adjacencyList.keySet();
    }

    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
        dijkstraShortestPath(sourceVertex);
        ArrayList<String> path = new ArrayList<>();
        Town current = destinationVertex;
        while (current != null && previousVertices.containsKey(current)) {
            Town previous = previousVertices.get(current);
            if (previous != null) {
                Road road = getEdge(previous, current);
                path.add(0, previous.getName() + " via " + road.getName() + " to " + current.getName() + " " + road.getWeight() + " mi");
            }
            current = previous;
        }
        return path;
    }

    @Override
    public void dijkstraShortestPath(Town sourceVertex) {
        Map<Town, Integer> distances = new HashMap<>();
        PriorityQueue<Town> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        distances.put(sourceVertex, 0);
        for (Town town : adjacencyList.keySet()) {
            if (!town.equals(sourceVertex)) {
                distances.put(town, Integer.MAX_VALUE);
            }
            previousVertices.put(town, null);
        }
        pq.add(sourceVertex);
        while (!pq.isEmpty()) {
            Town currentTown = pq.poll();
            for (Road road : adjacencyList.get(currentTown)) {
                Town neighbor = road.getDestination().equals(currentTown) ? road.getSource() : road.getDestination();
                int newDist = distances.get(currentTown) + road.getWeight();
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previousVertices.put(neighbor, currentTown);
                    pq.add(neighbor);
                }
            }
        }
    }
}
