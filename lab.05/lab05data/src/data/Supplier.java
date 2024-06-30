package data;

import java.util.concurrent.TimeUnit;

public class Supplier extends Thread{
    public final PaintBucket paintBucket = new PaintBucket(50);
    public void supply() throws InterruptedException {

        if(paintBucket.getValue() <= paintBucket.getMaxValue()/2) {
            try {
                paintBucket.setValue(paintBucket.getMaxValue());
                paintBucket.setBeingSupplied(true);
                TimeUnit.MILLISECONDS.sleep(2000);
                paintBucket.setBeingSupplied(false);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void run(){
        try {
            while(true) {
                supply();
                if(Thread.currentThread().isInterrupted())break;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
