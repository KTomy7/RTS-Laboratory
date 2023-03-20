package Lab3.Exercise4;

import java.io.PipedOutputStream;

public class WriteThread extends Thread {
    private PipedOutputStream po;
    WriteThread(){
        po = new PipedOutputStream();
    }
    public void run(){
        try{
            while (true){
                int d = (int)(10*Math.random());
                System.out.println("Writing Thread is sent : "+d);
                po.write(d);
                sleep(400);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    PipedOutputStream getPipe(){return po;}
}
