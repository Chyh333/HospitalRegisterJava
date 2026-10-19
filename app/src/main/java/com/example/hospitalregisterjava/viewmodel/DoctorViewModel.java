package com.example.hospitalregisterjava.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.hospitalregisterjava.model.Doctor;
import com.example.hospitalregisterjava.repository.HospitalRepository;
import java.util.List;

public class DoctorViewModel extends ViewModel {
    private HospitalRepository repository;
    private final MutableLiveData<List<Doctor>> doctorLiveData = new MutableLiveData<>();

    public void initRepo(Context context){
        if(repository == null){
            repository = new HospitalRepository(context);
        }
    }

    public MutableLiveData<List<Doctor>> getDoctorLiveData(){
        return doctorLiveData;
    }

    public void loadDoctorByDeptId(int deptId){
        new Thread(() -> {
            List<Doctor> list = repository.getDoctorByDeptId(deptId);
            doctorLiveData.postValue(list);
        }).start();
    }
}
