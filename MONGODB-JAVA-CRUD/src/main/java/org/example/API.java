package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.include;


public class API {

    // Logger Declaration
    private static final Logger logger = LoggerFactory.getLogger(API.class);

    /**
     * This method is used to Find the Documents from MongoDB Museum Api Database.
     */
    public String Find() {
        logger.info("Find Method Invoked");

        String uri = "mongodb://localhost:27017/forum";
        logger.debug("Connection uri -" + uri);

        MongoClient mongoClient = MongoClients.create(uri);
        logger.info("Database Connection SuccessFull");
        System.out.println("\n" + "Connected to the database successfully" + "\n");

        // Getting Forum DataBase
        MongoDatabase database = mongoClient.getDatabase("forum");
        logger.debug("Database Name - " + database.getName());
        System.out.println("\n" + "Credentials ::" + database.getName() + "\n");

        // Getting Museum Api Collection
        MongoCollection<Document> collection = database.getCollection("museumApiTest");
        logger.debug("Collection Name -" + collection.getNamespace());

        JSONArray jsArray = new JSONArray();

        // Filter Object ID & Projection _id
        Bson filter = Filters.exists("objectID");
        Bson projection = exclude("_id");
        logger.debug("BSON Filter - " + filter + " BSON Projection " + projection);

        System.out.println();
        logger.info("Data Retrieval Started");
        collection.find(filter).projection(projection).forEach(doc -> System.out.println(doc.toJson()));
        System.out.println();

        // Inserting Doc into Json Array to Return as String
        for (Document d : collection.find(filter).projection(projection)
        ) {
            logger.debug("Data - " + d.toJson());
            jsArray.put(d);
        }

        // Closing Collection
        logger.info("Database Connection Closed");
        mongoClient.close();
        logger.info("Find SuccessFull");
         return jsArray.toString();
      //  return "Find SuccessFull";
    }

    /**
     * This method is used to Insert the Documents in MongoDB Museum Api Database.
     */
    public String Insert(String input) throws JSONException {
        logger.info("Insert Method Invoked");

        String uri = "mongodb://localhost:27017/forum";
        logger.debug("Connection uri -" + uri);

        MongoClient mongoClient = MongoClients.create(uri);
        logger.info("Database Connection SuccessFull");
        System.out.println("\n" + "Connected to the database successfully" + "\n");

        // Getting Forum DataBase
        MongoDatabase database = mongoClient.getDatabase("forum");
        logger.debug("Database Name - " + database.getName());
        System.out.println("\n" + "Credentials ::" + database.getName() + "\n");

        // Getting Museum Api Collection
        MongoCollection<Document> collection = database.getCollection("museumApiTest");
        logger.debug("Collection Name -" + collection.getNamespace());

        JSONObject obj = new JSONObject(input);

        // Filter Object ID , Key - Value
        Bson filter = Filters.eq("objectID", obj.get("objectID").toString());
        logger.debug("BSON Filter - " + filter);

        // Counting Doc With Filter
        int count = 0;
        for (Document ignored : collection.find(filter)) {
            count++;
        }

        // Checking Doc Exist or Not
        if (count > 0) {
            logger.info("Document Already Exist, Cannot be inserted again");
            System.out.println("\n" + "Document Already Exist, Cannot be inserted again" + "\n");
            logger.info("Database Connection Closed");
            mongoClient.close();
            logger.info("Insertion Failed");
            return "Insertion Failed";
        } else {
            Document doc = new Document();
            Iterator<String> keys = obj.keys();

            while (keys.hasNext()) {
                String temp = keys.next();
                doc.append(temp, obj.get(temp).toString());
            }
            logger.debug("Document -" + doc);
            System.out.println(doc);
            logger.info("Insertion Started");

            // Insertion & Closing Connection
            collection.insertOne(doc);
            logger.info("Database Connection Closed");
            mongoClient.close();
            logger.info("Insertion SuccessFull");
            return "Insertion SuccessFull";
        }
    }

