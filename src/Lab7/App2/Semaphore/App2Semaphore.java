package Lab7.App2.Semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class App2Semaphore extends Thread {
    Semaphore monitor1, monitor2;
    CountDownLatch latch;
    int activity_min, activity_max, sleep;

    public App2Semaphore(Semaphore monitor1, Semaphore monitor2, int activity_min, int activity_max, int sleep, CountDownLatch latch) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.sleep = sleep;
        this.latch = latch;
    }

    public void run() {
        String threadName = this.getName();
        switch(threadName) {
            case "Thread-0":
                System.out.println(this.getName() + " - STATE 1");
                if(monitor1.tryAcquire())
                    try{
                        System.out.println(this.getName() + " - STATE 2");
                        int k = (int) Math.round(Math.random()*(activity_max - activity_min) + activity_min);
                        for (int i = 0; i < k * 100000; i++) {
                            i++; i--;
                        }
                        try {
                            Thread.sleep(sleep * 500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        monitor1.release(1);
                    }

                System.out.println(this.getName() + " - STATE 3");

                latch.countDown();

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                break;
            case "Thread-1":
                System.out.println(this.getName() + " - STATE 1");
                try {
                    monitor1.acquire(1);
                    try {
                        monitor2.acquire(1);
                        System.out.println(this.getName() + " - STATE 2");
                        int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                        for (int i = 0; i < k * 100000; i++) {
                            i++;
                            i--;
                        }
                        try {
                            Thread.sleep(sleep * 500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        monitor2.release();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    monitor1.release();
                }

                System.out.println(this.getName() + " - STATE 3");

                latch.countDown();

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "Thread-2":
                try {
                    monitor2.acquire(1);
                    System.out.println(this.getName() + " - STATE 2");
                    int k = (int) Math.round(Math.random()*(activity_max - activity_min) + activity_min);
                    for (int i = 0; i < k * 100000; i++) {
                        i++; i--;
                    }
                    try {
                        Thread.sleep(sleep * 500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    monitor2.release();
                }

                System.out.println(this.getName() + " - STATE 3");

                latch.countDown();

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
        }

        System.out.println(this.getName() + " - STATE 3");
    }
}
