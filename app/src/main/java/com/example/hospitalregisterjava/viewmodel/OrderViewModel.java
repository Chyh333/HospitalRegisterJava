package com.example.hospitalregisterjava.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.hospitalregisterjava.model.Order;
import com.example.hospitalregisterjava.repository.HospitalRepository;
import java.util.List;

public class OrderViewModel extends ViewModel {
    private HospitalRepository repository;
    private final MutableLiveData<List<Order>> orderLiveData = new MutableLiveData<>();

    public void initRepo(Context context) {
        if (repository == null) {
            repository = new HospitalRepository(context);
        }
    }

    public MutableLiveData<List<Order>> getOrderLiveData() {
        return orderLiveData;
    }

    public void loadOrdersByUsername(String username) {
        new Thread(() -> {
            List<Order> list = repository.getOrdersByUsername(username);
            orderLiveData.postValue(list);
        }).start();
    }

    public void deleteOrder(int orderId) {
        new Thread(() -> {
            repository.deleteOrderById(orderId);
            // 这里需要重新加载当前用户订单，简单起见直接刷新全部
            List<Order> newList = repository.getAllOrders();
            orderLiveData.postValue(newList);
        }).start();
    }
}
