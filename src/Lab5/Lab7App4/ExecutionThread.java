package Lab5.Lab7App4;

import java.util.concurrent.Semaphore;

public class ExecutionThread extends Thread {
    final Semaphore s;
    int activityMin, activityMax;
    int sleep;

    public ExecutionThread(Semaphore s, int activityMax, int activityMin, int sleep) {
        this.s = s;
        this.activityMax = activityMax;
        this.activityMin = activityMin;
        this.sleep = sleep;
    }

    public void run() {

        System.out.println(this.getName() + " - STATE 1");
        try {
            s.acquire(2); // Acquire the semaphore
            System.out.println(this.getName() + " - TRANSITION 1-2");
            System.out.println(this.getName() + " - STATE 2");
            int k = (int) Math.round(Math.random() * (activityMax - activityMin) + activityMax);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            s.release(2); // Release the semaphore
            System.out.println(this.getName() + " - STATE 3");
            Thread.sleep(sleep);
            System.out.println(this.getName() + " - STATE 4");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