    /**
     * This method is used to Update the Documents in MongoDB Museum Api Database.
     */
    public String Update(String input) throws JSONException {
        logger.info("Update Method Invoked");

        String uri = "mongodb://localhost:27017/forum";
        logger.debug("Connection uri -" + uri);

        MongoClient mongoClient = MongoClients.create(uri);
        logger.info("Database Connection SuccessFull");
        System.out.println("\n" + "Connected to the database successfully" + "\n");

        // Getting Forum DataBase
        MongoDatabase database = mongoClient.getDatabase("forum");
        logger.debug("Database Name - " + database.getName());
        System.out.println("\n" + "Credentials ::" + database.getName() + "\n");

        // Getting Museum Api Collection
        MongoCollection<Document> collection = database.getCollection("museumApiTest");
        logger.debug("Collection Name -" + collection.getNamespace());

        JSONObject obj = new JSONObject(input);

        // Filter Object ID , Key - Value
        Bson filter = Filters.eq("objectID", obj.get("objectID").toString());
        logger.debug("BSON Filter - " + filter);

        // Counting Doc With Filter
        int count = 0;
        for (Document ignored : collection.find(filter)) {
            count++;
        }

        // Checking Doc Exist or Not
        if (count <= 0) {
            logger.info("Document Not Exist, Cannot be Updated");
            System.out.println("\n" + "Document Not Exist, Cannot be Updated" + "\n");
            logger.info("Database Connection Closed");
            mongoClient.close();
            logger.info("Updating Failed");
            return "Updating Failed";
        } else {
            System.out.println("\n" + "Old Doc - " + collection.find(filter).first() + "\n");
            logger.debug("Old Doc - " + collection.find(filter).first());

            // Projection To Get _id from Old Doc & store _id inside json object
            Bson projection = include("_id");
            logger.debug("Projection - " + projection);
            Document oldDoc = collection.find(filter).projection(projection).first();
            JSONObject oldDocObject = new JSONObject(oldDoc);

            // Append _id json object to New Doc
            logger.debug("Old Doc _id -" + oldDoc);
            Document doc = new Document();
            doc.append(oldDocObject.keys().next().toString(), oldDocObject.get(oldDocObject.keys().next().toString()));

            // Creating New Doc which contains all fields
            Iterator<String> keys = obj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                doc.append(key, obj.get(key).toString());
            }

            logger.debug("New Doc -" + doc);
            System.out.println("\n" + "New Doc - " + doc + "\n");
            logger.info("Update Started");

            // Updating Doc & Closing Connection
            collection.updateOne(new Document("objectID", obj.get("objectID").toString()),
                    new Document("$set", doc));

            logger.info("Database Connection Closed");
            mongoClient.close();
            logger.info("Update SuccessFull");
            return "Update SuccessFull";
        }
    }

    /**
     * This method is used to Delete the Documents from MongoDB Museum Api Database.
     */
    public String Delete(String id) {
        logger.info("Delete Method Invoked");

        String uri = "mongodb://localhost:27017/forum";
        logger.debug("Connection uri -" + uri);

        MongoClient mongoClient = MongoClients.create(uri);
        logger.info("Database Connection SuccessFull");
        System.out.println("\n" + "Connected to the database successfully" + "\n");

        // Getting Forum DataBase
        MongoDatabase database = mongoClient.getDatabase("forum");
        logger.debug("Database Name - " + database.getName());
        System.out.println("\n" + "Credentials ::" + database.getName() + "\n");

        // Getting Museum Api Collection
        MongoCollection<Document> collection = database.getCollection("museumApiTest");
        logger.debug("Collection Name -" + collection.getNamespace());

        // Filter On Object ID Field
        Bson filter = Filters.eq("objectID", id);
        logger.debug("BSON Filter - " + filter);

        // Counting Doc With Filter
        int count = 0;
        for (Document ignored : collection.find(filter)) {
            count++;
        }

        // Checking Doc Exist or Not
        if (count <= 0) {
            logger.info("Document Not Exist, Cannot be Updated");
            System.out.println("\n" + "Document not Exist, Cannot be Deleted" + "\n");
            logger.info("Database Connection Closed");
            mongoClient.close();
            logger.info("Deletion Failed");
            return "Deletion Failed";
        } else {
            logger.info("Delete Started");

            // Deleting Doc & Closing Connection
            collection.deleteOne(filter);
            logger.info("Database Connection Closed");
            mongoClient.close();
            logger.info("Deletion SuccessFull");
            return "Deletion SuccessFull";
        }

    }


