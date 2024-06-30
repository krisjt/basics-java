package lab04client.measurementStation;

public class MeasurementStation {
    public int id;
    public String stationName;
    public double gegrLat;
    public double gegrLon;
    public City city;
    public String adressStreet;

    public MeasurementStation() {
    }

    @Override
    public String toString() {
        return "MeasurementStation{" +
                "id=" + id +
                ", stationName='" + stationName + '\'' +
                ", gegrLat=" + gegrLat +
                ", gegrLon=" + gegrLon +
                ", city=" + city +
                ", adressStreet='" + adressStreet + '\'' +
                '}';
    }

}