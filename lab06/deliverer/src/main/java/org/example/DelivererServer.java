package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class DelivererServer extends Thread {//nasluchuje od customera i keeperea
    private static int port;
    private static final String host = "localhost";
    private ServerSocket delivererServer = new ServerSocket();
    private boolean exit = false;
    private final Deliverer deliverer;

    public DelivererServer(Deliverer deliverer, int port) throws IOException {
        DelivererServer.port = port;
        this.deliverer = deliverer;
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
            delivererServer = new ServerSocket(port);
            while (!exit) {
                Socket socket = delivererServer.accept();
                manageClientConnection(socket);
            }
        } catch (IOException e) {
            System.out.println("PORT TAKEN");
            System.exit(0);
        }
    }

    public void manageClientConnection(Socket clientSocket) throws IOException {
        System.out.println("Client connected: " + clientSocket.getInetAddress());
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        deliverer.returnOrder(in);
        clientSocket.close();
    }

    public void quit() throws IOException {
        exit = true;
        delivererServer.close();
    }
}
