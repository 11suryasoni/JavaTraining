/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This PieChartExample Class is used implement Pie Chart from the CSV file.
@Author -> suryaPs (Surya Prakash sonI)
 */
package OOPS;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

public interface InterfaceChart {

    // Declaring AreaChart Method.
    void areaChart() throws IOException;

    // Declaring BarGraph Method.
    void barGraph() throws IOException;

    // Declaring PieChart Method.
    void pieChart();

    void csvToPdf() throws IOException, DocumentException;

    void csvToHtml();

    void csvToXml();

    void createDirectory();

   void zipping();
}