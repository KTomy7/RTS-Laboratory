package Lab3.Exercise4;

import java.io.PipedOutputStream;
import java.io.*;

public class ReadThread extends Thread {
    private PipedInputStream pi;
    ReadThread(){
        pi = new PipedInputStream();
    }
    public void run() {
        try {
            while (true) {
                if (pi.available() > 0) {
                    System.out.println("Read Thread is received : " + pi.read());
                }
            }
        } catch (Exception e) {
        }
    }
    void conect (PipedOutputStream os) throws Exception {
        pi.connect(os);
    }
}
