package com.example.hospitalregisterjava.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.hospitalregisterjava.DBHelper;
import com.example.hospitalregisterjava.model.Department;
import com.example.hospitalregisterjava.model.Doctor;
import com.example.hospitalregisterjava.model.Order;
import com.example.hospitalregisterjava.model.Schedule;
import com.example.hospitalregisterjava.model.User;
import java.util.ArrayList;
import java.util.List;

public class HospitalRepository {
    private DBHelper dbHelper;

    public HospitalRepository(Context context) {
        dbHelper = new DBHelper(context);
    }

    // ========== 用户相关 ==========

    // 用户注册：返回true成功，false用户名已存在
    public boolean registerUser(String username, String password, String phone) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // 先检查用户名是否已存在
        Cursor cursor = db.query("user", null, "username=?", new String[]{username}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return false; // 用户名已存在
        }
        cursor.close();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        values.put("phone", phone);
        long result = db.insert("user", null, values);
        db.close();
        return result != -1;
    }

    // 用户登录：返回true登录成功，false账号或密码错误
    public boolean loginUser(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("user", null,
                "username=? and password=?",
                new String[]{username, password},
                null, null, null);
        boolean success = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return success;
    }

    // ========== 原有科室/医生/排班/订单方法保持不变 ==========

    public List<Department> queryAllDepartment() {
        List<Department> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("department", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Department dept = new Department();
            dept.setId(cursor.getInt(0));
            dept.setDeptName(cursor.getString(1));
            dept.setDeptDesc(cursor.getString(2));
            list.add(dept);
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<Doctor> getDoctorByDeptId(int deptId) {
        List<Doctor> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("doctor", null, "deptId=?", new String[]{String.valueOf(deptId)}, null, null, null);
        while (cursor.moveToNext()) {
            Doctor doctor = new Doctor();
            doctor.setId(cursor.getInt(0));
            doctor.setDeptId(cursor.getInt(1));
            doctor.setName(cursor.getString(2));
            doctor.setTitle(cursor.getString(3));
            doctor.setAvatar(cursor.getString(4));
            doctor.setIntro(cursor.getString(5));
            list.add(doctor);
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<Schedule> getScheduleByDoctor(int doctorId) {
        List<Schedule> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("schedule", null, "doctorId=?", new String[]{String.valueOf(doctorId)}, null, null, null);
        while (cursor.moveToNext()) {
            Schedule schedule = new Schedule();
            schedule.setId(cursor.getInt(0));
            schedule.setDoctorId(cursor.getInt(1));
            schedule.setWorkDate(cursor.getString(2));
            schedule.setTimePeriod(cursor.getString(3));
            schedule.setRemainNum(cursor.getInt(4));
            schedule.setPrice(cursor.getDouble(5));
            list.add(schedule);
        }
        cursor.close();
        db.close();
        return list;
    }

    public boolean addOrder(String username, String doctorName, String deptName, String workDate, String timePeriod, double price){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("doctorName", doctorName);
        values.put("deptName", deptName);
        values.put("workDate", workDate);
        values.put("timePeriod", timePeriod);
        values.put("price", price);
        long result = db.insert("orders", null, values);
        db.close();
        return result != -1;
    }

    public List<Order> getAllOrders(){
        List<Order> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("orders", null, null, null, null, null, "id desc");
        while (cursor.moveToNext()){
            Order order = new Order();
            order.setId(cursor.getInt(0));
            order.setDoctorName(cursor.getString(2));
            order.setDeptName(cursor.getString(3));
            order.setWorkDate(cursor.getString(4));
            order.setTimePeriod(cursor.getString(5));
            order.setPrice(cursor.getDouble(6));
            list.add(order);
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<Order> getOrdersByUsername(String username){
        List<Order> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("orders", null, "username=?", new String[]{username}, null, null, "id desc");
        while (cursor.moveToNext()){
            Order order = new Order();
            order.setId(cursor.getInt(0));
            order.setDoctorName(cursor.getString(2));
            order.setDeptName(cursor.getString(3));
            order.setWorkDate(cursor.getString(4));
            order.setTimePeriod(cursor.getString(5));
            order.setPrice(cursor.getDouble(6));
            list.add(order);
        }
        cursor.close();
        db.close();
        return list;
    }

    public boolean deleteOrderById(int orderId){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deleteRows = db.delete("orders", "id=?", new String[]{String.valueOf(orderId)});
        db.close();
        return deleteRows > 0;
    }
}

