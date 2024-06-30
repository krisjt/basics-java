package org.example;

import org.example.GUI.MainFrame;

import javax.swing.*;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new MainFrame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}