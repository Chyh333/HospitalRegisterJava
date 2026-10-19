package com.example.hospitalregisterjava;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.hospitalregisterjava.ui.home.HomeActivity;
import com.example.hospitalregisterjava.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 检查是否已登录
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String username = sp.getString("username", null);

        if (username != null) {
            // 已登录，直接进首页
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        } else {
            // 未登录，进登录页
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        finish();
    }
}

