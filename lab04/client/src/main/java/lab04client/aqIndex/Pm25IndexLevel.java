package lab04client.aqIndex;

public class Pm25IndexLevel {
    public int id;
    public String indexLevelName;
    public Pm25IndexLevel(){}

    @Override
    public String toString() {
        return "Pm25IndexLevel{" +
                "id=" + id +
                ", indexLevelName='" + indexLevelName + '\'' +
                '}';
    }
}
