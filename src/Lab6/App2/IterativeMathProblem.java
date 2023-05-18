package Lab6.App2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class IterativeMathProblem {
    private static final int MIN_VALUE = -10;
    private static final int MAX_VALUE = 10;
    private static final int NUM_THREADS = 3;

    private static volatile int iterationCount = 0;
    private static volatile int sum = 0;

    private static final CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS, new Runnable() {
        @Override
        public void run() {
            iterationCount++;
            if (sum == 0) {
                System.out.println("Number of iterations performed by each thread: " + iterationCount);
                System.exit(0);
            }
            sum = 0;
        }
    });

    public static void main(String[] args) throws InterruptedException, IOException {

        // create a BufferedWriter to write results to a file
        BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt"));

        // create three worker threads
        Thread t1 = new Thread(new Worker(writer));
        Thread t2 = new Thread(new Worker(writer));
        Thread t3 = new Thread(new Worker(writer));

        // start the threads
        t1.start();
        t2.start();
        t3.start();

        // wait for the threads to finish
        t1.join();
        t2.join();
        t3.join();

        // close the writer
        writer.close();
    }

    private static class Worker implements Runnable {

        private final BufferedWriter writer;
        private final Random random = new Random();

        public Worker(BufferedWriter writer) {
            this.writer = writer;
        }

        @Override
        public void run() {
            while (true) {
                int value = random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;
                sum += value;
                try {
                    barrier.await();
                    writer.write(String.format("%d %d\n", iterationCount, sum));
                    writer.flush();
                } catch (InterruptedException | BrokenBarrierException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
