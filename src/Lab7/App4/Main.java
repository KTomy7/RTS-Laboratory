package Lab7.App4;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(2);

        ExecutionThread thread1 = new ExecutionThread(500, semaphore, 5, 1);
        ExecutionThread thread2 = new ExecutionThread(500, semaphore, 10, 3);
        ExecutionThread thread3 = new ExecutionThread(500, semaphore, 7, 2);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
