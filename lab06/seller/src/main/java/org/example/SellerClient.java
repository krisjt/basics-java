package org.example;

import com.google.gson.Gson;
import org.example.data.UserData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SellerClient {

    private final Seller seller;

    public SellerClient(Seller seller) {
        this.seller = seller;
    }

    public void register(PrintWriter out, BufferedReader in) {
        try {
            Gson gson = new Gson();
            out.println(1);
            out.println(gson.toJson(new UserData("Seller", "localhost", SellerServer.getPort(), seller.getId())));
            seller.setId(Integer.parseInt(in.readLine()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unregister(PrintWriter out) {
        Gson gson = new Gson();
        out.println(2);
        out.println(gson.toJson(new UserData("Seller", "localhost", SellerServer.getPort(), seller.getId())));
    }

    public void returnReceipt() throws IOException {
       try(Socket socket = new Socket(seller.getOrderList().get(0).customer.host, seller.getOrderList().get(0).customer.port)) {
           PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
           switch (seller.getOrderList().get(0).getWay()) {
               case "return" -> {
                   out.println(2);
                   out.println("Thank u for returning " + seller.getOrderList().get(0).product + ", it has been given to deliverer.");
               }
               case "buy" -> {
                   out.println(2);
                   out.println("Thank u for ordering " + seller.getOrderList().get(0).product + ", it has been successfully ordered.");
               }
           }
           seller.getOrderList().remove(0);
       } catch (Exception ex) {
           ex.printStackTrace();
       }
    }
}

