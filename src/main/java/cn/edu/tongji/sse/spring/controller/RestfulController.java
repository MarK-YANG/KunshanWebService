package cn.edu.tongji.sse.spring.controller;

import cn.edu.tongji.sse.spring.dao.RecordDAO;
import cn.edu.tongji.sse.spring.dao.WifiDAO;
import cn.edu.tongji.sse.spring.service.WifiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
}
