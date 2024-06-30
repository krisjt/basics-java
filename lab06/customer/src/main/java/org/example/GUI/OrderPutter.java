package org.example.GUI;

import org.example.Customer;
import org.example.CustomerClient;
import org.example.CustomerServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class OrderPutter extends JFrame implements ActionListener {


    private final JButton closeButton = new JButton("Close");
    private final CustomerClient customerClient;
    private final CustomerServer customerServer;
    private final Customer customer;
    private final JPanel buttonsPanel = new JPanel(new GridLayout(0, 1));
    private final Socket customerSocket = new Socket("localhost", 6666);
    private final PrintWriter out = new PrintWriter(customerSocket.getOutputStream(), true);
    private final BufferedReader in = new BufferedReader(new InputStreamReader(customerSocket.getInputStream()));
    private String[] offer;


    public OrderPutter(CustomerClient customerClient, CustomerServer customerServer, Customer customer) throws IOException {
        this.customerClient = customerClient;
        this.customer = customer;
        this.customerServer = customerServer;
        JScrollPane scrollPane = new JScrollPane(buttonsPanel);
        add(scrollPane, BorderLayout.CENTER);
        closeButton.addActionListener(this);
        add(closeButton, BorderLayout.SOUTH);
        setSize(800, 500);
        setText();
        this.setLocation(600,600);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(closeButton)) {
            this.dispose();
        }
    }

    private void setText() {
        getOffer();
        for (int i = 0; i < offer.length; i++) {
            String string = offer[i];
            buttonsPanel.add(createButton(string));
        }
        pack();
    }

    private JButton createButton(String string) {

        JButton button = new JButton("Order " + string);
        button.setPreferredSize(new Dimension(200, 50));
        button.addActionListener(e -> {
            customerClient.putOrder(out, string);
            try {
                SwingUtilities.invokeLater(() -> {
                    try {
                        new OrderManager(customer, customerClient, customerServer);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });

                new OrderManager(customer, customerClient, customerServer);
                quit();
                this.dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        return button;
    }

    private void getOffer() {
        offer = customerClient.getOffer(out, in);
    }

    private void quit() throws IOException {
        customerSocket.close();
        in.close();
        out.close();
    }
}
