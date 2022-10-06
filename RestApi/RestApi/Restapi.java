/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This RestApi code class is used to fetch data from API and save into CSV.

@Author -> suryaPs (Surya prakash sonI)
 */

package RestApi;

import com.github.opendevl.JFlat;
import loggingLearn.logTest;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Restapi {

    // Logger Declaration with Initialization.
    static final Logger logger = Logger.getLogger(logTest.class);

    /**
     * This method is to Fetch Api Response.
     */
    private static HttpResponse<String> fetchingHttpUrl(String url, String logInfo) throws IOException {

        logger.info("SETTING HTTP REQUEST...GET " + logInfo);
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        logger.info("SETTING HTTP CLIENT ...BUILD " + logInfo);
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200)
                throw new InterruptedIOException("WRONG URL - INTERRUPTED EXCEPTION");
        } catch (InterruptedException e) {
            logger.error("HTTP RESPONSE INTERRUPT ERROR");
            System.out.println(e + "");
            throw new IOException("Error in Io");
        } catch (IOException e) {
            logger.error("HTTP RESPONSE IO ERROR");
            System.out.println(e + "");
            throw new IOException("Error in REQUEST");
        }
        return response;
    }

    /**
     * This method is to write json data into json file.
     */
    private static void writingJsonData(PrintWriter pw, HttpResponse<String> response2) {

        // Initializing String Builder to Append Json Object Data.
        logger.debug("OBJECT DATA - " + response2.body());
        StringBuilder sb = new StringBuilder();
        sb.append(response2.body());

        // Writing Json Object Data into Json File.
        logger.info("WRITING JSON DATA INTO JSON FILE");
        pw.write(sb.toString());
        System.out.println(sb);
    }

    /**
     * This method is to write json data in CSV file
     */
    private static void writingCSVData(String sb) throws Exception {

        // Initializing JFlat for json to csv file conversion.
        logger.info("INITIALIZING FLAT FOR JSON TO CSV CONVERSION");
        JFlat jFlatVar = new JFlat(sb);
        jFlatVar.json2Sheet().headerSeparator("_").getJsonAsSheet();
        logger.warn("CSV FILE PATH - \"/Users/azuga/Desktop/DATA1.csv\"");
        jFlatVar.write2csv("/Users/azuga/Desktop/DATA1.csv");
    }

    /**
     * This method is to Manipulate Json Data.
     */
    private static String jsonDataManipulation() throws IOException {
        // Reading Json File Data into String.
        logger.warn("JSON FILE PATH - " + "/Users/azuga/Desktop/test3.json");
        String newJsonString = new String(Files.readAllBytes(Paths.get("/Users/azuga/Desktop/test3.json")));
        logger.info("JSON FILE READ SUCCESSFULLY");

        // Manipulating Json Data String to Extract All Objects to write into CSV.
        String JsonString = newJsonString.replaceAll("}\\{", "},{");

        String sb = "[" + JsonString + "]";
        logger.debug("COMPLETE JSON DATA - " + JsonString);
        logger.info("JSON FILE MANIPULATION DONE");
        return sb;
    }

    public static void main(String[] args) throws Exception {

        logger.info("INVOKING REST API");
        String url = "https://collectionapi.metmuseum.org/public/collection/v1/objects";

        // Requesting json data and Getting Response.
        logger.info("REQUESTING URL - " + url);
        HttpResponse<String> response = fetchingHttpUrl(url, "1");
        logger.debug("HTTP RESPONSE RECEIVED");

        // Converting Response data to Json Object and then into Json Array.
        JSONObject jsonObject = new JSONObject(response.body());
        JSONArray jsonArray = jsonObject.getJSONArray("objectIDs");

        // Initializing File Writer to insert Json Array Data.
        logger.warn("JSON FILE PATH - \"/Users/azuga/Desktop/test3.json\" ");
        PrintWriter pw = new PrintWriter("/Users/azuga/Desktop/test3.json");
        logger.debug("JSON FILE CREATED SUCCESSFULLY");

        for (int i = 0; i < 10; i++) {
            // Requesting Particular Object Data form URL.
            String url2 = "https://collectionapi.metmuseum.org/public/collection/v1/objects/" + jsonArray.get(i);
            logger.info("REQUESTING REST API OBJECT URL 2 - " + url2);

            // Requesting json Object data and Getting Response.
            HttpResponse<String> response2 = fetchingHttpUrl(url2, "2");
            logger.debug("HTTP RESPONSE 2 RECEIVED FOR OBJECT ID - " + jsonArray.get(i));

            // Writing Json Object Data into Json File.
            writingJsonData(pw, response2);
        }
        pw.close();
        logger.info("DATA WRITTEN INTO JSON FILE SUCCESSFULLY");
        logger.info("JSON FILE CLOSED");

        // Writing Data to CSV File.
        writingCSVData(jsonDataManipulation());
    }
}