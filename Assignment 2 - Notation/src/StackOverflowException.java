/**
 * 
 * This class occurs when a push method is called on a full stack.
 * @author Sokha Heng
 */
public class StackOverflowException extends Exception {
	public StackOverflowException(String message) {
		super(message);
	}
	
	public StackOverflowException() {
		super("Stack is full, cannot push another object on top");
	}
}
