/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This PieChartExample Class is used implement Pie Chart from the CSV file.
@Author -> suryaPs (Surya Prakash sonI)
 */
package OOPS;

import au.com.bytecode.opencsv.CSVReader;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
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
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
//import java.util.logging.Logger;

public class DifferentCharts extends JFrame implements InterfaceChart {

    private static final long serialVersionUID = 6294689542092367723L;
    static final Logger logg = Logger.getLogger(DifferentCharts.class);

    static int k = 0;

    // Creating Non Parameterized Constructor
    public DifferentCharts() {
    }

    /**
     * This method Creates the Area Chart.
     */
    private void initUiAreaChart(String[] myString) throws IOException {

        logg.info("CREATING AREA CHART -UI");
        CategoryDataset dataset = createDataSetAreaChart(myString);

        logg.info("AREA CHART -CREATING UI");
        JFreeChart chart = createChartAreaChart(dataset);
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
    private CategoryDataset createDataSetAreaChart(String[] myString) {

        logg.info("CREATING AREA CHART DATASET");
        String[] arr = new String[20];
        logg.warn("ONLY FOR 6 VALUE'S");
        for (int i = 0; i < 6; i++) {
            arr[i] = myString[i].replace("\"", "").replace(":", ",");
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
    private JFreeChart createChartAreaChart(CategoryDataset dataset) throws IOException {

        logg.info("SETTING UP AREA CHART UI DATA");
        JFreeChart chart = ChartFactory.createAreaChart("Trade Volume Chart", "Coins", "Volume Traded (24hr)", dataset, PlotOrientation.VERTICAL, false, true, true);
        ChartUtils.saveChartAsJPEG(new File(System.getProperty("user.dir") + "Directory1/areachart.jpeg"), chart, 500, 300);

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
    private void initUiBarChart() throws IOException {

        logg.info("INVOKING BAR GRAPH -UI");
        CategoryDataset dataset = createDataSetBarChart();

        logg.info("BAR GRAPH -PLOTTING");
        JFreeChart chart = createChartBarChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        logg.debug("CREATING CHART PANEL - 25 , 25 , 35 , 35 ");
        logg.debug("BACKGROUND - COLOR - WHITE");
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        ChartUtils.saveChartAsJPEG(new File(System.getProperty("user.dir") + "Directory1/barchart.jpeg"), chart, 500, 300);

    }

    /***
     * This method Create Data set for Bar Graph.
     */
    private CategoryDataset createDataSetBarChart() {

        logg.info("INITIALIZING BAR GRAPH DATASET");
        var dataset = new DefaultCategoryDataset();
        try {
            logg.warn("FILE PATH - " + System.getProperty("user.dir"));
            logg.error(System.getProperties());
            List<String> lines = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "/META-INF/Testnew3.csv"));
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
    private JFreeChart createChartBarChart(CategoryDataset dataset) {

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
    private DifferentCharts(String percentage_exchange) throws IOException {
        super(percentage_exchange);

        logg.info("CREATING PIE CHART -UI");
        PieDataset<String> dataset = createDataset();

        logg.debug("CALLING DATASET -METHOD");
        JFreeChart chart = ChartFactory.createPieChart("Supply/Demand Percentage", dataset, true, true, false);
        ChartUtils.saveChartAsJPEG(new File(System.getProperty("user.dir") + "Directory1/piechart.jpeg"), chart, 500, 300);

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
            List<String> lines = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "/META-INF/cryptocsv2.csv"));

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
        String[] areaStringArray = new String[0];
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
            areaStringArray = jsonObject1.toString().replace("{", "").replace("}", "").split(",");

        } catch (IOException e) {
            logg.error("IO ERROR - " + e);
            e.printStackTrace();
        } catch (InterruptedException e) {
            logg.error("INTERRUPTED ERROR - " + e);
            throw new RuntimeException(e);
        }
        return areaStringArray;
    }


    public void csvToHtml() {

        logg.info("INVOKING REST API");

        logg.warn("CSV FILE PATH ENTERED MANUALLY");
        logg.warn("HTML FILE PATH ENTERED");

        String csvFile = System.getProperty("user.dir") + "/META-INF/Testnew3.csv";
        String outputFile = System.getProperty("user.dir") + "Directory1/html_file.html";

        // Read lines of csv to a string array list

        logg.info("FILE READER INITIALIZED");

        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
                logg.debug("FILE DATA - " + currentLine);
            }
        } catch (IOException e) {
            logg.error("INPUT ERROR");
            e.printStackTrace();
        }

        // Adding <td> and <tr> tags for lines and columns.

        logg.info("ADDING TAGS");
        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, "<tr><td>" + lines.get(i) + "</td></tr>");
            lines.set(i, lines.get(i).replaceAll(",", "</td><td>"));
            logg.debug(lines);
        }

        // Adding <table> and </table> tags.

        logg.info("ADDING BORDERS");
        lines.set(0, "<table border>" + lines.get(0));
        lines.set(lines.size() - 1, lines.get(lines.size() - 1) + "</table>");

        logg.info("WRITING DATA INTO FILE");
        try (FileWriter writer = new FileWriter(outputFile)) {
            for (String line : lines) {
                writer.write(line + "\n");
                logg.debug("DATA - " + lines);
            }
        } catch (IOException e) {
            logg.error("INPUT ERROR");
            e.printStackTrace();
        }
    }

    public void csvToPdf() throws IOException, DocumentException {

        // Initializing a CSV reader to read CSV file.
        logg.info("INVOKING REST API");

        logg.warn("CSV FILE PATH ENTERED MANUALLY");
        logg.info("INITIALIZING CSV FILE READER");
        CSVReader reader = new CSVReader(new FileReader(System.getProperty("user.dir") + "/META-INF/Testnew3.csv"));

        // Creating a PDF file and Writing data using pdf writer and Setting page size.

        logg.debug("CREATING FILE AND SETTING PAGE SIZE");
        Document data = new Document();
        com.itextpdf.text.Rectangle rc = new com.itextpdf.text.Rectangle(8300, 8000);
        data.setPageSize(rc);

        logg.debug("PDF FILE PATH ENTERED");
        PdfWriter.getInstance(data, new FileOutputStream(System.getProperty("user.dir") + "Directory1/pdf_file.pdf"));
        data.open();

        CSVReader newReader = new CSVReader(new FileReader(System.getProperty("user.dir") + "/META-INF/Testnew3.csv"));
        int count = 0;
        while ((newReader.readNext()) != null) {
            count++;
        }

        logg.info("PDF WRITER INITIALIZED");
        PdfPTable myTable = new PdfPTable(count);
        PdfPCell cells;
        String[] Lines;
        while ((Lines = reader.readNext()) != null) {
            int i = 0;
            logg.debug("OBJECT ID - " + Lines[i]);
            while (i < count) {
                cells = new PdfPCell(new Phrase(Lines[i]));
                myTable.addCell(cells);
                i++;
            }
        }
        data.add(myTable);
        data.close();
        logg.info("FILE CLOSED");
    }


    public static String convertToXML(String jsonString, String root) throws JSONException {

        logg.info("CONVERT TO XML -INVOKED");
        JSONObject jsonObject = new JSONObject(jsonString);
        logg.info("STRING RETURNED");
        logg.debug("XML -DATA - " + XML.toString(jsonObject));
        if (k == 0)
            return "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>\n<roots>\n<" + root + ">" + XML.toString(jsonObject) + "</" + root + ">";
        else
            return XML.toString(jsonObject);
    }

    public void csvToXml() {
        logg.info("XML PROGRAM INVOKED");
        logg.warn("JSON FILE PATH ENTERED MANUALLY");
        String loc = System.getProperty("user.dir") + "/META-INF/test3.json";

        logg.debug("PATH - " + loc);
        String result;
        try {

            result = new String(Files.readAllBytes(Paths.get(loc)));
            String newJson = result.replaceAll("}\\{", "}},{{");
            String[] loopObj = newJson.split("},\\{");

            logg.debug("XML -DATA - " + newJson);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++, k++) {
                String xmlvalue = convertToXML(loopObj[i], "root");
                sb.append(xmlvalue.replaceAll("><", ">\n<"));
            }
            sb.append("\n</roots>");
            String res = sb.toString();

            logg.warn("XML -XML FILE PATH ENTERED MANUALLY");
            FileWriter file = new FileWriter(System.getProperty("user.dir") + "Directory1/test3XMLData.xml");

            logg.debug("XML - FILE PATH - " + file);
            file.write(res);
            file.flush();
            file.close();
            logg.info("XML -FILE  CLOSED SUCCESSFULLY");

        } catch (IOException e1) {
            logg.error("Input Error");
            e1.printStackTrace();
        } catch (JSONException e2) {
            logg.error("JSON FILE ERROR");
            e2.printStackTrace();
        }
    }

    public void createDirectory() {
        File file = new File(System.getProperty("user.dir") + "Directory1");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
    }

    public void areaChart() throws IOException {

        // Invoking Area Chart Method.
        String[] areastringArray = httpRequestMethod();
        DifferentCharts sd = new DifferentCharts();
        sd.initUiAreaChart(areastringArray);
    }

    public void barGraph() throws IOException {

        // Invoking Area Chart Method.
        DifferentCharts sd = new DifferentCharts();
        sd.initUiBarChart();
    }

    public void pieChart() {
        logg.info("PIE CHART PROGRAM INVOKED");
        SwingUtilities.invokeLater(() -> {
            DifferentCharts example;
            try {
                example = new DifferentCharts("PERCENTAGE EXCHANGE");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            example.setSize(800, 400);
            logg.debug("PIE CHART -UI SIZE SET");
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);

        });
    }


    private void zipFiles(File[] filePaths) {
        try {
            String zipFileName = System.getProperty("user.dir") + "Directory1/my.zip";

            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (File aFile : filePaths) {
                System.out.println(aFile+"   .......     ");
                zos.putNextEntry(new ZipEntry(new File(aFile.toURI()).getName()));
                byte[] bytes = Files.readAllBytes(Paths.get(aFile.toURI()));
                zos.write(bytes, 0, bytes.length);
                zos.closeEntry();
            }

            zos.close();

        } catch (FileNotFoundException ex) {
            System.err.println("A file does not exist: " + ex);
        } catch (IOException ex) {
            System.err.println("I/O error: " + ex);
        }
    }

    public void zipping() {
        File file = new File(System.getProperty("user.dir") + "Directory1");
        File[] allFiles = file.listFiles();

        assert allFiles != null;
        zipFiles(allFiles);
    }
}