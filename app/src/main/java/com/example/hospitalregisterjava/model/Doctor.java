package com.example.hospitalregisterjava.model;

public class Doctor {
    private int id;
    private int deptId;
    private String name;
    private String title;
    private String avatar;
    private String intro;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getDeptId() {
        return deptId;
    }
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }
}
