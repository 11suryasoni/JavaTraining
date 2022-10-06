/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This Html Code Class is used to Convert Csv to Html format.
@Author -> suryaps (Surya prakash soni)
 */


package RestApi;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class htmlcode {
    static final Logger logger = Logger.getLogger(htmlcode.class);

    public static void main(String[] args) {

        logger.info("INVOKING REST API");

        logger.warn("CSV FILE PATH ENTERED MANUALLY");
        logger.warn("HTML FILE PATH ENTERED");

        String csvFile = "/Users/azuga/Desktop/data.csv";
        String outputFile = "/Users/azuga/Desktop/html_file.html";

        // Read lines of csv to a string array list

        logger.info("FILE READER INITIALIZED");

        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
                logger.debug("FILE DATA - " + currentLine);
            }
        } catch (IOException e) {
            logger.error("INPUT ERROR");
            e.printStackTrace();
        }

        // Adding <td> and <tr> tags for lines and columns.

        logger.info("ADDING TAGS");
        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, "<tr><td>" + lines.get(i) + "</td></tr>");
            lines.set(i, lines.get(i).replaceAll(",", "</td><td>"));
            logger.debug(lines);
        }

        // Adding <table> and </table> tags.

        logger.info("ADDING BORDERS");
        lines.set(0, "<table border>" + lines.get(0));
        lines.set(lines.size() - 1, lines.get(lines.size() - 1) + "</table>");

        logger.info("WRITING DATA INTO FILE");
        try (FileWriter writer = new FileWriter(outputFile)) {
            for (String line : lines) {
                writer.write(line + "\n");
                logger.debug("DATA - " + lines);
            }
        } catch (IOException e) {
            logger.error("INPUT ERROR");
            e.printStackTrace();
        }
    }
}