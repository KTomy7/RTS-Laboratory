package Lab3.Exercise2;

public class JoinTestThread extends Thread {
    Thread t;
    JoinTestThread (String n, Thread t) {
        this.setName(n);
        this.t = t;
    }
    public void run() {
        int sumOfDividers = 0;
        boolean isFinished = false;

        System.out.println("Thread "+ this.getName() +" has entered the run() method");
        try {
            if (t != null) {
                t.join();
            }

            if (this.getName().equals("Thread 1")) {
                sumOfDividers = getDividersSum(50005);
            }

            Main.sum += sumOfDividers;

            if (this.getName().equals("Thread 2")) {
                sumOfDividers = getDividersSum(20002);
                isFinished = true;
            }
            Main.sum += sumOfDividers;

            System.out.println("Thread "+ this.getName() +" executing operation.");
            Thread.sleep(3000);
            System.out.println("Thread "+ this.getName() +" has terminated operation.");

            if (isFinished) {
                System.out.println("The sum is: " + Main.sum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getDividersSum(int n) {
        int sumOfDividers = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                sumOfDividers += i;
            }
        }
        return sumOfDividers;
    }
}
