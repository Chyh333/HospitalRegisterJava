package com.example.hospitalregisterjava.ui.doctor;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.example.hospitalregisterjava.R;
import com.example.hospitalregisterjava.model.Doctor;
import com.example.hospitalregisterjava.ui.schedule.ScheduleActivity;
import com.example.hospitalregisterjava.viewmodel.DoctorViewModel;

public class DoctorActivity extends AppCompatActivity {
    private ListView lvDoctor;
    private DoctorViewModel doctorViewModel;
    private TextView tvDeptTitle;
    private String deptName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        lvDoctor = findViewById(R.id.lv_doctor);
        tvDeptTitle = findViewById(R.id.tv_dept_title);

        int deptId = getIntent().getIntExtra("deptId", -1);
        deptName = getIntent().getStringExtra("deptName");
        tvDeptTitle.setText(deptName + " - 医生列表");

        doctorViewModel = new ViewModelProvider(this).get(DoctorViewModel.class);
        doctorViewModel.initRepo(getApplicationContext());

        doctorViewModel.getDoctorLiveData().observe(this, doctorList -> {
            if (doctorList != null) {
                DoctorAdapter adapter = new DoctorAdapter(DoctorActivity.this, doctorList);
                lvDoctor.setAdapter(adapter);
            }
        });
        doctorViewModel.loadDoctorByDeptId(deptId);

        lvDoctor.setOnItemClickListener((parent, view, position, id) -> {
            Doctor selectDoctor = (Doctor) lvDoctor.getItemAtPosition(position);
            Intent intent = new Intent(DoctorActivity.this, ScheduleActivity.class);
            intent.putExtra("doctorId", selectDoctor.getId());
            intent.putExtra("doctorName", selectDoctor.getName());
            intent.putExtra("deptName", deptName);
            startActivity(intent);
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
