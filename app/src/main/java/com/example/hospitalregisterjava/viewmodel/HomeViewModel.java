package com.example.hospitalregisterjava.viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.hospitalregisterjava.model.Department;
import com.example.hospitalregisterjava.repository.HospitalRepository;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private final MutableLiveData<List<Department>> deptLiveData = new MutableLiveData<>();
    private HospitalRepository repository;

    public void initRepo(Context context) {
        if(repository == null){
            repository = new HospitalRepository(context);
        }
    }

    public LiveData<List<Department>> getDeptLiveData() {
        return deptLiveData;
    }

    public void loadDeptData() {
        new Thread(() -> {
            List<Department> dataList = repository.queryAllDepartment();
            deptLiveData.postValue(dataList);
        }).start();
    }
}
