package com.jutter.j_box.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class BoxesAdapter extends BaseAdapter {

    private ArrayList<Box> boxes;
    private LayoutInflater inflater;
    private Context context;

    public BoxesAdapter (Context context, ArrayList<Box> boxes) {
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
            view = inflater.inflate(R.layout.box_list_item, parent, false);
        }


        ((TextView) view.findViewById(R.id.boxTypeInput)).setText("Box type: " + boxes.get(position).getType());

        Button reserveBox = view.findViewById(R.id.reserve);
        reserveBox.setOnClickListener(v -> {
            ReserveBoxDialogFragment dialog = new ReserveBoxDialogFragment(context, boxes.get(position));
            dialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "tag");
        });
        return view;
    }
}
