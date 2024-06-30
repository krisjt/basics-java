module client {
    exports lab04client.aqIndex;
    exports lab04client.measurementStation;
    exports lab04client.data;
    requires java.net.http;
    requires org.json;
    requires com.google.gson;
}