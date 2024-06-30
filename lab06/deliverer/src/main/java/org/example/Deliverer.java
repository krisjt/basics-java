package org.example;

import com.google.gson.Gson;
import org.example.data.Customer;
import org.example.data.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Deliverer {
    private int id = 0;
    private final List<Order> orderList = new ArrayList<>();
    private final List<Order> toReturnOrderList = new ArrayList<>();


    void returnOrder(BufferedReader in) throws IOException {
        Gson gson = new Gson();
        String product = in.readLine();
        Customer customer = gson.fromJson(in.readLine(), Customer.class);
        Order order = new Order(customer, product);
        toReturnOrderList.add(order);
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

    public List<Order> getToReturnOrderList() {
        return toReturnOrderList;
    }

    public String getRole() {
        return "Deliverer";
    }
}
