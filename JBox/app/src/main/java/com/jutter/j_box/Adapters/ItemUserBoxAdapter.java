package com.jutter.j_box.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jutter.j_box.Classes.Box;
import com.jutter.j_box.Fragments.ReserveBoxDialogFragment;
import com.jutter.j_box.R;

import java.util.ArrayList;

public class ItemUserBoxAdapter  extends BaseAdapter {

    private ArrayList<Box> boxes;
    private LayoutInflater inflater;
    private Context context;

    public ItemUserBoxAdapter (Context context, ArrayList<Box> boxes) {
        this.context = context;
        this.boxes = boxes;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return boxes.size();
    }

    @Override
    public Object getItem(int position) {
        return boxes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_user_info_box, parent, false);
        }


        ((TextView) view.findViewById(R.id.boxTypeInput)).setText("Box type: " + boxes.get(position).getType());
        ((TextView) view.findViewById(R.id.location)).setText("Location: " + boxes.get(position).getPlace());
        ((TextView) view.findViewById(R.id.startDate)).setText("Start date: " + boxes.get(position).getStartDate());
        ((TextView) view.findViewById(R.id.endDate)).setText("End date: " + boxes.get(position).getEndDate());

        return view;
    }
}