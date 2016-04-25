import edu.princeton.cs.algs4.*;

public class Mergesort {
	// Merge a[lo..mid] with a[mid+1..hi]
	
	public static char[] array = {'M','E','R','G','E','S','O','R','T','E','X','A','M','P','L','E'};
	private static char[] aux;
	
	private static boolean less(char elem1, char elem2) {
		return (elem1 < elem2);
	}
	
	private static void sort(char[] a, int lo, int hi) {
		if(hi <= lo) return; // When this happens, start to sort backward.
		int mid = lo +(hi-lo)/2;
		sort(a,lo,mid); 	// Sort left half
		sort(a,mid+1,hi);	// Sort Right half
//		StdOut.println("merge lo:"+lo+" mid:"+mid+" hi:"+hi); // trace debug
		merge_inplace(a, lo, mid, hi);	// Merge results.
	}
	
	// Top-down merge sort
	public static void sort(char[] a) {
		aux = new char[a.length]; // allocate the space
		sort(a,0,a.length-1);
	}
	
	// Bottom-up merge sort
	public static void sort_bu(char[] a) {
		// do lgN passes of pairwise merges
		int N=a.length;
		aux = new char[N];
		
		for(int sz=1; sz<N; sz=sz+sz) {			// sz: subarray size
			for(int lo=0;lo<N-sz;lo+=sz+sz) {	// lo: subarray index
				StdOut.println("merge lo:"+lo+" mid:"+(lo+sz-1)+" hi:"+(Math.min(lo+sz+sz-1,N-1))); // trace debug
				merge_inplace(a, lo, lo+sz-1, Math.min(lo+sz+sz-1,N-1));
			}
		}
	}
	
	public static void merge_inplace(char[] a, int lo, int mid, int hi) {
		int i = lo, j = mid+1;
//		aux = new char[hi+1];
		
		for(int k = lo; k <= hi; k++) { // Copy a[lo..hi] to aux[lo..hi]
			aux[k] = a[k];
		}
		
		for(int k = lo; k <= hi; k++) { // Merge back to a[lo..hi]
			if(i > mid)						a[k] = aux[j++]; // is left half over
			else if(j > hi)					a[k] = aux[i++]; // is right half over
			else if(less(aux[j],aux[i]))	a[k] = aux[j++]; // right is smaller
			else							a[k] = aux[i++]; // left is smaller or equal
		
			/*// Info Display
			StdOut.println("Round "+(k+1)+"   i:"+i+", j:"+j);
			StdOut.print("aux:");
			for(int m=0;m<=hi;m++) {
				StdOut.print(aux[m]+",");
			}
			StdOut.println();
			StdOut.print("dst:");
			for(int m=0;m<=hi;m++) {
				StdOut.print(a[m]+",");
			}
			StdOut.println();StdOut.println();
			// End of Info Display*/
		}
	}
	
	
	public static void main(String[] args) {
//		Mergesort ms=new Mergesort();
		int array_lo=0;
		int array_hi=array.length-1;
		int array_mid=(array_hi-array_lo)/2+array_lo;
		
		StdOut.println("lo:"+array_lo+", mid:"+array_mid+", hi:"+array_hi);
//		ms.merge_inplace(ms.array, array_lo, array_mid, array_hi);
		sort_bu(array);
		
		for(int i=array_lo; i<=array_hi; i++) {
			StdOut.print(array[i]+",");
		}
		StdOut.println();
	}
}