package org.example.data;

public class User {
    public String role;
    public String host;
    public int port;
    public int id;

    public User() {
    }

    public User(String role, int id) {
        this.role = role;
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "role='" + role + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
