package com.example.hospitalregisterjava.ui.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hospitalregisterjava.R;
import com.example.hospitalregisterjava.repository.HospitalRepository;

public class RegisterActivity extends AppCompatActivity {
    private TextView tvDept, tvDoctor, tvDate, tvTime, tvPrice;
    private Button btnSubmit;
    private HospitalRepository repository;
    private String doctorName;
    private String deptName;
    private String workDate;
    private String timePeriod;
    private double price;
    private String currentUsername; // 当前登录用户

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // 读取登录的用户名
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        currentUsername = sp.getString("username","");

        repository = new HospitalRepository(getApplicationContext());
        tvDept = findViewById(R.id.tv_dept);
        tvDoctor = findViewById(R.id.tv_doctor);
        tvDate = findViewById(R.id.tv_date);
        tvTime = findViewById(R.id.tv_time);
        tvPrice = findViewById(R.id.tv_price);
        btnSubmit = findViewById(R.id.btn_submit);

        Intent intent = getIntent();
        deptName = intent.getStringExtra("deptName");
        doctorName = intent.getStringExtra("doctorName");
        workDate = intent.getStringExtra("workDate");
        timePeriod = intent.getStringExtra("timePeriod");
        price = intent.getDoubleExtra("price",0);

        tvDept.setText("科室："+deptName);
        tvDoctor.setText("医生："+doctorName);
        tvDate.setText("出诊日期："+workDate);
        tvTime.setText("出诊时段："+timePeriod);
        tvPrice.setText("挂号费用："+price+" 元");

        btnSubmit.setOnClickListener(v -> {
            // 子线程执行数据库插入
            new Thread(() -> {
                boolean success = repository.addOrder(currentUsername, doctorName,deptName,workDate,timePeriod,price);
                runOnUiThread(() -> {
                    if(success){
                        Toast.makeText(RegisterActivity.this,"挂号成功！",Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(RegisterActivity.this,"挂号失败！",Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
