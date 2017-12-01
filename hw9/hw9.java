/********************************************************
 * Robert Wagner
 * CISC 3150 HW #9
 * 2017-12-01
 *
 * hw9.java:
 *   In which threads are synchronized to print alphabet
 *
 ********************************************************/

import java.util.*;

public class hw9 {

    public static void main(String[] args) {
        int numThreads = 4;
        Thread[] threads = new Thread[numThreads];
        QTask[]  qtasks  = new QTask[numThreads];
        Counter c = new Counter('A');

        qtasks[0] = new QTask("AEIMQUY".toCharArray(), c);
        qtasks[1] = new QTask("BFJNRVZ".toCharArray(), c);
        qtasks[2] = new QTask("CGKOSW".toCharArray(), c);
        qtasks[3] = new QTask("DHLPTX".toCharArray(), c);

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(qtasks[i]);
            threads[i].start();
        }
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Interrupted.");
                System.exit(1);
            }
        }
        System.out.println();
    }
}

class QTask implements Runnable {
    char[] values;
    int position;
    Counter counter;

    QTask(char[] values, Counter c) {
        this.counter = c;
        this.values = values;
        this.position = 0;
    }

    @Override
    public void run() {
        while (this.position < this.values.length) {
            if (this.counter.getNext() >= this.values[this.position]) {
                this.counter.printIncremented(this.values[this.position++]);
            }
        }
    }
}

class Counter {
    char next;
    Counter(char start) {
        this.next = start;
    }

    char getNext() {
        return this.next;
    }

    synchronized void printIncremented(char value) {
        System.out.print(value);
        while (value >= this.next)
            this.next++;
    }
}

