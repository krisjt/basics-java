//package lab04gui;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class MeasurementSensorChoice extends JFrame implements ActionListener {
//
//    private JTextField textField2;
//    private JButton confirmButton;
//    private JPanel panel1;
//    private JButton exitButton;
//
//    public MeasurementSensorChoice(){
//        setContentPane(panel1);
//        setVisible(true);
//        confirmButton.addActionListener(this);
//        exitButton.addActionListener(this);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        pack();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//            if (e.getSource().equals(confirmButton)) {
//                new MeasurementSensor(Integer.parseInt(textField2.getText()));
//                this.dispose();
//            }
//        if (e.getSource().equals(exitButton)) {
//            new MainFrame();
//            this.dispose();
//        }
//        }
//    }
//
