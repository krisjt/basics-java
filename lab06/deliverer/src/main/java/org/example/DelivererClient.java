package org.example;

import com.google.gson.Gson;
import org.example.data.Customer;
import org.example.data.Order;
import org.example.data.UserData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class DelivererClient {

    private final Deliverer deliverer;

    public DelivererClient(Deliverer deliverer) {
        this.deliverer = deliverer;
    }

    public void register(PrintWriter out, BufferedReader in) {
        try {
            Gson gson = new Gson();
            out.println(1);
            out.println(gson.toJson(new UserData(deliverer.getRole(), DelivererServer.getHost(), DelivererServer.getPort(), deliverer.getId())));
            deliverer.setId(Integer.parseInt(in.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void unregister(PrintWriter out) {
        Gson gson = new Gson();
        out.println(2);
        out.println(gson.toJson(new UserData(deliverer.getRole(), DelivererServer.getHost(), DelivererServer.getPort(), deliverer.getId())));
    }

    public boolean getOrder(BufferedReader in, PrintWriter out) throws IOException {
        out.println(5);
        int i = Integer.parseInt(in.readLine());
        if (i == 1) {
            Gson gson = new Gson();
            Customer customer = gson.fromJson(in.readLine(), Customer.class);
            String product = in.readLine();
            Order order = new Order(customer, product);
            deliverer.getOrderList().add(order);
            return true;
        }
        return false;
    }


    public void deliverOrder() {

        try(Socket deliverySocket = new Socket(deliverer.getOrderList().get(0).customer.getHost(), deliverer.getOrderList().get(0).customer.getPort())) {
            PrintWriter out = new PrintWriter(deliverySocket.getOutputStream(), true);
            out.println(1);
            out.println(deliverer.getOrderList().get(0).getProduct());
            deliverer.getOrderList().remove(0);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void returnOrder2(PrintWriter out) {
        out.println(6);
        int i = 0;
        while (deliverer.getToReturnOrderList().get(i) == null) {
            i++;
        }
        String product = deliverer.getToReturnOrderList().get(i).getProduct();
        deliverer.getToReturnOrderList().remove(i);
        out.println(product);
    }
}
