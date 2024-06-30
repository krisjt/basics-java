package org.example.data;

public class Seller {

    public String role;
    public String host;
    public int id;
    public int port;
    public boolean free;

    public Seller() {
    }

    public Seller(String role, String host, int id, int port) {
        this.host = host;
        this.id = id;
        this.port = port;
        this.free = true;
    }

    @Override
    public String toString() {
        return "Seller{" +
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

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
}
