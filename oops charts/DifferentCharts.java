package OOPS;

import RestApi.PieChartExample;
import org.apache.log4j.Logger;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.AreaRendererEndType;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtils;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;

public class DifferentCharts extends JFrame implements InterfaceChart {

    static final Logger logg = Logger.getLogger(DifferentCharts.class);

    /**
     * This method Creates the Area Chart.
     */
    private void initUIareaChart(String[] mystring) throws IOException {

        logg.info("CREATING AREA CHART -UI");
        CategoryDataset dataset = createDatasetareaChart(mystring);

        logg.info("AREA CHART -CREATING UI");
        JFreeChart chart = createChartareaChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        logg.debug("SETTING BORDER - 15 , 15 , 15 , 15 ");
        chartPanel.setBackground(Color.white);
        logg.debug("BACKGROUND COLOR - WHITE");
        add(chartPanel);
        pack();
        setTitle("Crypto Vol chart");
        logg.debug("TITLE - " + getTitle());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logg.info("J_FRAME EXIT ON CLOSE");
        logg.info("AREA CHART -UI CREATED");
    }


    /***
     * This method defines the data set of Area Chart.
     */
    private CategoryDataset createDatasetareaChart(String[] mystring) {

        logg.info("CREATING AREA CHART DATASET");
        String[] arr = new String[20];
        logg.warn("ONLY FOR 6 VALUE'S");
        for (int i = 0; i < 6; i++) {
            arr[i] = mystring[i].replace("\"", "").replace(":", ",");
            logg.debug("AREA CHART - DATA - " + arr[i]);
        }
        String[] coins = new String[20];
        long[] values = new long[20];
        logg.info("DECLARING COINS ARRAY SIZE - " + coins.length);
        logg.info("DECLARING VALUES ARRAY SIZE - " + values.length);
        for (int i = 0; i < 6; i++) {
            String[] brr = arr[i].split(",");
            coins[i] = brr[0];
            values[i] = (long) Double.parseDouble(brr[1]);
            logg.debug("AREA CHART -COIN DATA - " + coins[i]);
            logg.debug("AREA CHART -VALUES - " + values[i]);
        }
        double[][] data = new double[][]{{values[0], values[1], values[3], values[4], values[5]}};
        logg.info("AREA CHART -DATA INSERTED INTO DATASET");

        return DatasetUtils.createCategoryDataset(new String[]{"Coins"}, new String[]{coins[0], coins[1], coins[3], coins[4], coins[5]}, data);
    }

    /**
     * This method defines the GUI of Area Chart.
     */
    private JFreeChart createChartareaChart(CategoryDataset dataset) throws IOException {

        logg.info("SETTING UP AREA CHART UI DATA");
        JFreeChart chart = ChartFactory.createAreaChart("Trade Volume Chart", "Coins", "Volume Traded (24hr)", dataset, PlotOrientation.VERTICAL, false, true, true);
        ChartUtils.saveChartAsJPEG(new File("/Users/azuga/Desktop/areachart.jpeg"), chart, 500, 300);

        logg.debug("TITLE - " + getTitle());
        logg.debug("CATEGORY AXIS LABEL - " + getAlignmentX());
        logg.debug("VALUE AXIS LABEL - " + getAlignmentY());

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setForegroundAlpha(0.3f);

        logg.info("AREA CHART -PLOTTING");
        AreaRenderer renderer = (AreaRenderer) plot.getRenderer();
        renderer.setEndType(AreaRendererEndType.LEVEL);

        logg.info("AREA CHART -RENDER");
        chart.setTitle(new TextTitle("Trade Volume Chart", new Font("Serif", java.awt.Font.BOLD, 18)));
        logg.info("AREA CHART CREATED SUCCESSFULLY");
        return chart;
    }

    /***
     * This method Create Bar Graph.
     */
    private void initUIbarChart() throws IOException {

        logg.info("INVOKING BAR GRAPH -UI");
        CategoryDataset dataset = createDatasetbarChart();

        logg.info("BAR GRAPH -PLOTTING");
        JFreeChart chart = createChartbarChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        logg.debug("CREATING CHART PANEL - 25 , 25 , 35 , 35 ");
        logg.debug("BACKGROUND - COLOR - WHITE");
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        ChartUtils.saveChartAsJPEG(new File("/Users/azuga/Desktop/barchart.jpeg"), chart, 500, 300);

    }

    /***
     * This method Create Data set for Bar Graph.
     */
    private CategoryDataset createDatasetbarChart() {

        logg.info("INITIALIZING BAR GRAPH DATASET");
        var dataset = new DefaultCategoryDataset();
        try {
            logg.warn("FILE PATH - \"/Users/azuga/Desktop/Testnew3.csv\"");
            List<String> lines = Files.readAllLines(Paths.get("/Users/azuga/Desktop/Testnew3.csv"));
            logg.warn("NO. OF LINES IN THE FILE  - " + lines.size());
            for (int i = 50; i < lines.size() - 16; i++) {
                logg.debug("BAR GRAPH -DATA - " + lines.get(i));
                String p = lines.get(i);
                String[] arr = p.split(",", '\n');
                String k = arr[3];
                k = k.replaceAll("\"", "");
                int value = (Integer.parseInt(k));
                logg.info("DATA MANIPULATION COMPLETED...");
                logg.debug("BAR GRAPH -DATA - " + value);
                dataset.setValue(value, "Object Name", arr[8] + i);
            }
            logg.debug("DATASET CREATED SUCCESSFULLY");
            return dataset;
        } catch (IOException e) {
            logg.error("INPUT ERROR");
            throw new RuntimeException(e);
        }
    }

