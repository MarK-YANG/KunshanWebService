package cn.edu.tongji.sse.spring.dao;

import cn.edu.tongji.sse.spring.model.Wifi;

import java.util.List;
import java.util.Map;

/**
 * Created by mark on 12/8/16.
 */
public interface RecordDAO {

    public List<String> findMacsByDeviceAndTime(long _startTime, long _endTime, List<String> _devices);
    public Map<String, int[]> queryMatrixRecords(Map<String, int[]> _preMatrix, int _length, int _index, long _startTime, long _endTime, List<String> _devices);
}
