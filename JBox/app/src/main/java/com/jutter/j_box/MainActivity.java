package com.jutter.j_box;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.jutter.j_box.Adapters.PointsAdapter;
import com.jutter.j_box.Classes.Point;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String SERVER_IP = "http://192.168.43.191:8080/";
    public ArrayList<Point> points;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView pointsListView = findViewById(R.id.pointsListView);
        PointsAdapter adapter = new PointsAdapter(this, points);
    }
}
