package org.example;

import com.google.gson.Gson;
import org.example.data.Customer;
import org.example.data.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Seller {
    private int id = 0;
    private final List<Order> orderList = new ArrayList<>();

    public void acceptOrder(BufferedReader in) throws IOException {
        Gson gson = new Gson();
        Customer customer = gson.fromJson(in.readLine(), Customer.class);
        String product = in.readLine();
        String way = in.readLine();
        Order order = new Order(customer, product, way);
        orderList.add(order);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

}
