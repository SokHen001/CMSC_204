
public class UnmatchedException extends Exception {
	public UnmatchedException(String message) {
		super(message);
	}

	public UnmatchedException() {
		super("The passwords do not match");
	}
}
