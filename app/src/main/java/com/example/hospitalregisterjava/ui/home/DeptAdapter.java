package com.example.hospitalregisterjava.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.hospitalregisterjava.R;
import com.example.hospitalregisterjava.model.Department;
import java.util.List;

public class DeptAdapter extends BaseAdapter {
    private Context context;
    private List<Department> list;

    public DeptAdapter(Context context, List<Department> list){
        this.context = context;
        this.list = list;
    }

    public void refreshList(List<Department> newList) {
        this.list = newList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dept, parent, false);
            holder = new ViewHolder();
            holder.tvName = convertView.findViewById(R.id.tv_dept_name);
            holder.tvDesc = convertView.findViewById(R.id.tv_dept_desc);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Department dept = list.get(position);
        holder.tvName.setText(dept.getDeptName());
        holder.tvDesc.setText(dept.getDeptDesc());
        return convertView;
    }

    static class ViewHolder{
        TextView tvName;
        TextView tvDesc;
    }
}

