package com.jutter.j_box.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jutter.j_box.Classes.Box;
import com.jutter.j_box.R;

import java.util.ArrayList;

public class CarAdapter extends BaseAdapter {

    String[] data;
    private LayoutInflater inflater;
    private Context context;

    public CarAdapter(Context context, String[] data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_user_info_car, parent, false);
        }

        ((TextView) view.findViewById(R.id.t1)).setText("Car code: " + data[position].split("<!>")[0]);
        ((TextView) view.findViewById(R.id.t2)).setText("Start location: " + data[position].split("<!>")[1]);
        ((TextView) view.findViewById(R.id.t3)).setText("End location: " + data[position].split("<!>")[2]);

        return view;
    }
}