/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This RestApi code class is used to fetch data from API and save into CSV.

@Author -> suryaPs (Surya prakash sonI)
 */
package MysqlJava;

import loggingLearn.logTest;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlInsertion {

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


    public static void main(String[] args) {

        Connection con = null;
        try {
            logger.info("INVOKING REST API");
            String url = "https://collectionapi.metmuseum.org/public/collection/v1/objects";

            // Requesting json data and Getting Response.
            logger.info("REQUESTING URL - " + url);
            JSONObject jsonObject = null;
            try {
                HttpResponse<String> response = fetchingHttpUrl(url, "1");
                logger.debug("HTTP RESPONSE RECEIVED");

                // Converting Response data to Json Object and then into Json Array.
                logger.info("Converting Response 1 into json object for particular object IDs");
                jsonObject = new JSONObject(response.body());
            } catch (IOException e) {
                logger.error("HTTP RESPONSE ERROR " + e);
                System.out.println("HTTP RESPONSE ERROR " + e);
            }
            logger.info("Converting json object to json array");
            JSONArray jsonArray = jsonObject.getJSONArray("objectIDs");

            // Creating List to store response 2 (object id - row) as Array list.
            logger.info("Creating ArrayList to store Response 2 body");
            List<String> list = new ArrayList<>();

            for (int i = 8; i < 9; i++) {

                // Requesting Particular Object Data form URL.
                String url2 = "https://collectionapi.metmuseum.org/public/collection/v1/objects/" + jsonArray.get(i);
                logger.info("REQUESTING REST API OBJECT URL 2 - " + url2);

                // Requesting json Object data and Getting Response.
                HttpResponse<String> response2 = null;
                try {
                    response2 = fetchingHttpUrl(url2, "2");
                } catch (IOException e) {
                    logger.info("HTTP RESPONSE ERROR " + e);
                    System.out.println("HTTP RESPONSE ERROR " + e);
                }
                logger.debug("HTTP RESPONSE 2 RECEIVED FOR OBJECT ID - " + jsonArray.get(i));

                // Adding response body to list.
                logger.info("Adding Response 2 body into List");
                list.add(response2.body());

            }

            // Creating string array to store all header values.
            logger.info("Creating String array to store Table Headers");
            String[] tableHeaders = {"objectID", "isHighlight", "accessionNumber", "accessionYear", "isPublicDomain", "primaryImage",
                    "primaryImageSmall", "additionalImages", "constituents", "department", "objectName", "title", "culture", "period",
                    "dynasty", "reign", "portfolio", "artistRole", "artistPrefix", "artistDisplayName", "artistDisplayBio",
                    "artistSuffix", "artistAlphaSort", "artistNationality",
                    "artistBeginDate", "artistEndDate", "artistGender", "artistWikidata_URL", "artistULAN_URL", "objectDate",
                    "objectBeginDate",
                    "objectEndDate", "medium", "dimensions", "measurements", "creditLine", "geographyType", "city", "state",
                    "county", "country", "region",
                    "subregion", "locale", "locus", "excavation", "river", "classification", "rightsAndReproduction",
                    "linkResource", "metadataDate",
                    "repository", "objectURL", "tags", "objectWikidata_URL", "isTimelineWork", "GalleryNumber"};

            PreparedStatement pstmt;
            try {
                // Establishing MySql Database Connection.
                logger.info("Establishing MySql Database Connection.");
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MuseumApi", "root", "9876");
                logger.debug("Connection Request : " + con);
                if (con != null) {
                    logger.error("DATABASE CONNECTION FAILED");
                    System.out.println("Connected to the database MuseumApi");
                }

                // Preparing insert Statement.
                logger.info("Preparing Insert Statement.");
                pstmt = con.prepareStatement("INSERT INTO ApiInfoBase values (?, ?, ?, ?,?, ?, ?, ?,?," +
                        " ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, " +
                        "?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?)");

                // Reading object row - list value and convert to json object to fetch particular key values.
                logger.info("Taking out particular row from list into String 's'.");
                String s = list.get(0);
                logger.debug("Object Data - " + s);
                System.out.println(list.get(0));
                logger.info("Creating Json Object to Fetch particular Key - Values.");
                JSONObject k = new JSONObject(s);

                // Converting json object to particular data type as per mysql table column data types and inserting.
                for (int range = 0; range < 57; range++) {
                    System.out.print(k.get(tableHeaders[range]));
                    logger.debug("Table header values -" + k.get(tableHeaders[range]));
                    if (range == 0 || range == 30 || range == 31)
                        pstmt.setInt(range + 1, Integer.parseInt(String.valueOf(k.get(tableHeaders[range]))));
                    else if (range == 1 || range == 4 || range == 55)
                        pstmt.setBoolean(range + 1, Boolean.parseBoolean(String.valueOf(k.get(tableHeaders[range]))));
                    else
                        pstmt.setString(range + 1, String.valueOf(k.get(tableHeaders[range])));

                }
                // Inserting Prepared Statement into Mysql table.
                logger.info("Executing Insert Statement.");
                pstmt.executeUpdate();
                System.out.println("Data Inserted");
                logger.info("Data Insertion SuccessFull");
            } catch (SQLException e) {
                logger.error("SqlException - " + e);
                System.out.println("SQL Exception : " + e);
            } catch (Exception e) {
                logger.error("Exception - " + e);
                System.out.println("EXCEPTION IN MAIN THREAD : " + e);
            }
        } finally {
            // Closing Database connection.
            logger.info("Closing Sql Database connection");
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    logger.error("SqlException While Closing Database Connection -" + ex);
                    ex.printStackTrace();
                }
            }
        }
    }
}