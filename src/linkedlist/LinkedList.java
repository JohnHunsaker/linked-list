
package linkedlist;

/**
 * Class to implement a linked list.
 */

public class LinkedList {
	
	/**
	 * Internal class to represent a node.
	 * 
	 */
	
	private class Node {
		private int mData;
		private Node mNext;
		private Node mPrev;
		
		public Node(int data) {
			mData = data;
		}
		
		public int getData() {
			return mData;
		}
		
		public void setNext(Node next) {
			this.mNext = next;
		}
		
		public void setPrev(Node prev) {
			this.mPrev = prev;
		}
		
		public void setData(int data) {
			this.mData = data;
		}
	}
	
	private Node mHead;
	private int mCount;
	
	/**
	 * Method adds the data into a new node at the first position in the list.
	 * @param data 
	 */
	
	public void addFirst(int data) {
		Node newNode = new Node(data);
		if (mHead == null) {
			mHead = newNode;
			mHead.setNext(newNode);
			mHead.setPrev(newNode);
		}
		else {
			newNode.setNext(mHead);
			mHead.mPrev.setNext(newNode);
			newNode.setPrev(mHead.mPrev);
			mHead.setPrev(newNode);
		}
		mHead = newNode;
		mCount++;
	}
	
	/**
	 * Method adds the data into a new node at the last position in the list.
	 * @param data 
	 */
	public void addLast(int data) {
		Node newNode = new Node(data);
		if (mHead == null) {
			mHead = newNode;
			mHead.setNext(mHead);
			mHead.setPrev(mHead);
		}
		else {
			newNode.setNext(mHead);
			newNode.setPrev(mHead.mPrev);
			mHead.mPrev.setNext(newNode);
			mHead.setPrev(newNode);
		}
		mCount++;
	}

	/**
	 * Method adds the data at the specified index in the list.
	 * @param index
	 * @param data 
	 */
	
	public void insert(int index, int data) {
		Node n = mHead;
		Node newNode = new Node(data);
		
		if (index < 0) {
			throw new NullPointerException("Given index is less than 0.");
		}
		
		else if (index > mCount) {
			throw new NullPointerException("Cannot add beyond the count.");
		}
		
		else if (index == 0) {
			addFirst(data);
		}
		
		else if (mHead == null) {
			mHead = newNode;
			mHead.setNext(mHead);
			mHead.setPrev(mHead);
		}

		else {
			for (int i = 0; i < index; i++) {
				n = n.mNext;
			}
			n.mPrev.setNext(newNode);
			newNode.setPrev(n.mPrev);
			n.setPrev(newNode);
			newNode.setNext(n);
			mCount++;
		}
	}
	
	/**
	 * Method removes and returns the first data from the list.
	 * @return 
	 */
	
	public int removeFirst() {
		if (mCount == 0) {
			throw new NullPointerException("Cannot remove from an empty list.");
		}
		Node n = mHead;
		if (mCount == 1) {
			mHead = null;
		}
		
		else {
			mHead.mPrev.setNext(mHead.mNext);
			mHead.mNext.setPrev(mHead.mPrev);
			mHead = mHead.mNext;
		}
		mCount--;
		return n.mData;
	}
	
	/**
	 * Method removes and returns the last data from the list.
	 * @return 
	 */
	
	public int removeLast() {
		if (mCount == 0) {
			throw new NullPointerException("Cannot remove from an empty list.");
		}
		Node n = mHead.mPrev;
		if (mCount == 1) {
			mHead = null;
		}
		else {
			mHead.mPrev.mPrev.setNext(mHead);
			mHead.setPrev(mHead.mPrev.mPrev);
		}
		mCount--;
		return n.mData;
		}

	/**
	 * Method removes and returns the data at the specified index in the list.
	 * @param index
	 * @return 
	 */
	
	public int removeAt(int index) {
		Node n = mHead;
		
		if (n.mNext == null) {
			throw new NullPointerException("Only one node in list.");
		}
		
		else if (index > mCount) {
			throw new NullPointerException("Index larger than list length.");
		}
			
		else if (mCount == 1) {
			mHead = null;
		}
		
		else if (index < 0) {
			throw new NullPointerException("Given index is less than 0.");
		}
		
		else {
			for (int i = 0; i < index; i++) {
				n = n.mNext;
			}
		
			n.mNext.setPrev(n.mPrev);
			n.mPrev.setNext(n.mNext);
		}
		mCount--;
		return n.mData;
	}
	
/**
 * Method finds and removes the first occurrence of the specified data.
 * @param data 
 */
	
	public void remove(int data) {
		Node n = mHead;
		int i = 0;
		
		if (mCount == 0) {
			throw new NullPointerException("Cannot remove from an empty list.");
		}
		
		while (i <= mCount && (n.mData != data)) {
			i++;
			n = n.mNext;
		}
		
		if (i == mCount) {
			throw new NullPointerException("Index larger than list length.");
		}

		else if (i == 0) {
			removeFirst();
		}
		
		else if (i == mCount - 1) {
			removeLast();
		}
		
		else {
			n.mNext.setPrev(n.mPrev);
			n.mPrev.setNext(n.mNext);
			mCount--;
		}
	}
	
	/**
	 * Method returns the integer data at the specified index.
	 * @param index
	 * @return 
	 */
	public int get(int index) {
		Node n = mHead;
		
		if (n.mNext == null) {
			throw new NullPointerException("Only one node in list.");
		}
		
		else if (index >= mCount) {
			throw new NullPointerException("Index larger than list length.");
		}
		
		else {
			for (int i = 0; i < index; i++) {
				n = n.mNext;
			}
		
			return n.mData;
		}
	}
	
	/**
	 * Method clears the list so that it is empty.
	 */
	
	public void clear() {
		mHead = null;
		mCount = 0;
		System.out.println("List has been cleared.");
	}
	
	/**
	 * Method returns the integer index where the specified data first
	 * occurs in the list, or -1 if it is not found.
	 * @param data
	 * @return 
	 */
	
	public int indexOf(int data) {
		int i = 0;
		Node n = mHead;
		
		while ((n.mData != data) && i < mCount) {
			i++;
			n = n.mNext;
		}
		
		if (i == mCount) {
			return -1;
		}
		
		else {
			return i;
		}
	}
	
	public int getCount() {
		return mCount;
	}
	
	 /**
	  * Method prints all the data integers stored in the list on one line,
	  * by walking from one Node to the next and printing each Node's data
	  * until reaching the end of the list.
	  */
	
	public void printAll() {
		Node n = mHead;
		
		for (int i = 0; i < mCount; i++) {
				System.out.print(n.mData + ",  ");
				n = n.mNext;
		}
	}
}