package com.example.ptms.Model;

public class BusTimeDisplay {
    private String arrTime,busTimeKey,depTime,from,routeRegNo,to,trackNo;

    public BusTimeDisplay(String arrTime, String busTimeKey, String depTime, String from, String routeRegNo, String to, String trackNo) {
        this.arrTime = arrTime;
        this.busTimeKey = busTimeKey;
        this.depTime = depTime;
        this.from = from;
        this.routeRegNo = routeRegNo;
        this.to = to;
        this.trackNo = trackNo;
    }

    public BusTimeDisplay() {

    }

    public String getBusTimeKey() {
        return busTimeKey;
    }

    public void setBusTimeKey(String busTimeKey) {
        this.busTimeKey = busTimeKey;
    }

    public String getRouteRegNo() {
        return routeRegNo;
    }

    public void setRouteRegNo(String routeRegNo) {
        this.routeRegNo = routeRegNo;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getTrackNo() {
        return trackNo;
    }

    public void setTrackNo(String trackNo) {
        this.trackNo = trackNo;
    }
}
