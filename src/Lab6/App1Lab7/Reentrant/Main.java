package Lab6.App1Lab7.Reentrant;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String args[]) {
        ReentrantLock monitor1 = new ReentrantLock();
        ReentrantLock monitor2 = new ReentrantLock();
        CyclicBarrier barrier = new CyclicBarrier(3);

        int[] activity_min = {2, 4};
        int[] activity_max = {4, 6};

        int[] activity_min2 = {10, 5};
        int[] activity_max2 = {10, 7};

        while(true) {
            App1Reentrant t1 = new App1Reentrant(monitor1, monitor2, activity_min, activity_max, 4, null, barrier);
            t1.start();

            new App1Reentrant(monitor2, monitor1, activity_min2, activity_max2, 5, t1, barrier).start();

            try{
                barrier.await();
            } catch (Exception e){
                e.printStackTrace();
            }

            barrier.reset();
        }
    }
}
