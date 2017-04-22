package com.example.duclinh1610.android_sqlite.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duclinh1610.android_sqlite.R;
import com.example.duclinh1610.android_sqlite.activity.Student;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {
    ArrayList<Student> listData;
    LayoutInflater inflater;
    Context context;

    // Ham tao custom
    public StudentAdapter(Context context, ArrayList<Student> listData) {
        this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.listData = listData;
        this.context = context;
    }

    // Ham tra ve so luong phan tu hien thi trong listview
    @Override
    public int getCount() {
        return listData.size();
    }

    // Ham tra ve doi tuong duoc lay theo vi tri
    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
            holder = new ViewHolder();
            holder.tv_id = (TextView) convertView.findViewById(R.id.tv_item_id);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_item_name);
            holder.tv_address = (TextView) convertView.findViewById(R.id.tv_item_address);
            holder.tv_phone_number = (TextView) convertView.findViewById(R.id.tv_item_phone_number);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Student contact = listData.get(position);
        holder.tv_id.setText(contact.getId() + "");
        holder.tv_name.setText(contact.getName());
        holder.tv_address.setText(contact.getAddress());
        holder.tv_phone_number.setText(contact.getPhone_number());

        return convertView;
    }

    static class ViewHolder {
        TextView tv_id;
        TextView tv_name;
        TextView tv_address;
        TextView tv_phone_number;
    }
}
