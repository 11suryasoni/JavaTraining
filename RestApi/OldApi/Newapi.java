package RestApi;

import com.github.opendevl.JFlat;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Newapi {


    public static void main(String[] args) throws Exception {
        String url = "https://collectionapi.metmuseum.org/public/collection/v1/objects";
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(response.body());
        JSONArray jsonArray = jsonObject.getJSONArray("objectIDs");

        PrintWriter pw= new PrintWriter("/Users/azuga/Desktop/test3.json");

     //   String[] lines = new String[10];
        for(int i=300;i<350;i++)
        {
            String url2 = "https://collectionapi.metmuseum.org/public/collection/v1/objects/"+jsonArray.get(i);
            HttpRequest request2 = HttpRequest.newBuilder().GET().uri(URI.create(url2)).build();
            HttpClient client2 = HttpClient.newBuilder().build();
            HttpResponse<String> response2= client2.send(request2, HttpResponse.BodyHandlers.ofString());

            if(!(response2.statusCode()==200))
                System.out.println("Connection LOST");
            StringBuilder sb = new StringBuilder();
            sb.append(response2.body());

//            lines[i] = String.valueOf(response2.body());

            //            JSONObject jsonObject2 = new JSONObject(response2.body());
//            JSONArray jsonArray2 = new JSONArray();

//
//            try (FileWriter file = new FileWriter("/Users/azuga/Desktop/Test4.csv")) {
//
//                // use write() method to add JSONObject into file
//                file.write(jsonObject2.toString());
//                file.flush();
//
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }


            pw.write(sb.toString());

//            JFlat jFlatvar= new JFlat(sb.toString());
//          jFlatvar.json2Sheet().headerSeparator("_").getJsonAsSheet();
//          jFlatvar.write2csv("/Users/azuga/Desktop/Testschema3.csv");
//
//


//            CSVReader reader=new CSVReader(new FileReader("/Users/azuga/Desktop/Testschema3.csv"));
//            lines[i]= String.valueOf(reader.readAll());

//            System.out.println(lines[i]);
//           lines[i]= jFlatvar.toString();
         //   lines[i] = String.valueOf(sb2);

//          FileWriter csvWriter = new FileWriter("/Users/azuga/Desktop/mynew2.csv");
//            csvWriter.append("Keys,Default (en_US)\n");
//
//                csvWriter.append(lines[i]).append("\n");
//            csvWriter.flush();
//            csvWriter.close();
//
//
//
//            System.out.println(sb);

        }
        pw.close();

        String newjsonString= new String(Files.readAllBytes(Paths.get("/Users/azuga/Desktop/test3.json")));

        String jsonn=newjsonString.replaceAll("}\\{","},{");
         // jsonn.replaceAll("[\\ ]"," ");
          StringBuilder sb=new StringBuilder();
          sb.append("[");
          sb.append(jsonn);
          sb.append("]");

          JFlat jFlatvar= new JFlat(sb.toString());
          jFlatvar.json2Sheet().headerSeparator("_").getJsonAsSheet();
          jFlatvar.write2csv("/Users/azuga/Desktop/Testschema3.csv");




        try (PrintWriter output = new PrintWriter("/Users/azuga/Desktop/Testnew3.csv"))
        {
            List<String> lines = Files.readAllLines(Paths.get("/Users/azuga/Desktop/Testschema3.csv"));
            // assuming your csv has an even number of lines

//            String p=lines.get(1);
//            String arr[] =p.split(",",'\n');
//     //       System.out.println(arr[0]);

            for (int i = 0; i < lines.size()-2; i++) {

                String p=lines.get(i);
                String[] arr =p.split(",",'\n');

                String q=lines.get(i+1);
                String[] brr =q.split(",",'\n');

                if(arr[0].equals(brr[0]))
                 output.println(lines.get(i) + ";" + lines.get(++i));
                else
                    output.println(lines.get(i));
            }
        }






//        JFlat jFlatvar= new JFlat(sb.toString());
//          jFlatvar.json2Sheet().headerSeparator("_").getJsonAsSheet();
//          jFlatvar.write2csv("/Users/azuga/Desktop/Testschema3.csv");



//        FileWriter csvWriter = new FileWriter("/Users/azuga/Desktop/mynew.csv");
//        csvWriter.append("Keys,Default (en_US)\n");
//
//        for (int i = lines.length - 1; i >= 0; i--) {
//            csvWriter.append(lines[i]).append("\n");
//        }
//        csvWriter.flush();
//        csvWriter.close();



//
//        String newjsonString= new String(Files.readAllBytes(Paths.get("/Users/azuga/Desktop/test3.json")));
//
//        String jsonn=newjsonString.replaceAll("}\\{","},{");
//         // jsonn.replaceAll("[\\ ]"," ");
//          StringBuilder sb=new StringBuilder();
//          sb.append("[");
//          sb.append(jsonn);
//          sb.append("]");

//          JFlat jFlatvar= new JFlat(sb.toString());
//          jFlatvar.json2Sheet().headerSeparator("_").getJsonAsSheet();
//          jFlatvar.write2csv("/Users/azuga/Desktop/Testschema3.csv");
//



//          JSONArray docs = new JSONArray(sb.toString());
//
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
//        Newapi [] cars = objectMapper.readValue(docs.toString(), Newapi[].class);

      //  JsonNode jsonTree = new ObjectMapper().readTree(new File("/Users/azuga/Desktop/test3.json"));

//        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
//        JsonNode firstObject = jsonTree.elements().next();
//        firstObject.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn("isHighlight");} );
//        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
//
//        CsvMapper csvMapper = new CsvMapper();
//        csvMapper.writerFor(JsonNode.class)
//                .with(csvSchema)
//                .writeValue(new File("/Users/azuga/Desktop/Testschema3.csv"), jsonTree);
//


//        JSONTokener tokener = new JSONTokener(sb.toString());
//        JSONArray jsonArray2 = new JSONArray(tokener);
//
//        try {
//            // Convert JSONArray into csv and save to file
//            String csv = CDL.toString(jsonArray2);
//            Files.write(Path.of("/Users/azuga/Desktop/data.csv"), csv.getBytes());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//



//        String jsonn=newjsonString.replaceAll("}\\{","},{");
//          jsonn.replaceAll("[\\ ]"," ");
//          StringBuilder sb=new StringBuilder();
//          sb.append("[");
//          sb.append(jsonn);
//          sb.append("]");
//
//        JSONArray docs = new JSONArray(sb.toString());
//
//
//        File file = new File("/Users/azuga/Desktop/TestTest2.csv");
//
//        String csvString = CDL.toString(docs);
//        FileUtils.writeStringToFile(file, csvString);

//
//
//        File file = new File("/Users/azuga/Desktop/TestTest.csv");
//            String csv = CDL.toString(docs);
//            FileUtils.writeStringToFile(file, csv);
//            System.out.println("Data has been Sucessfully Writeen to "+ file);
//            System.out.println(csv);
//


//        String newjsonString= new String(Files.readAllBytes(Paths.get("/Users/azuga/Desktop/test3.json")));
//        JSONObject newjsonObject= new JSONObject(newjsonString);
//        JSONArray docs = newjsonObject.getJSONArray("objectID");
//
//
//        File file = new File("/Users/azuga/Desktop/TestTest.csv");
//
//        String csvString = CDL.toString(docs);
//        FileUtils.writeStringToFile(file, csvString);
//
//





//        String json = new String(Files.readAllBytes(Paths.get("/Users/azuga/Desktop/test3.json")));


//        JsonNode jsonTree = new ObjectMapper().readTree(new File("/Users/azuga/Desktop/test3.json"));
//
//        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
//        JsonNode firstObject = jsonTree.elements().next();
//        firstObject.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn("isHighlight");} );
//        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
//
//        CsvMapper csvMapper = new CsvMapper();
//        csvMapper.writerFor(JsonNode.class)
//                .with(csvSchema)
//                .writeValue(new File("/Users/azuga/Desktop/Testschema3.csv"), jsonTree);


//
//        JsonNode jsonTree = new ObjectMapper().readTree(new File("/Users/azuga/Desktop/test3.json"));
//
//        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
//        JsonNode firstObject = jsonTree.elements().next();
//        firstObject.fieldNames().forEachRemaining(csvSchemaBuilder::addColumn);
//        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
//
//        CsvMapper csvMapper = new CsvMapper();
//        csvMapper.writerFor(JsonNode.class)
//                .with(csvSchema)
//                .writeValue(new File("/Users/azuga/Desktop/Test3.csv"), jsonTree);
        }
    }