//    public static void main(String[] args) throws JSONException {
//        API obj = new API();
//
//        System.out.println(obj.Insert("{\"objectID\":18,\"isHighlight\":false,\"accessionNumber\":\"1979.486.1\",\"accessionYear\":\"1979\",\"isPublicDomain\":false,\"primaryImage\":\"\",\"primaryImageSmall\":\"\",\"additionalImages\":[],\"constituents\":[{\"constituentID\":164292,\"role\":\"Maker\",\"name\":\"James Barton Longacre\",\"constituentULAN_URL\":\"http://vocab.getty.edu/page/ulan/500011409\",\"constituentWikidata_URL\":\"https://www.wikidata.org/wiki/Q3806459\",\"gender\":\"\"}],\"department\":\"The American Wing\",\"objectName\":\"Coin\",\"title\":\"One-dollar Liberty Head Coin\",\"culture\":\"\",\"period\":\"\",\"dynasty\":\"\",\"reign\":\"\",\"portfolio\":\"\",\"artistRole\":\"Maker\",\"artistPrefix\":\"\",\"artistDisplayName\":\"James Barton Longacre\",\"artistDisplayBio\":\"American, Delaware County, Pennsylvania 1794–1869 Philadelphia, Pennsylvania\",\"artistSuffix\":\"\",\"artistAlphaSort\":\"Longacre, James Barton\",\"artistNationality\":\"American\",\"artistBeginDate\":\"1794\",\"artistEndDate\":\"1869\",\"artistGender\":\"\",\"artistWikidata_URL\":\"https://www.wikidata.org/wiki/Q3806459\",\"artistULAN_URL\":\"http://vocab.getty.edu/page/ulan/500011409\",\"objectDate\":\"1853\",\"objectBeginDate\":1853,\"objectEndDate\":1853,\"medium\":\"Gold\",\"dimensions\":\"Dimensions unavailable\",\"measurements\":null,\"creditLine\":\"Gift of Heinz L. Stoppelmann, 1979\",\"geographyType\":\"\",\"city\":\"\",\"state\":\"\",\"county\":\"\",\"country\":\"\",\"region\":\"\",\"subregion\":\"\",\"locale\":\"\",\"locus\":\"\",\"excavation\":\"\",\"river\":\"\",\"classification\":\"\",\"rightsAndReproduction\":\"\",\"linkResource\":\"\",\"metadataDate\":\"2021-04-06T04:41:04.967Z\",\"repository\":\"Metropolitan Museum of Art, New York, NY\",\"objectURL\":\"https://www.metmuseum.org/art/collection/search/1\",\"tags\":null,\"objectWikidata_URL\":\"\",\"isTimelineWork\":false,\"GalleryNumber\":\"\"}"));
////
//        System.out.println(obj.Delete("19"));
////
////        System.out.println(obj.Update("{\"objectID\":17,\"isHighlight\":false,\"accessionNumber\":\"1979.486.1\",\"accessionYear\":\"1979\",\"isPublicDomain\":false,\"primaryImage\":\"\",\"primaryImageSmall\":\"\",\"additionalImages\":[],\"constituents\":[{\"constituentID\":164292,\"role\":\"Maker\",\"name\":\"James Barton Longacre\",\"constituentULAN_URL\":\"http://vocab.getty.edu/page/ulan/500011409\",\"constituentWikidata_URL\":\"https://www.wikidata.org/wiki/Q3806459\",\"gender\":\"\"}],\"department\":\"The American Wing\",\"objectName\":\"Coin\",\"title\":\"One-dollar Liberty Head Coin\",\"culture\":\"\",\"period\":\"\",\"dynasty\":\"\",\"reign\":\"\",\"portfolio\":\"\",\"artistRole\":\"Maker\",\"artistPrefix\":\"\",\"artistDisplayName\":\"James Barton Longacre\",\"artistDisplayBio\":\"American, Delaware County, Pennsylvania 1794–1869 Philadelphia, Pennsylvania\",\"artistSuffix\":\"\",\"artistAlphaSort\":\"Longacre, James Barton\",\"artistNationality\":\"American\",\"artistBeginDate\":\"1794\",\"artistEndDate\":\"1869\",\"artistGender\":\"\",\"artistWikidata_URL\":\"https://www.wikidata.org/wiki/Q3806459\",\"artistULAN_URL\":\"http://vocab.getty.edu/page/ulan/500011409\",\"objectDate\":\"1853\",\"objectBeginDate\":1853,\"objectEndDate\":1853,\"medium\":\"Gold\",\"dimensions\":\"Dimensions unavailable\",\"measurements\":null,\"creditLine\":\"Gift of Heinz L. Stoppelmann, 1979\",\"geographyType\":\"\",\"city\":\"\",\"state\":\"\",\"county\":\"\",\"country\":\"\",\"region\":\"\",\"subregion\":\"\",\"locale\":\"\",\"locus\":\"\",\"excavation\":\"\",\"river\":\"\",\"classification\":\"\",\"rightsAndReproduction\":\"\",\"linkResource\":\"\",\"metadataDate\":\"2021-04-06T04:41:04.967Z\",\"repository\":\"Metropolitan Museum of Art, New York, NY\",\"objectURL\":\"https://www.metmuseum.org/art/collection/search/1\",\"tags\":null,\"objectWikidata_URL\":\"\",\"isTimelineWork\":false,\"GalleryNumber\":\"\"}"));
//
//  //      System.out.println("\n" + obj.Find() + "\n");
//    }

