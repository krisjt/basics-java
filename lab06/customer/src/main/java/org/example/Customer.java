package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final List<String> myOrders = new ArrayList<>();
    private int id = 0;
    private String receiptInfo = "";

    public void putOrder(BufferedReader in) throws IOException {
        String product = in.readLine();
        getMyOrders().add(product);
    }

    public void returnReceipt(BufferedReader in) throws IOException {
        String ans = in.readLine();
        receiptInfo += ans + "\n";
    }


    public List<String> getMyOrders() {
        return myOrders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return "Customer";
    }

    public String getReceiptInfo() {
        return receiptInfo;
    }
}
