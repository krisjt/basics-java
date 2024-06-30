package org.example.GUI;

import org.example.Seller;
import org.example.SellerClient;
import org.example.SellerServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ActionHolder extends JFrame implements ActionListener {
    private final SellerClient sellerClient;
    private final Seller seller;
    private final SellerServer sellerServer;
    private Socket sellerSocket;
    private PrintWriter out;
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JTextField textField1;

    public ActionHolder(Seller seller, SellerClient sellerClient, SellerServer sellerServer) {
        setContentPane(panel1);
        setSize(800, 500);
        this.setLocation(40,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.seller = seller;
        this.sellerClient = sellerClient;
        this.sellerServer = sellerServer;

        button1.addActionListener(this);
        button2.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(button1)) {
            returnReceipt();
        }
        if (e.getSource().equals(button2)) {
            unregister();
        }
    }

    private void unregister() {
        try {
            sellerSocket = new Socket("localhost", 6666);
            out = new PrintWriter(sellerSocket.getOutputStream(), true);
            sellerClient.unregister(out);
            sellerServer.quit();
            close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
                System.exit(0);
        }

    private void returnReceipt(){
        if (!seller.getOrderList().isEmpty()) {
            try {
                sellerClient.returnReceipt();
                textField1.setText("Receipt given.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else textField1.setText("There are no customers awaiting for receipt.");
    }

    private void close() throws IOException {
        out.close();
        sellerSocket.close();
    }
}
