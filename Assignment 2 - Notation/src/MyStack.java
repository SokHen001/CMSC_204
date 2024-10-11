import java.util.ArrayList;

/**
 * MyStack will implement the Stack Interface
 * 
 * @param <T>
 */
public class MyStack<T> implements StackInterface<T> {
	private T[] stackArray; // Array of stack entries
	private int topIndex; // Index of top entry
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;

	/**
	 * takes in an int as the size of the stack
	 * 
	 * @param defaultCapacity 
	 */
	public MyStack(int defaultCapacity) {
		@SuppressWarnings("unchecked")
		T[] newStack = (T[]) new Object[defaultCapacity];
		stackArray = newStack;
		topIndex = -1; // Stack is initially empty
	} // end constructor

	/**
	 * uses default as the size of the stack
	 */
	public MyStack() {
		this(DEFAULT_CAPACITY);
	} // end default constructor

	/**
	 * Determines if Stack is empty
	 * 
	 * @return true if Stack is empty, false if not
	 */
	public boolean isEmpty() {
		return topIndex < 0;
	}

	/**
	 * Determines if Stack is full
	 * 
	 * @return true if Stack is full, false if not
	 */
	public boolean isFull() {
		return topIndex >= stackArray.length - 1;
	}

	/**
	 * Deletes and returns the element at the top of the Stack
	 * 
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	public T pop() {
		if (isEmpty()) {
			throw new StackUnderflowException();
		}
		return stackArray[topIndex--];
	}

	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * 
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	public T top() {
		if (isEmpty()) {
			throw new StackUnderflowException();
		}
		return stackArray[topIndex];
	}

	/**
	 * Number of elements in the Stack
	 * 
	 * @return the number of elements in the Stack
	 */
	public int size() {
		return topIndex + 1;
	}

	/**
	 * Adds an element to the top of the Stack
	 * 
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException if stack is full
	 */
	public boolean push(T e) {
		if (isFull()) {
			throw new StackOverflowException();
		}
		stackArray[++topIndex] = e;
		return true;
	}

	/**
	 * Returns the elements of the Stack in a string from bottom to top, the
	 * beginning of the String is the bottom of the stack
	 * 
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString() {
		return toString("");
	}

	/**
	 * Returns the string representation of the elements in the Stack, the beginning
	 * of the string is the bottom of the stack Place the delimiter between all
	 * elements of the Stack
	 * 
	 * @return string representation of the Stack from bottom to top with elements
	 *         separated with the delimiter
	 */
	public String toString(String delimiter) {
		String displayStack = "";

		for (int i = 0; i <= topIndex; i++) {
			displayStack += stackArray[i];
			if (i != topIndex) {
				displayStack += delimiter; // separate each element with comma
			}
		}

		return displayStack;
	}

	/**
	 * Fills the Stack with the elements of the ArrayList, First element in the
	 * ArrayList is the first bottom element of the Stack YOU MUST MAKE A COPY OF
	 * LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the list reference
	 * within your Stack, you will be allowing direct access to the data of your
	 * Stack causing a possible security breech.
	 * 
	 * @param list elements to be added to the Stack from bottom to top
	 * @throws StackOverflowException if stack gets full
	 */
	public void fill(ArrayList<T> list) {
		for(int i = 0; i < list.size(); i++) {
			T e = list.get(i);
			
			if(!push(e)) {
				throw new StackOverflowException();
			}
		}
	}

}
