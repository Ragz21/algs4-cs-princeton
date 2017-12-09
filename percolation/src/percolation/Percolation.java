package percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
	private WeightedQuickUnionUF uf;
	private boolean[][] box;
	private int top = 0;
	private int bottom;
	private int size;
	private int opensites = 0;
	public Percolation(int n) {
		if (n <= 0){
			throw new java.lang.IllegalArgumentException();
		} else {
		uf = new WeightedQuickUnionUF(n*n+2);
		size = n;
		box = new boolean[n][n];
		bottom = size*size+1;
		}
	}
	public void open(int row, int col) {
		if(row > size || col > size || row <=0 || col <= 0){
			throw new java.lang.IllegalArgumentException();
		} else {
		opensites++;
		box[row-1][col-1] = true;
		if (row == 1) {
		   uf.union(helper(row, col), top);
		}
		if (row == size) {
			uf.union(helper(row, col), bottom);
		}
		if (row > 1 && isOpen(row-1, col)) {
		   uf.union(helper(row, col), helper(row-1, col));
		}
		if (row < size && isOpen(row+1, col)) {
		   uf.union(helper(row, col), helper(row+1, col));
		}
		if (col > 1 && isOpen(row, col-1)) {
		   uf.union(helper(row, col), helper(row, col-1));
		}
		if (col < size && isOpen(row, col+1)) {
		   uf.union(helper(row, col), helper(row, col+1));
		}
		}
	}
	public boolean isOpen(int row, int col) {
		if(row > size || col > size || row <=0 || col <= 0){
			throw new java.lang.IllegalArgumentException();
		} else {
	   return box[row-1][col-1];
		}
	}
	public boolean isFull(int row, int col) {
	   if (row > 0 && row <= size && col >0 && col <= size) {
	   return uf.connected(top , helper(row,col));
	   } else { 
		   throw new java.lang.IllegalArgumentException();
	   }	   
	}
	public int numberOfOpenSites() {
		   // number of open sites
		   return opensites;
	   }
	public boolean percolates() {
		return uf.connected(top, bottom);
	}
	private int helper(int x, int y) {
		return size*(x-1)+(y);
	}

}
