/**
 * 
 * This class occurs when a dequeue method is called on an empty queue.
 * @author Sokha Heng
 */
public class QueueUnderflowException extends Exception {
	public QueueUnderflowException(String message) {
		super(message);
	}
	
	public QueueUnderflowException() {
		super("Queue is empty, cannot dequeue .");
	}
}
