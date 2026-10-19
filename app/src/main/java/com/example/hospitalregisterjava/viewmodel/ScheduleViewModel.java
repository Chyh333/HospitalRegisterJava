package com.example.hospitalregisterjava.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.hospitalregisterjava.model.Schedule;
import com.example.hospitalregisterjava.repository.HospitalRepository;
import java.util.List;

public class ScheduleViewModel extends ViewModel {
    private HospitalRepository repository;
    private final MutableLiveData<List<Schedule>> scheduleLiveData = new MutableLiveData<>();

    public void initRepo(Context context){
        if(repository == null){
            repository = new HospitalRepository(context);
        }
    }

    public MutableLiveData<List<Schedule>> getScheduleLiveData(){
        return scheduleLiveData;
    }

    public void loadScheduleByDoctorId(int doctorId){
        new Thread(() -> {
            List<Schedule> list = repository.getScheduleByDoctor(doctorId);
            scheduleLiveData.postValue(list);
        }).start();
    }
}
