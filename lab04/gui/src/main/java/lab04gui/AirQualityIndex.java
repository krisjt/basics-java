package lab04gui;

import lab04client.aqIndex.AirQualityColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AirQualityIndex extends JFrame implements ActionListener {

    private Map<Integer, Color> idColorMap;
    private final int id;

    public AirQualityIndex(int id) {
        this.id = id;

        setTitle("Air quality index");

        initializeIdColorMap();

        JButton closeButton = new JButton("Sensor list");
        closeButton.addActionListener(this);

        JPanel labelsPanel = new JPanel(new GridLayout(0, 1));
        for (AirQualityColor airQualityColor : AirQualityColor.getColorList(id)) {
            JLabel label = createLabel(airQualityColor);
            labelsPanel.add(label);
        }

        JPanel legendPanel = createLegendPanel();

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(labelsPanel, BorderLayout.CENTER);
        containerPanel.add(legendPanel, BorderLayout.EAST);

        add(containerPanel, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);

        setSize(600, 300);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeIdColorMap() {
        idColorMap = new HashMap<>();
        idColorMap.put(-1, new Color(150,150,150));
        idColorMap.put(0, new Color(4,194,143));
        idColorMap.put(1, new Color(23,191,11));
        idColorMap.put(2, new Color(164,191,11));
        idColorMap.put(3, new Color(247,223,5));
        idColorMap.put(4, new Color(247,154,5));
        idColorMap.put(5, new Color(247,74,5));
    }

    private JLabel createLabel(AirQualityColor myObject) {
        JLabel label = new JLabel(myObject.getName());
        label.setOpaque(true);
        label.setBackground(idColorMap.getOrDefault(myObject.getIndex(), Color.BLACK));

        return label;
    }
    private JPanel createLegendPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Map.Entry<Integer, Color> entry : idColorMap.entrySet()) {
            JLabel legendLabel = new JLabel("Index " + entry.getKey());
            legendLabel.setOpaque(true);
            legendLabel.setBackground(entry.getValue());
            panel.add(legendLabel);
        }

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        disposeChartPanel();
    }
    private void disposeChartPanel() {
        new SensorList(id);
        this.dispose();
    }
}
