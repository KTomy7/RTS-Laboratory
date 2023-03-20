package Lab3.Exercise5;

public class RThread extends Thread {
    FileService service;
    public RThread(FileService service) {
        this.service = service;
    }
    public void run(){
        while (true) {
            synchronized (service) {
                service.notify();
            }
            try {
                String readMsg = service.read();
                System.out.println(readMsg);
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
