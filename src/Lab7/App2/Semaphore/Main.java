package Lab7.App2.Semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore monitor1 = new Semaphore(1);
        Semaphore monitor2 = new Semaphore(1);
        CountDownLatch latch = new CountDownLatch(3);

        new App2Semaphore(monitor1, monitor2, 2, 4, 4, latch).start();
        new App2Semaphore(monitor1, monitor2, 4, 6, 3, latch).start();
        new App2Semaphore(monitor1, monitor2, 3, 5, 5, latch).start();
    }
}
