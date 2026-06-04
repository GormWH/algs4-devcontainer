import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IllegalArgumentException;

public class Percolation {
    private final int _n;
    private final int _nn;
    private boolean[] _isSiteOpen;
    private int[] _sites;
    private int[] _sz;
    private int openCount;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();

        // initialize properties
        _n = n;
        _nn = n*n;
        _isSiteOpen = new boolean[_nn]; // all values are false(closed) by default
        _sites = new int[n*n];
        for (int i = 0; i < n*n; i++) {
            _sites[i] = i;
            _sz[i] = 1;
        }
        openCount = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        isValidRowCol(row, col);

        int i = (row-1) * _n + (col-1);
        _isSiteOpen[i] = true;
        openCount = openCount + 1;

        // TODO: connect with neighboring sites if it's open
        if (i % _n != 0) { // not left edge
            connect
        }

        

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        isValidRowCol(row, col);
        return _isSiteOpen[row-1 * _n + col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        isValidRowCol(row, col);
        int i = (row - 1) * _n + (col - 1);
        while (!(_sites[i] == i || i == -1 || i == _nn)) {
            i = _sites[i];
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    private boolean isValidRowCol(int row, int col) throws IllegalArgumentException {
        if (row < 1 || row > _n || col < 1 || col > _n) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    private void connect(int row1, int col1, int row2, int col2) {

    }

    private boolean isConnected(int row1, int col1, int row2, int col2) {
        
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
