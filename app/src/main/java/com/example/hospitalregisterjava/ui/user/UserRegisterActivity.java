package com.example.hospitalregisterjava.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hospitalregisterjava.R;
import com.example.hospitalregisterjava.repository.HospitalRepository;
import com.example.hospitalregisterjava.ui.login.LoginActivity;

public class UserRegisterActivity extends AppCompatActivity {
    private EditText etRegUsername, etRegPassword, etRegPhone;
    private Button btnRegister, btnBackLogin;
    private HospitalRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        etRegUsername = findViewById(R.id.et_reg_username);
        etRegPassword = findViewById(R.id.et_reg_password);
        etRegPhone = findViewById(R.id.et_reg_phone);
        btnRegister = findViewById(R.id.btn_register);
        btnBackLogin = findViewById(R.id.btn_back_login);
        repository = new HospitalRepository(getApplicationContext());

        // 注册按钮
        btnRegister.setOnClickListener(v -> {
            String username = etRegUsername.getText().toString().trim();
            String password = etRegPassword.getText().toString().trim();
            String phone = etRegPhone.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.length() < 6) {
                Toast.makeText(this, "密码长度不能少于6位", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                boolean success = repository.registerUser(username, password, phone);
                runOnUiThread(() -> {
                    if (success) {
                        Toast.makeText(this, "注册成功！请登录", Toast.LENGTH_SHORT).show();
                        finish(); // 返回登录页
                    } else {
                        Toast.makeText(this, "用户名已存在，请换一个", Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });

        // 返回登录
        btnBackLogin.setOnClickListener(v -> finish());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
