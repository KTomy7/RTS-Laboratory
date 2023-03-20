package Lab3.Exercise1;

import java.util.ArrayList;

public class Buffer {
    ArrayList<Double> content = new ArrayList<Double>();
    synchronized void put(double d){
        content.add(new Double(d));
        notify();
    }
    synchronized double get(){
        double d=-1;
        try{
            if(content.size()==0) wait();
            d = (content.get(0)).doubleValue();
            content.remove(0);
        }catch(Exception e){e.printStackTrace();}
        return d;
    }

}
