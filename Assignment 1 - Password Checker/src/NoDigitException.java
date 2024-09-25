/**
 * @author Sokha Heng
 */
public class NoDigitException extends Exception {
	public NoDigitException(String message) {
		super(message);
	}

	public NoDigitException() {
		super("The password must contain at least one digit");
	}

}
