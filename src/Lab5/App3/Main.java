package Lab5.App3;

public class Main {
    public static void main(String[] args) {
        NumberBuffer numberBuffer = new NumberBuffer();

        Producer producer = new Producer(numberBuffer);

        Consumer consumer1 = new Consumer(numberBuffer, false);
        Consumer consumer2 = new Consumer(numberBuffer, false);
        Consumer consumer3 = new Consumer(numberBuffer, false);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
        new Thread(consumer3).start();
    }
}
