package percolation;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
	private int c;
	private Percolation pr;
	private double[] f;
	public PercolationStats(int n, int trials) {
		if(n<=0 || trials<=0) {
			throw new IllegalArgumentException("n<=0 or trails<=0");
		}
		c=trials;
		f=new double[c];
		for(int i=0;i<c;i++) {
			pr=new Percolation(n);
			int open=0;
			while (!pr.percolates()) {
				int x=StdRandom.uniform(1, n+1);
				int y=StdRandom.uniform(1, n+1);
				if(!pr.isOpen(x,y)) {
					pr.open(x, y);
					open++;
				}
			}
			double f1=(double) open/(n*n);
			f[i]=f1;
		}
	}
	   public double mean() {
		   // sample mean of percolation threshold
		   return StdStats.mean(f);
	   }
	   public double stddev() {
		   // sample standard deviation of percolation threshold
		   return StdStats.stddev(f);
	   }
	   public double confidenceLo() {
		   // low  endpoint of 95% confidence interval
		   return mean()-((1.96*stddev())/Math.sqrt(c));
	   }
	   public double confidenceHi() {
		   return mean()+((1.96*stddev())/Math.sqrt(c));
	   }
	   public static void main(String[] args) {
		int n=Integer.parseInt(args[0]);
		int t=Integer.parseInt(args[1]);
		PercolationStats p=new PercolationStats(n, t);
		String cc=p.confidenceLo()+", "+p.confidenceHi();
		StdOut.println("mean                    ="+p.mean());
		StdOut.println("stddiv                  ="+p.stddev());
		StdOut.println("95% confidence interval = "+cc);
	}

}
