package com.jutter.j_box;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jutter.j_box.Classes.URLSendRequest;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        URLSendRequest url = new URLSendRequest(MainActivity.SERVER_IP, 5000);

        findViewById(R.id.login).setOnClickListener(v -> {
            String number = ((EditText) findViewById(R.id.number)).getText().toString();
            String pin = ((EditText) findViewById(R.id.pin)).getText().toString();

            String result = url.get("login?act=log&number="+number+"&pin="+pin).replaceAll("\n", "");
            Logger.getLogger("mylog").log(Level.INFO, "result " + result);
            if (result.equals("ok")) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
