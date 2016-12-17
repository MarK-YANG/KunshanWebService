package cn.edu.tongji.sse.spring.controller;

import cn.edu.tongji.sse.spring.dao.RecordDAO;
import cn.edu.tongji.sse.spring.dao.WifiDAO;
import cn.edu.tongji.sse.spring.mongo.MongoUtil;
import cn.edu.tongji.sse.spring.service.RecordService;
import cn.edu.tongji.sse.spring.service.ThreadService;
import cn.edu.tongji.sse.spring.service.WifiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mark on 12/8/16.
 */

@RestController
public class RestfulController {
    @Autowired
    private WifiDAO _wifiDAO;
    @Autowired
    private RecordDAO recordDAO;

    private WifiService wifiService;
    private RecordService recordService;
    private ThreadService threadService;

    @GetMapping(value = "/hhhh")
    public ResponseEntity sayHello(){
        return new ResponseEntity("hello", HttpStatus.OK);
    }

    @GetMapping(value="/w")
    public ResponseEntity getWifi(){
        Set<String> setWifi = _wifiDAO.set();
        List<String> listWifi = new ArrayList<>(setWifi);
        return new ResponseEntity(listWifi, HttpStatus.OK);
    }

    @PostMapping(value = "/post")
    public ResponseEntity postJson(@RequestBody Map<String, Object> json){
        List<Map<String, Object>> conditions = (List<Map<String, Object>>) json.get("conditions");
        Map<String, Object> condition = conditions.get(0);
        long startTime = new Long((int)condition.get("start_time"));
        long endTime = new Long((int)condition.get("end_time"));

        List<String> macs = recordDAO.findMacsByDeviceAndTime(startTime, endTime, (List<String>) condition.get("devices"));
        return new ResponseEntity(macs, HttpStatus.OK);
    }

    @PostMapping(value = "/test")
    public ResponseEntity testInterface(@RequestBody Map<String, Object> json){
        List<Map<String, Object>> conditions = (List<Map<String, Object>>) json.get("conditions");
        wifiService = new WifiService();
        Map<String, int[]> returnMap = wifiService.wifiResult(conditions);
        return new ResponseEntity(returnMap, HttpStatus.OK);
    }

    @PostMapping(value = "/memory")
    public ResponseEntity rsInterface(@RequestBody Map<String, Object> json){
        List<Map<String, Object>> conditions = (List<Map<String, Object>>) json.get("conditions");
        recordService = new RecordService();
        Map<String, int[]> returnMap = recordService.queryWifiRecords(conditions);
        return new ResponseEntity(returnMap, HttpStatus.OK);
    }

    @PostMapping(value = "/thread")
    public ResponseEntity threadInterface(@RequestBody Map<String, Object> json){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Start: " + df.format(new Date()));
        List<Map<String, Object>> conditions = (List<Map<String, Object>>) json.get("conditions");
        threadService = new ThreadService();
        Map<String, int[]> returnMap = threadService.queryResultByCallable(conditions);
        MongoUtil util = new MongoUtil();
        System.out.println("Start writing into mongodb: " + df.format(new Date()));
        util.insertOneRecord("Wifi", util.Map2Document(returnMap));
        System.out.println("FINISH: " + df.format(new Date()));
        return new ResponseEntity("HelloWorld", HttpStatus.OK);
    }
}
