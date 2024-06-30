package org.example.data;

public class Deliverer {
    public String role;
    public String host;
    public int id;
    public int port;

    public Deliverer() {
    }

    public Deliverer(String role, String host, int id, int port) {
        this.host = host;
        this.id = id;
        this.port = port;
    }

    @Override
    public String toString() {
        return "Deliverer{" +
                "host='" + host + '\'' +
                ", id=" + id +
                ", port=" + port +
                '}';
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
