//package lab04gui;
//
//import lab04client.measurementStation.MeasurementStationsParser;
//import lab04client.measurementStation.MeasurementStation;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class MeasurementStations extends JFrame implements ActionListener {
//    private JPanel panel1;
//    private JButton backButton;
//    private JTextArea textArea1;
//    private int id;
//
//    MeasurementStations(){
//        setContentPane(panel1);
//        setVisible(true);
//        backButton.addActionListener(this);
//        textArea1.setEditable(false);
//        setTextArea1();
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        pack();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource().equals(backButton)){
//            new MainFrame();
//            this.dispose();
//
//        }
//    }
//
//
//    private void setTextArea1(){
//        StringBuilder string = new StringBuilder("Here are the measurment stations = \n");
//        for(MeasurementStation o : MeasurementStationsParser.getMeasurementStationsList()){
//            string.append("Station name = ").append(o.stationName).append(", adress street = ").append(o.adressStreet).append(", id = ").append(o.id).append(", city = ").append(o.city.name).append(".\n");
//        }
//        System.out.println(string);
//        textArea1.setText(string.toString());
//    }
//
//}
