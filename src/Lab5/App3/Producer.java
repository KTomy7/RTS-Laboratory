package Lab5.App3;

import java.util.Random;

public class Producer implements Runnable {
    private final NumberBuffer numberBuffer;
    private final Random random;

    public Producer(NumberBuffer numberBuffer) {
        this.numberBuffer = numberBuffer;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                int number = random.nextInt(100);
                numberBuffer.addNumber(number);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
