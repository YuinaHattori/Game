package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Button btnRetry = findViewById(R.id.btnRetry);
        btnRetry.setOnClickListener(view ->{
            Intent i = new Intent(WinActivity.this,MainActivity.class);
            startActivity(i);
        });

    }
}