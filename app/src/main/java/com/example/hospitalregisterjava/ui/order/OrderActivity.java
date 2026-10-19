package com.example.hospitalregisterjava.ui.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.hospitalregisterjava.R;
import com.example.hospitalregisterjava.model.Order;
import com.example.hospitalregisterjava.viewmodel.OrderViewModel;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements OrderAdapter.OnOrderDeleteListener {
    private ListView lvOrder;
    private TextView tvEmptyTip;
    private OrderViewModel orderViewModel;
    private OrderAdapter orderAdapter;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("我的挂号订单");
        }

        username = getIntent().getStringExtra("username");
        lvOrder = findViewById(R.id.lv_order);
        tvEmptyTip = findViewById(R.id.tv_empty_tip);

        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        orderViewModel.initRepo(getApplicationContext());

        orderViewModel.getOrderLiveData().observe(this, (List<Order> orderList) -> {
            if (orderList == null || orderList.isEmpty()) {
                tvEmptyTip.setVisibility(TextView.VISIBLE);
                lvOrder.setVisibility(ListView.GONE);
            } else {
                tvEmptyTip.setVisibility(TextView.GONE);
                lvOrder.setVisibility(ListView.VISIBLE);
                if (orderAdapter == null) {
                    orderAdapter = new OrderAdapter(OrderActivity.this, orderList);
                    orderAdapter.setOnOrderDeleteListener(this);
                    lvOrder.setAdapter(orderAdapter);
                } else {
                    orderAdapter.refreshList(orderList);
                }
            }
        });
        orderViewModel.loadOrdersByUsername(username);
    }

    @Override
    public void onDelete(int orderId) {
        orderViewModel.deleteOrder(orderId);
        Toast.makeText(this, "已取消该挂号订单", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