    /***
     * This method Defines the GUI of Bar Graph.
     */
    private JFreeChart createChartbarChart(CategoryDataset dataset) {

        logg.info("BAR GRAPH -CREATION");
        JFreeChart barChart = ChartFactory.createBarChart("Metropolitan Museum of Art, AccessionYear/Object Name", "", "accessionYear", dataset, PlotOrientation.VERTICAL, true, true, false);
        logg.debug("TITLE - " + getTitle());
        logg.debug("CATEGORY AXIS LABEL - " + getAlignmentX());
        logg.debug("VALUE AXIS LABEL - " + getAlignmentY());
        logg.debug("BAR GRAPH -PLOTTING");

        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(1900, 2100);

        logg.info("BAR GRAPH -CREATED");
        return barChart;
    }


    /***
     * This method create Pie chart with given Labels.
     */
    private void PieChartExample() {

        logg.info("CREATING PIE CHART -UI");
        PieDataset<String> dataset = createDataset();

        logg.debug("CALLING DATASET -METHOD");
        JFreeChart chart = ChartFactory.createPieChart("Supply/Demand Percentage", dataset, true, true, false);

        logg.debug("CREATING -UI");
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("Coin- {0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
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
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get("/Users/azuga/Desktop/cryptocsv2.csv"));

            String p = lines.get(1);
            String[] arr = p.split(",");
            dataset.setValue(arr[1].replace("\"", ""), (int) Double.parseDouble(arr[2]));
            logg.debug("PIE CHART - DATA - " + arr[1].replace("\"", "") + " " + (int) Double.parseDouble(arr[2]));
            dataset.setValue(arr[5].replace("\"", ""), (int) Double.parseDouble(arr[6]));
            logg.debug("PIE CHART - DATA - " + arr[5].replace("\"", "") + " " + (int) Double.parseDouble(arr[6]));
            dataset.setValue(arr[9].replace("\"", ""), (int) Double.parseDouble(arr[10]));
            logg.debug("PIE CHART - DATA - " + arr[9].replace("\"", "") + " " + (int) Double.parseDouble(arr[10]));
            dataset.setValue(arr[13].replace("\"", ""), (int) Double.parseDouble(arr[14]));
            logg.debug("PIE CHART - DATA - " + arr[13].replace("\"", "") + " " + (int) Double.parseDouble(arr[14]));
            dataset.setValue(arr[17].replace("\"", ""), (int) Double.parseDouble(arr[18]));
            logg.debug("PIE CHART - DATA - " + arr[17].replace("\"", "") + " " + (int) Double.parseDouble(arr[18]));

            logg.info("PIE CHART -DATASET RETURN");
            return dataset;
        } catch (IOException e) {
            logg.error("INPUT EXCEPTION");
            throw new RuntimeException(e);
        }
    }


    private String[] httpRequestMethod() {

        // API Request and Response.
        String[] areastringArray = new String[0];
        try {

            String url = "https://api.coingecko.com/api/v3/global";
            logg.info("INVOKING BAR AND AREA CHART USING API URL - " + url);
            logg.info("SETTING HTTP REQUEST...GET");
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
            logg.info("SETTING HTTP CLIENT ...BUILD");
            HttpClient client = HttpClient.newBuilder().build();
            logg.debug("HTTP RESPONSE RECEIVED");

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());
            JSONObject jsonObject1 = jsonObject.getJSONObject("data").getJSONObject("total_volume");

            logg.debug("RESPONSE - " + response.body());
            areastringArray = jsonObject1.toString().replace("{", "").replace("}", "").split(",");

        } catch (IOException e) {
            logg.error("IO ERROR - " + e);
            e.printStackTrace();
        } catch (InterruptedException e) {
            logg.error("INTERRUPTED ERROR - " + e);
            throw new RuntimeException(e);
        }
        return areastringArray;
    }


    public void areaChart() throws IOException {

        // Invoking Area Chart Method.
        String[] areastringArray = httpRequestMethod();
        DifferentCharts sd = new DifferentCharts();
        sd.initUIareaChart(areastringArray);
    }

    public void barGraph() throws IOException {

        // Invoking Area Chart Method.
        DifferentCharts sd = new DifferentCharts();
        sd.initUIbarChart();
    }

    public void pieChart() {
        logg.info("PIE CHART PROGRAM INVOKED");
        SwingUtilities.invokeLater(() -> {
            PieChartExample example = new PieChartExample("PERCENTAGE EXCHANGE");
            example.setSize(800, 400);

            logg.debug("PIE CHART -UI SIZE SET");
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);

            DifferentCharts sd = new DifferentCharts();
            sd.PieChartExample();
        });
    }
}