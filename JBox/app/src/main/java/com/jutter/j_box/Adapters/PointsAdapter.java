package com.jutter.j_box.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jutter.j_box.Classes.Point;
import com.jutter.j_box.R;

import java.util.ArrayList;

public class PointsAdapter extends BaseAdapter {

    private ArrayList<Point> points;
    private LayoutInflater inflater;
    private Context context;
    private String[] pointList;


    public PointsAdapter(Context context, ArrayList<Point> points) {
        this.context = context;
        this.points = points;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return points.size();
    }

    @Override
    public Object getItem(int position) {
        return points.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.point_list_item, parent, false);
        }

        ((TextView) view.findViewById(R.id.pointName)).setText("Location: " + points.get(position).getName());
        ((TextView) view.findViewById(R.id.count)).setText("Count box: " + points.get(position).getCount());

        Button pointInfoBtn = view.findViewById(R.id.pointInfo);
        pointInfoBtn.setOnClickListener(v -> {

        });
        return view;
    }
}
