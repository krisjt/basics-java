package lab04client.aqIndex;

import java.util.ArrayList;
import java.util.List;

public class AirQualityColor {
    private String name;
    private int index;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public AirQualityColor(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static List<AirQualityColor> getColorList(int id){
        List<AirQualityColor> airQualityColors = new ArrayList<>();
        if(AqIndexParser.getRestData(id).stIndexLevel!=null)airQualityColors.add(new AirQualityColor("stIndexLevel", AqIndexParser.getRestData(id).stIndexLevel.id));
        if(AqIndexParser.getRestData(id).so2IndexLevel!=null)airQualityColors.add(new AirQualityColor("so2IndexLevel", AqIndexParser.getRestData(id).so2IndexLevel.id));
        if(AqIndexParser.getRestData(id).no2IndexLevel!=null)airQualityColors.add(new AirQualityColor("no2IndexLevel", AqIndexParser.getRestData(id).no2IndexLevel.id));
        if(AqIndexParser.getRestData(id).o3IndexLevel!=null)airQualityColors.add(new AirQualityColor("o3IndexLevel", AqIndexParser.getRestData(id).o3IndexLevel.id));
        if(AqIndexParser.getRestData(id).pm25IndexLevel!=null)airQualityColors.add(new AirQualityColor("pm25IndexLevel", AqIndexParser.getRestData(id).pm25IndexLevel.id));
        if(AqIndexParser.getRestData(id).pm10IndexLevel!=null)airQualityColors.add(new AirQualityColor("pm10IndexLevel", AqIndexParser.getRestData(id).pm10IndexLevel.id));

        return airQualityColors;
    }

    public static void main(String[] args) {
        getColorList(52);

    }

    @Override
    public String toString() {
        return "AirQualityColor{" +
                "name='" + name + '\'' +
                ", index=" + index +
                '}';
    }
}
