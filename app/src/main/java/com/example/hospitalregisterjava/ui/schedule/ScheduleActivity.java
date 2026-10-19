package com.example.hospitalregisterjava.ui.schedule;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.example.hospitalregisterjava.R;
import com.example.hospitalregisterjava.model.Schedule;
import com.example.hospitalregisterjava.ui.register.RegisterActivity;
import com.example.hospitalregisterjava.viewmodel.ScheduleViewModel;

public class ScheduleActivity extends AppCompatActivity {
    private ListView lvSchedule;
    private ScheduleViewModel scheduleViewModel;
    private TextView tvDoctorInfo;
    private String doctorName;
    private String deptName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        lvSchedule = findViewById(R.id.lv_schedule);
        tvDoctorInfo = findViewById(R.id.tv_doctor_info);

        int doctorId = getIntent().getIntExtra("doctorId", -1);
        doctorName = getIntent().getStringExtra("doctorName");
        deptName = getIntent().getStringExtra("deptName");
        tvDoctorInfo.setText(deptName + " | 医生：" + doctorName + " 的出诊排班");

        scheduleViewModel = new ViewModelProvider(this).get(ScheduleViewModel.class);
        scheduleViewModel.initRepo(getApplicationContext());

        scheduleViewModel.getScheduleLiveData().observe(this, scheduleList -> {
            ScheduleAdapter adapter = new ScheduleAdapter(ScheduleActivity.this, scheduleList);
            lvSchedule.setAdapter(adapter);
        });
        scheduleViewModel.loadScheduleByDoctorId(doctorId);

        lvSchedule.setOnItemClickListener((parent, view, position, id) -> {
            Schedule schedule = (Schedule) lvSchedule.getItemAtPosition(position);
            Intent intent = new Intent(ScheduleActivity.this, RegisterActivity.class);
            intent.putExtra("deptName", deptName);
            intent.putExtra("doctorName", doctorName);
            intent.putExtra("workDate", schedule.getWorkDate());
            intent.putExtra("timePeriod", schedule.getTimePeriod());
            intent.putExtra("price", schedule.getPrice());
            startActivity(intent);
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
