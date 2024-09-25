
public class NoLowerAlphaException extends Exception {
	public NoLowerAlphaException(String message) {
		super(message);
	}

	public NoLowerAlphaException() {
		super("The password must contain at least one lowercase alphabetic character");
	}
}
