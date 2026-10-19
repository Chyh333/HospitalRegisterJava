package com.example.hospitalregisterjava;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "hospital.db";
    // 数据库版本+1，触发onUpgrade重建表
    private static final int DB_VERSION = 2;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 用户表
        String sqlUser = "create table user(" +
                "id integer primary key autoincrement," +
                "username text unique," +
                "password text," +
                "phone text)";
        db.execSQL(sqlUser);

        String sqlDept = "create table department(" +
                "id integer primary key autoincrement," +
                "deptName text," +
                "deptDesc text)";
        db.execSQL(sqlDept);

        String sqlDoctor = "create table doctor(" +
                "id integer primary key autoincrement," +
                "deptId integer," +
                "name text," +
                "title text," +
                "avatar text," +
                "intro text)";
        db.execSQL(sqlDoctor);

        String sqlSchedule = "create table schedule(" +
                "id integer primary key autoincrement," +
                "doctorId integer," +
                "workDate text," +
                "timePeriod text," +
                "remainNum integer," +
                "price real)";
        db.execSQL(sqlSchedule);

        String sqlOrder = "create table orders(" +
                "id integer primary key autoincrement," +
                "username text," +
                "doctorName text," +
                "deptName text," +
                "workDate text," +
                "timePeriod text," +
                "price real)";
        db.execSQL(sqlOrder);

        // 初始测试账号
        db.execSQL("insert into user(username,password,phone) values('admin','123456','13800138000')");

        // 科室数据
        db.execSQL("insert into department(deptName,deptDesc) values('内科','处理内科相关疾病')");
        db.execSQL("insert into department(deptName,deptDesc) values('外科','处理外科手术相关疾病')");
        db.execSQL("insert into department(deptName,deptDesc) values('儿科','儿童疾病诊疗')");

        // 医生数据
        db.execSQL("insert into doctor(deptId,name,title,avatar,intro) values(1,'张医生','主任医师','','擅长心血管疾病')");
        db.execSQL("insert into doctor(deptId,name,title,avatar,intro) values(1,'李医生','副主任医师','','擅长胃病诊疗')");
        db.execSQL("insert into doctor(deptId,name,title,avatar,intro) values(2,'王医生','主任医师','','擅长骨折手术')");
        db.execSQL("insert into doctor(deptId,name,title,avatar,intro) values(3,'陈医生','主治医师','','擅长小儿感冒、肺炎')");
        db.execSQL("insert into doctor(deptId,name,title,avatar,intro) values(3,'刘医生','副主任医师','','儿童生长发育指导')");

        // 排班数据
        db.execSQL("insert into schedule(doctorId,workDate,timePeriod,remainNum,price) values(1,'2026-10-15','上午8:00-12:00',10,25.0)");
        db.execSQL("insert into schedule(doctorId,workDate,timePeriod,remainNum,price) values(1,'2026-10-16','下午14:00-17:00',8,25.0)");
        db.execSQL("insert into schedule(doctorId,workDate,timePeriod,remainNum,price) values(2,'2026-10-15','上午8:00-12:00',12,20.0)");
        db.execSQL("insert into schedule(doctorId,workDate,timePeriod,remainNum,price) values(2,'2026-10-17','上午8:00-12:00',15,20.0)");
        db.execSQL("insert into schedule(doctorId,workDate,timePeriod,remainNum,price) values(3,'2026-10-16','上午8:00-12:00',6,30.0)");
        db.execSQL("insert into schedule(doctorId,workDate,timePeriod,remainNum,price) values(3,'2026-10-18','下午14:00-17:00',5,30.0)");
        db.execSQL("insert into schedule(doctorId,workDate,timePeriod,remainNum,price) values(4,'2026-10-15','下午14:00-17:00',20,18.0)");
        db.execSQL("insert into schedule(doctorId,workDate,timePeriod,remainNum,price) values(4,'2026-10-17','下午14:00-17:00',16,18.0)");
        db.execSQL("insert into schedule(doctorId,workDate,timePeriod,remainNum,price) values(5,'2026-10-16','上午8:00-12:00',18,22.0)");
        db.execSQL("insert into schedule(doctorId,workDate,timePeriod,remainNum,price) values(5,'2026-10-18','上午8:00-12:00',14,22.0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists department");
        db.execSQL("drop table if exists doctor");
        db.execSQL("drop table if exists schedule");
        db.execSQL("drop table if exists orders");
        onCreate(db);
    }
}


