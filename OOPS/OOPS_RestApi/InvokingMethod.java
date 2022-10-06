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

public class InvokingMethod {
    public static void main(String[] args) throws IOException, DocumentException {

        // Calling methods through Interface.
        InterfaceChart ID = new DifferentCharts();
        ID.createDirectory();
        ID.pieChart();
        ID.areaChart();
        ID.barGraph();
        ID.csvToHtml();
        ID.csvToPdf();
        ID.csvToXml();
        ID.zipping();
    }
}