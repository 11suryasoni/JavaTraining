package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.codehaus.jettison.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class Features {
    private static final Logger logger = LoggerFactory.getLogger(API.class);

//    public JSONArray Find() {
//
//        logger.info("Find Method Invoked");
//
//        String uri = "mongodb://localhost:27017/forum";
//        logger.debug("Connection uri -" + uri);
//
//        MongoClient mongoClient = MongoClients.create(uri);
//        logger.info("Database Connection SuccessFull");
//
//        // Getting Forum DataBase
//        MongoDatabase database = mongoClient.getDatabase("forum");
//        logger.debug("Database Name - " + database.getName());
//
//        // Getting Museum Api Collection
//        MongoCollection<Document> collection = database.getCollection("museumApiTest");
//        logger.debug("Collection Name -" + collection.getNamespace());
//
//        JSONArray jsArray = new JSONArray();
//
//        // Filter Object ID & Projection _id
//        Bson filter = Filters.exists("objectID");
//        Bson projection = exclude("_id");
//        logger.debug("BSON Filter - " + filter + " BSON Projection " + projection);
//
//        logger.info("Data Retrieval Started");
//        // Inserting Doc into Json Array to Return as String
//        for (Document d : collection.find(filter).projection(projection)
//        ) {
//            logger.debug("Data - " + d.toJson());
//            jsArray.put(d);
//        }
//
//        // Closing Collection
//        logger.info("Database Connection Closed");
//        mongoClient.close();
//        logger.info("Find SuccessFull");
//        return jsArray;
//    }
//
//    public static String sorted(String key,String order) throws JSONException {
//        Features obj = new Features();
//        JSONArray data = obj.Find();
//
//        JSONArray sortedJsonArray = new JSONArray();
//
//        List<JSONObject> jsonValues = new ArrayList<>();
//        for (int i = 0; i < data.length(); i++) {
//            jsonValues.add(data.getJSONObject(i));
//        }
//        Collections.sort(jsonValues, new Comparator<>() {
//            private final String KEY_NAME = key;
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
//                if (order.equalsIgnoreCase("ASC"))
//                    return valA.compareTo(valB);
//                else
//                    return valB.compareTo(valA);
//            }
//        });
//
//        for (int i = 0; i < data.length(); i++) {
//            sortedJsonArray.put(jsonValues.get(i));
//        }
//        System.out.println();
//        for (int i = 0; i < data.length(); i++) {
//            System.out.println(sortedJsonArray.get(i));
//        }
//        return sortedJsonArray.toString();
//    }

    public static String sorted(String key, String order){

        logger.info("Sort Method Invoked");

        String uri = "mongodb://localhost:27017/forum";
        logger.debug("Connection uri -" + uri);

        MongoClient mongoClient = MongoClients.create(uri);
        logger.info("Database Connection SuccessFull");

        // Getting Forum DataBase
        MongoDatabase database = mongoClient.getDatabase("forum");
        logger.debug("Database Name - " + database.getName());

        // Getting Museum Api Collection
        MongoCollection<Document> collection = database.getCollection("museumApiTest");
        logger.debug("Collection Name -" + collection.getNamespace());

        JSONArray jsArray = new JSONArray();

        // Filter Object ID & Projection _id
        Bson filter = Filters.exists("objectID");
        Bson projection = exclude("_id");
        logger.debug("BSON Filter - " + filter + " BSON Projection " + projection);

        logger.info("Data Retrieval Started");
        // Inserting Doc into Json Array to Return as String

        List<Document> results = new ArrayList<>();
        if (order.equalsIgnoreCase("ASC"))
            collection.find(filter).projection(projection).sort(ascending(key)).into(results);
        else
            collection.find(filter).projection(projection).sort(descending(key)).into(results);
        for (Document d : results
        ) {
            logger.debug("Data - " + d.toJson());
            jsArray.put(d);
        }

        // Closing Collection
        logger.info("Database Connection Closed");
        mongoClient.close();
        logger.info("Sort SuccessFull");
        return jsArray.toString();
    }

    public static String search(String key) {

        String uri = "mongodb://localhost:27017/forum";
        logger.debug("Connection uri -" + uri);

        MongoClient mongoClient = MongoClients.create(uri);
        logger.info("Database Connection SuccessFull");

        // Getting Forum DataBase
        MongoDatabase database = mongoClient.getDatabase("forum");
        logger.debug("Database Name - " + database.getName());

        // Getting Museum Api Collection
        MongoCollection<Document> collection = database.getCollection("museumApiTest");
        logger.debug("Collection Name -" + collection.getNamespace());
        JSONArray jsArray = new JSONArray();

        Bson filter = Filters.text(key);
        Bson projectionFields = Projections.fields(Projections.excludeId());


        collection.find(filter).projection(projectionFields).forEach(doc -> System.out.println("\n" + doc.toJson()));
        System.out.println();

        for (Document d : collection.find(filter).projection(projectionFields)
        ) {
            logger.debug("Data - " + d.toJson());
            jsArray.put(d);
        }

        // Closing Collection
        logger.info("Database Connection Closed");
        mongoClient.close();
        return jsArray.toString();
    }

//    public static void main(String[] args) throws JSONException {
//
//        Features obj = new Features();
//   //   obj.sorted("objectID","DESC");
//        //   obj.sorted("accessionYear","DESC");
//
//      //  obj.search("one");
//    }
}
