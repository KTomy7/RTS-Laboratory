package Lab7.App1.Semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String args[]) {
        Semaphore monitor1 = new Semaphore(1);
        Semaphore monitor2 = new Semaphore(1);

        int[] activity_min = {2, 4};
        int[] activity_max = {4, 6};

        int[] activity_min2 = {10, 5};
        int[] activity_max2 = {10, 7};

        while(true) {
            CountDownLatch latch = new CountDownLatch(3);

            App1Semaphore t1 = new App1Semaphore(monitor1, monitor2, activity_min, activity_max, 4, null, latch);
            t1.start();

            new App1Semaphore(monitor2, monitor1, activity_min2, activity_max2, 5, t1, latch).start();

            latch.countDown();

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
