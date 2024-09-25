
public class NoUpperAlphaException extends Exception {
	public NoUpperAlphaException(String message) {
		super(message);
	}

	public NoUpperAlphaException() {
		super("The password must contain at least one uppercase alphabetic character");
	}
}
