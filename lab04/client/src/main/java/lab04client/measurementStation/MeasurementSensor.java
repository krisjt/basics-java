package lab04client.measurementStation;

public class MeasurementSensor {
    public int id;
    public int stationId;
    public Param param;
    public MeasurementSensor(){}

    @Override
    public String toString() {
        return "MeasurementSensor{" +
                "id=" + id +
                ", stationId=" + stationId +
                ", param=" + param +
                '}';
    }
}
