package percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
	WeightedQuickUnionUF uf;
	private boolean[][] box;
	private int top=0;
	private int bottom;
	private int size;
	private int opensites=0;
	   public Percolation(int n) {
		   uf=new WeightedQuickUnionUF(n*n+2);
		   size=n;
		   box=new boolean[n][n];
		   bottom=size*size+1;
	   }
	   public void open(int row, int col) {
		   box[row-1][col-1]=true;
		   if(row==1) {
			   uf.union(col, top);
		   }
		   if(row==size) {
			   uf.union(rowmajor(row, col), bottom);
		   }
		   if(row>1 && isOpen(row-1, col)) {
			   uf.union(rowmajor(row, col), rowmajor(row-1, col));
		   }
		   if(row<size && isOpen(row+1, col)) {
			   uf.union(rowmajor(row, col), rowmajor(row+1, col));
		   }
		   if(col>1 && isOpen(row, col-1)) {
			   uf.union(rowmajor(row, col), rowmajor(row, col-1));
		   }
		   if(col<size && isOpen(row, col+1)) {
			   uf.union(rowmajor(row, col), rowmajor(row, col+1));
		   }
	   }
	   public boolean isOpen(int row, int col) {
		   return box[row-1][col-1];
	   }
	   public boolean isFull(int row, int col) {
		   if(row>0 && row<size && col<0 && col>size) {
		   return uf.connected(top,rowmajor(row,col) );
		   }else {
			   throw new IndexOutOfBoundsException();
		   }
			   
	   }
	   public int numberOfOpenSites() {
		   // number of open sites
		   for(int i=0;i<size;i++) {
			   for(int j=0;j<size;j++) {
				   if(box[i][j]==true) {
					   opensites++;
				   }
			   }
		   }
		   return opensites;
	   }
	   public boolean percolates() {
		   
		   return uf.connected(top, bottom);
	   }
	   private int rowmajor(int x, int y) {
		   return size*(x-1)+y;
	   }

}
