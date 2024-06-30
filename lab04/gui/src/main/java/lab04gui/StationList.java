package lab04gui;

import lab04client.measurementStation.MeasurementStation;
import lab04client.measurementStation.MeasurementStationsParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StationList extends JFrame implements ActionListener {
    JPanel buttonsPanel = new JPanel(new GridLayout(0, 1));


    StationList(){
        setVisible(true);
        setTitle("Station list");
        setTextArea1();
        JScrollPane scrollPane = new JScrollPane(buttonsPanel);
        add(scrollPane, BorderLayout.CENTER);
        setSize(800, 500);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void setTextArea1(){
        for(MeasurementStation o : MeasurementStationsParser.getMeasurementStationsList()){
            buttonsPanel.add(createButton(o.stationName,o.id));
        }
    }
    private JButton createButton(String element, int id) {
        JButton button = new JButton("See data for station " + element);
        button.setPreferredSize(new Dimension(200, 50));

        button.addActionListener(e -> new AirQualityIndex(id));

        return button;
    }

    public static void main(String[] args) {
        new StationList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
