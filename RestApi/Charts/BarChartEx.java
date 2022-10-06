package RestApi;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BarChartEx extends JFrame {

    public BarChartEx() {

        initUI();
    }

    private void initUI() {

        CategoryDataset dataset = createDataset();

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 35, 35));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Bar chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private CategoryDataset createDataset() {
        var dataset = new DefaultCategoryDataset();
    try{
        List<String> lines = Files.readAllLines(Paths.get("/Users/azuga/Desktop/Testnew3.csv"));
        for (int i = 50; i < lines.size()-2; i++) {
            String p = lines.get(i);
            String[] arr = p.split(",", '\n');
            String k = arr[3];
            k = k.replaceAll("\"", "");
            int value = (Integer.parseInt(k));

            //System.out.println(value);

            dataset.setValue(value, "accessionYear", "objectID" + i);

        }
        return dataset;
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    }

        private JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Metropolitan Museum of Art, AccessionYear/ObjectId",
                "",
                "accessionYear",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        return barChart;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new BarChartEx();
            ex.setVisible(true);
        });
    }
}
