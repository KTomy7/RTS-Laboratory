package LabExam.Q2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class ExecutionThread extends Thread {
    Semaphore semaphore1;
    Semaphore semaphore2;
    CountDownLatch latch;
    int[] activity1;
    int[] activity2;
    int sleep_min1;
    int sleep_max1;
    int sleep_min2;
    int sleep_max2;

    public ExecutionThread(Semaphore s1, Semaphore s2, CountDownLatch latch, int[] activity1, int[] activity2, int min1, int max1, int min2, int max2) {
        this.semaphore1 = s1;
        this.semaphore2 = s2;
        this.latch = latch;
        this.activity1 = activity1;
        this.activity2 = activity2;
        this.sleep_min1 = min1;
        this.sleep_max1 = max1;
        this.sleep_min2 = min2;
        this.sleep_max2 = max2;
    }

    public void run() {
        while(true) {
            System.out.println(this.getName() + " - State 1");

            // P9 Semaphore
            try {
                semaphore1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + " - State 2");
            int k = (int) Math.round(Math.random()*(activity1[1]- activity1[0]) + activity1[0]);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            try {
                Thread.sleep(Math.round(Math.random() * (sleep_max1 - sleep_min1)+ sleep_min1) * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore1.release();

            // P11 Semaphore
            try {
                semaphore2.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + " - State 3");
            k = (int) Math.round(Math.random()*(activity2[1]- activity2[0]) + activity2[0]);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            try {
                Thread.sleep(Math.round(Math.random() * (sleep_max2 - sleep_min2)+ sleep_min2) * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore2.release();

            System.out.println(this.getName() + " - State 4");

            // T7 CountDownLatch
            try{
                latch.countDown();
                latch.await();
            } catch (InterruptedException  e)
            {
                e.printStackTrace();
            }
            System.out.println(this.getName() + " - END");
        }
    }
}
