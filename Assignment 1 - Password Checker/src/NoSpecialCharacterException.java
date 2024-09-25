
public class NoSpecialCharacterException extends Exception {
	public NoSpecialCharacterException(String message) {
		super(message);
	}

	public NoSpecialCharacterException() {
		super("The password must contain at least one special character");
	}

}
