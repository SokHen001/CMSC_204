
public class Road extends Object implements Comparable<Road>{

	private String roadName;
	private Town destination;
	private Town source;
	private int weight;
	
	/**
	 * Constructor
	 * 
	 * @param source - One town on the road
	 * @param destination - Another town on the road
	 * @param degrees - Weight of the edge
	 * @param roadName - Name of the road
	 */
	public Road(Town source, Town destination, int weight, String roadName) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		this.roadName = roadName;
	}
	
	/**
	 * Constructor with weight preset at 1
	 * 
	 * @param source - One town on the road
	 * @param destination - Another town on the road
	 * @param roadName - Name of the road
	 */
	public Road(Town source, Town destination, String roadName) {
		this.source = source;
		this.destination = destination;
		this.weight = 1;
		this.roadName = roadName;
	}
	
	/**
	 * Returns true only if the edge contains the given town
	 * 
	 * @param town - a vertex of the graph
	 * @return true only if the edge is connected to the given vertex
	 */
	public boolean contains(Town town) {
		if (town == null) {
	        return false; 
		}
		
	    return source.equals(town) || destination.equals(town);
	}
	
	@Override
	public String toString() {
		return source + " and " + destination + " is connected by the road " + roadName + " and the distance is "
				+ weight + " miles";
	}
	
	/**
	 * Returns the road name
	 * 
	 * @return The name of the road
	 */
	public String getName() {
		return roadName;
	}
	
	/**
	 * Returns the second town on the road
	 * 
	 * @return A town on the road
	 */
	public Town getDestination() {
		return destination;
	}
	
	/**
	 * Returns the first town on the road
	 * 
	 * @return A town on the road
	 */
	public Town getSource() {
		return source;
	}
	
	@Override
	public int compareTo(Road o) {
		return Integer.compare(this.weight, o.weight);
	}

	/**	
	 * Returns the distance of the road
	 * 
	 * @return the distance of the road
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Returns true if each of the ends of the road r is the same as the ends of
	 * this road. Remember that a road that goes from point A to point B is the same
	 * as a road that goes from point B to point A.
	 * 
	 * @param r - road object to compare it to
	 */
	@Override
	public boolean equals(Object r) {
		if (this == r) {
			return true;
		} else if (r == null) {
			return false;
		}
		
		Road newRoad = (Road) r;

		return (newRoad.destination.equals(destination) && newRoad.source.equals(source))
				|| (newRoad.source.equals(destination) && newRoad.destination.equals(source));
	}

}
