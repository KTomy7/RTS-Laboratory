package LabExam.Q2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore s1 = new Semaphore(3);
        Semaphore s2 = new Semaphore(2);
        CountDownLatch latch = new CountDownLatch(2);

        int[] activity1 = {3, 6};
        int[] activity2 = {4, 5};
        int sleep_min1 = 3;
        int sleep_max1 = 3;
        int sleep_min2 = 2;
        int sleep_max2 = 4;

        int[] activity3 = {2, 5};
        int[] activity4 = {2, 6};
        int sleep_min3 = 2;
        int sleep_max3 = 2;
        int sleep_min4 = 3;
        int sleep_max4 = 5;

        new ExecutionThread(s1, s2, latch, activity1, activity2, sleep_min1, sleep_max1, sleep_min2, sleep_max2).start();
        new ExecutionThread(s1, s2, latch, activity3, activity4, sleep_min3, sleep_max3, sleep_min4, sleep_max4).start();

        latch.countDown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
