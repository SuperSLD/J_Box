package com.jutter.j_box;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jutter.j_box.Adapters.BoxesAdapter;
import com.jutter.j_box.Classes.Box;

import java.util.ArrayList;

public class PointInfoActivity extends AppCompatActivity {

    ArrayList<Box> boxes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_info);

        ListView boxesListView = findViewById(R.id.boxesListView);
        BoxesAdapter adapter = new BoxesAdapter(this, boxes);
    }
}
