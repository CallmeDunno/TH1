package com.example.th1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Contact> contactList;

    public ContactAdapter(Context context, int layout, List<Contact> contactList) {
        this.context = context;
        this.layout = layout;
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);

            viewHolder.tv_name = view.findViewById(R.id.tv_name);
            viewHolder.tv_phone = view.findViewById(R.id.tv_phone);
            viewHolder.cb_status = view.findViewById(R.id.cb_status);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Contact c = contactList.get(i);

        viewHolder.tv_name.setText(c.getName());
        viewHolder.tv_phone.setText(c.getPhone());
        if (c.getStatus()){
            viewHolder.cb_status.setChecked(true);
        } else {
            viewHolder.cb_status.setChecked(false);
        }

        viewHolder.cb_status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    c.setStatus(true);
                    viewHolder.cb_status.setChecked(c.getStatus());

                } else {
                    c.setStatus(false);
                    viewHolder.cb_status.setChecked(c.getStatus());

                }
            }
        });

        return view;
    }

    private class ViewHolder{
        TextView tv_name, tv_phone;
        CheckBox cb_status;}
}
