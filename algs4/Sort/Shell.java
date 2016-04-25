import edu.princeton.cs.algs4.*;

public class Shell
{
	// Sort a[] into increasing order
	public static void sort(Comparable[] a) {
		int N = a.length;			// array length
		int h = 1;
		while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
		
//		StdOut.println("h-sort starts with "+h);
		
		while (h >= 1) { // h-sort the array
			for (int i=h; i<N; i++) { // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]...
				
//				StdOut.println(">> i is "+i);
				for(int j=i; j>=h && less(a[j], a[j-h]); j-=h) {
					exch(a, j, j-h);
				}
			}
			h=h/3;
//			StdOut.println("h-sort new with "+h);
		}
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