package com.example.hospitalregisterjava.ui.doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.hospitalregisterjava.R;
import com.example.hospitalregisterjava.model.Doctor;
import java.util.List;

public class DoctorAdapter extends BaseAdapter {
    private Context context;
    private List<Doctor> doctorList;
    public DoctorAdapter(Context context, List<Doctor> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
    }

    @Override
    public int getCount() {
        return doctorList.size();
    }

    @Override
    public Object getItem(int position) {
        return doctorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_doctor, parent, false);
            holder = new ViewHolder();
            holder.tvName = convertView.findViewById(R.id.tv_doctor_name);
            holder.tvTitle = convertView.findViewById(R.id.tv_doctor_title);
            holder.tvIntro = convertView.findViewById(R.id.tv_doctor_intro);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Doctor doctor = doctorList.get(position);
        holder.tvName.setText(doctor.getName());
        holder.tvTitle.setText(doctor.getTitle());
        holder.tvIntro.setText(doctor.getIntro());
        return convertView;
    }

    static class ViewHolder {
        TextView tvName;
        TextView tvTitle;
        TextView tvIntro;
    }
}
