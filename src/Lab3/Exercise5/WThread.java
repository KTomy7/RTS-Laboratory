package Lab3.Exercise5;

public class WThread extends Thread {
    FileService service;
    public WThread(FileService service) {
        this.service = service;
    }

    public void run(){
        while (true) {
            synchronized (service) {
                String msg = String.valueOf(Math.round(Math.random() * 100));
                service.write(msg);
            }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }
}
