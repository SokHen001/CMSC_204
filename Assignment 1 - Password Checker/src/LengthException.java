/**
 * @author Sokha Heng
 */
public class LengthException extends Exception {

	public LengthException(String message) {
		super(message);
	}

	public LengthException() {
		super("The password must be at least 6 characters long");
	}

}