//    public static void main(String[] args) throws JSONException {
//
//        StringBuilder sb = new StringBuilder("[{\"objectID\":18,\"isHighlight\":halse,\"accessionNumber\":\"1979.486.1\",\"accessionYear\":\"1979\",\"isPublicDomain\":false,\"primaryImage\":\"\",\"primaryImageSmall\":\"\",\"additionalImages\":[],\"constituents\":[{\"constituentID\":164292,\"role\":\"Maker\",\"name\":\"James Barton Longacre\",\"constituentULAN_URL\":\"http://vocab.getty.edu/page/ulan/500011409\",\"constituentWikidata_URL\":\"https://www.wikidata.org/wiki/Q3806459\",\"gender\":\"\"}],\"department\":\"The American Wing\",\"objectName\":\"Coin\",\"title\":\"One-dollar Liberty Head Coin\",\"culture\":\"\",\"period\":\"\",\"dynasty\":\"\",\"reign\":\"\",\"portfolio\":\"\",\"artistRole\":\"Maker\",\"artistPrefix\":\"\",\"artistDisplayName\":\"James Barton Longacre\",\"artistDisplayBio\":\"American, Delaware County, Pennsylvania 1794–1869 Philadelphia, Pennsylvania\",\"artistSuffix\":\"\",\"artistAlphaSort\":\"Longacre, James Barton\",\"artistNationality\":\"American\",\"artistBeginDate\":\"1794\",\"artistEndDate\":\"1869\",\"artistGender\":\"\",\"artistWikidata_URL\":\"https://www.wikidata.org/wiki/Q3806459\",\"artistULAN_URL\":\"http://vocab.getty.edu/page/ulan/500011409\",\"objectDate\":\"1853\",\"objectBeginDate\":1853,\"objectEndDate\":1853,\"medium\":\"Gold\",\"dimensions\":\"Dimensions unavailable\",\"measurements\":null,\"creditLine\":\"Gift of Heinz L. Stoppelmann, 1979\",\"geographyType\":\"\",\"city\":\"\",\"state\":\"\",\"county\":\"\",\"country\":\"\",\"region\":\"\",\"subregion\":\"\",\"locale\":\"\",\"locus\":\"\",\"excavation\":\"\",\"river\":\"\",\"classification\":\"\",\"rightsAndReproduction\":\"\",\"linkResource\":\"\",\"metadataDate\":\"2021-04-06T04:41:04.967Z\",\"repository\":\"Metropolitan Museum of Art, New York, NY\",\"objectURL\":\"https://www.metmuseum.org/art/collection/search/1\",\"tags\":null,\"objectWikidata_URL\":\"\",\"isTimelineWork\":false,\"GalleryNumber\":\"\"}" +
//                ",{\"objectID\":19,\"isHighlight\":false,\"accessionNumber\":\"19.486.1\",\"accessionYear\":\"79\",\"isPublicDomain\":false,\"primaryImage\":\"\",\"primaryImageSmall\":\"\",\"additionalImages\":[],\"constituents\":[{\"constituentID\":164292,\"role\":\"Maker\",\"name\":\" Barton Longacre\",\"constituentULAN_URL\":\"http://vocab.getty.edu/page/ulan/500011409\",\"constituentWikidata_URL\":\"https://www.wikidata.org/wiki/Q3806459\",\"gender\":\"\"}],\"department\":\"The American Wing\",\"objectName\":\"Coin\",\"title\":\"One-dollar Liberty Head Coin\",\"culture\":\"\",\"period\":\"\",\"dynasty\":\"\",\"reign\":\"\",\"portfolio\":\"\",\"artistRole\":\"Maker\",\"artistPrefix\":\"\",\"artistDisplayName\":\"James Barton Longacre\",\"artistDisplayBio\":\"American, Delaware County, Pennsylvania 1794–1869 Philadelphia, Pennsylvania\",\"artistSuffix\":\"\",\"artistAlphaSort\":\"Longacre, James Barton\",\"artistNationality\":\"American\",\"artistBeginDate\":\"1794\",\"artistEndDate\":\"1869\",\"artistGender\":\"\",\"artistWikidata_URL\":\"https://www.wikidata.org/wiki/Q3806459\",\"artistULAN_URL\":\"http://vocab.getty.edu/page/ulan/500011409\",\"objectDate\":\"1853\",\"objectBeginDate\":1853,\"objectEndDate\":1853,\"medium\":\"Gold\",\"dimensions\":\"Dimensions unavailable\",\"measurements\":null,\"creditLine\":\"Gift of Heinz L. Stoppelmann, 1979\",\"geographyType\":\"\",\"city\":\"\",\"state\":\"\",\"county\":\"\",\"country\":\"\",\"region\":\"\",\"subregion\":\"\",\"locale\":\"\",\"locus\":\"\",\"excavation\":\"\",\"river\":\"\",\"classification\":\"\",\"rightsAndReproduction\":\"\",\"linkResource\":\"\",\"metadataDate\":\"2021-04-06T04:41:04.967Z\",\"repository\":\"Metropolitan Museum of Art, New York, NY\",\"objectURL\":\"https://www.metmuseum.org/art/collection/search/1\",\"tags\":null,\"objectWikidata_URL\":\"\",\"isTimelineWork\":false,\"GalleryNumber\":\"\"}]");
//
//        JSONArray sortedJsonArray = new JSONArray();
//        JSONArray arr = new JSONArray(sb.toString());

