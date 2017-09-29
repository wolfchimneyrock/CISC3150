/********************************************************
 * Robert Wagner
 * CISC 3150 HW #5
 * 2017-09-29
 *
 * MCPi.java:
 *   In which darts are thrown to determine pi
 *
 ********************************************************/

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
class MCPi {
    private long accepted;

    public MCPi() {
        this.accepted = 0;
    }

	public void toss() {
        double x = ThreadLocalRandom.current().nextDouble();
        double y = ThreadLocalRandom.current().nextDouble();

        // no need to sqrt() when using unit circle since sqrt(1) == 1
        double value = x*x + y*y;
        if (value <= 1)
            this.accepted++;
	}

    // we want to postpone doing any precision killing operations
    // like division as much as possible so this is just a cast
    public double ratio() {
        return (double)this.accepted;
    }

    public static double sumOf(double[] values) {
        return Arrays.stream(values).sum();
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        long size = 4000000000L;
        int numThreads = Runtime.getRuntime().availableProcessors();
         
        if (args.length > 0) {
            try {
                size = Long.parseLong(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid count specified.");
                System.exit(1);
            }
        }
        if (size < 1) {
            System.out.println("Invalid count specified.");
            System.exit(1);
        }

        long limit = size / numThreads;

        // if there happened to be a truncation error
        size = limit * numThreads; 

        System.out.printf("Using %d threads to approximate pi with %d trials.\n", numThreads, size);

        Thread[] threads = new Thread[numThreads]; 
        PiTask[] ptasks  = new PiTask[numThreads];
        double[] results = new double[numThreads];
        for (int t = 0; t < numThreads; t++) {
            ptasks[t] = new PiTask(limit);
            threads[t] = new Thread(ptasks[t]);
            threads[t].start();
        }
        for (int t = 0; t < numThreads; t++) {
            try {
                threads[t].join();
            } catch (InterruptedException e) {
                System.out.println("Interrupted. Quitting.");
                System.exit(1);
            }
            results[t] = ptasks[t].getRatio();
        }

        double divisor = size / 4;
        double result = sumOf(results) / divisor;
        System.out.printf("PI    is approx %20.18f\n", result);
        System.out.printf("error is approx %20.18f\n", Math.abs(Math.PI - result)); 
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.printf("Took %dms\n", duration);
    }
}

class PiTask implements Runnable {
    long limit;
    MCPi p;
    PiTask(long iterations) {
        this.limit = iterations;
    }
    @Override
    public void run() {
        this.p = new MCPi();
        for (long i = 0; i < this.limit; i++) {
            p.toss();
        }
    }

    public double getRatio() {
        return this.p.ratio();
    }
}

