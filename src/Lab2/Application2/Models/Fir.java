package Lab2.Application2.Models;

import Lab2.Application2.Views.Window;

import java.util.Observable;

public class Fir extends Observable implements Runnable {
    public int id;
    public int cc = 0;
    Window window;
    int processorLoad;

    public Fir(int id, Window window, int procLoad) {
        this.id = id;
        this.window = window;
        this.processorLoad = procLoad;
    }

    public void run(){
        int c = 0;
        while(c < 1000) {
            for (int j = 0; j < this.processorLoad; j++) {
                j++;
                j--;
            }
            c++;
            cc = c;
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setChanged();
            notifyObservers();
        }
    }
}
