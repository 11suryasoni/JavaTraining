/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This Pdf code Class is used to Convert the CSV file to PDF file.

@Author -> suryaps (Surya Prakash Soni)
 */
package RestApi;

import au.com.bytecode.opencsv.CSVReader;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import loggingLearn.logTest;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.FileReader;

public class pdfcode {
    static final Logger logger = Logger.getLogger(logTest.class);

    public static void main(String[] args) throws Exception {

        // Initializing a CSV reader to read CSV file.
        logger.info("INVOKING REST API");

        logger.warn("CSV FILE PATH ENTERED MANUALLY");
        logger.info("INITIALIZING CSV FILE READER");
        CSVReader reader = new CSVReader(new FileReader("/Users/azuga/Desktop/Testnew3.csv"));

        // Creating a PDF file and Writing data using pdf writer and Setting page size.

        logger.debug("CREATING FILE AND SETTING PAGE SIZE");
        Document data = new Document();
        Rectangle rc = new Rectangle(8300, 8000);
        data.setPageSize(rc);

        logger.debug("PDF FILE PATH ENTERED");
        PdfWriter.getInstance(data, new FileOutputStream("/Users/azuga/Desktop/pdf_file.pdf"));
        data.open();

        CSVReader newReader = new CSVReader(new FileReader("/Users/azuga/Desktop/Testnew3.csv"));
        int count = 0;
        while ((newReader.readNext()) != null) {
            count++;
        }

        logger.info("PDF WRITER INITIALIZED");
        PdfPTable myTable = new PdfPTable(count);
        PdfPCell cells;
        String[] Lines;
        while ((Lines = reader.readNext()) != null) {
            int i = 0;
            logger.debug("OBJECT ID - " + Lines[i]);
            while (i < count) {
                cells = new PdfPCell(new Phrase(Lines[i]));
                myTable.addCell(cells);
                i++;
            }
        }
        data.add(myTable);
        data.close();
        logger.info("FILE CLOSED");
    }
}