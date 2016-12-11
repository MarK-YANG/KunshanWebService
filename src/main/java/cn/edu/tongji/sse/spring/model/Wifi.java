package cn.edu.tongji.sse.spring.model;

/**
 * Created by mark on 12/8/16.
 */
public class Wifi {

    private int record_id;
    private String deviceid;
    private String mac;
    private String signal;
    private double longitude;
    private double latitude;
    private long firsttime;
    private long lasttime;
    private int times;

    public Wifi(){
    }

    public Wifi(int _recordId, String _deviceId, String _mac, String _signal, double _longitude, double _latitude, long _firsttime, long _lasttime, int _times){
        this.record_id = _recordId;
        this.deviceid = _deviceId;
        this.mac = _mac;
        this.signal = _signal;
        this.longitude = _longitude;
        this.latitude = _latitude;
        this.firsttime = _firsttime;
        this.lasttime = _lasttime;
        this.times = _times;
    }

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(long firsttime) {
        this.firsttime = firsttime;
    }

    public long getLasttime() {
        return lasttime;
    }

    public void setLasttime(long lasttime) {
        this.lasttime = lasttime;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
