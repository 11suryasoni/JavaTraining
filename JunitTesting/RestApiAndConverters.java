package JunitTesting;

import au.com.bytecode.opencsv.CSVReader;
import com.github.opendevl.JFlat;
import com.github.underscore.U;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class RestApiAndConverters {

    static final org.apache.log4j.Logger logg = Logger.getLogger(RestApiAndConverters.class);
    static String ansString = "";


    public HttpResponse<String> fetchingHttpUrl(String url, String logInfo) throws IOException, InterruptedException {

        logg.info("SETTING HTTP REQUEST...GET " + logInfo);
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        logg.info("SETTING HTTP CLIENT ...BUILD " + logInfo);
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200)
                throw new InterruptedIOException("WRONG URL - INTERRUPTED EXCEPTION");
        } catch (InterruptedException e) {
            logg.error("HTTP RESPONSE INTERRUPT ERROR");
            System.out.println(e + "");
            throw new InterruptedException("Http Interrupt Error");
        } catch (IOException e) {
            logg.error("HTTP RESPONSE IO ERROR");
            System.out.println(e + "");
            throw new IOException("Error in HTTP REQUEST");
        }
        return response;
    }

    /**
     * This method is to write json data into json file.
     */
    public void writingJsonData(PrintWriter pw, HttpResponse<String> response2) {

        // Initializing String Builder to Append Json Object Data.
        logg.debug("OBJECT DATA - " + response2.body());
        StringBuilder sb = new StringBuilder();
        sb.append(response2.body());

        // Writing Json Object Data into Json File.
        logg.info("WRITING JSON DATA INTO JSON FILE");
        pw.write(sb.toString());
        System.out.println(sb);
    }

    /**
     * This method is to write json data in CSV file
     */
    public void writingCSVData(String jsonData, String csvFilePath) throws Exception {

        // Initializing JFlat for json to csv file conversion.
        logg.info("INITIALIZING FLAT FOR JSON TO CSV CONVERSION");
        JFlat jFlatVar = new JFlat(jsonData);
        try {
            jFlatVar.json2Sheet().headerSeparator("_").getJsonAsSheet();
            logg.warn("CSV FILE PATH -" + csvFilePath);
            jFlatVar.write2csv(csvFilePath);
        } catch (Exception e) {
            throw new Exception("JFlat Exception -" + e);
        }
        ansString = csvFilePath;
    }

    /**
     * This method is to Manipulate Json Data.
     */
    public String jsonDataManipulation(String jsonFilePath) throws IOException {
        // Reading Json File Data into String.
        logg.warn("JSON FILE PATH - " + jsonFilePath);
        String sb;
        try {
            String newJsonString = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            logg.info("JSON FILE READ SUCCESSFULLY");
            // Manipulating Json Data String to Extract All Objects to write into CSV.
            String JsonString = newJsonString.replaceAll("}\\{", "},{");
            sb = "[" + JsonString + "]";
            logg.debug("COMPLETE JSON DATA - " + JsonString);
            logg.info("JSON FILE MANIPULATION DONE");

        } catch (IOException e) {
            throw new IOException("IOEXCEPTION -" + e);
        }
        return sb;
    }


    public String restApi(String url, String jsonFilePath, String csvFilePath) throws Exception {

        logg.info("INVOKING REST API");

        // Requesting json data and Getting Response.
        logg.info("REQUESTING URL - " + url);
        HttpResponse<String> response;
        try {
            response = fetchingHttpUrl(url, "1");
            logg.debug("HTTP RESPONSE RECEIVED");
        } catch (InterruptedException e) {
            logg.error("HTTP RESPONSE INTERRUPT ERROR");
            throw new InterruptedException("Url Interrupt Error");
        } catch (IOException e) {
            logg.error("HTTP RESPONSE IO ERROR");
            throw new IOException("Error in Http Request");
        }

        // Converting Response data to Json Object and then into Json Array.
        JSONObject jsonObject = new JSONObject(response.body());
        JSONArray jsonArray = jsonObject.getJSONArray("objectIDs");

        // Initializing File Writer to insert Json Array Data.
        logg.warn("JSON FILE PATH - \"/Users/azuga/Desktop/newTest3.json\" ");
        if (!checkFileExist(jsonFilePath))
            throw new FileNotFoundException("Json File Not Found");

        PrintWriter pw = new PrintWriter(jsonFilePath);
        logg.debug("JSON FILE CREATED SUCCESSFULLY");

        for (int i = 0; i < 10; i++) {
            // Requesting Particular Object Data form URL.
            String url2 = "https://collectionapi.metmuseum.org/public/collection/v1/objects/" + jsonArray.get(i);
            logg.info("REQUESTING REST API OBJECT URL 2 - " + url2);

            // Requesting json Object data and Getting Response.
            HttpResponse<String> response2;
            try {
                response2 = fetchingHttpUrl(url2, "2");
                logg.debug("HTTP RESPONSE 2 RECEIVED FOR OBJECT ID - " + jsonArray.get(i));
                logg.debug("HTTP RESPONSE RECEIVED");
            } catch (InterruptedException e) {
                logg.error("HTTP RESPONSE INTERRUPT ERROR");
                throw new InterruptedException("Url Interrupt Error");
            } catch (IOException e) {
                logg.error("HTTP RESPONSE IO ERROR");
                throw new IOException("Error in Http Request");
            }

            // Writing Json Object Data into Json File.
            writingJsonData(pw, response2);
        }
        pw.close();
        logg.info("DATA WRITTEN INTO JSON FILE SUCCESSFULLY");
        logg.info("JSON FILE CLOSED");

        // Writing Data to CSV File.
        try {
            writingCSVData(jsonDataManipulation(jsonFilePath), csvFilePath);
        } catch (Exception e) {
            throw new Exception("Exception in Csv Writer or Json Data Manipulator -" + e);
        }
        return ansString;
    }


    // This method checks file existence.
    public boolean checkFileExist(String loc) {
        File f1 = new File(loc);
        return f1.exists();
    }

    // This method convert Csv File to Pdf File.
    public String csvToPdf(String loc, String outputFile) throws IOException, DocumentException {

        // Initializing a CSV reader to read CSV file.
        logg.info("INVOKING REST API");

        logg.warn("CSV FILE PATH ENTERED MANUALLY");
        logg.info("INITIALIZING CSV FILE READER");

        if (!checkFileExist(loc)) {
            logg.error("Csv File Not Found");
            throw new FileNotFoundException("csvFileNotFound");
        }

        CSVReader reader = new CSVReader(new FileReader(loc));

        // Creating a PDF file and Writing data using pdf writer and Setting page size.
        logg.debug("CREATING FILE AND SETTING PAGE SIZE");
        Document data = new Document();
        com.itextpdf.text.Rectangle rc = new com.itextpdf.text.Rectangle(8300, 8000);
        data.setPageSize(rc);

        logg.debug("PDF FILE PATH ENTERED");
        try {
            PdfWriter.getInstance(data, new FileOutputStream(outputFile));

            data.open();

            CSVReader newReader = new CSVReader(new FileReader(loc));
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
                ansString += Lines[i];
                while (i < count) {
                    cells = new PdfPCell(new Phrase(Lines[i]));
                    myTable.addCell(cells);
                    i++;
                }
            }
            data.add(myTable);
            data.close();
            logg.info("FILE CLOSED");
        } catch (DocumentException d) {
            throw new DocumentException("Document Not Found");
        }
        return outputFile;
    }


    public String csvToHtml(String csvFile, String outputFile) throws IOException {

        logg.info("INVOKING REST API");

        logg.warn("CSV FILE PATH ENTERED MANUALLY");
        logg.warn("HTML FILE PATH ENTERED");


        // Read lines of csv to a string array list
        logg.info("FILE READER INITIALIZED");

        List<String> lines = new ArrayList<>();

        if (!checkFileExist(csvFile)) {
            logg.error("Csv File Not Found");
            throw new FileNotFoundException("csvFileNotFound");
        }

        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            lines.add(currentLine);
            logg.debug("FILE DATA - " + currentLine);
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
            logg.error("WRITER INPUT ERROR");
            throw new IOException("NO SUCH FILE OR DIRECTORY");
        }
        return outputFile;
    }


    public String checkSum(String outputFile) throws IOException, NoSuchAlgorithmException {
        // This Code is to Generate MD5 Checksum for Files

        MessageDigest digest = MessageDigest.getInstance("MD5");
        FileInputStream reader1 = new FileInputStream(outputFile);
        StringBuilder sb;
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;
        while ((bytesCount = reader1.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }
        ;
        reader1.close();

        // Create byte array to read data in chunks
        byte[] bytes = digest.digest();

        // Converts the decimal into hexadecimal format and appends that to the StringBuilder object.
        sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer
                    .toString((aByte & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return sb.toString();
    }

    public String jsonToXml(String loc, String outputFile) {
        logg.info("XML PROGRAM INVOKED");
        logg.warn("JSON FILE PATH ENTERED MANUALLY");
        logg.debug("PATH - " + loc);
        try {
            logg.info("Reading json from museum.json");
            String json = Files.readString(Path.of(loc));
            logg.info("Converting json to xml");
            String xml = U.jsonToXml(json);
            FileWriter file;
            logg.info("Writing xml into museum.xml");
            file = new FileWriter(outputFile);
            file.write(xml);
            file.flush();
            file.close();
        } catch (IOException e1) {
            logg.error("Input Error");
            e1.printStackTrace();
        } catch (JSONException e2) {
            logg.error("JSON FILE ERROR");
            e2.printStackTrace();
        }
        return outputFile;
    }
}