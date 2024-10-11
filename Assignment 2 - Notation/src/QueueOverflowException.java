/**
 * This class occurs when an enqueue method is called on a full queue.
 * 
 * @author Sokha Heng
 */
public class QueueOverflowException extends RuntimeException {
	public QueueOverflowException(String message) {
		super(message);
	}
	
	public QueueOverflowException() {
		super("Queue is full, cannot enqueue.");
	}
}
