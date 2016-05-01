import edu.princeton.cs.algs4.*;

public class Quick3way
{
	// Sort a[] into increasing order
	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo) return;
		int lt = lo, i = lo+1, gt = hi;
		Comparable v = a[lo];
		while(i <= gt) {
			int cmp = a[i].compareTo(v);
			if		(cmp < 0) 	exch(a, lt++, i++);
			else if	(cmp > 0) 	exch(a, i, gt--);
			else 				i++;
		} // Now a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]
		sort(a, lo, lt-1);
		sort(a, gt+1, hi);
	}
	
	// Judge if v less than w
	private static boolean less(Comparable v, Comparable w)
	{	return v.compareTo(w) < 0;	}
	
	// Exchange two elements
	private static void exch(Comparable[] a, int i, int j)
	{	Comparable t = a[i]; a[i] = a[j]; a[j] = t;	}
	
	private static void show(Comparable[] a) {
		// Print the array on a single line
		for(int i=0; i<a.length; i++)
			StdOut.print(a[i]+" ");
		StdOut.println();
	}
	
	// Test whether the array is sorted
	public static boolean isSorted(Comparable[] a) {
		for(int i=1; i<a.length; i++)
			if(less(a[i], a[i-1])) return false;
		return true;
	}
	
	// Read strings from standard input, sort them and print
	public static void main(String[] args)
	{
		String[] a = StdIn.readAllStrings();
		sort(a);
		assert isSorted(a);
		show(a);
	}
}