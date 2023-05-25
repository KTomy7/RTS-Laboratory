package Test2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class ExecutionThread extends Thread {
    private final Semaphore semaphore1;
    private final Semaphore semaphore2;
    private final CountDownLatch latch;
    private final int sleep_min1;
    private final int sleep_min2;
    private final int sleep_max1;
    private final int sleep_max2;

    public ExecutionThread(Semaphore sem1, Semaphore sem2, CountDownLatch latch, int min1, int max1, int min2, int max2) {
        this.semaphore1 = sem1;
        this.semaphore2 = sem2;
        this.latch = latch;
        sleep_min1 = min1;
        sleep_min2 = min2;
        sleep_max1 = max1;
        sleep_max2 = max2;
    }

    public void run() {
        System.out.println(this.getName() + " - State 1");

        // P9 Semaphore
        semaphore1.tryAcquire();
        System.out.println(this.getName() + " - State 2");
        try {
            Thread.sleep(Math.round(Math.random() * (sleep_max1 - sleep_min1)+ sleep_min1) * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // P11 Semaphore
        semaphore2.tryAcquire();
        System.out.println(this.getName() + " - State 3");
        try {
            Thread.sleep(Math.round(Math.random() * (sleep_max2 - sleep_min2)+ sleep_min2) * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // T7 CountDownLatch
        System.out.println(this.getName() + " - State 4");
        try {
            latch.await();
        } catch (InterruptedException  e) {
            e.printStackTrace();
        }
    }
}
