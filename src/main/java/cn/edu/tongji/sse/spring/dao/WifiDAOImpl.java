package cn.edu.tongji.sse.spring.dao;

import cn.edu.tongji.sse.spring.model.Wifi;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mark on 12/8/16.
 */
public class WifiDAOImpl implements WifiDAO {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public WifiDAOImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Wifi> list() {
        String sql = "SELECT * FROM WIFI_DATA_AUTO_KEY WHERE mac = '74785120061503'";
        List<Wifi> listWifi = jdbcTemplate.query(sql, new WifiMapper());
        return listWifi;

    }


    @Override
    public Set<String> set() {

        String sql = "SELECT mac FROM WIFI_DATA_AUTO_KEY WHERE firsttime = :firsttime";

        SqlParameterSource namedParameters = new MapSqlParameterSource("firsttime", 1473471131);

        HashSet<String> macSet = new HashSet<>();
        SqlRowSet results = namedParameterJdbcTemplate.queryForRowSet(sql, namedParameters);

        while (results.next()){
            macSet.add(results.getString(1));
        }

        return new HashSet(macSet);

    }

    @Override
    public Set<String> distinctMac() {
        String sql = "SELECT MAC FROM WIFI_USER.WIFI_DATA_AUTO_KEY WHERE\n" +
                "  (FIRSTTIME >= 1473471000 and LASTTIME <= 1473471300) and\n" +
                "  (\n" +
                "      DEVICEID = '728489494000FE20116B2' OR\n" +
                "      DEVICEID = '71466611100E04CF6121A' OR\n" +
                "      DEVICEID = '728489494000FE2010F80' OR\n" +
                "      DEVICEID = '728489494000FE2010F47' OR\n" +
                "      DEVICEID = '728489494000FE2010EC0' OR\n" +
                "      DEVICEID = '71466611100E04CF61211' OR\n" +
                "      DEVICEID = '71466611100E04CF61227' OR\n" +
                "      DEVICEID = '71466611100E04CF61967'\n" +
                "  )";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        HashSet<String> macs = new HashSet<>();
        while (rowSet.next()){
            macs.add(rowSet.getString(1));
        }
        return macs;
    }

    private static final class WifiMapper implements RowMapper<Wifi> {

        public Wifi mapRow(ResultSet resultSet, int i) throws SQLException {

            Wifi aWifi = new Wifi();
            aWifi.setRecord_id(resultSet.getInt("record_id"));
            aWifi.setDeviceid(resultSet.getString("deviceid"));
            aWifi.setMac(resultSet.getString("mac"));
            aWifi.setSignal(resultSet.getString("signal"));
            aWifi.setLongitude(resultSet.getDouble("longtitude"));
            aWifi.setLatitude(resultSet.getDouble("latitude"));
            aWifi.setFirsttime(resultSet.getLong("firsttime"));
            aWifi.setLasttime(resultSet.getLong("lasttime"));
            aWifi.setTimes(resultSet.getInt("times"));

            return aWifi;
        }
    }

}
