package cn.edu.tongji.sse.spring.service;

import cn.edu.tongji.sse.spring.config.AppConfig;
import cn.edu.tongji.sse.spring.dao.RecordDAO;
import cn.edu.tongji.sse.spring.dao.RecordDAOImpl;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mark on 12/17/16.
 */
public class RecordService {

    private RecordDAO recordDAO;

    public Map<String, int[]> queryWifiRecords(List<Map<String, Object>> conditions){
        recordDAO = new RecordDAOImpl(AppConfig.getDataSource());

        Map<String, int[]> returnMap = new HashMap<>();

        int iLength = conditions.size();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(new Date()));

        int iIndex = 0;

        for (Map<String, Object> condition: conditions){
            long startTime = new Long((int)condition.get("start_time"));
            long endTime = new Long((int)condition.get("end_time"));
            List<String> devices = (List<String>) condition.get("devices");
            System.out.println(iIndex + "\tSTART\t" + df.format(new Date()));
            returnMap = recordDAO.queryMatrixRecords(returnMap, iLength, iIndex, startTime, endTime, devices);
            System.out.println(iIndex + "\tEND   \t" + df.format(new Date()));
            iIndex ++;
        }


        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        return returnMap;

    }
}
