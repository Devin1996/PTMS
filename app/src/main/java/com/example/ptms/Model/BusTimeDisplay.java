package com.example.ptms.Model;

public class BusTimeDisplay {
    private String arrTime, depTime, from,rideNo, routeRegNo, searchkey, timeSlotKey, to, trackNo;

//    public BusTimeDisplay(String arrTime, String busTimeKey, String depTime, String from, String routeRegNo, String to, String trackNo) {
//        this.arrTime = arrTime;
//        this.busTimeKey = busTimeKey;
//        this.depTime = depTime;
//        this.from = from;
//        this.routeRegNo = routeRegNo;
//        this.to = to;
//        this.trackNo = trackNo;
//    }
//
//    public BusTimeDisplay(String arrTime, String depTime, String from, String to, String rideNo) {
//        this.arrTime = arrTime;
//        this.depTime = depTime;
//        this.from = from;
//        this.to = to;
//        this.rideNo = rideNo;
//    }
//
//    public BusTimeDisplay(String arrTime , String busTimeKey , String depTime , String from , String to , String rideNo) {
//        this.arrTime = arrTime;
//        this.busTimeKey = busTimeKey;
//        this.depTime = depTime;
//        this.from = from;
//        this.to = to;
//        this.rideNo = rideNo;
//    }


    public BusTimeDisplay(String arrTime , String depTime , String from , String rideNo , String routeRegNo , String searchkey , String timeSlotKey , String to , String trackNo) {
        this.arrTime = arrTime;
        this.depTime = depTime;
        this.from = from;
        this.rideNo = rideNo;
        this.routeRegNo = routeRegNo;
        this.searchkey = searchkey;
        this.timeSlotKey = timeSlotKey;
        this.to = to;
        this.trackNo = trackNo;
    }


    public BusTimeDisplay() {

    }

    public String getRideNo() {
        return rideNo;
    }

    public void setRideNo(String rideNo) {
        this.rideNo = rideNo;
    }

    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey;
    }

    public String getTimeSlotKey() {
        return timeSlotKey;
    }

    public void setTimeSlotKey(String timeSlotKey) {
        this.timeSlotKey = timeSlotKey;
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
