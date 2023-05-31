package LabExam.Q2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class ExecutionThread extends Thread {
    Semaphore semaphore1;
    private Semaphore semaphore2;
    private final CountDownLatch latch;
    int[] activity1;
    int[] activity2;
    int[] activity3;
    int sleep_min;
    int sleep_max;

    public ExecutionThread(Semaphore sem1, Semaphore sem2, CountDownLatch latch, int[] act1, int[] act2, int[] act3, int min, int max) {
        this.semaphore1 = sem1;
        this.semaphore2 = sem2;
        this.latch = latch;
        this.activity1 = act1;
        this.activity2 = act2;
        this.activity3 = act3;
        this.sleep_min = min;
        this.sleep_max = max;
    }

    public void run() {
        System.out.println(this.getName() + " - State 1");

        // P9 Semaphore
        try {
            semaphore1.tryAcquire();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(this.getName() + " - State 2");
        int k = (int) Math.round(Math.random()*(activity1[1]- activity1[0]) + activity1[0]);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        try {
            Thread.sleep(Math.round(Math.random() * (sleep_max - sleep_min)+ sleep_min) * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore1.release();

        // P11 Semaphore
        semaphore2.tryAcquire();
        System.out.println(this.getName() + " - State 3");
        k = (int) Math.round(Math.random()*(activity2[1]- activity2[0]) + activity2[0]);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        semaphore2.release();

        k = (int) Math.round(Math.random()*(activity3[1]- activity3[0]) + activity3[0]);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }

        System.out.println(this.getName() + " - State 4");
        // T7 CountDownLatch
        try{
            latch.countDown();
            latch.await();
        } catch (InterruptedException  e)
        {
            e.printStackTrace();
        }
    }
}
