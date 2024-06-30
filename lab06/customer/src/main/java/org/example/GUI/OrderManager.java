package org.example.GUI;

import org.example.Customer;
import org.example.CustomerClient;
import org.example.CustomerServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class OrderManager extends JFrame implements ActionListener {
    private final Customer customer;
    private final CustomerClient customerClient;
    private Socket customerSocket;
    private PrintWriter out;
    private final CustomerServer customerServer;
    private JPanel panel1;
    private JButton putOrderButton;
    private JButton unregisterButton;
    private JButton returnOrderButton;
    private JButton receiptInfoButton;

    public OrderManager(Customer customer, CustomerClient customerClient, CustomerServer customerServer) throws IOException {
        setContentPane(panel1);
        setSize(800, 500);
        this.setLocation(600,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.customer = customer;
        this.customerClient = customerClient;
        this.customerServer = customerServer;

        putOrderButton.addActionListener(this);
        unregisterButton.addActionListener(this);
        returnOrderButton.addActionListener(this);
        receiptInfoButton.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(putOrderButton)) {
            SwingUtilities.invokeLater(() -> {
                try {
                    new OrderPutter(customerClient, customerServer, customer);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            this.dispose();
        }
        if (e.getSource().equals(returnOrderButton)) {
            SwingUtilities.invokeLater(() -> {
                try {
                    new OrderReturner(customerClient, customerServer, customer);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            this.dispose();
        }
        if (e.getSource().equals(unregisterButton)) {
            unregister();
        }
        if (e.getSource().equals(receiptInfoButton)) {
            SwingUtilities.invokeLater(() -> {
                try {
                    new ReceiptInfo(customerClient, customerServer, customer);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            this.dispose();
        }
    }

    private void unregister() {
        try {
            customerSocket = new Socket("localhost", 6666);
        out = new PrintWriter(customerSocket.getOutputStream(), true);
        customerClient.unregister(out);
        customerServer.quit();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }

}
