package Lab6.App2Lab7.Reentrant;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class App2Reentrant extends Thread {
    ReentrantLock monitor1, monitor2;

    String name;
    CyclicBarrier barrier;
    int activity_min, activity_max, sleep;

    public App2Reentrant(String name, ReentrantLock monitor1, ReentrantLock monitor2, int activity_min, int activity_max, int sleep, CyclicBarrier barrier) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.sleep = sleep;
        this.barrier = barrier;
        this.name = name;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        switch(this.name) {
            case "Thread-0":
                monitor1.lock();
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

                monitor1.unlock();

                System.out.println(this.getName() + " - STATE 3");

                try{
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case "Thread-1":
                monitor1.lock();
                monitor2.lock();
                System.out.println(this.getName() + " - STATE 2");
                k = (int) Math.round(Math.random()*(activity_max - activity_min) + activity_min);
                for (int i = 0; i < k * 100000; i++) {
                    i++; i--;
                }

                try {
                    Thread.sleep(sleep * 500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                monitor2.unlock();
                monitor1.unlock();

                System.out.println(this.getName() + " - STATE 3");

                try{
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "Thread-2":
                monitor2.lock();
                System.out.println(this.getName() + " - STATE 2");
                k = (int) Math.round(Math.random()*(activity_max - activity_min) + activity_min);
                for (int i = 0; i < k * 100000; i++) {
                    i++; i--;
                }
                try {
                    Thread.sleep(sleep * 500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                monitor2.unlock();

                System.out.println(this.getName() + " - STATE 3");

                try{
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }
    }
}
