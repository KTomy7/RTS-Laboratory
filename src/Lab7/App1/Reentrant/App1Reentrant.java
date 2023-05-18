package Lab7.App1.Reentrant;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class App1Reentrant extends Thread {
    ReentrantLock monitor1, monitor2;
    CyclicBarrier barrier;
    int[] activity_min, activity_max;
    int sleep;

    Thread t;

    public App1Reentrant(ReentrantLock monitor1, ReentrantLock monitor2, int[] activity_min, int[] activity_max, int sleep, Thread t, CyclicBarrier barrier) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.sleep = sleep;
        this.t = t;
        this.barrier = barrier;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        int k = (int) Math.round(Math.random() * (activity_max[0] - activity_min[0]) + activity_min[0]);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }

        if (monitor1.tryLock()) {
            try {
                System.out.println(this.getName() + " - STATE 2");
                k = (int) Math.round(Math.random() * (activity_max[1] - activity_min[1]) + activity_min[1]);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }
                if (monitor2.tryLock()) {
                    System.out.println(this.getName() + " - STATE 3");

                    try {
                        Thread.sleep(sleep * 500);
                        System.out.println("Sleep" + sleep * 500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        monitor2.unlock();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                monitor1.unlock();
            }}

        System.out.println(this.getName() + " - STATE 4");

        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
