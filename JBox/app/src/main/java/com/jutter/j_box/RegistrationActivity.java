package com.jutter.j_box;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        EditText number = findViewById(R.id.number);
        EditText name = findViewById(R.id.name);
        EditText password = findViewById(R.id.password);

        Button loginBtn = findViewById(R.id.login);
        loginBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        Button regBtn = findViewById(R.id.reg);
        regBtn.setOnClickListener(v -> {
            
        });
    }
}
