package lab04gui;

import lab04client.measurementStation.MeasurementSensor;
import lab04client.measurementStation.MeasurementStationsParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SensorList extends JFrame implements ActionListener {
    private final JButton closeButton = new JButton("Close");
    JPanel buttonsPanel = new JPanel(new GridLayout(0, 1));

    SensorList(int id){
        setVisible(true);
        setTitle("Sensor list");
        setText(id);
        JScrollPane scrollPane = new JScrollPane(buttonsPanel);
        add(scrollPane, BorderLayout.CENTER);
        setSize(800, 500);
        this.setLocationRelativeTo(null);
        closeButton.addActionListener(this);
        add(closeButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(closeButton)){
            this.dispose();
        }
    }

    private void setText(int id){
        for(MeasurementSensor o : MeasurementStationsParser.getMeasurementStation(id)){
            String string2 = "Sensor id = " + o.id + ", parameter name = " + o.param.paramName;
            buttonsPanel.add(createButton(string2,o.id));
        }
        pack();
    }
    private JButton createButton(String string, int id) {
        JButton button = new JButton("See data chart for sensor: " + string);
        button.setPreferredSize(new Dimension(200, 50));

        button.addActionListener(e -> new DataChart(id));

        return button;
    }

}
