
public class Town extends Object implements Comparable<Town> {

	private String name;
	
	/**
	 * Constructor
	 * 
	 * @param name - name of the town
	 */
	public Town(String name) {
		this.name = name;
	}
	
	/**
	 * Copy constructor.
	 * 
	 * @param templateTown - an instance of Town
	 */
	public Town(Town templateTown) {
		this.name = templateTown.getName();
	}

	/**
	 * Returns the town's name
	 * 
	 * @return - town's name
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public int compareTo(Town o) {
		return name.compareTo(o.getName());
	}

	/**
	 * To string method
	 * 
	 * @return - the town name
	 */
	@Override
	public String toString() {
		return getName();
	}
	
	/**
	 * 
	 *
	 * @return the hashcode for the name of the town
	 */
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	/**
	 * check if 2 towns have the same name
	 * 
	 * @return true if the town names are equal, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		}
		Town town = (Town) obj;
		return this.name.equals(town.getName());
	}
	
	
}
