package gui;

import data.Service;
import data.Supplier;

import javax.swing.*;


import static data.Service.fence;
import static data.Service.paintersList;

public class MainFrame extends JFrame {
    private JPanel panel1;
    private JTextArea textArea1;

    public MainFrame(Supplier supplier) {
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 500);
        setLocationRelativeTo(null);
        textArea1.setLineWrap(true);
        textArea1.setEditable(false);

        new Thread(() -> {
            while (true) {
                String finalString1 = createText(supplier);
                SwingUtilities.invokeLater(() -> textArea1.setText(finalString1));
            }
        }).start();
    }
    //asdast
    public static void main(String[] args) {
        Supplier supplier = new Supplier();
        supplier.start();
        Service.fenceInitialization(supplier);

        SwingUtilities.invokeLater(()->{
            MainFrame frame = new MainFrame(supplier);
            frame.setVisible(true);
        });

        Service.waitForPaintersToFinish(paintersList);
        supplier.interrupt();
    }

    public String createText(Supplier supplier){
        StringBuilder finalString = new StringBuilder();
        if (supplier.paintBucket.isBeingSupplied())
            finalString.append("S | ").append(supplier.paintBucket.getValue()).append(" | ").append(supplier.paintBucket.getCurrentlyDrawn()).append("\n");
        else
            finalString.append(". | ").append(supplier.paintBucket.getValue()).append(" | ").append(supplier.paintBucket.getCurrentlyDrawn()).append("\n");
        for (int i = 0; i < paintersList.size(); i++) {
            finalString.append(paintersList.get(i).getPainterName()).append(" | ");
        }
        finalString.append("\n");
        for (int i = 0; i < paintersList.size(); i++) {
            finalString.append(paintersList.get(i).getBucketContent()).append(" | ");
        }
        finalString.append("\n");
        finalString.append(fence);
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return finalString.toString();
    }
}
