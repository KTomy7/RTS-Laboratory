package Lab2.Application2.Controller;

import Lab2.Application2.Models.Fir;
import Lab2.Application2.Views.Window;

public class Main {
    public static void main(String[] args) {
        int nrThreads = 9;
        Window window = new Window(nrThreads);

        for (int i = 0; i < nrThreads; i++) {
            Fir fir = new Fir(i, window, 1000000);
            fir.addObserver(window);
            Thread thread = new Thread(fir);
            thread.setPriority(i + 2);
            thread.start();
        }
    }
}
