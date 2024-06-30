package org.example.GUI;

import org.example.Customer;
import org.example.CustomerClient;
import org.example.CustomerServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ReceiptInfo extends JFrame implements ActionListener {
    private final CustomerClient customerClient;
    private final CustomerServer customerServer;
    private final Customer customer;
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton closeButton;

    public ReceiptInfo(CustomerClient customerClient, CustomerServer customerServer, Customer customer) throws IOException {
        setContentPane(panel1);
        this.customerClient = customerClient;
        this.customer = customer;
        this.customerServer = customerServer;
        closeButton.addActionListener(this);
        setSize(800, 500);
        this.setLocation(600,600);
        setText();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(closeButton)) {
            SwingUtilities.invokeLater(() -> {
                try {
                    new OrderManager(customer, customerClient, customerServer);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            this.dispose();
        }
    }

    private void setText() {
        textArea1.setText(customer.getReceiptInfo());
    }
}
