package org.example.GUI;

import org.example.Deliverer;
import org.example.DelivererClient;
import org.example.DelivererServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ActionHolder extends JFrame implements ActionListener {
    private final DelivererClient delivererClient;
    private final Deliverer deliverer;
    private final DelivererServer delivererServer;
    private static final String host = "localhost";
    private Socket delivererSocket;
    private PrintWriter out;
    private BufferedReader in;
    private JPanel panel1;
    private JButton unRegisterButton;
    private JButton getOrderButton;
    private JButton deliverButton;
    private JButton returnButton;
    private JTextField textField1;

    public ActionHolder(Deliverer deliverer, DelivererClient delivererClient, DelivererServer delivererServer) throws IOException {
        setContentPane(panel1);
        setSize(800, 500);
        this.setLocation(40,40);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.deliverer = deliverer;
        this.delivererClient = delivererClient;
        this.delivererServer = delivererServer;

        deliverButton.addActionListener(this);
        getOrderButton.addActionListener(this);
        unRegisterButton.addActionListener(this);
        returnButton.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(unRegisterButton)) {
            unregister();
        }
        if (e.getSource().equals(getOrderButton)) {
            getOrder();
        }
        if (e.getSource().equals(deliverButton)) {
            deliver();
        }
        if (e.getSource().equals(returnButton)) {
            returnOrder();
        }
    }

    private void unregister() {
        try {
            delivererSocket = new Socket(host, 6666);
            out = new PrintWriter(delivererSocket.getOutputStream(), true);
            delivererClient.unregister(out);
            delivererServer.quit();
            close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }

    private void getOrder() {
        try {
            delivererSocket = new Socket(host, 6666);
        out = new PrintWriter(delivererSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(delivererSocket.getInputStream()));
        if (delivererClient.getOrder(in, out)) textField1.setText("Order taken.");
        else textField1.setText("There are no orders awaiting.");
        close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void deliver() {
        try {
            if (!deliverer.getOrderList().isEmpty()) {
            delivererClient.deliverOrder();
            textField1.setText("Delivered. Order List:" + deliverer.getOrderList());
            close();
        }
        else textField1.setText("There are no orders to be delivered.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void returnOrder() {
    try{
        if (!deliverer.getToReturnOrderList().isEmpty()) {
            delivererSocket = new Socket(host, 6666);
            out = new PrintWriter(delivererSocket.getOutputStream(), true);
            delivererClient.returnOrder2(out);
            textField1.setText("Returned. Orders that still need to be returned: " + deliverer.getToReturnOrderList());
            close();
        } else textField1.setText("There are no orders to be returned.");
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }

    private void close() throws IOException {
        in.close();
        out.close();
        delivererSocket.close();
    }
}
