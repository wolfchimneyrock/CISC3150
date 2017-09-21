import java.util.*;

class NQueens {
    
    private boolean[][] board;
    private int size;
    private long limit;

    public NQueens(int size) {
        this.size  = size;
        this.limit = factorial(size) - 1;
        this.setTo(0);
    }

    public void clear() {
        if (this.board == null) 
            this.board = new boolean[this.size][this.size];
        for (int r = 0; r < this.size; r++)
            for (int c = 0; c < this.size; c++)
                this.board[r][c] = false;
    }

    public void setTo(long val) {
        if (val < 0 || val > this.limit)
            throw new IndexOutOfBoundsException();

        List<Integer> possibilities = new ArrayList<Integer>(this.size);
        //List<Integer> possibilities = new LinkedList<Integer>();
        for (int i = 0; i < this.size; i++)
            possibilities.add(i);

        //this.board = new boolean[this.size][this.size];
        this.clear();
        long mod = this.size;
        
        for (int col = 0; col < this.size - 1; col++) {
            int rowIndex = (int)(val % mod);
            val = (val - rowIndex) / mod;
            mod--;
            int row = possibilities.remove(rowIndex);
            this.board[row][col] = true;
        }   
        // the last column has only one possible value since it is fully determined
        // by all the previous columns 
        this.board[possibilities.get(0)][this.size - 1] = true;
    }

    public int rowOfCol(int col) {
        for (int row = 0; row < this.size; row++)
            if (this.board[row][col])
                return row;
        return -1;
    }

    public boolean isValid() {
        // we already know the rows and columns are clear based on the encoding style.
        // all we have to do is check diagonals, sweeping from left to right

        for (int c = 0; c < this.size - 1; c++) {
            int up = this.rowOfCol(c) + 1;
            int down = this.rowOfCol(c) - 1;
            int testCol = c + 1;
            while (testCol < this.size && up < this.size) {
                if(this.board[up][testCol])
                    return false;
                testCol++;
                up++;
            }
            testCol = c + 1;
            while (testCol < this.size && down >= 0) {
                if (this.board[down][testCol])
                    return false;
                testCol++;
                down--;
            }
        } 
        return true;  
    }

    public String toString() {
        StringBuilder s = new StringBuilder(this.size * this.size * 2 + this.size + 1);
        for (int r = 0; r < this.size; r++) {
            for (int c = 0; c < this.size; c++) {
                s.append(' ');
                if (this.board[r][c])
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
        NQueens q = new NQueens(size);
        long limit = factorial(size);
        int count = 0;
        for (int i = 0; i < limit; i++) {
            q.setTo(i);
            if (q.isValid()) {
                count++;
                System.out.printf("Board state %d is a solution:\n", i);
                System.out.println(q);
            }
        }
        System.out.printf("%d solutions found.\n", count);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.printf("Took %dms\n", duration);
    }
}


