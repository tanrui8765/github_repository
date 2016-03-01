import javax.lang.model.type.UnionType;

import edu.princeton.cs.algs4.*;

public class Deque<Item> implements Iterable<Item> {
		
		private Item[] dq;		// Array of Deque
		private int dq_size=0;	// Size of Deque
	
		// construct an empty deque
	   	public Deque() {
	   		dq = (Item[]) new Object[2];
	   		dq_size = 0;
	   	}
	   
	   	// is the deque empty?
	   	public boolean isEmpty() {
		   return (dq_size==0);
	   	}
	   	
	   	// return the number of items on the deque
	   	public int size() {
	   		return dq_size;
	   	}
	   	
	   	private void resize(int capacity) {
	   		
	   	}
	   	
        // add the item to the front
	   	public void addFirst(Item item) {
	   		
	   	}

        // add the item to the end
	   	public void addLast(Item item) {
	   		
	   	}
	   	
	   	// remove and return the item from the front
	   	public Item removeFirst() {
	   		
	   	}
	   	
	   	// remove and return the item from the end
	   	public Item removeLast() {
	   		
	   	}
	   	
	   	// return an iterator over items in order from front to end
	   	public Iterator<Item> iterator() {
	   		
	   	}
	   	
	   	// unit testing
	   	public static void main(String[] args) {
	   		
	   	}
	}