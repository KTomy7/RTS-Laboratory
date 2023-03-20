package Lab3.Exercise1;

public class Main {
    public static void main(String[] args){
        Buffer b = new Buffer();
        Producer pro = new Producer(b);
        Consumer c = new Consumer(b);
        Consumer c2 = new Consumer(b);
        pro.start();
        c.start(); c2.start();
    }
}
