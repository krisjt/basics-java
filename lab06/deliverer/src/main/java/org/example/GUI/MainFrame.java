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

public class MainFrame extends JFrame implements ActionListener {
    private final DelivererClient delivererClient;
    private final Deliverer deliverer;
    private DelivererServer delivererServer;
    private JPanel panel1;
    private JTextField textField1;
    private JButton acceptButton;
    private JTextArea textArea1;
    private JButton registerButton;
    private int port = 0;

    public MainFrame() throws IOException {
        setContentPane(panel1);
        setSize(800, 500);
        this.setLocation(40,40);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        acceptButton.addActionListener(this);
        registerButton.addActionListener(this);
        deliverer = new Deliverer();
        delivererClient = new DelivererClient(deliverer);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(acceptButton)) {
            setPort();
            establishServer();
        }
        if (e.getSource().equals(registerButton)) {
            register(delivererClient);
            this.dispose();
        }
    }

    private void setPort() {
        try {
            port = Integer.parseInt(textField1.getText());
        } catch (NumberFormatException e) {
            textArea1.setText("Input mismatch.");
        }
    }

    private void register(DelivererClient delivererClient) {
        try {
            Socket customerSocket = new Socket("localhost", 6666);
            BufferedReader in = new BufferedReader(new InputStreamReader(customerSocket.getInputStream()));
            PrintWriter out = new PrintWriter(customerSocket.getOutputStream(), true);
            delivererClient.register(out, in);
            customerSocket.close();
            SwingUtilities.invokeLater(() -> {
                try {
                    new ActionHolder(deliverer, delivererClient, delivererServer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void establishServer() {
        try {
            if (port != 0) {
                delivererServer = new DelivererServer(deliverer, port);
                delivererServer.start();
                textArea1.setText("Deliverer server works at port " + port);
            } else {
                textArea1.setText("Invalid port number. Server not started.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
