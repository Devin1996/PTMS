package com.example.ptms.Model;

public class Book {
    private String BookedDate, arrTime, date, depTime, from, numberOfSeats, time, timeSlotKey, to, trackNo;

    public Book(String bookedDate, String arrTime, String date, String depTime, String from, String numberOfSeats, String time, String timeSlotKey, String to, String trackNo) {
        this.BookedDate = bookedDate;
        this.arrTime = arrTime;
        this.date = date;
        this.depTime = depTime;
        this.from = from;
        this.numberOfSeats = numberOfSeats;
        this.time = time;
        this.timeSlotKey = timeSlotKey;
        this.to = to;
        this.trackNo = trackNo;
    }

    public Book() {
    }

    public String getBookedDate() {
        return BookedDate;
    }

    public void setBookedDate(String bookedDate) {
        this.BookedDate = bookedDate;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(String numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
