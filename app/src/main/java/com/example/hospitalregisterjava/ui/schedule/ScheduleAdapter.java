package com.example.hospitalregisterjava.ui.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.hospitalregisterjava.R;
import com.example.hospitalregisterjava.model.Schedule;
import java.util.List;

public class ScheduleAdapter extends BaseAdapter {
    private Context context;
    private List<Schedule> scheduleList;
    public ScheduleAdapter(Context context, List<Schedule> scheduleList) {
        this.context = context;
        this.scheduleList = scheduleList;
    }

    @Override
    public int getCount() {
        return scheduleList.size();
    }

    @Override
    public Object getItem(int position) {
        return scheduleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_schedule, parent, false);
            holder = new ViewHolder();
            holder.tvDate = convertView.findViewById(R.id.tv_schedule_date);
            holder.tvTime = convertView.findViewById(R.id.tv_schedule_time);
            holder.tvPrice = convertView.findViewById(R.id.tv_schedule_price);
            holder.tvRemain = convertView.findViewById(R.id.tv_remain_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Schedule schedule = scheduleList.get(position);
        holder.tvDate.setText("出诊日期：" + schedule.getWorkDate());
        holder.tvTime.setText("时段：" + schedule.getTimePeriod());
        holder.tvPrice.setText("挂号费：" + schedule.getPrice() + "元");
        holder.tvRemain.setText("剩余号源：" + schedule.getRemainNum());
        return convertView;
    }

    static class ViewHolder{
        TextView tvDate;
        TextView tvTime;
        TextView tvPrice;
        TextView tvRemain;
    }
}
