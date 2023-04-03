package Lab4.App3;

public class Main {
    public static void main(String[] args) {
        Integer monitor = 0;

        new ExecutionThread(monitor, 5, 3, 6).start();
        new ExecutionThread(monitor, 3, 4, 7).start();
        new ExecutionThread(monitor, 6, 5, 7).start();
    }
}
