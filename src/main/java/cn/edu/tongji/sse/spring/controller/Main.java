package cn.edu.tongji.sse.spring.controller;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.*;


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

        for (int a: array){
            if(hello.containsKey("abc")){
                continue;
            }else{
                hello.put(String.valueOf(a), new int[5]);
                hello.get(String.valueOf(a))[1] = 111;
            }
        }


        for (Map.Entry<String, int[]> entry : hello.entrySet()){
            for (int a : entry.getValue()){
                System.out.println(a);
            }
        }


        System.out.println("=======");
        int apple = 5;

        for (int i = 0; i < 10; ++i){
            apple = apple + apple;
            System.out.println(apple);
        }
        
        System.out.println(apple);
    }
}
