package Lab5.Lab7App4;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(1); // initialize semaphore with 1 permit

        new ExecutionThread(s, 5, 1, 500).start();
        new ExecutionThread(s, 10, 3, 500).start();
        new ExecutionThread(s, 7, 2, 500).start();
    }
}
