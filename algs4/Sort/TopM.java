import java.security.cert.PolicyQualifierInfo;

import edu.princeton.cs.algs4.*;

public class TopM
{
	// Print the top M lines in the input stream.
	public static void main(String[] args) {
		int M = Integer.parseInt(args[0]);
		MinPQ<Transaction> pq = new MinPQ<Transaction>(M+1);
		
		// Create an entry frome the next line and put on the PQ
		while(StdIn.hasNextLine()) {
			pq.insert(new Transaction(StdIn.readLine()));
			// Remove minimum if M+1 entries on the PQ.
			if(pq.size()>M)
				pq.delMin(); 
		} // Top M entries are on the PQ.
		
		Stack<Transaction> stack = new Stack<Transaction>();
		while(!pq.isEmpty()) stack.push(pq.delMin());
		for (Transaction t : stack) StdOut.println(t);
	}
}