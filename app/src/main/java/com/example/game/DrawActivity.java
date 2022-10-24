package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class DrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        Button btnRetry = findViewById(R.id.btnRetry);
        btnRetry.setOnClickListener(view ->{
            Intent i = new Intent(DrawActivity.this,MainActivity.class);
            startActivity(i);
        });
    }
}