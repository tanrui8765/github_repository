import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
	
	private int side_num=0;
	private int loop_num=0;
	private int sites_num=0;
	private int[] open_count;
	
	// perform T independent experiments on an N-by-N grid
	public PercolationStats(int N, int T) {
		
		side_num = N;
		sites_num = side_num*side_num;
		loop_num = T;
		open_count=new int[loop_num];
	
		for(int i=0;i<loop_num;i++) {
			Percolation pl = new Percolation(side_num);
			open_count[i]=0;
			
			do {
				int row = StdRandom.uniform(1,side_num+1);
				int col = StdRandom.uniform(1,side_num+1);
				
				if(pl.isOpen(row, col)!=true) {
					pl.open(row, col);
					open_count[i]++;
				}
				
			} while(pl.percolates()!=true);			
		}
	}

	// sample mean of percolation threshold
	public double mean() {
		double[] thresh_array = new double[loop_num];
		
		for(int i=0;i<loop_num;i++) {
			thresh_array[i]=((double)open_count[i]/(double)sites_num);
		}

		return (StdStats.mean(thresh_array));
	}
	
	// sample standard deviation of percolation threshold
	public double stddev() {
		double[] thresh_array = new double[loop_num];
		
		for(int i=0;i<loop_num;i++) {
			thresh_array[i]=((double)open_count[i]/(double)sites_num);
		}

		return (StdStats.stddev(thresh_array));
	}
	
	// low  endpoint of 95% confidence interval
	public double confidenceLo() {
		double mean_temp=mean();
		double stddev_temp=stddev();
		
		return (mean_temp-1.96*stddev_temp/Math.sqrt(loop_num));
	}
	
	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		double mean_temp=mean();
		double stddev_temp=stddev();
		
		return (mean_temp+1.96*stddev_temp/Math.sqrt(loop_num));	
	}
	
	// test client (described below)
	public static void main(String[] args) {
		int N=0;
		int T=0;
		StdOut.println("Enter Main, PercolateStats start...");
		N=StdIn.readInt();
		T=StdIn.readInt();
		StdOut.println("Params Received, N is "+N+", T is "+T+".");
		PercolationStats pl_stats = new PercolationStats(N,T);
		
		StdOut.println("% java PercolationStats "+N+" "+T);
		StdOut.println("mean                    = "+ pl_stats.mean());
		StdOut.println("stddev                  = "+ pl_stats.stddev());
		StdOut.println("95% confidence interval = "+ pl_stats.confidenceLo()+" "+pl_stats.confidenceHi());

	}
}