package lab04client.aqIndex;

public class Pm10IndexLevel {
    public int id;
    public String indexLevelName;

    @Override
    public String toString() {
        return "Pm10IndexLevel{" +
                "id=" + id +
                ", indexLevelName='" + indexLevelName + '\'' +
                '}';
    }

    public Pm10IndexLevel(){}
}
