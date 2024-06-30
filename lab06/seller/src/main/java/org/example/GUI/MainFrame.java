package org.example.GUI;

import org.example.Seller;
import org.example.SellerClient;
import org.example.SellerServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainFrame extends JFrame implements ActionListener {
    private final SellerClient sellerClient;
    private final Seller seller;
    private JPanel panel1;
    private JTextField textField1;
    private JButton button1;
    private JTextArea textArea1;
    private JButton button2;
    private int port = 0;
    private SellerServer sellerServer;

    public MainFrame() throws IOException {
        setContentPane(panel1);
        setSize(800, 500);
        this.setLocation(40,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        button1.addActionListener(this);
        button2.addActionListener(this);
        seller = new Seller();
        sellerClient = new SellerClient(seller);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(button1)) {
            setPort();
            establishServer();
        }
        if (e.getSource().equals(button2)) {
            register(sellerClient);
        }
    }

    private void setPort() {
        try {
            port = Integer.parseInt(textField1.getText());
        } catch (NumberFormatException e) {
            textArea1.setText("Input mismatch.");
        }
    }

    private void register(SellerClient delivererClient) {
        try {
            Socket customerSocket = new Socket("localhost", 6666);
            BufferedReader in = new BufferedReader(new InputStreamReader(customerSocket.getInputStream()));
            PrintWriter out = new PrintWriter(customerSocket.getOutputStream(), true);
            delivererClient.register(out, in);
            customerSocket.close();
            SwingUtilities.invokeLater(() -> new ActionHolder(seller, sellerClient, sellerServer));
        this.dispose();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }

    private void establishServer() {
        try {
            if (port != 0) {
                sellerServer = new SellerServer(seller, port);
                sellerServer.start();
                textArea1.setText("Seller server works at port " + port);
            } else {
                textArea1.setText("Invalid port number. Server not started.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
