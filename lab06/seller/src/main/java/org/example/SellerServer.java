package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SellerServer extends Thread {
    private static int port;
    private ServerSocket serverSocket = new ServerSocket();
    private final Seller seller;
    private boolean exit = false;

    public SellerServer(Seller seller, int port) throws IOException {
        SellerServer.port = port;
        this.seller = seller;
    }

    public static int getPort() {
        return port;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Seller server works at port " + port);
            while (!exit) {
                Socket socket = serverSocket.accept();
                manageClientConnection(socket);

            }
        } catch (IOException ignored) {
            System.out.println("Port taken ");
            System.exit(0);
        }
    }

    public void manageClientConnection(Socket clientSocket) throws IOException {
        System.out.println("Client connected: " + clientSocket.getInetAddress());
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        seller.acceptOrder(in);
        clientSocket.close();
    }

    public void quit() throws IOException {
        exit = true;
        serverSocket.close();
    }
}
