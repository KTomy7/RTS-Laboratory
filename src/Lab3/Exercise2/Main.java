package Lab3.Exercise2;

public class Main {
    public static int sum = 0;

    public static void main(String[] args) {
        JoinTestThread w1 = new JoinTestThread("Thread 1",null);
        JoinTestThread w2 = new JoinTestThread("Thread 2", w1);
        w1.start();
        w2.start();
    }
}
