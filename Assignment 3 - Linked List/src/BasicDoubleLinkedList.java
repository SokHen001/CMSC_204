import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This generic double-linked list that implements the Iterable interface and
 * relies on a head (reference to first element of the list) and tail (reference
 * to the last element of the list). Both are set to null when the list is
 * empty. Both point to the same element when there is only one element in the
 * list. The class must only define the following entities: 
 * 
 * 1) an inner class Node, The inner Node class has only three fields: data, the prev and next
 * 		references.
 * 2) an inner class named DoubleLinkedListIterator that implements ListIterator
 * 		(for the iterator method), This inner class implements only the hasNext(),
 * 		next(), hasPrevious() and previous() methods of ListIterator , all other
 * 		methods can throw the UnsupportedOperationException:
 * 
 * All the entities are defined as protected so they can be accessed by the
 * subclasses.
 * 
 * @author Sokha Heng 
 * Project 3: Linked List
 * Due Date: 10/22/2024
 */
public class BasicDoubleLinkedList<T> extends Object implements Iterator<T> {

	protected class Node extends Object {
		protected T data;
		protected Node prev;
		protected Node next;

		/**
		 * constructor
		 * 
		 * @param dataNode - data of the node
		 */
		public Node(T dataNode) {
			this.data = dataNode;
			this.prev = null;
			this.next = null;
		}// end constructor

	}

	protected Node head;
	protected Node tail;
	protected int size;

	/**
	 * Constructor to set to initialize head, tail and size to null, null and 0
	 * 
	 */
	public BasicDoubleLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}// end constructor

	/**
	 * Adds an element to the end of the list and updated the size of the list.
	 * 
	 * @param data - the data to be added to the list
	 */
	public void addToEnd(T data) {
		Node tempNode = new Node(data);
		if (tail == null) {
			head = tempNode;
			tail = tempNode;
		} else {
			tail.next = tempNode;
			tempNode.prev = tail;
			tail = tempNode;
		}
		size++;
	}

	/**
	 * Adds element to the front of the list and updated the size of the list.
	 * 
	 * @param data - the data to be added to the list
	 */
	public void addToFront(T data) {
		Node tempNode = new Node(data);
		if (head == null) {
			head = tempNode;
			tail = tempNode;
		} else {
			tempNode.next = head;
			head.prev = tempNode;
			head = tempNode;
		}
		size++;
	}

	/**
	 * Returns but does not remove the first element from the list. If there are no
	 * elements the method returns null.
	 * 
	 * @return the data element or null
	 */
	public T getFirst() {
		if (head == null) {
			return null;
		}
		return head.data;

	}

	/**
	 * Returns but does not remove the last element from the list. If there are no
	 * elements the method returns null.
	 * 
	 * @return the data element or null
	 */
	public T getLast() {
		if (tail == null) {
			return null;
		}
		return tail.data;
	}

	/**
	 * Returns the number of nodes in the list.
	 * 
	 * @return the size of the linked list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * This method returns an object of the DoubleLinkedListIterator.
	 * 
	 * @return a ListIterator object
	 */
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator();
	}

	/**
	 * Removes the first instance of the targetData from the list.
	 * 
	 * @param data       - the data element to be removed
	 * @param comparator - the comparator to determine equality of data elements
	 * @return the node containing the targetData or null
	 */
	@SuppressWarnings("rawtypes")
	public BasicDoubleLinkedList.Node remove(T targetData, Comparator<T> comparator) {
		Node currentNode = head;
		while (currentNode != null) {
			if (comparator.compare(currentNode.data, targetData) == 0) {
				if (currentNode.prev != null) {
					currentNode.prev.next = currentNode.next;
				} else {
					head = currentNode.next;
				}
				if (currentNode.next != null) {
					currentNode.next.prev = currentNode.prev;
				} else {
					tail = currentNode.prev;
				}
				size--;
				return currentNode;
			}
			currentNode = currentNode.next;
		}
		return null;
	}

	/**
	 * Removes and returns the first element from the list.
	 * 
	 * @return data element or null
	 */
	public T retrieveFirstElement() {
		if (head == null)
			return null;
		T data = head.data;
		head = head.next;
		if (head != null) {
			head.prev = null;
		} else {
			tail = null;
		}
		size--;
		return data;
	}

	/**
	 * Removes and returns the last element from the list.
	 * 
	 * @return data element or null
	 */
	public T retrieveLastElement() {
		if (tail == null)
			return null;
		T data = tail.data;
		tail = tail.prev;
		if (tail != null) {
			tail.next = null;
		} else {
			head = null;
		}
		size--;
		return data;
	}

	/**
	 * Returns an arraylist of all the items in the Double Linked list
	 * 
	 * @return an arraylist of the items in the list
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> arrayList = new ArrayList<>();
		Node currentNode = head;
		while (currentNode != null) {
			arrayList.add(currentNode.data);
			currentNode = currentNode.next;
		}
		return arrayList;
	}

	/**
	 * A generic inner class named DoubleLinkedListIterator that implements java’s
	 * ListIterator interface (for the iterator method).
	 */
	public class DoubleLinkedListIterator extends Object implements ListIterator<T> {
		
		private Node currentNode; // Pointer to the current node

		/**
		 * Constructor to initialize the current pointer to the head of the
		 * BasicDoubleLinkedList.
		 */
		public DoubleLinkedListIterator() {
			this.currentNode = head;
		} // end constructor

	    @Override
		public void add(T arg0) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
	    
		@Override
		public boolean hasNext() {
			return currentNode != null;
		}

		@Override
		public boolean hasPrevious() {
			return currentNode != null && currentNode.prev != null;
		}
		
		@Override
		public T next() throws NoSuchElementException {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			currentNode = currentNode.next;
			return currentNode.data;
		}

		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public T previous() throws NoSuchElementException {
			if(!hasPrevious()) {
				throw new NoSuchElementException();
			}
			currentNode = currentNode.prev;
			return currentNode.data;
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T arg0) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
