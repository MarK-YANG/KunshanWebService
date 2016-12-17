package cn.edu.tongji.sse.spring.service;

import cn.edu.tongji.sse.spring.config.AppConfig;
import cn.edu.tongji.sse.spring.dao.RecordDAO;
import cn.edu.tongji.sse.spring.dao.RecordDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mark on 12/8/16.
 */

public class WifiService {

    private RecordDAO recordDAO;

    public Map<String, int[]> wifiResult(List<Map<String, Object>> conditions){
        recordDAO = new RecordDAOImpl(AppConfig.getDataSource());
        HashMap<String, int[]> returnMap = new HashMap<>();
        ArrayList<List<String>> allMacs = new ArrayList<>();

        int iNum = conditions.size();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

        int iIndex = 0;
        for (Map<String, Object> condition: conditions){
            long startTime = new Long((int)condition.get("start_time"));
            long endTime = new Long((int)condition.get("end_time"));
            List<String> devices = (List<String>) condition.get("devices");
            System.out.println(iIndex + "\tSTART\t" + df.format(new Date()));
            List<String> macs = recordDAO.findMacsByDeviceAndTime(startTime, endTime, devices);
            System.out.println(iIndex + "\tEND   \t" + df.format(new Date()));
            allMacs.add(macs);
            iIndex ++;
        }

        for (int i = 0; i < allMacs.size(); ++i){
            for (String currentMac : allMacs.get(i)){
                if (returnMap.containsKey(currentMac)){
                    returnMap.get(currentMac)[i] = 1;
                }else{
                    int[] newCode = new int[iNum];
                    newCode[i] = 1;
                    returnMap.put(currentMac, newCode);
                }
            }
        }

        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        return returnMap;

    }
}
