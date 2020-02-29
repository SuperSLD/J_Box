package com.jutter.j_box;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jutter.j_box.Adapters.PointsAdapter;
import com.jutter.j_box.Classes.Parametrs;
import com.jutter.j_box.Classes.Point;
import com.jutter.j_box.Classes.URLSendRequest;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String SERVER_IP = "http://192.168.43.191:8080/";
    public ArrayList<Point> points;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setTitle("List points");
        getMenuInflater().inflate(R.menu.point_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user_info:
                Intent intent = new Intent(this, UserInfoActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        points = new ArrayList<>();
        ListView pointsListView = findViewById(R.id.pointsListView);


        URLSendRequest url = new URLSendRequest(MainActivity.SERVER_IP, 5000);

        String data = url.get("point").replaceAll("\n", "");
        String[] objects = data.split("<!!>");
        for (int i = 0; i < objects.length; i++) {
            String[] pointString = objects[i].split("<!>");
            points.add(new Point(Integer.parseInt(pointString[0]), pointString[1], Integer.parseInt(pointString[2])));
        }

        PointsAdapter adapter = new PointsAdapter(this, points);
        pointsListView.setAdapter(adapter);
    }

    public void openPointInfo(Point point) {
        Intent intent = new Intent(this, PointInfoActivity.class);
        Parametrs.setParam("point", point);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //<><><><><><><><><><>
    }
}
