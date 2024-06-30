package org.example;

import com.google.gson.Gson;
import org.example.data.CustomerModel;
import org.example.data.Deliverer;
import org.example.data.Seller;
import org.example.data.UserData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomerClient {

    private final Customer customer;

    public CustomerClient(Customer customer) {
        this.customer = customer;
    }

    public void register(PrintWriter out, BufferedReader in) {
        try {
            Gson gson = new Gson();
            out.println(1);
            out.println(gson.toJson(new UserData(customer.getRole(), CustomerServer.getHost(), CustomerServer.getPort(), customer.getId())));
            customer.setId(Integer.parseInt(in.readLine()));
            System.out.println("registered");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void unregister(PrintWriter out) {
        Gson gson = new Gson();
        out.println(2);
        out.println(gson.toJson(new UserData(customer.getRole(), CustomerServer.getHost(), CustomerServer.getPort(), customer.getId())));
    }

    public String[] getOffer(PrintWriter out, BufferedReader in) {
        try {
            Gson gson = new Gson();
            out.println(3);
            String offer = in.readLine();
            String[] offer1 = gson.fromJson(offer, String[].class);
            offer = "";
            for (String s : offer1) {
                offer += (s + " ");
            }
            System.out.println(offer);
            return offer1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    public void putOrder(PrintWriter out, String product) {
        Gson gson = new Gson();
        CustomerModel customerModel = new CustomerModel(customer.getRole(), CustomerServer.getHost(), customer.getId(), CustomerServer.getPort());
        out.println(4);
        out.println(gson.toJson(customerModel));
        out.println(product);
    }

    public void returnOrder(PrintWriter out, int id) {
        Gson gson = new Gson();
        CustomerModel customerModel = new CustomerModel(customer.getRole(), CustomerServer.getHost(), id, CustomerServer.getPort());
        out.println(customer.getMyOrders().get(id));
        out.println(gson.toJson(customerModel));
    }

    public void acceptOrder(PrintWriter out, String way, CustomerModel customerModel, String product) throws IOException {
        Gson gson = new Gson();
        out.println(gson.toJson(customerModel));
        out.println(product);
        out.println(way);
        customer.getMyOrders().remove(product);
    }

    public Seller getSeller(BufferedReader in, PrintWriter out) throws IOException {
        Gson gson = new Gson();
        out.println(9);
        return gson.fromJson(in.readLine(), Seller.class);
    }

    public Deliverer getDeliverer(BufferedReader in, PrintWriter out) throws IOException {
        out.println(8);
        Gson gson = new Gson();
        return gson.fromJson(in.readLine(), Deliverer.class);
    }
}
