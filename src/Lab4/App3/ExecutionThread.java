package Lab4.App3;

public class ExecutionThread extends Thread {
    Integer monitor;
    int sleep, activity_min, activity_max;

    public ExecutionThread(Integer monitor, int sleep, int activity_min, int activity_max) {
        this.monitor = monitor;
        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");

        if (this.getName().equals("Thread-0")) {
            transition(this, monitor, activity_min, activity_max);
        }

        if (this.getName().equals("Thread-1")) {
            transition(this, monitor, activity_min, activity_max);
        }

        if (this.getName().equals("Thread-2")) {
            transition(this, monitor, activity_min, activity_max);
        }
    }

    public void transition(Thread thread, Integer monitor, int activity_min, int activity_max) {
        System.out.println(thread.getName() + " - TRANSITION 1-2");

        synchronized (monitor) {
            System.out.println(thread.getName() + " - STATE 2");
            int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
        }
        System.out.println(thread.getName() + " - STATE 3");
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getName() + " - STATE 4");
    }
}
