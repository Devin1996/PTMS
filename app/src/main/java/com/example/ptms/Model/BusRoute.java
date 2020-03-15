package com.example.ptms.Model;

public class BusRoute {
    private String routeRegNo, routeNo, routeName, totalDistance, busType, avgSpeed, avgTime , efectiveDate;

    public BusRoute() {
    }

    public BusRoute(String routeRegNo, String routeNo, String routeName, String totalDistance, String busType, String avgSpeed, String avgTime, String efectiveDate) {
        this.routeRegNo = routeRegNo;
        this.routeNo = routeNo;
        this.routeName = routeName;
        this.totalDistance = totalDistance;
        this.busType = busType;
        this.avgSpeed = avgSpeed;
        this.avgTime = avgTime;
        this.efectiveDate = efectiveDate;
    }

    public String getRouteRegNo() {
        return routeRegNo;
    }

    public void setRouteRegNo(String routeRegNo) {
        this.routeRegNo = routeRegNo;
    }

    public String getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(String routeNo) {
        this.routeNo = routeNo;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(String avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public String getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(String avgTime) {
        this.avgTime = avgTime;
    }

    public String getEfectiveDate() {
        return efectiveDate;
    }

    public void setEfectiveDate(String efectiveDate) {
        this.efectiveDate = efectiveDate;
    }
}
