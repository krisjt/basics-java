package lab04client.measurementStation;

public class Param {
    public String paramName;
    public String paramFormula;
    public String paramCode;
    public int idParam;
    public Param(){
    }

    @Override
    public String toString() {
        return "Param{" +
                "paramName='" + paramName + '\'' +
                ", paramFormula='" + paramFormula + '\'' +
                ", paramCode='" + paramCode + '\'' +
                ", idParam=" + idParam +
                '}';
    }
}
