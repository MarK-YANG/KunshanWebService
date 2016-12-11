package cn.edu.tongji.sse.spring.dao;

import cn.edu.tongji.sse.spring.model.Wifi;

import java.util.List;

/**
 * Created by mark on 12/8/16.
 */
public interface RecordDAO {

    public List<String> findMacsByDeviceAndTime(long _startTime, long _endTime, List<String> _devices);
}
