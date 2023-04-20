package Lab4.App4;

public class ExecutionThread extends Thread {
    private final Object[] notifyTo;
    private final Object waitsFor;
    private final int sleepMin;
    private final int sleepMax;
    private final int activityMin;
    private final int activityMax;
    private final Thread joinTo;

    public ExecutionThread(Object[] notifyTo, Object waitsFor, int sleepMin, int sleepMax, int activityMin, int activityMax, Thread joinTo) {
        this.notifyTo = notifyTo == null ? new Object[] {} : notifyTo;
        this.waitsFor = waitsFor;
        this.sleepMin = sleepMin;
        this.sleepMax = sleepMax;
        this.activityMin = activityMin;
        this.activityMax = activityMax;
        this.joinTo = joinTo;
    }

    @Override
    public void run() {
        this.printMessage("State 1");

        if (this.waitsFor != null) {
            synchronized (this.waitsFor) {
                try {
                    this.waitsFor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        this.sleep(this.sleepMin, this.sleepMax);

        this.printMessage("State 2");

        this.activity(this.activityMin, this.activityMax);

        if (this.notifyTo != null) {
            for (int i = 0; i < this.notifyTo.length; i++) {
                synchronized (this.notifyTo[i]) {
                    this.notifyTo[i].notify();
                }
            }
        }

        this.printMessage("State 3");

        if (this.joinTo != null) {
            try {
                this.joinTo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void activity(int min, int max) {
        var k = (int) Math.round(Math.random() * (max - min) + min);
        for (int i = 0; i < k * 100000; i++) {
            ++i;
            --i;
        }
    }

    private void sleep(int min, int max) {
        try {
            Thread.sleep(Math.round(Math.random() * (max - min) + min) * 500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void printMessage(String message) {
        System.out.println(this.getName() + " - " + message);
    }
}
