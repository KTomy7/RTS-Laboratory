package Lab4.App2;

public class ExecutionThread extends Thread {
    Integer[] monitor;
    int sleep;
    int[] activity1, activity2;

    public ExecutionThread(Integer[] monitor, int sleep, int[] activity1, int[] activity2) {
        this.monitor = monitor;
        this.sleep = sleep;
        this.activity1 = activity1;
        this.activity2 = activity2;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        transition(activity1);

        System.out.println(this.getName() + " - TRANSITION 1 - 2");
        if (this.getName().equals("Thread-0")) {
            synchronized (monitor[0]) {
                System.out.println(this.getName() + " - STATE 2");

                transition(activity2);

                System.out.println(this.getName() + " - TRANSITION 2 - 3");

                synchronized (monitor[1]) {
                    System.out.println(this.getName() + " - STATE 3");
                }
                System.out.println(this.getName() + " - TRANSITION 3 - 4");
                try {
                    Thread.sleep(sleep);
                } catch (Exception e) {
                }
                System.out.println(this.getName() + " - STATE 4");
            }
        }

        if (this.getName().equals("Thread-1")) {
            synchronized (monitor[1]) {
                System.out.println(this.getName() + " - STATE 2");

                transition(activity2);

                System.out.println(this.getName() + " - TRANSITION 2 - 3");

                synchronized (monitor[0]) {
                    System.out.println(this.getName() + " - STATE 3");
                }
                System.out.println(this.getName() + " - TRANSITION 3 - 4");
                try {
                    Thread.sleep(sleep);
                } catch (Exception e) {
                }
                System.out.println(this.getName() + " - STATE 4");
            }
        }
    }

    public void transition(int[] activity) {
        int k = (int) Math.round(Math.random() * (activity[1] - activity[0]) + activity[1]);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
    }
}
