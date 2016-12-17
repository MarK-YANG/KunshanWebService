package cn.edu.tongji.sse.spring.controller;

import cn.edu.tongji.sse.spring.mongo.MongoUtil;
import com.mongodb.client.MongoCollection;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.bson.Document;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Created by mark on 12/8/16.
 */
public class Main {
    public static void main(String[] args) {
        String whereClause = "";
        String[] _devices = {"a", "b", "c"};
        for (String device: _devices){
            whereClause += "DEVICEID = '" + device + "' OR ";
        }
        whereClause = whereClause.substring(0, whereClause.length()-4);
        System.out.println(whereClause);

        Integer[] arr = new Integer[5];
        System.out.println(arr.length);
        for (Integer a : arr){
            System.out.println(a);
        }

        HashMap<String, int[]> hello = new HashMap<>();
        int[] array = {1,2,3,4,5,6,8};


        MongoUtil util = new MongoUtil();
        Map<String, List> map = new HashMap<>();
        int[] arrNum = {1, 2, 3, 4};
        Arrays.asList(arrNum);
        map.put("B", IntStream.of(arrNum).boxed().collect(Collectors.toList()));
//        map.put("B", arrNum);
//        map.put("C", arrNum);
//        map.put("D", arrNum);
        MongoCollection collection = util.getCollection("Wifi");
        collection.insertOne(new Document("Hello", map));


    }
}
