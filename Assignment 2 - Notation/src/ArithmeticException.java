/**
 * This class occurs when a attempting to divide an integer by zero
 * 
 * @author Sokha Heng
 */
public class ArithmeticException extends RuntimeException{
	public ArithmeticException() {
		super("Cannot divide an integer by zero");
	}
	
	public ArithmeticException(String message) {
		super(message);
	}
}
