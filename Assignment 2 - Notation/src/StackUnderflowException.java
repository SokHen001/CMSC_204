/**
 * This class occurs when a top or pop method is called on an empty stack.
 * 
 * @author Sokha Heng
 */
public class StackUnderflowException extends RuntimeException {
	public StackUnderflowException(String message) {
		super(message);
	}
	
	public StackUnderflowException() {
		super("Stack is empty, pop() or top() can't occur at this time");
	}
}
