package com.example.hospitalregisterjava.ui.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.example.hospitalregisterjava.R;
import com.example.hospitalregisterjava.model.Order;
import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private List<Order> orderList;
    private OnOrderDeleteListener deleteListener;

    public interface OnOrderDeleteListener{
        void onDelete(int orderId);
    }

    public void setOnOrderDeleteListener(OnOrderDeleteListener listener){
        this.deleteListener = listener;
    }

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
            holder = new ViewHolder();
            holder.tvDept = convertView.findViewById(R.id.tv_order_dept);
            holder.tvDoctor = convertView.findViewById(R.id.tv_order_doctor);
            holder.tvDate = convertView.findViewById(R.id.tv_order_date);
            holder.tvTime = convertView.findViewById(R.id.tv_order_time);
            holder.tvPrice = convertView.findViewById(R.id.tv_order_price);
            holder.btnDelete = convertView.findViewById(R.id.btn_delete_order);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Order order = orderList.get(position);
        holder.tvDept.setText("科室：" + order.getDeptName());
        holder.tvDoctor.setText("医生：" + order.getDoctorName());
        holder.tvDate.setText("出诊日期：" + order.getWorkDate());
        holder.tvTime.setText("出诊时段：" + order.getTimePeriod());
        holder.tvPrice.setText("挂号费：" + order.getPrice() + "元");

        holder.btnDelete.setOnClickListener(v -> {
            if(deleteListener != null){
                deleteListener.onDelete(order.getId());
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView tvDept;
        TextView tvDoctor;
        TextView tvDate;
        TextView tvTime;
        TextView tvPrice;
        Button btnDelete;
    }

    public void refreshList(List<Order> newList){
        this.orderList = newList;
        notifyDataSetChanged();
    }
}