//        String jsonString;
//
//        JSONObject jsonObject;
//
//        try {
//
//            jsonString = new String(Files.readAllBytes(Paths.get("/Users/azuga/Desktop/test2.json")));
//
//            jsonObject = new JSONObject(jsonString);
//
//            JSONArray docs = jsonObject.getJSONArray("{");
//
//            // Step 4: Create a new CSV file using
//            //  the package java.io.File
//            File file = new File("/Users/azuga/Desktop/Test2.csv");
//
//            // Step 5: Produce a comma delimited text from
//            // the JSONArray of JSONObjects
//            // and write the string to the newly created CSV
//            // file
//
//            String csvString = CDL.toString(docs);
//            FileUtils.writeStringToFile(file, csvString);
//        }
//
//        // Catch block to handle exceptions
//        catch (Exception e) {
//
//            // Display exceptions on console with line
//            // number using printStackTrace() method
//            e.printStackTrace();
//        }


//        JSONArray docs = jsonObject.getJSONArray("rates");
//
//        File file = new File("/Users/azuga/Desktop/EmpDetails.csv");
//        String csv = CDL.toString(docs);
//        FileUtils.writeStringToFile(file, csv);
//        System.out.println("Data has been Sucessfully Writeen to "+ file);
//        System.out.println(csv);
//
//

//        File file = new File("/Users/azuga/Desktop/Test2.csv");
//
//
//            String csvString = CDL.toString(docs);
//            FileUtils.writeStringToFile(file, csvString);
//        }

// Catch block to handle exceptions
//        catch (Exception e) {
//
//            // Display exceptions on console with line
//            // number using printStackTrace() method
//            e.printStackTrace();
//        }

//        StringBuilder sb = new StringBuilder();
//        sb.append(response.body());
//        System.out.println(response.body());
//        try (FileWriter fw = new FileWriter("/Users/azuga/Desktop/hello.csv")) {
//            String[] str = sb.toString().split(",");
//            for (String s : str) {
//                fw.write(s + ",\n");
//            }
//            System.out.println("data is filled into the file hello.csv");
//        } catch (Exception e) {
//            System.out.println("an error occurred while creating or writing to the file");