package Lab6.App1Lab7.Semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class App1Semaphore extends Thread {
    Semaphore monitor1, monitor2;
    CountDownLatch latch;
    int[] activity_min, activity_max;
    int sleep;

    Thread t;

    public App1Semaphore(Semaphore monitor1, Semaphore monitor2, int[] activity_min, int[] activity_max, int sleep, Thread t, CountDownLatch latch) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.sleep = sleep;
        this.t = t;
        this.latch = latch;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        int k = (int) Math.round(Math.random() * (activity_max[0] - activity_min[0]) + activity_min[0]);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }

        if(monitor1.tryAcquire(1))
            try{
                System.out.println(this.getName() + " - STATE 2");
                k = (int) Math.round(Math.random() * (activity_max[1] - activity_min[1]) + activity_min[1]);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }

                if(monitor2.tryAcquire(1))
                    try {
                        System.out.println(this.getName() + " - STATE 3");
                        Thread.sleep(sleep * 500L);
                        System.out.println("Sleep" + sleep * 500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        monitor2.release(1);
                    }
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                monitor1.release(1);
            }

        System.out.println(this.getName() + " - STATE 4");

        latch.countDown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
