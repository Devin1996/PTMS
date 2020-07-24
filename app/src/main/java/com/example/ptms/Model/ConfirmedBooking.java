package com.example.ptms.Model;

public class ConfirmedBooking {
    private String arrTime, depTime, from, rideNo, routeRegNo, searchkey, timeSlotKey, to, trackNo, noOfSeats, bookedDate;

    public ConfirmedBooking() {
    }

    public ConfirmedBooking(String arrTime , String depTime , String from , String rideNo , String routeRegNo , String searchkey , String timeSlotKey , String to , String trackNo , String noOfSeats , String bookedDate) {
        this.arrTime = arrTime;
        this.depTime = depTime;
        this.from = from;
        this.rideNo = rideNo;
        this.routeRegNo = routeRegNo;
        this.searchkey = searchkey;
        this.timeSlotKey = timeSlotKey;
        this.to = to;
        this.trackNo = trackNo;
        this.noOfSeats = noOfSeats;
        this.bookedDate = bookedDate;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getRideNo() {
        return rideNo;
    }

    public void setRideNo(String rideNo) {
        this.rideNo = rideNo;
    }

    public String getRouteRegNo() {
        return routeRegNo;
    }

    public void setRouteRegNo(String routeRegNo) {
        this.routeRegNo = routeRegNo;
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

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTrackNo() {
        return trackNo;
    }

    public void setTrackNo(String trackNo) {
        this.trackNo = trackNo;
    }

    public String getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(String noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(String bookedDate) {
        this.bookedDate = bookedDate;
    }
}
