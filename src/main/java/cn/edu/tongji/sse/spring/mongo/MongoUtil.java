package cn.edu.tongji.sse.spring.mongo;

import cn.edu.tongji.sse.spring.config.AppConfig;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by a508 on 17/12/2016.
 */
public class MongoUtil {

    private final String ADDRESS = "10.10.240.118";
    private final int PORT = 17017;
    private final String DB_NAME = "Kunshan";
    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoUtil(){
        mongoClient = new MongoClient(ADDRESS, PORT);
        database = mongoClient.getDatabase(DB_NAME);
    }

    public MongoCollection getCollection(String _collection){
        return database.getCollection(_collection);
    }

    public void insertOneRecord(String _collectionName, Document _row){
        MongoCollection collection = getCollection(_collectionName);
        collection.insertOne(_row);
    }

    public Document Map2Document(Map<String, int[]> _inputMap){
        Document document = new Document();
        //temp
        //temp
        document.append("QueryID", new Random().nextInt());
        List<Document> results = new ArrayList<>();
        for (Map.Entry<String, int[]> entry: _inputMap.entrySet()){
            Document row = new Document("mac", entry.getKey());
            List<Integer> iList = IntStream.of(entry.getValue()).boxed().collect(Collectors.toList());
            row.append("arr", iList);
            results.add(row);
        }
        document.append("result", results);
        return document;
    }
}
