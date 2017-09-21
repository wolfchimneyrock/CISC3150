/********************************************************
 * Robert Wagner
 * CISC 3150 HW #4
 * 2017-09-21
 *
 * NQueens.java:
 *   In which queens are placed
 *
 ********************************************************/

import java.util.*;

class NQueens {
    // the values in board are the row# of the queen in each column
    private int[] board;

    private int size;
    // limit = factorial of size
    private long limit;

    public NQueens(int size) {
        this.size  = size;
        this.limit = factorial(size) - 1;
        this.board = new int[this.size];
        this.setTo(0);
    }


    public void setTo(long val) {
        // in order to cleanly iterate through a tractable valid subset of all possible
        // board states, I present a bijective encoding which represents the n! board states
        // that satisfy an "n-Rooks" restraint: no two pieces share the same row / column.
        // this reduces the total state space from 2^(n*n) down to n!.
        // the validation algorithm is still brute-force, but much simpler (only checks
        // forward diagonals) and has to iterate over factorial, not exponential space.
        // by exploiting symmetry, we only consider n! / 2 states.
        // Also, we can multi-thread since the encoding is bijective, taking advantage of
        // multiple cores and hyperthreading.
        if (val < 0 || val > this.limit)
            throw new IndexOutOfBoundsException();

        List<Integer> possibilities = new ArrayList<Integer>(this.size);
        //List<Integer> possibilities = new LinkedList<Integer>();
        for (int i = 0; i < this.size; i++)
            possibilities.add(i);

        int mod = this.size;
        
        for (int col = 0; col < this.size - 1; col++) {
            int rowIndex = (int)(val % mod);
            val /= mod;
            mod--;
            int row = possibilities.remove(rowIndex);
            this.board[col] = row;
        }   
        // the last column has only one possible value since it is fully determined
        // by all the previous columns 
        this.board[this.size - 1] = possibilities.get(0);
    }


    public boolean isValid() {
        // we already know the rows and columns are clear based on the encoding style.
        // all we have to do is check forward diagonals, sweeping from left to right, 
        // and we can skip the last column.

        for (int c = 0; c < this.size - 1; c++) {
            int up      = this.board[c] + 1;
            int down    = this.board[c] - 1;
            int testCol = c + 1;
            while (testCol < this.size && up < this.size)  {
                if(this.board[testCol] == up)
                    return false;
                testCol++;
                up++;
            }
            testCol = c + 1;
            while (testCol < this.size && down >= 0) {
                if (this.board[testCol] == down)
                    return false;
                testCol++;
                down--;
            }
        } 
        return true;  
    }

    public String toString() {
        StringBuilder s = new StringBuilder(this.size * this.size * 2 + this.size + 1);
        for (byte r = 0; r < this.size; r++) {
            for (int c = 0; c < this.size; c++) {
                s.append(' ');
                if (this.board[c] == r)
                    s.append('Q');
                else
                    s.append('.');
            }
            s.append('\n');
        }
        return s.toString();
    }

    public static long factorial(int n) {
        long result = 1;
        for (int i = n; i > 1; i--)
            result *= i;
        return result;
    }


    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int size = 8;
        int numThreads = 8;
         
        if (args.length > 0) {
            try {
                size = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid size specified.");
                System.exit(1);
            }
        }
        if (size < 1) {
            System.out.println("Invalid size specified.");
            System.exit(1);
        }
        if (size < numThreads) 
            numThreads = size;
        
        
        long limit = factorial(size);
        
        System.out.printf("Using %d threads to search %d states.\n", numThreads, limit);

        Thread[] threads = new Thread[numThreads]; 
        QTask[]  qtasks  = new QTask[numThreads];
        long start = 0;
        long upperBound = limit / 2;
        if (size == 1) upperBound = 1;
        long stop = upperBound / numThreads;
        for (int t = 0; t < numThreads; t++) {
            qtasks[t] = new QTask(size, limit - 1, start, stop);
            threads[t] = new Thread(qtasks[t]);
            threads[t].start();
            start = stop; 
            stop = stop + (upperBound / numThreads);
            if (stop > upperBound) stop = upperBound;
        }
        long count = 0;
        for (int t = 0; t < numThreads; t++) {
            try {
                threads[t].join();
            } catch (InterruptedException e) {
                System.out.println("Interrupted. Quitting.");
                System.exit(1);
            }
            count += qtasks[t].getCount();
        }

        System.out.printf("%d solutions found.\n", count);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.printf("Took %dms\n", duration);
    }
}

class QTask implements Runnable {
    long start;
    long limit;
    long stop;
    int size;
    long count;
    QTask(int size, long limit, long start, long stop) {
        this.size = size;
        this.limit = limit;
        this.start = start;
        this.stop = stop;
        this.count = 0;
    }
    @Override
    public void run() {
        NQueens q = new NQueens(this.size);
        for (long i = this.start; i < this.stop; i++) {
            q.setTo(i);
            if (q.isValid()) {
                this.count++;
                // no need to synchronize println()
                System.out.println(q);
                if (i != this.limit - i) {
                    // we can exploit symmetry
                    q.setTo(this.limit - i);
                    System.out.println(q);
                    this.count++;
                }
            }
        }
    }

    public long getCount() {
        return this.count;
    }

}
