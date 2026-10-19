package com.example.hospitalregisterjava.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.hospitalregisterjava.R;
import com.example.hospitalregisterjava.model.Department;
import com.example.hospitalregisterjava.ui.doctor.DoctorActivity;
import com.example.hospitalregisterjava.ui.login.LoginActivity;
import com.example.hospitalregisterjava.ui.order.OrderActivity;
import com.example.hospitalregisterjava.viewmodel.HomeViewModel;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ListView lvDept;
    private HomeViewModel homeViewModel;
    private Button btnMyOrder, btnLogout;
    private TextView tvEmptyDeptTip;
    private DeptAdapter deptAdapter;
    private String currentUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lvDept = findViewById(R.id.lv_dept);
        btnMyOrder = findViewById(R.id.btn_my_order);
        btnLogout = findViewById(R.id.btn_logout);
        tvEmptyDeptTip = findViewById(R.id.tv_empty_dept_tip);

        // 获取当前登录用户名
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        currentUsername = sp.getString("username", "");
        Toast.makeText(this, "欢迎您，" + currentUsername, Toast.LENGTH_SHORT).show();

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.initRepo(getApplicationContext());

        homeViewModel.getDeptLiveData().observe(this, (List<Department> deptList) -> {
            if (deptList == null || deptList.isEmpty()) {
                tvEmptyDeptTip.setVisibility(TextView.VISIBLE);
                lvDept.setVisibility(ListView.GONE);
            } else {
                tvEmptyDeptTip.setVisibility(TextView.GONE);
                lvDept.setVisibility(ListView.VISIBLE);
                if (deptAdapter == null) {
                    deptAdapter = new DeptAdapter(HomeActivity.this, deptList);
                    lvDept.setAdapter(deptAdapter);
                } else {
                    deptAdapter.refreshList(deptList);
                }
            }
        });
        homeViewModel.loadDeptData();

        lvDept.setOnItemClickListener((parent, view, position, id) -> {
            Department selectDept = (Department) lvDept.getItemAtPosition(position);
            Intent intent = new Intent(HomeActivity.this, DoctorActivity.class);
            intent.putExtra("deptId", selectDept.getId());
            intent.putExtra("deptName", selectDept.getDeptName());
            startActivity(intent);
        });

        btnMyOrder.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, OrderActivity.class);
            intent.putExtra("username", currentUsername);
            startActivity(intent);
        });

        // 退出登录
        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.apply();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
        });
    }
}