//        List<JSONObject> jsonValues = new ArrayList<>();
//        for (int i = 0; i < arr.length(); i++) {
//            jsonValues.add(arr.getJSONObject(i));
//        }
//        Collections.sort( jsonValues, new Comparator<>() {
//            private static final String KEY_NAME = "isHighlight";
//
//            @Override
//            public int compare(JSONObject a, JSONObject b) {
//                String valA = null;
//                String valB = null;
//                try {
//                    valA = (String) a.get(KEY_NAME);
//                    valB = (String) b.get(KEY_NAME);
//                } catch (JSONException e) {
//                    System.out.println("Error");
//                }
//                return valA.compareTo(valB);
//            }
//        });
//
//        for (int i = 0; i < arr.length(); i++) {
//            sortedJsonArray.put(jsonValues.get(i));
//        }
//        System.out.println(sortedJsonArray.get(0)+"\n"+sortedJsonArray.get(1));



    //    ArrayList<Object> objectValues= new ArrayList<>();
//        Object[] objectValues = new Object[data.length()];
//        for (int i = 0; i < data.length(); i++) {
//            JSONObject jsonObject = (JSONObject) data.get(i);
//            objectValues[i] = jsonObject.get(key);
//        }
//
//    //    objectValues.sort(Comparator.comparing(Object::toString));
//        System.out.println(objectValues);





    //   }

}