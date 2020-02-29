package com.jutter.j_box;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jutter.j_box.Adapters.BoxesAdapter;
import com.jutter.j_box.Classes.Box;
import com.jutter.j_box.Classes.Parametrs;
import com.jutter.j_box.Classes.Point;
import com.jutter.j_box.Classes.URLSendRequest;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PointInfoActivity extends AppCompatActivity {

    ArrayList<Box> boxes;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setTitle("Point information");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_info);
        boxes = new ArrayList<>();

        findViewById(R.id.button).setOnClickListener(v -> {
            onBackPressed();
        });

        ((TextView) findViewById(R.id.pointName)).setText(((Point)Parametrs.getParam("point")).getName());
        ((TextView) findViewById(R.id.count)).setText(Integer.toString(((Point)Parametrs.getParam("point")).getCount()));

        URLSendRequest url = new URLSendRequest(MainActivity.SERVER_IP, 5000);

        try {
            String data = url.get("boxlist?act=0&box=" + ((Point) Parametrs.getParam("point")).getId()).replaceAll("\n", "");
            Logger.getLogger("mylog").log(Level.INFO, "data= " + data);
            String[] objects = data.split("<!!>");
            for (int i = 0; i < objects.length; i++) {
                String[] boxString = objects[i].split("<!>");
                boxes.add(new Box(Integer.parseInt(boxString[0]),
                        Integer.parseInt(boxString[1]),
                        boxString[3], 0,
                        boxString[5]));
            }

            ListView boxesListView = findViewById(R.id.boxesListView);
            BoxesAdapter adapter = new BoxesAdapter(this, boxes);
            boxesListView.setAdapter(adapter);
        } catch (Exception ex) {}
    }
}
