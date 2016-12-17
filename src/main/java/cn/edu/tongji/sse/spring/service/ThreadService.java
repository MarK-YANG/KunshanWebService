package cn.edu.tongji.sse.spring.service;

import cn.edu.tongji.sse.spring.config.AppConfig;
import cn.edu.tongji.sse.spring.dao.ThreadDAOImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by a508 on 17/12/2016.
 */
public class ThreadService {

//    private ThreadDAOImpl threadDAO;
//
//    public ThreadService(){
//        threadDAO = new ThreadDAOImpl();
//    }

    public Map<String, int[]> queryResultByCallable(List<Map<String, Object>> conditions){
        Map<String, int[]> returnMap = new HashMap<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<List<String>>> resultList = new ArrayList<>();

        for (Map<String, Object> condition: conditions){
            //split json parameters
            long startTime = new Long((int)condition.get("start_time"));
            long endTime = new Long((int)condition.get("end_time"));
            List<String> devices = (List<String>) condition.get("devices");

            Future<List<String>> future = executorService.submit(new ThreadDAOImpl(startTime, endTime, devices, AppConfig.getDataSource()));
            resultList.add(future);
        }

        //whether the thread is done
        for (int i = 0 ; i < resultList.size() ;++i){
            try {
                int currentIndex = i;
                Future<List<String>> fs = resultList.get(i);

                //wait for thread finish
                while (! fs.isDone());
                for (String currentMac : fs.get()) {
                    if (returnMap.containsKey(currentMac)) {
                        returnMap.get(currentMac)[currentIndex] = 1;
                    } else {
                        int[] newCode = new int[conditions.size()];
                        newCode[currentIndex] = 1;
                        returnMap.put(currentMac, newCode);
                    }
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (ExecutionException e){
                e.printStackTrace();
            }finally {
                executorService.shutdown();
            }
        }
        return returnMap;

    }
}
