    package data;

    import java.util.Objects;
    import java.util.concurrent.locks.Lock;
    import java.util.concurrent.locks.ReentrantLock;

    import static data.Service.*;

    public class Painter extends Thread {

        private String painterName;
        private int velocity;
        private int bucketCapacity;
        private int bucketContent;
        private final Supplier supplier;
        private static final Lock lock1 = new ReentrantLock();
        private static final Lock lock2 = new ReentrantLock();
        private static final Lock lock3 = new ReentrantLock();
        private boolean shouldSleep = false;

        public Painter(String name, int velocity, int bucketCapacity, Supplier supplier) {
            this.painterName = name;
            this.velocity = velocity;
            this.bucketCapacity = bucketCapacity;
            this.bucketContent = bucketCapacity;
            this.supplier = supplier;
        }

        @Override
        public void run () {
            try {
                findEmptySegment();
                findOtherSegment();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void findEmptySegment() throws InterruptedException{

            for (int i = 0; i < fence.size(); i++) {
                if (bucketContent == 0) fillBucket();
                if(isSegmentEmpty(i)) {
                    if (bucketContent <= 0) fillBucket();
//                    Thread.sleep(10000);
                    for (int j = 0; j < fence.get(i).size(); j++) {
                        if (bucketContent == 0) fillBucket();
                        isTaken(i, j);
                        if(shouldSleep)Thread.sleep(velocity);
                    }
                }
            }
        }

        public boolean isSegmentEmpty(int i){
           try{
               lock1.lock();
                if (!segmentTaken.get(i) && fence.get(i).get(0).equals("-")) {
                    segmentTaken.set(i, true);
                    return true;
                }
                return false;
            }finally {
               lock1.unlock();
           }
        }


        public void isTaken(int i, int j){
            try{
                lock2.lock();
                shouldSleep = false;
                if (fence.get(i).get(j).equals("-")) {
                    fence.get(i).set(j, painterName);
                    shouldSleep = true;
                    bucketContent--;
                }
            }finally {
                lock2.unlock();
            }
        }

public void findOtherSegment() throws InterruptedException {
    for (int i = 0; i < fence.size(); i++) {
        int index;
        int counter = 0;
        index = findLongestRow(i);
//        Thread.sleep(5000);
        if(index > 0 && Objects.equals(isPlaceTaken.get(i).get(index), painterName)){
            for (int j = index; j < fence.get(i).size(); j++) {
                if (bucketContent <= 0) fillBucket();
                Thread.sleep(2000);
                isTaken(i,j);
                if(shouldSleep)Thread.sleep(velocity);
                if(!shouldSleep){
                    j = findLongestRow(i);
                    counter++;
                    if(counter == 3)break;
                }
            }
        }
    }
}

        public int findLongestRow(int i){
            try {
                lock3.lock();
                int max = 0;
                int currentLongestRow = 0;
                int id = 0;
                int maxID = 0;
                for (int j = 0; j < fence.get(i).size(); j++) {
                    if (fence.get(i).get(j) == "-") {
                        currentLongestRow++;
                        id = j;
                    }
                    if (j == fence.get(i).size() - 1 || fence.get(i).get(j) != "-") {
                        if (currentLongestRow >= max) {
                            max = currentLongestRow;
                            currentLongestRow = 0;
                            maxID = id;
                        }
                    }
                    isPlaceTaken.get(i).set(maxID - (max-1)/2,painterName);
                }
                return maxID - (max-1)/2;

            }finally {
                lock3.unlock();
            }
        }


        public void fillBucket() throws InterruptedException {
            synchronized (supplier.paintBucket) {
                while (supplier.paintBucket.getValue() < supplier.paintBucket.getMaxValue()/2) {
                    supplier.paintBucket.wait();
                }
                supplier.paintBucket.setCurrentlyDrawn(painterName);
                supplier.paintBucket.setValue(supplier.paintBucket.getValue() - bucketCapacity);
                Thread.sleep(velocity);
                supplier.paintBucket.setCurrentlyDrawn(".");
                supplier.paintBucket.notifyAll();
            }
            bucketContent = bucketCapacity;
        }


        public String getPainterName() {
            return painterName;
        }

        public void setPainterName(String name) {
            this.painterName = name;
        }

        public int getVelocity() {
            return velocity;
        }

        public void setVelocity(int velocity) {
            this.velocity = velocity;
        }

        public int getBucketCapacity() {
            return bucketCapacity;
        }

        public void setBucketCapacity(int bucketCapacity) {
            this.bucketCapacity = bucketCapacity;
        }

        public int getBucketContent() {
            return bucketContent;
        }

        public void setBucketContent(int bucketContent) {
            this.bucketContent = bucketContent;
        }

    }
