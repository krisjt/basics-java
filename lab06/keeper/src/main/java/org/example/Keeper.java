package org.example;

import com.google.gson.Gson;
import org.example.data.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Keeper {
    private static int idc = 0;
    private static int ids = 0;
    private static int idd = 0;
    private final List<Customer> customerList = new ArrayList<>();
    private final List<Seller> sellerList = new ArrayList<>();
    private final List<Deliverer> delivererList = new ArrayList<>();
    private final List<String> offer = new ArrayList<>();
    private final List<Order> orderList = new ArrayList<>();
    private final List<User> userList = new ArrayList<>();

    void getOffer(PrintWriter out) {
        Gson gson = new Gson();
        out.println(gson.toJson(offer));
    }


    void putOrder(BufferedReader in) throws IOException {
        Gson gson = new Gson();
        Customer customer = gson.fromJson(in.readLine(), Customer.class);
        String product = in.readLine();
        Order order = new Order(customer, product);
        orderList.add(order);
        offer.remove(order.getGoods());
    }


    void getOrder(PrintWriter out) {
        if (!orderList.isEmpty()) {
            out.println(1);
            Gson gson = new Gson();
            Order order = orderList.get(0);
            Customer customer = order.getIdc();
            String product = order.getGoods();
            out.println(gson.toJson(customer));
            out.println(product);
            orderList.remove(0);
        } else out.println(0);
    }

    void returnOrder(BufferedReader in) throws IOException {
        String order = in.readLine();
        offer.add(order);
    }

    void getInfo(BufferedReader in, PrintWriter out) throws IOException {
        int id = Integer.parseInt(in.readLine());
        out.println(customerList.get(findCustomer(id)).getPort());
        out.println(customerList.get(findCustomer(id)).getHost());
    }

    void register(PrintWriter out, BufferedReader in) throws IOException {

        User user = getClientData(in);
        String role = user.role;

        switch (role) {
            case "Customer" -> {
                out.println(idc);
                Customer customer = new Customer("Customer", user.host, idc, user.port);
                customerList.add(customer);
                userList.add(new User("Customer", idc));
                idc++;
            }
            case "Deliverer" -> {
                out.println(idd);
                Deliverer deliverer = new Deliverer("Deliverer", user.host, idd, user.port);
                userList.add(new User("Deliverer", idd));
                delivererList.add(deliverer);
                idd++;
            }
            case "Seller" -> {
                out.println(ids);
                Seller seller = new Seller("Seller", user.host, ids, user.port);
                userList.add(new User("Seller", ids));
                sellerList.add(seller);
                ids++;
            }
        }
    }

    public void giveDelivererInfo(PrintWriter out) {
        Gson gson = new Gson();
        out.println(gson.toJson(delivererList.get(0)));
    }

    public void giveSellerInfo(PrintWriter out) {
        Gson gson = new Gson();
        out.println(gson.toJson(sellerList.get(0)));
    }

    void unregister(BufferedReader in) throws IOException {

        User user = getClientData(in);
        String role = user.role;

        switch (role) {
            case "Customer" -> customerList.remove(findCustomer(user.id));
            case "Deliverer" -> delivererList.remove(findDeliverer(user.id));
            case "Seller" -> sellerList.remove(findSeller(user.id));
        }
    }

    private User getClientData(BufferedReader in) throws IOException {
        String customerData = in.readLine();
        Gson gson = new Gson();
        return gson.fromJson(customerData, User.class);
    }

    private int findCustomer(int id) {
        int wantedId = -1;
        for (Customer a : customerList) {
            wantedId++;
            if (a.getId() == id) {
                return wantedId;
            }
        }
        return wantedId;
    }

    private int findSeller(int id) {
        int wantedId = -1;
        for (Seller a : sellerList) {
            wantedId++;
            if (a.getId() == id) {
                return wantedId;
            }
        }
        return wantedId;
    }

    private int findDeliverer(int id) {
        int wantedId = -1;
        for (Deliverer a : delivererList) {
            wantedId++;
            if (a.getId() == id) {
                return wantedId;
            }
        }
        return wantedId;
    }

    void createOffer() {
        for (int i = 0; i < 20; i++) {
            String name = "towar" + i;
            offer.add(name);
        }
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<String> getOffer() {
        return offer;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
