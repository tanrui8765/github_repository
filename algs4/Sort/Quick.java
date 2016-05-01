import edu.princeton.cs.algs4.*;

public class Quick
{
	// Partition into  a[lo..j-1], a[j], a[j+1..hi] and return j.
	private static int partition(Comparable[] a, int lo, int hi) {
		int i=lo, j=hi+1;	// left and right scan indices
		Comparable v=a[lo];	// partitioning item
		while (true) {		// Scan right, scan left, check for scan complete and exchange
			while (less(a[++i], v)) if(i == hi) break;
			while (less(v, a[--j])) if(j == lo) break;
			if(i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);	// Put partitioning item v into a[j].
		return j;		// with a[lo..j-1] <= a[j] <= a[j+1..hi]
	}
	
	// Sort a[] into increasing order
	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo) return;
		int j = partition(a, lo, hi); 	// Partition
		sort(a, lo, j-1);				// Sort left part a[lo .. j-1].
		sort(a, j+1, hi);				// Sort right part a[j+1 .. hi].
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