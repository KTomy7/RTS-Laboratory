package Lab7.App3;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Integer[] monitors = {0,0};
        CountDownLatch latch = new CountDownLatch(3);

        ExecutionThread t1 = new ExecutionThread(monitors, 7,3,2, latch,null);
        t1.start();
        new ExecutionThread(monitors, 0, 5,3, latch, t1).start();
        new ExecutionThread(monitors, 0, 6,4, latch, t1).start();
    }
}
