import edu.princeton.cs.algs4.*;
import java.util.Iterator;
import java.util.Random;
import java.util.jar.JarException;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] rq;		// Array of Randomized Queue
	private int rq_size=0;	// Size of Randomized Queue
	
	// construct an empty randomized queue
	public RandomizedQueue(){
		rq = (Item[]) new Object[2];
	}
	
	// is the queue empty?
	public boolean isEmpty() {
		return (rq_size==0);
	}
	
	// return the number of items on the queue
	public int size() {
		return rq_size;
	}
	
	private void resize(int capacity) {
		assert capacity >= rq_size; // the resized capacity should be larger than rq_size at any time.
		Item[] temp = (Item[]) new Object[capacity];
		
		for(int i=0; i<rq_size; i++) {
			temp[i]=rq[i];
		}
		
		rq = temp;
	}
	
	// add the item
	public void enqueue(Item item) {
		if(item==null) throw new java.lang.NullPointerException();
		if(rq_size==rq.length) resize(2*rq.length);
		rq[rq_size++]=item;
	}
	
	// remove and return a random item
	public Item dequeue() {
		if(isEmpty()) throw new java.util.NoSuchElementException("Stack underflow");
		Item item=rq[rq_size-1];
		rq[rq_size-1]=null;
		rq_size--;
		// shrink the size of the array if necessary
		if((rq_size>0) && (rq_size == (rq.length/4))) resize(rq.length/2);
		return item;
	}
	
	// return (but do not remove) a random item
	public Item sample() {
		if(isEmpty()) throw new java.util.NoSuchElementException();
		int index = (StdRandom.uniform(rq_size));		
		return rq[index];
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<Item> {
		private Item[] rq_temp = (Item[]) new Object[rq.length];
		private int i = rq_size;
		
		public ArrayIterator() {
			for(int j=0; j<rq.length; j++) {
				rq_temp[j]=rq[j];
			}
		}
		
		public boolean hasNext() { return i>0; }
		
		public void remove()	 { throw new java.lang.UnsupportedOperationException(); }
		
		public Item next() {
			if(!hasNext()) throw new java.util.NoSuchElementException();
			int index=StdRandom.uniform(i);
			Item item=rq[index];
			if(index != i-1) {
				rq_temp[index]=rq_temp[i-1];
			}
			rq_temp[i-1]=null;
			i--;
			return item;
		}
	}
	
	// unit testing
	public static void main(String[] args) {
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		StdOut.println("Randomized Queue Start...");
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if(!item.equals("-")) rq.enqueue(item);
			else if(!rq.isEmpty()) StdOut.println(rq.dequeue());
		}
		StdOut.println("(" + rq.size() + " left on queue)");
	}

}