package org.example.GUI;

import org.example.Customer;
import org.example.CustomerClient;
import org.example.CustomerServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainFrame extends JFrame implements ActionListener {
    private final CustomerClient customerClient;
    private final Customer customer;
    private CustomerServer customerServer;
    private JPanel panel1;
    private JTextField textField1;
    private JButton acceptButton;
    private JButton registerButton;
    private JTextArea textArea1;
    private int port = 0;

    public MainFrame() throws IOException {
        setContentPane(panel1);
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        acceptButton.addActionListener(this);
        registerButton.addActionListener(this);
        customer = new Customer();
        customerClient = new CustomerClient(customer);

        this.setLocation(600,600);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(acceptButton)) {
            setPort();
            establishServer();
        }
        if (e.getSource().equals(registerButton)) {
            register(customerClient);
        }
    }

    private void setPort() {
        try {
            port = Integer.parseInt(textField1.getText());
        } catch (NumberFormatException e) {
            textArea1.setText("Input mismatch.");
        }
    }

    private void register(CustomerClient customerClient) {
        try {
            Socket customerSocket = new Socket("localhost", 6666);
            BufferedReader in = new BufferedReader(new InputStreamReader(customerSocket.getInputStream()));
            PrintWriter out = new PrintWriter(customerSocket.getOutputStream(), true);
            customerClient.register(out, in);
            customerSocket.close();
            SwingUtilities.invokeLater(() -> {
        try {
            new OrderManager(customer, customerClient, customerServer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            });
            this.dispose();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void establishServer() {
        try {
            if (port != 0) {
                customerServer = new CustomerServer(customer, port);
                customerServer.start();
                textArea1.setText("Customer server works at port " + port);
            } else {
                textArea1.setText("Invalid port number. Server not started.");
            }
        }  catch (IOException ex) {
        ex.printStackTrace();
    }
    }

}
