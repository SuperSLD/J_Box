package com.jutter.j_box;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.jutter.j_box.Classes.URLSendRequest;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Logger.getLogger("mylog").log(Level.INFO, "on create");
        URLSendRequest url = new URLSendRequest(MainActivity.SERVER_IP, 5000);

        EditText number = findViewById(R.id.number);
        EditText name = findViewById(R.id.name);
        EditText password = findViewById(R.id.pin);

        Button loginBtn = findViewById(R.id.login);
        loginBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        // Регистрация
        Button regButton = findViewById(R.id.reg);
        regButton.setOnClickListener(v -> {
            Logger.getLogger("mylog").log(Level.INFO, "send");
            String pinS = password.getText().toString();
            String nameS = name.getText().toString();
            String numberS = number.getText().toString();

            int r = Integer.parseInt(url.get("login?act=reg&pin="+pinS+"&number="+numberS+"&name="+nameS).replaceAll("\n",""));
            System.out.println(r + " result");
            Logger.getLogger("mylog").log(Level.INFO, "result " + r);
            if (r > 0) {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
