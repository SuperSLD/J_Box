package com.jutter.j_box;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jutter.j_box.Classes.URLSendRequest;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences mSettings;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSettings = getSharedPreferences("appSettings", Context.MODE_PRIVATE);

        URLSendRequest url = new URLSendRequest(MainActivity.SERVER_IP, 5000);

        if (mSettings.getString("pin", "").length() > 3) {
            ((EditText) findViewById(R.id.number)).setText(mSettings.getString("number", ""));
            ((EditText) findViewById(R.id.pin))   .setText(mSettings.getString("pin", ""));
        }

        findViewById(R.id.login).setOnClickListener(v -> {
            String number = ((EditText) findViewById(R.id.number)).getText().toString();
            String pin = ((EditText) findViewById(R.id.pin)).getText().toString();

            String result = url.get("login?act=log&number="+number+"&pin="+pin).replaceAll("\n", "");
            Logger.getLogger("mylog").log(Level.INFO, "result " + result);
            if (result.split("<!>")[0].equals("ok")) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString("number", number);
                editor.putString("pin", pin);
                editor.putString("name", result.split("<!>")[1]);
                editor.apply();
            }
        });
    }
}
