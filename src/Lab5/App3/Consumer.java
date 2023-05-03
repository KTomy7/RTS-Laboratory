package Lab5.App3;

public class Consumer implements Runnable {
    private final NumberBuffer numberBuffer;
    private final boolean lockingEnabled;

    public Consumer(NumberBuffer numberBuffer, boolean lockingEnabled) {
        this.numberBuffer = numberBuffer;
        this.lockingEnabled = lockingEnabled;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (lockingEnabled) {
                    numberBuffer.lock.lock();
                }
                int number = numberBuffer.removeNumber();
                if (lockingEnabled) {
                    numberBuffer.lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() + " consumed " + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
