package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CustomerServer extends Thread {

    private static int port;
    private static final String host = "localhost";
    private ServerSocket customerServer = new ServerSocket();
    private Customer customer;
    private boolean exit = false;

    public CustomerServer(Customer customer, int port) throws IOException {
        CustomerServer.port = port;
        this.customer = customer;
    }

    public static int getPort() {
        return port;
    }

    public static String getHost() {
        return host;
    }

    @Override
    public void run() {
        try {
            customerServer = new ServerSocket(port);
            while (!exit) {
                Socket socket = customerServer.accept();
                manageClientConnection(socket);
            }
        } catch (IOException e) {
            System.out.println("port taken ");
            System.exit(0);
        } catch (IllegalArgumentException a) {
            System.out.println("Port value out of range.");
            System.exit(0);
        }
    }

    public void manageClientConnection(Socket clientSocket) throws IOException {
        System.out.println("Client connected: " + clientSocket.getInetAddress());
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        while (true) {
            String string = in.readLine();
            if (string == null) break;
            int userData = Integer.parseInt(string);
            switch (userData) {
                case 1 -> customer.putOrder(in);
                case 2 -> customer.returnReceipt(in);
            }
        }
        clientSocket.close();
    }

    public void quit() throws IOException {
        exit = true;
        customerServer.close();
    }
}
