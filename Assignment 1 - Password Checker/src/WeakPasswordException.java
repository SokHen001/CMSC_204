
public class WeakPasswordException extends Exception {
	public WeakPasswordException(String message) {
		super(message);
	}

	public WeakPasswordException() {
		super("The password is OK but weak - it contains fewer than 10 character");
	}
}
