//package lab04gui;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class MainFrame extends JFrame implements ActionListener {
//    private JPanel panel1;
//    private JButton chartButton;
//    private JButton measurementStationButton;
//    private JButton seeAirQualityButton;
//    private JButton chooseMeasurementSensorButton;
//    private JButton exitButton;
//
//    public MainFrame(){
//        setContentPane(panel1);
//        setVisible(true);
//        exitButton.addActionListener(this);
//        measurementStationButton.addActionListener(this);
//        chooseMeasurementSensorButton.addActionListener(this);
//        chartButton.addActionListener(this);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        pack();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource().equals(exitButton)){
//            this.dispose();
//        }
//        if(e.getSource().equals(measurementStationButton)){
//            new MeasurementStations();
//            this.dispose();
//        }
//        if(e.getSource().equals(chooseMeasurementSensorButton)){
//            new MeasurementSensorChoice();
//            this.dispose();
//        }
//        if(e.getSource().equals(chartButton)){
//            new DataChoice();
//            this.dispose();
//        }
//    }
//
//    public static void main(String[] args) {
//        new MainFrame();
//    }
//}
