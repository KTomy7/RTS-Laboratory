package Lab6.App2Lab7.Reentrant;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        ReentrantLock monitor1 = new ReentrantLock();
        ReentrantLock monitor2 = new ReentrantLock();
        CyclicBarrier barrier = new CyclicBarrier(4);

        while(true) {
            new App2Reentrant("Thread-0", monitor1, monitor2, 2, 4, 4, barrier).start();
            new App2Reentrant("Thread-1", monitor1, monitor2, 4, 6, 3, barrier).start();
            new App2Reentrant("Thread-2", monitor1, monitor2, 3, 5, 5, barrier).start();

            try{
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }

            barrier.reset();
        }
    }
}
