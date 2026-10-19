package com.example.hospitalregisterjava.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hospitalregisterjava.R;
import com.example.hospitalregisterjava.repository.HospitalRepository;
import com.example.hospitalregisterjava.ui.home.HomeActivity;
import com.example.hospitalregisterjava.ui.user.UserRegisterActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin, btnGoRegister;
    private HospitalRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnGoRegister = findViewById(R.id.btn_go_register);
        repository = new HospitalRepository(getApplicationContext());

        // 登录按钮
        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            // 子线程执行登录验证
            new Thread(() -> {
                boolean success = repository.loginUser(username, password);
                runOnUiThread(() -> {
                    if (success) {
                        // 保存登录状态到SharedPreferences
                        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
                        sp.edit().putString("username", username).apply();

                        Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });

        // 跳转到注册页面
        btnGoRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, UserRegisterActivity.class));
        });
    }
}
