package lab04client.aqIndex;

import java.util.Arrays;

public class AqIndex {
//    public String  key;
    public Values[] values;
    public AqIndex(){}

    @Override
    public String toString() {
        return "AqIndex{" +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
