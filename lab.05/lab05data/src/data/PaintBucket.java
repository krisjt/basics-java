package data;

public class PaintBucket {

    private int value;
    private String currentlyDrawn = ".";
    private boolean isBeingSupplied;
    private final int maxValue;

    public PaintBucket(int value) {
        this.value = value;
        this.maxValue = value;
    }

    public boolean isBeingSupplied() {
        return isBeingSupplied;
    }

    public void setBeingSupplied(boolean beingSupplied) {
        isBeingSupplied = beingSupplied;
    }

    public String getCurrentlyDrawn() {
        return currentlyDrawn;
    }

    public void setCurrentlyDrawn(String currentlyDrawn) {
        this.currentlyDrawn = currentlyDrawn;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMaxValue() {
        return maxValue;
    }
}
