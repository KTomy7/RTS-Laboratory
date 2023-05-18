package Lab7.App3;

import java.util.concurrent.CountDownLatch;

public class ExecutionThread extends Thread {
    Integer[] monitors;
    int sleep, activity_max, activity_min;
    Thread t;

    CountDownLatch latch;


    ExecutionThread(Integer[] monitors, int sleep, int activity_max, int activity_min, CountDownLatch latch, Thread t) {
        this.monitors = monitors;
        this.sleep = sleep;
        this.activity_max = activity_max;
        this.activity_min = activity_min;
        this.latch = latch;
        this.t = t;
    }
    public void run() {
        switch (this.getName()){
            case "Thread-0":
                System.out.println(this.getName() + " STATE 1");
                try {
                    Thread.sleep(7 * 500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(this.getName() + " STATE 2");

                int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }

                synchronized (monitors[1]) {
                    synchronized (monitors[0])
                    {
                        monitors[0].notify();
                    }
                    monitors[1].notify();
                }

                System.out.println(this.getName() + " STATE 3");
                latch.countDown();

                try{
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "Thread-1":
                System.out.println(this.getName() + " STATE 1");

                try {
                    Thread.sleep(7 * 500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (monitors[0]){
                    try {
                        monitors[0].wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println(this.getName() + " STATE 2");

                int k1 = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                for (int i = 0; i < k1 * 100000; i++) {
                    i++;
                    i--;
                }

                System.out.println(this.getName() + " STATE 3");
                latch.countDown();

                try{
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "Thread-2":
                System.out.println(this.getName() + " STATE 1");

                try {
                    Thread.sleep(7 * 500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (monitors[1]){
                    try {
                        monitors[1].wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println(this.getName() + " STATE 2");

                k1 = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                for (int i = 0; i < k1 * 100000; i++) {
                    i++;
                    i--;
                }

                System.out.println(this.getName() + " STATE 3");
                latch.countDown();

                try{
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
