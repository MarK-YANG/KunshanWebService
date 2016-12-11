package cn.edu.tongji.sse.spring.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mark on 12/8/16.
 */
public class RecordDAOImpl implements RecordDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RecordDAOImpl(DataSource dataSource){
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public List<String> findMacsByDeviceAndTime(long _startTime, long _endTime, List<String> _devices) {

        //add device conditions
        String whereClause = "";
        for (String device: _devices){
            whereClause += "DEVICEID = '" + device + "' OR ";
        }
        whereClause = whereClause.substring(0, whereClause.length()-4);

        String sql = "SELECT MAC FROM WIFI_USER.WIFI_DATA_AUTO_KEY WHERE\n" +
                "  (LASTTIME >= :firsttime and FIRSTTIME <= :lasttime) and\n" +
                "(" + whereClause + ")";

        Map<String, Object> parameterSource = new HashMap<>();
        parameterSource.put("firsttime", _startTime);
        parameterSource.put("lasttime", _endTime);

        List<String> listMacs = namedParameterJdbcTemplate.queryForList(sql, parameterSource, String.class);

        return listMacs;
    }
}
