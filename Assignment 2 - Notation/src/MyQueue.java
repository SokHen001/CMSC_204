import java.util.ArrayList;

/**
 * MyQueue will implement the QueueInterface
 * 
 * @param <T>
 */
public class MyQueue<T> implements QueueInterface<T> {
	private T[] queueArray; // Array of queue entries
	private int front, rear, size;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;

	/**
	 * takes an int as the size of the queue
	 * 
	 * @param defaultCapacity
	 */
	public MyQueue(int defaultCapacity) {
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[defaultCapacity];
		queueArray = tempQueue;
		front = 0;
		rear = -1;
		size = 0;
	} // end constructor

	/**
	 * uses a default as the size of the queue
	 */
	public MyQueue() {
		this(DEFAULT_CAPACITY);
	} // end default constructor

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return size == queueArray.length;
	}

	@Override
	public T dequeue() {

		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
		T frontE = queueArray[front];
		queueArray[front] = null;
		front = (front + 1) % DEFAULT_CAPACITY;
		size--;
		return frontE;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean enqueue(T e) {
		if (isFull()) {
			throw new QueueOverflowException();
		}
		rear = (rear + 1) % queueArray.length;
		queueArray[rear] = e;
		size++;
		return true;
	}

	@Override
	public String toString() {
		return toString("");
	}

	@Override
	public String toString(String delimiter) {
	    StringBuilder displayQueue = new StringBuilder(); 

	    for (int i = 0; i < size; i++) { // Change to < size
	        displayQueue.append(queueArray[(front + i) % queueArray.length]); 
	        if (i < size - 1) {
	            displayQueue.append(delimiter); 
	        }
	    }

	    return displayQueue.toString(); // 
	}
	
	@Override
	public void fill(ArrayList<T> list) {
		for (int i = 0; i < list.size(); i++) {
			T e = list.get(i);

			if (!enqueue(e)) {
				throw new QueueOverflowException();
			}
		}
	}
}
