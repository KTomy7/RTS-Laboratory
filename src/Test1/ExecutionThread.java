package Test1;

public class ExecutionThread extends Thread {
    int sleepMin, sleepMax, activity_min, activity_max;
    public ExecutionThread(int sleepMin, int sleepMax, int activity_min, int activity_max) {
        this.sleepMin = sleepMin;
        this.sleepMax = sleepMax;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
    }

    public void run() {
        while(true) {
            System.out.println(this.getName() + "- State 1");

            System.out.println(this.getName() + " - TRANSITION 1-2");
            if (this.getName().equals("Thread-0")) {
                synchronized (this) {
                    activity(activity_min, activity_max);
                }
            }
            if (this.getName().equals("Thread-1")) {
                synchronized (this) {
                    this.notify();
                    activity(activity_min, activity_max);
                }
            }
            System.out.println(this.getName() + " - State 2");

            System.out.println(this.getName() + " - TRANSITION 2-3");
            try {
                Thread.sleep(sleepMax - sleepMin);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + " - State 3");
        }
    }

    private void activity(int min, int max) {
        var k = (int) Math.round(Math.random() * (max - min) + min);
        for (int i = 0; i < k * 100000; i++) {
            ++i;
            --i;
        }
    }
}
