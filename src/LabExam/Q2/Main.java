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
        int[] activity3 = {2, 4};
        int sleep1 = 3;
        int[] activity4 = {2, 5};
        int[] activity5 = {2, 6};
        int[] activity6 = {3, 5};
        int sleep2 = 2;

        new ExecutionThread(s1, s2, latch, activity1, activity2, activity3, sleep1, sleep1).start();
        new ExecutionThread(s1, s2, latch, activity4, activity5, activity6, sleep2, sleep2).start();
        latch.countDown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
