package Test1;

public class Main {
    public static void main(String[] args) {
        new ExecutionThread(5, 7, 3, 6).start();
        new ExecutionThread(5, 7, 7, 9).start();
    }
}
