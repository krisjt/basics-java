package lab04client.aqIndex;

public class Values {
    public int id;
    public String stCalcDate;
    public StIndexLevel stIndexLevel;
    public String stSourceDataDate;
    public String so2CalcDate;
    public SoIndexLevel so2IndexLevel;
    public String so2SourceDataDate;
    public String no2CalcDate;
    public No2IndexLevel no2IndexLevel;
    public String no2SourceDataDate;
    public String pm10CalcDate;
    public Pm10IndexLevel pm10IndexLevel;
    public String pm10SourceDataDate;
    public String pm25CalcDate;
    public Pm25IndexLevel pm25IndexLevel;
    public String pm25SourceDataDate;
    public String o3CalcDate;
    public O3IndexLevel o3IndexLevel;
    public String o3SourceDataDate;
    public boolean stIndexStatus;
    public String stIndexCrParam;
    public Values(){}

    @Override
    public String toString() {
        return "Values{" +
                "id=" + id +
                ", stCalcDate='" + stCalcDate + '\'' +
                ", stIndexLevel=" + stIndexLevel +
                ", stSourceDataDate='" + stSourceDataDate + '\'' +
                ", so2CalcDate='" + so2CalcDate + '\'' +
                ", so2IndexLevel=" + so2IndexLevel +
                ", so2SourceDataDate='" + so2SourceDataDate + '\'' +
                ", no2CalcDate='" + no2CalcDate + '\'' +
                ", no2IndexLevel=" + no2IndexLevel +
                ", no2SourceDataDate='" + no2SourceDataDate + '\'' +
                ", pm10CalcDate='" + pm10CalcDate + '\'' +
                ", pm10IndexLevel=" + pm10IndexLevel +
                ", pm10SourceDataDate='" + pm10SourceDataDate + '\'' +
                ", pm25CalcDate='" + pm25CalcDate + '\'' +
                ", pm25IndexLevel=" + pm25IndexLevel +
                ", pm25SourceDataDate='" + pm25SourceDataDate + '\'' +
                ", o3CalcDate='" + o3CalcDate + '\'' +
                ", o3IndexLevel=" + o3IndexLevel +
                ", o3SourceDataDate='" + o3SourceDataDate + '\'' +
                ", stIndexStatus=" + stIndexStatus +
                ", stIndexCrParam='" + stIndexCrParam + '\'' +
                '}';
    }
}
