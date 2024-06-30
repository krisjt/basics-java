package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class KeeperServer implements Runnable {

    private final Keeper keeper;
    private ServerSocket keeperServer;
    private boolean exit = false;

    public KeeperServer(Keeper keeper) {
        this.keeper = keeper;
    }

    @Override
    public void run() {
        try {
            int port = 6666;
            keeperServer = new ServerSocket(port);
            keeper.createOffer();
            System.out.println("Server running on port: " + port);
            while (!exit) {
                Socket socket = keeperServer.accept();
                manageClientConnection(socket, keeperServer);

            }
        } catch (IOException ignored) {
        }
    }


    public void manageClientConnection(Socket clientSocket, ServerSocket socket) throws IOException {
        System.out.println("Client connected: " + clientSocket.getInetAddress());
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        while (true) {
            String string = in.readLine();
            if (string == null) break;
            int userData = Integer.parseInt(string);
            switch (userData) {
                case 1 -> keeper.register(out, in);
                case 2 -> keeper.unregister(in);
                case 3 -> keeper.getOffer(out);
                case 4 -> keeper.putOrder(in);
                case 5 -> keeper.getOrder(out);
                case 6 -> keeper.returnOrder(in);
                case 7 -> keeper.getInfo(in, out);
                case 8 -> keeper.giveDelivererInfo(out);
                case 9 -> keeper.giveSellerInfo(out);
            }
        }
        clientSocket.close();

    }

    public void quit() throws IOException {
        exit = true;
        keeperServer.close();
    }
}
