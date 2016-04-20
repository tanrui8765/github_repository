import edu.princeton.cs.algs4.*;

public class Mergesort {
	// Merge a[lo..mid] with a[mid+1..hi]
	
	public char[] array = {'E','E','G','M','R','A','C','E','R','T'};
	private char[] aux;
	
	private static boolean less(char elem1, char elem2) {
		return (elem1 < elem2);
	}
	
	public void merge_inplace(char[] a, int lo, int mid, int hi) {
		int i = lo, j = mid+1;
		aux = new char[hi+1];
		
		for(int k = lo; k <= hi; k++) { // Copy a[lo..hi] to aux[lo..hi]
			aux[k] = a[k];
		}
		
		for(int k = lo; k <= hi; k++) { // Merge back to a[lo..hi]
			if(i > mid)						a[k] = aux[j++]; // is left half over
			else if(j > hi)					a[k] = aux[i++]; // is right half over
			else if(less(aux[j],aux[i]))	a[k] = aux[j++]; // right is smaller
			else							a[k] = aux[i++]; // left is smaller or equal
		
			// Info Display
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
			// End of Info Display
		}
	}
	
	
	public static void main(String[] args) {
		Mergesort ms=new Mergesort();
		int array_lo=0;
		int array_mid=4;
		int array_hi=9;
		
		StdOut.println("lo:"+array_lo+", mid:"+array_mid+", hi:"+array_hi);
		ms.merge_inplace(ms.array, array_lo, array_mid, array_hi);
		
		for(int i=0; i<10; i++) {
			StdOut.print(ms.array[i]+",");
		}
		StdOut.println();
	}
}