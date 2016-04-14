import edu.princeton.cs.algs4.*;
import java.util.Iterator;

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
   	
   	private void resize(int capacity, boolean is_dir_front) {
   		assert capacity >= dq_size;	// if true then go on
   		Item[] temp = (Item[]) new Object[capacity];
   		
   		// Check the resize stack direction
   		if(is_dir_front==true) {
	   		for (int i=0; i<dq_size; i++){
	   			temp[i]=dq[i];
	   		}
   		}
   		else {
	   		for (int i=(capacity-1); i>(capacity-1-dq_size); i++) {
	   			temp[i]=dq[i];
	   		}
   		}

   		dq = temp;
   	}
   	
    // add the item to the front
   	public void addFirst(Item item) {
   		if(item == null) throw new java.lang.NullPointerException();
   		if(dq_size==dq.length)resize(2*dq.length, true);
   		dq[dq_size++]=item;
   	}

    // add the item to the end
   	public void addLast(Item item) {
   		if(item == null) throw new java.lang.NullPointerException();
   		if(dq_size==dq.length)resize(2*dq.length, false);
   		dq[dq.length-1-dq_size]=item;
   	}
   	
   	// remove and return the item from the front
   	public Item removeFirst() {
   		if(isEmpty()) throw new java.util.NoSuchElementException("Stack underflow");
   		Item item=dq[dq_size-1];
   		dq[dq_size-1]=null; 	// to avoid loitering
   		dq_size--;
   		// shrink the size of the array if necessary
   		if(dq_size>0 && dq_size == dq.length/4) resize(dq.length/2,true);
   		return item;
   	}
   	
   	// remove and return the item from the end
   	public Item removeLast() {
   		if(isEmpty()) throw new java.util.NoSuchElementException("Stack underflow");
   		Item item=dq[dq.length-1-dq_size];
   		dq[dq.length-1-dq_size]=null; 	// to avoid loitering
   		dq_size--;
   		// shrink the size of the array if necessary
   		if(dq_size>0 && dq_size == dq.length/4) resize(dq.length/2,false);
   		return item;
   	}
   	
   	// return an iterator over items in order from front to end
   	public java.util.Iterator<Item> iterator() {
   		return new ArrayIterator();
   	}
   	
   	private class ArrayIterator implements Iterator<Item> {
   		private int i = dq_size;
   		public boolean hasNext() {
   			return i > 0;	
   		}
   		
   		public Item next() {
   			if (!hasNext()) throw new java.util.NoSuchElementException();
   			return dq[--i];
   		}
   		
   		public void remove() {
   			throw new java.lang.UnsupportedOperationException();
   		}
   	}
   	
   	// unit testing
   	public static void main(String[] args) {
   		Deque<String> deque = new Deque<String>();
   		StdOut.println("Deque Program Starts...");
   		
   		while(!StdIn.isEmpty()) {
   			String s = StdIn.readString();
   			if(!s.equals("-")) {
   				StdOut.println("1->deque.size()=" +deque.size());
   				deque.addFirst(s);
   				StdOut.println("2->deque.size()=" +deque.size());
   			}
   			else if(!deque.isEmpty()) {
   				StdOut.println(deque.removeFirst() + " ");
   				StdOut.println("3->deque.size()=" +deque.size());
   			}
   		}
   		StdOut.println("(" + deque.size() +" left on the deque");
   	}
}