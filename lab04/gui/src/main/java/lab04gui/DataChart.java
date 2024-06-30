package lab04gui;

import lab04client.data.DataParser;
import lab04client.data.Values;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataChart extends JFrame implements ActionListener {

    public DataChart(int id) {
        setTitle("Data chart");
        setSize(600, 400);
        this.setLocationRelativeTo(null);
        add(createChartPanel(id));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createChartPanel(int id) {
        JFreeChart chart = createBarChart(id);
        ChartPanel chartPanel = new ChartPanel(chart);
        JButton closeButton = new JButton("Close Chart");

        chartPanel.setPreferredSize(new Dimension(500, 300));
        closeButton.addActionListener(this);

        add(closeButton, BorderLayout.SOUTH);

        return chartPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        disposeChartPanel();
    }
    private void disposeChartPanel() {
        this.dispose();
    }

    private JFreeChart createBarChart(int id) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(Values o: DataParser.getRestData(id).values){
            dataset.addValue(o.value, "Value" , o.date);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                DataParser.getRestData(id).key,
                "Date of measurement",
                "Values",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );
        CategoryPlot plot = (CategoryPlot) barChart.getPlot();

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);


        return barChart;
    }

}