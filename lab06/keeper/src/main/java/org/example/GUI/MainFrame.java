package org.example.GUI;

import org.example.Keeper;
import org.example.KeeperServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainFrame extends JFrame implements ActionListener {
    private final Keeper keeper;
    private final KeeperServer keeperServer;
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button5;
    private JButton button6;

    public MainFrame(Keeper keeper, KeeperServer keeperServer) {
        setContentPane(panel1);
        setSize(800, 500);
        this.setLocation(600,40);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.keeper = keeper;
        this.keeperServer = keeperServer;

        button1.addActionListener(this);
        button2.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(button1)) {
            SwingUtilities.invokeLater(() -> new OfferList(keeper, keeperServer));
            this.dispose();
        }
        if (e.getSource().equals(button2)) {
            SwingUtilities.invokeLater(() -> new OrderList(keeper, keeperServer));
            this.dispose();
        }
        if (e.getSource().equals(button5)) {
            SwingUtilities.invokeLater(() -> new UserList(keeper, keeperServer));
            this.dispose();
        }
        if (e.getSource().equals(button6)) {
            try {
                keeperServer.quit();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            this.dispose();

        }

    }
}
