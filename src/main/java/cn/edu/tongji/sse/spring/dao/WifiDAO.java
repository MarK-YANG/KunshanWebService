package cn.edu.tongji.sse.spring.dao;

import cn.edu.tongji.sse.spring.model.Wifi;

import java.util.List;
import java.util.Set;

/**
 * Created by mark on 12/8/16.
 */
public interface WifiDAO {
    public List<Wifi> list();
    public Set<String> set();

    public Set<String> distinctMac();
}
