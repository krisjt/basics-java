//package lab04gui;
//
//import lab04client.measurementStation.MeasurementStationsParser;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class MeasurementSensor extends JFrame  implements ActionListener{
//    private JPanel panel1;
//    private JButton button1;
//    private JTextArea textArea1;
//    private int id;
//    private int sensorID;
//
//    MeasurementSensor(int id){
//        this.id = id;
//        setContentPane(panel1);
//        setVisible(true);
//        button1.addActionListener(this);
//        textArea1.setEditable(false);
//        setText(id);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        pack();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource().equals(button1)){
//            this.dispose();
//            new MainFrame();
//        }
//    }
//
//    private void setText(int id){
//        StringBuilder string = new StringBuilder("Here are the measurment stations, available in the measurement station with id = " + id + " = \n");
//        for(lab04client.measurementStation.MeasurementSensor o : MeasurementStationsParser.getMeasurementStation(id)){
//            string.append("Sensor id = ").append(o.id).append(", parameter name = ").append(o.param.paramName).append(", parameter id = ").append(o.param.idParam).append(", parameter formula = ").append(o.param.paramFormula).append(", parameter code = ").append(o.param.paramCode).append(".\n");
//            sensorID = o.id;
//        }
//        textArea1.setText(string.toString());
//        pack();
//    }
//}
