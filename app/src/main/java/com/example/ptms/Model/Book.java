package com.example.ptms.Model;

public class Book {
    private String bookingDate, bookigFrom, bookingTo, bookingArrTime, bookingDepTime;
    private int noOfSeats;

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookigFrom() {
        return bookigFrom;
    }

    public void setBookigFrom(String bookigFrom) {
        this.bookigFrom = bookigFrom;
    }

    public String getBookingTo() {
        return bookingTo;
    }

    public void setBookingTo(String bookingTo) {
        this.bookingTo = bookingTo;
    }

    public String getBookingArrTime() {
        return bookingArrTime;
    }

    public void setBookingArrTime(String bookingArrTime) {
        this.bookingArrTime = bookingArrTime;
    }

    public String getBookingDepTime() {
        return bookingDepTime;
    }

    public void setBookingDepTime(String bookingDepTime) {
        this.bookingDepTime = bookingDepTime;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }
}
