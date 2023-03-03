package com.example.th1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
            viewHolder.img_call = view.findViewById(R.id.img_call);
            viewHolder.img_send = view.findViewById(R.id.img_sms);
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

        viewHolder.img_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCall = new Intent();
                intentCall.setAction(Intent.ACTION_DIAL);
                intentCall.setData(Uri.parse("tel:" + c.getPhone()));
                context.startActivity(intentCall);
            }
        });

        viewHolder.img_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSend = new Intent();
                intentSend.setAction(Intent.ACTION_SENDTO);
                intentSend.setData(Uri.parse("sms:" + c.getPhone()));
                context.startActivity(intentSend);
            }
        });

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

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strName = contactList.get(i).getLastName();
                int count = 0;
                for (Contact c : contactList){
                    if (c.getLastName().equals(strName)){
                        count++;
                    }
                }
                Toast.makeText(context, "Số người có cùng tên " + strName + ": " + count , Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private class ViewHolder{
        TextView tv_name, tv_phone;
        CheckBox cb_status;
        ImageView img_call, img_send;
    }
}
