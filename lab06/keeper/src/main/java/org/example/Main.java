package org.example;

import org.example.GUI.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Keeper keeper = new Keeper();
        KeeperServer keeperServer = new KeeperServer(keeper);
        SwingUtilities.invokeLater(() -> new MainFrame(keeper, keeperServer));
        keeperServer.run();
    }
}