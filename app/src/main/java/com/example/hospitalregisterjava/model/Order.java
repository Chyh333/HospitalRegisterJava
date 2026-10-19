package com.example.hospitalregisterjava.model;

public class Order {
    private int id;
    private String doctorName;
    private String deptName;
    private String workDate;
    private String timePeriod;
    private double price;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
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
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
