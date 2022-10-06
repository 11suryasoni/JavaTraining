/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This PieChartExample Class is used implement Pie Chart from the CSV file.
@Author -> suryaps (Surya Prakash Soni)
 */

package RestApi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class PieChartExample extends JFrame {
    private static final long serialVersionUID = 6294689542092367723L;
    static final Logger logg = Logger.getLogger(PieChartExample.class);
    /***
     * This method create Pie chart with given Labels.
     */
    public PieChartExample(String title) {
        super(title);

        logg.info("CREATING PIE CHART -UI");
        PieDataset<String> dataset = createDataset();

        logg.debug("CALLING DATASET -METHOD");
        JFreeChart chart = ChartFactory.createPieChart(
                "Supply/Demand Percentage",
                dataset,
                true,
                true,
                false);

        logg.debug("CREATING -UI");
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "Coin- {0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        ((PiePlot<?>) chart.getPlot()).setLabelGenerator(labelGenerator);

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
        logg.info("UI CREATED");
    }

    /***
     * This method creates the data set for Pie Chart using CSV data.
     */
    private PieDataset<String> createDataset() {
        logg.info("PIE CHART  -CREATING DATASET");
        DefaultPieDataset<String> dataset= new DefaultPieDataset<>();
        try{
            List<String> lines = Files.readAllLines(Paths.get("/Users/azuga/Desktop/cryptocsv2.csv"));

                String p = lines.get(1);
                String[] arr = p.split(",");
                dataset.setValue(arr[1].replace("\"",""), (int)Double.parseDouble(arr[2]));
            logg.debug("PIE CHART - DATA - "+arr[1].replace("\"","")+" "+(int)Double.parseDouble(arr[2]));
            dataset.setValue(arr[5].replace("\"",""), (int)Double.parseDouble(arr[6]));
            logg.debug("PIE CHART - DATA - "+arr[5].replace("\"","")+" "+(int)Double.parseDouble(arr[6]));
                dataset.setValue(arr[9].replace("\"",""), (int)Double.parseDouble(arr[10]));
            logg.debug("PIE CHART - DATA - "+arr[9].replace("\"","")+" "+(int)Double.parseDouble(arr[10]));
                dataset.setValue(arr[13].replace("\"",""),(int)Double.parseDouble(arr[14]));
            logg.debug("PIE CHART - DATA - "+arr[13].replace("\"","")+" "+(int)Double.parseDouble(arr[14]));
                dataset.setValue(arr[17].replace("\"",""), (int)Double.parseDouble(arr[18]));
            logg.debug("PIE CHART - DATA - "+arr[17].replace("\"","")+" "+(int)Double.parseDouble(arr[18]));

            logg.info("PIE CHART -DATASET RETURN");
            return dataset;
        } catch (IOException e) {
            logg.error("INPUT EXCEPTION");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        logg.info("PIE CHART PROGRAM INVOKED");
        SwingUtilities.invokeLater(() -> {
            PieChartExample example = new PieChartExample("PERCENTAGE EXCHANGE");
            example.setSize(800, 400);

            logg.debug("PIE CHART -UI SIZE SET");
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}