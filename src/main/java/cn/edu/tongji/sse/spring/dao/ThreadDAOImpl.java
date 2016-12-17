package cn.edu.tongji.sse.spring.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by a508 on 17/12/2016.
 */
public class ThreadDAOImpl implements ThreadDAO, Callable<List<String>> {

    private long startTime;
    private long endTime;
    private List<String> devices;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ThreadDAOImpl(long _startTime, long _endTime, List<String> _devices, DataSource _dataSource){
        this.startTime = _startTime;
        this.endTime = _endTime;
        this.devices = _devices;
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(_dataSource);
    }

    @Override
    public List<String> call() throws Exception {
        //generate device where clause
        String whereClause = "";
        for (String device: devices){
            whereClause += "DEVICEID = '" + device + "' OR ";
        }
        whereClause = whereClause.substring(0, whereClause.length()-4);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String sql = "SELECT MAC FROM WIFI_USER.WIFI_DATA_AUTO_KEY WHERE\n" +
                "  (FIRSTTIME <= :lasttime and LASTTIME >= :firsttime) and\n" +
                "(" + whereClause + ")";

        Map<String, Object> parameterSource = new HashMap<>();
        parameterSource.put("firsttime", startTime);
        parameterSource.put("lasttime", endTime);
        System.out.println("START \t" + df.format(new Date()));
        List<String> listMacs = namedParameterJdbcTemplate.queryForList(sql, parameterSource, String.class);
        System.out.println("END  \t" + df.format(new Date()));

        return listMacs;
    }
}
