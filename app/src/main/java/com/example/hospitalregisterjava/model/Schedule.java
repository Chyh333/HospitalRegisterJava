package com.example.hospitalregisterjava.model;

public class Schedule {
    private int id;
    private int doctorId;
    private String workDate;
    private String timePeriod;
    private int remainNum;
    private double price;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public String getWorkDate() {
        return workDate;
    }
    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }
    public String getTimePeriod() {
        return timePeriod;
    }
    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }
    public int getRemainNum() {
        return remainNum;
    }
    public void setRemainNum(int remainNum) {
        this.remainNum = remainNum;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
