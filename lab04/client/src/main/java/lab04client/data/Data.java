package lab04client.data;

import java.util.Arrays;

public class Data {
    public String key;
    public Values[] values;
    public Data(){}

    @Override
    public String toString() {
        return "Data{" +
                "key='" + key + '\'' +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
