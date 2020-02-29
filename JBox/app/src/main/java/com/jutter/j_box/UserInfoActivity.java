package com.jutter.j_box;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jutter.j_box.Adapters.BoxesAdapter;
import com.jutter.j_box.Adapters.ItemUserBoxAdapter;
import com.jutter.j_box.Classes.Box;
import com.jutter.j_box.Classes.Parametrs;
import com.jutter.j_box.Classes.Point;
import com.jutter.j_box.Classes.URLSendRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.wasabeef.blurry.Blurry;

public class UserInfoActivity extends AppCompatActivity {

    ArrayList<Box> boxes;
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        boxes = new ArrayList<>();
        mSettings = getSharedPreferences("appSettings", Context.MODE_PRIVATE);

        ImageView imageView = findViewById(R.id.background);
        int delta = 50;

        imageView.post(() -> {
            Blurry.with(this)
                    .radius(25)
                    //.color(Color.argb(170, 101, 168, 198))
                    //.color(Color.argb(170, 50 - delta, 108 - delta, 198 - delta))
                    .from(getBitmapFromAsset(this, "icon2.png"))
                    .into(imageView);
        });

        findViewById(R.id.back).setOnClickListener(v -> {
            onBackPressed();
        });
        findViewById(R.id.backDown).setOnClickListener(v -> {
            onBackPressed();
        });

        URLSendRequest url = new URLSendRequest(MainActivity.SERVER_IP, 5000);

        try {
            String data = url.get("boxlist?act=1&user="+mSettings.getString("id", "")).replaceAll("\n", "");
            Logger.getLogger("mylog").log(Level.INFO, "data= " + data);
            String[] objects = data.split("<!!>");
            for (int i = 0; i < objects.length; i++) {
                String[] boxString = objects[i].split("<!>");
                Box box = new Box(Integer.parseInt(boxString[0]),
                        Integer.parseInt(boxString[1]),
                        boxString[3], 0,
                        boxString[5]);

                box.setStartDate(boxString[1]);
                box.setEndDate(boxString[2]);
                box.setPlace(boxString[6]);

                boxes.add(box);
            }
            ItemUserBoxAdapter adapter = new ItemUserBoxAdapter(this, boxes);

            LinearLayout newLinearLayout = new LinearLayout(getApplicationContext());
            newLinearLayout.setOrientation(LinearLayout.VERTICAL);

            newLinearLayout.removeAllViews();
            for(int i = 0 ; i < boxes.size(); i++)
                newLinearLayout.addView(adapter.getView(i, null, newLinearLayout));


            LinearLayout linearLayout = findViewById(R.id.line1);
            linearLayout.removeAllViews();
            linearLayout.addView(newLinearLayout);

            //((ListView) findViewById(R.id.information)).setAdapter(adapter);
        } catch (Exception ex) {}
        ((TextView)findViewById(R.id.name)).setText(mSettings.getString("name", ""));
        ((TextView)findViewById(R.id.date)).setText("Number: " + mSettings.getString("number", ""));

    }

    public static Bitmap getBitmapFromAsset(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();

        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            // handle exception
        }

        return bitmap;
    }
}
