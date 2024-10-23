import java.util.Comparator;
import java.util.ListIterator;

/**
 * Implements a generic sorted double list using a provided Comparator. 
 * It extends BasicDoubleLinkedList class.
 * 
 * @param <T>
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> implements Iterable<T>{

	private Comparator<T> comparator;
	
	/**
	 * Creates an empty list that is associated with the specified comparator.
	 * 
	 * @param compareableObject - Comparator to compare data elements
	 */
	public SortedDoubleLinkedList(Comparator<T> compareableObject) {
		super();
		this.comparator = compareableObject;
	} // end constructor

	/**
     * Inserts the specified element at the correct position in the sorted list.
     * 
     * @param data - the data to be added to the list

     */
	public void add(T data) {
		Node tempNode = new Node(data);

		// If the list is empty
		if (head == null) {
			head = tempNode;
			tail = tempNode;
		}
		// If the new data should be the new head
		else if (comparator.compare(data, head.data) <= 0) {
			tempNode.next = head;
			head.prev = tempNode;
			head = tempNode;
		}
		// If the new data should be the new tail
		else if (comparator.compare(data, tail.data) >= 0) {
			tempNode.prev = tail;
			tail.next = tempNode;
			tail = tempNode;
		}
		// For data that falls somewhere in the middle
		else {
			Node currentNode = head;

			// Traverse the list to find the correct insertion point
			while (currentNode != null && comparator.compare(data, currentNode.data) > 0) {
				currentNode = currentNode.next;
			}

			// Insert the new node before `current`
			tempNode.prev = currentNode.prev;
			tempNode.next = currentNode;

			// Re-link the previous node
			if (currentNode.prev != null) {
				currentNode.prev.next = tempNode;
			}

			// Link the current node's `prev` to the new node
			currentNode.prev = tempNode;
		}
		size++;
	}

	/**
	 * This operation is invalid for a sorted list.
	 * 
	 * @param data - the data for the Node within the linked list
	 * @throws UnsupportedOperationException - if method is called
	 */
    @Override 
	public void addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	/**
	 * This operation is invalid for a sorted list.
	 * 
	 * @param data - the data for the Node within the linked list
	 * @throws UnsupportedOperationException - if method is called
	 */
	@Override
	public void addToFront(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	/**
	 * Implements the iterator by calling the super class iterator method.
	 * 
	 * @return an iterator for this sorted double linked list
	 */
	@Override
	public ListIterator<T> iterator() {
        return super.iterator(); 
    }
	
	 /**
     * Implements the remove operation by calling the super class remove method.
     * 
     * @param data - the data element to be removed
     * @param comparator - the comparator to determine equality of data elements
     * @return a node containing the data or null
     */
    @Override
    public Node remove(T data, Comparator<T> comparator) {
        return super.remove(data, comparator); 
    }
	
	
}
