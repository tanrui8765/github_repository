import edu.princeton.cs.algs4.*;

public class Percolation {
	
	private int[] parent;		// parent[i] = parent of i
	private int[] size;			// size[i] = number of sites in subtree rooted at i
	private boolean[] state;	// state[i] = the state of i site, true is open; false is blocked
	private int count;			// number of components
	
	private int virtual_top_site = 0;		// all sites opened in the first row should link to this row;
	private int virtual_bottom_site = 0;	// all sites opened in the last(N) row should link to this row;

	private int row_num=0;	// row number
	private int col_num=0;	// column number
	
    // create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		count = N*N+2;			// total number of grid sites is N*N
		row_num=col_num = N;	// set row and col variable
		parent = new int[count];// create a two dimensional site array
		size = new int[count];	// create a two dimensional site array for site size.
		state = new boolean[count];	// create a two dimensional boolean array for state of open and block state.
		virtual_top_site = 0;
		virtual_bottom_site = count-1;
		
		// Initialize two arrays.
		for(int i=0; i<count; i++) {
			parent[i]=i;	// from 1 to N*N
			size[i]=1;		// all set to 1
			state[i]=false;	// all set to blocked
		}
		
		state[virtual_top_site]=state[virtual_bottom_site]=true;	// two virtual site should set to open.
	}
	
	private void validate(int row, int col) {
		if((row < 1) || (row > row_num)) {
			throw new IndexOutOfBoundsException("row index " + row + " is not between 1 and " + row_num);
		}
		else if((col < 1) || (col > col_num)) {
			throw new IndexOutOfBoundsException("column index " + col + " is not between 1 and " + col_num);
		}
	}
	
	private boolean connected(int pt1_row, int pt1_col, int pt2_row, int pt2_col) {
		return find(pt1_row,pt1_col) == find(pt2_row,pt2_col);
	}
	
	private int find(int row, int col) {
		validate(row,col);
		
		int index = (col-1)+(row-1)*col_num+1;
		while(index != parent[index])
			index = parent[index];
		return index;
	}
	
	private void union(int pt1_row, int pt1_col, int pt2_row, int pt2_col) {
		int rootP = find(pt1_row,pt1_col);
		int rootQ = find(pt2_row,pt2_col);
		if(rootP == rootQ) return;
		
		// if two sites are all virtual sites, then force the bottom site connect to the top site
		// However this may cause the back wash problem !!!
		if(((rootP == virtual_top_site) && (rootQ == virtual_bottom_site)) 
		|| ((rootQ == virtual_top_site) && (rootP == virtual_bottom_site))) {
			parent[virtual_bottom_site] = virtual_top_site;
			size[virtual_top_site] += size[virtual_bottom_site];
			count--;
		} 
		// if one of the site is virtual site then force the non-virtual point to the virtual
		else if((rootP == virtual_top_site) || (rootP == virtual_bottom_site)) {
			union_virtual(pt2_row, pt2_col, rootP);
		}
		else if((rootQ == virtual_top_site) || (rootQ == virtual_bottom_site)) {
			union_virtual(pt1_row, pt1_col, rootQ);
		}
		// if two sites are ordinary ones, then apply the weight union find criteria
		else {
			// make smaller root point to larger one
			if(size[rootP] < size[rootQ]) {
				parent[rootP] = rootQ;
				size[rootQ] += size[rootP];
			}
			else {
				parent[rootQ] = rootP;
				size[rootP] += size[rootQ];
			}
			count--;
		}
	}
	
	private void union_virtual(int pt1_row, int pt1_col, int virtual_site) {
		int root = find(pt1_row,pt1_col);	// this index is the root of the point, the goal is to change the parent of this root.
//		int index = (pt1_col-1)+(pt1_row-1)*col_num+1;
		
		if((virtual_site == virtual_top_site)) {
			parent[root] = virtual_top_site;
			size[virtual_site] += size[root];
			count--;
		}
		else if(virtual_site == virtual_bottom_site) {
			parent[root] = virtual_bottom_site;
			size[virtual_site] += size[root];
			count--;
		}
		else {
			throw new IndexOutOfBoundsException("virtual site " + virtual_site + " invalid.");
		}
	}
	
	// open site (row i, column j) if it is not open already
	public void open(int i, int j) {
		int row = i;
		int col = j;
		// 1. make sure i and j are valid
		validate(row,col);
		// 2. calculate the index of element in the array
		int index = (col-1)+(row-1)*col_num+1;
		// 3. toggle the tile open
		if(state[index]!=true) {
			state[index]=true;
			// 3.1 see if the neighbor site is open or not
			// Points with 4 neighbors;
			if(((row>=2)&&(row<=(row_num-1))) && ((col>=2)&&(col<=(col_num-1)))) {
				if(isOpen(row-1,col))union(row-1,col,row,col);	// Upper Point Check
				if(isOpen(row+1,col))union(row+1,col,row,col);	// Lower Point Check
				if(isOpen(row,col-1))union(row,col-1,row,col);	// Left Point Check
				if(isOpen(row,col+1))union(row,col+1,row,col);	// Right Point Check
			}
			
			// connect the top and bottom site to its virtual site
			if(row==1) {
				union_virtual(row,col,virtual_top_site);
			}
			else if(row==row_num) {
				union_virtual(row,col,virtual_bottom_site);
			}
			// Points with 3 neighbors;
			if((row==1)&&((col>=2)&&(col<=(col_num-1)))) {
				if(isOpen(row+1,col))union(row+1,col,row,col);	// Lower Point Check
				if(isOpen(row,col-1))union(row,col-1,row,col);	// Left Point Check
				if(isOpen(row,col+1))union(row,col+1,row,col);	// Right Point Check
			}
			else if((row==row_num)&&((col>=2)&&(col<=(col_num-1)))) {
				if(isOpen(row-1,col))union(row-1,col,row,col);	// Upper Point Check
				if(isOpen(row,col-1))union(row,col-1,row,col);	// Left Point Check
				if(isOpen(row,col+1))union(row,col+1,row,col);	// Right Point Check
			}
			else if(((row>=2)&&(row<=(row_num-1)))&&(col==1)) {
				if(isOpen(row-1,col))union(row-1,col,row,col);	// Upper Point Check
				if(isOpen(row+1,col))union(row+1,col,row,col);	// Lower Point Check
				if(isOpen(row,col+1))union(row,col+1,row,col);	// Right Point Check
			}
			else if(((row>=2)&&(row<=(row_num-1)))&&(col==col_num)) {
				if(isOpen(row-1,col))union(row-1,col,row,col);	// Upper Point Check
				if(isOpen(row+1,col))union(row+1,col,row,col);	// Lower Point Check
				if(isOpen(row,col-1))union(row,col-1,row,col);	// Left Point Check
			}
			// Points with 2 neighbors;
			else if((row==1)&&(col==1)) {
				if(isOpen(row+1,col))union(row+1,col,row,col);	// Lower Point Check
				if(isOpen(row,col+1))union(row,col+1,row,col);	// Right Point Check
			}
			else if((row==row_num)&&(col==col_num)) {
				if(isOpen(row-1,col))union(row-1,col,row,col);	// Upper Point Check
				if(isOpen(row,col-1))union(row,col-1,row,col);	// Left Point Check
			}
			else if((row==1)&&(col==col_num)) {
				if(isOpen(row,col-1))union(row,col-1,row,col);	// Left Point Check
				if(isOpen(row+1,col))union(row+1,col,row,col);	// Lower Point Check
			}
			else if((row==row_num)&&(col==1)) {
				if(isOpen(row-1,col))union(row-1,col,row,col);	// Upper Point Check
				if(isOpen(row,col+1))union(row,col+1,row,col);	// Right Point Check
			}
		}
	}
	
	 // is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		int row = i;
		int col = j;
		
		validate(row,col);	// make sure i and j are valid
		int index = (col-1)+(row-1)*col_num+1;

		return state[index];
	}
	
	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		int row = i;
		int col = j;
		validate(row, col);	// make sure i and j are valid

		int root = find(row, col);
		
		return (root == virtual_top_site);
	}
	
	 // does the system percolate?
	public boolean percolates() {
		return (parent[virtual_bottom_site] == virtual_top_site);
	}

	// test client (optional)
	public static void main(String[] args) {
		// 1. Enter Program
		StdOut.println("Enter Main, process start...");
		StdOut.println("Please input array side num N: ");
		int N = StdIn.readInt();
		Percolation pl = new Percolation(N);
		
		// 2. 
		StdOut.println("Created "+N+" Percolate Sites");
		
		
	}
}
