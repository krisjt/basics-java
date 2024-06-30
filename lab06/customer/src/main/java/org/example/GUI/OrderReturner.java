package org.example.GUI;

import org.example.Customer;
import org.example.CustomerClient;
import org.example.CustomerServer;
import org.example.data.CustomerModel;
import org.example.data.Deliverer;
import org.example.data.Seller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class OrderReturner extends JFrame implements ActionListener {
    private final JButton closeButton = new JButton("Close");
    private final CustomerClient customerClient;
    private final CustomerServer customerServer;
    private final Customer customer;
    private final JPanel buttonsPanel = new JPanel(new GridLayout(0, 1));
    private Socket customerSocket;
    private PrintWriter out;
    private BufferedReader in;
    private List<String> myOrders;
    private Deliverer deliverer;
    private Seller seller;
    private String host = "localhost";


    public OrderReturner(CustomerClient customerClient, CustomerServer customerServer, Customer customer) throws IOException {
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
        if (e.getSource().equals(closeButton)) this.dispose();
    }

    private void setText() {
        getOrders();
        int i = 0;
        for (String o : myOrders) {
            buttonsPanel.add(createButton(o, i));
            i++;
        }
        pack();
    }

    private JButton createButton(String string, int id) {
        JButton button = new JButton("Return " + string);
        button.setPreferredSize(new Dimension(200, 50));
        button.addActionListener(e -> {
            try {
                findDeliverer();
                findSeller();
                returnOrder(id);
                acceptOrder(id);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                SwingUtilities.invokeLater(() -> {
                    try {
                        new OrderManager(customer, customerClient, customerServer);
                        this.dispose();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                quit();
                this.dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        return button;
    }

    private void getOrders() {
        myOrders = customer.getMyOrders();
    }

    private void quit() throws IOException {
        customerSocket.close();
        in.close();
        out.close();
    }

    private void returnOrder(int id) throws IOException {
        customerSocket = new Socket(deliverer.getHost(), deliverer.getPort());
        out = new PrintWriter(customerSocket.getOutputStream(), true);
        customerClient.returnOrder(out, id);
        customerSocket.close();
        out.close();
    }

    private void acceptOrder(int id) throws IOException {
        customerSocket = new Socket(seller.getHost(), seller.getPort());
        in = new BufferedReader(new InputStreamReader(customerSocket.getInputStream()));
        out = new PrintWriter(customerSocket.getOutputStream(), true);
        customerClient.acceptOrder(out, "return", new CustomerModel("Customer", host, customer.getId(), CustomerServer.getPort()), customer.getMyOrders().get(id));
        customerSocket.close();
        in.close();
        out.close();
    }

    private void findDeliverer() throws IOException {
        customerSocket = new Socket(host, 6666);
        out = new PrintWriter(customerSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(customerSocket.getInputStream()));
        deliverer = customerClient.getDeliverer(in, out);
        customerSocket.close();
        in.close();
        out.close();
    }

    private void findSeller() throws IOException {
        try(Socket customerSocket = new Socket(host, 6666)) {
            PrintWriter out = new PrintWriter(customerSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(customerSocket.getInputStream()));
            seller = customerClient.getSeller(in, out);
            customerSocket.close();
            in.close();
            out.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

