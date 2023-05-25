package Test2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore s1 = new Semaphore(2);
        Semaphore s2 = new Semaphore(3);
        CountDownLatch latch = new CountDownLatch(1);

        int[] sleep_min1 = {5, 6};
        int[] sleep_max1 = {8, 3};
        int[] sleep_min2 = {3, 2};
        int[] sleep_max2 = {7, 4};

        new ExecutionThread(s1, s2, latch, sleep_min1[0], sleep_max1[0], sleep_min1[1], sleep_max1[1]).start();
        new ExecutionThread(s1, s2, latch,  sleep_min2[0], sleep_max2[0], sleep_min2[1], sleep_max2[1]).start();
    }
}
