package Lab2.Application2.Views;

import Lab2.Application2.Models.Fir;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Window extends JFrame implements Observer {
    ArrayList<JProgressBar> bars = new ArrayList<>();

    // Initialize the window:
    public Window(int nrThreads) {
        super("Application2");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 450);
        setLayout(null);
        setVisible(true);
        InitializeProgressBars(nrThreads);
    }

    // Initialize the progress bars:
    public void InitializeProgressBars(int nrThreads) {
        for (int i = 0; i < nrThreads; i++) {
            JLabel label = new JLabel("Thread " + (i + 1) + ":");
            JProgressBar pb = new JProgressBar();
            pb.setMaximum(1000);
            label.setBounds(30, (i + 1) * 40, 350, 35);
            pb.setBounds(100, (i + 1) * 40, 300, 35);
            this.add(label);
            this.add(pb);
            this.bars.add(pb);
        }
    }

    public void setProgressValue(int id, int val) {
        bars.get(id).setValue(val);
    }

    @Override
    public void update(Observable o, Object arg) {
        bars.get(((Fir) o).id).setValue(((Fir) o).cc);
    }
}
