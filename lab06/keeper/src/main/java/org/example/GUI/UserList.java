package org.example.GUI;

import org.example.Keeper;
import org.example.KeeperServer;
import org.example.data.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserList extends JFrame implements ActionListener {
    private final Keeper keeper;
    private final KeeperServer keeperServer;
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton backButton;

    public UserList(Keeper keeper, KeeperServer keeperServer) {
        setContentPane(panel1);
        setSize(800, 500);
        this.setLocation(600,40);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.keeper = keeper;
        this.keeperServer = keeperServer;
        setText();

        backButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(backButton)) {
            SwingUtilities.invokeLater(() -> new MainFrame(keeper, keeperServer));
            this.dispose();
        }
    }

    private void setText() {
        String string = "";
        for (User o : keeper.getUserList()) {
            string += o.role + ", id = " + o.id + "\n";
        }
        textArea1.setText(string);
    }
}
