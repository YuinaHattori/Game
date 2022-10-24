package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LoseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);

        Button btnRetry = findViewById(R.id.btnRetry);
        btnRetry.setOnClickListener(view ->{
            Intent i = new Intent(LoseActivity.this,MainActivity.class);
            startActivity(i);
        });
    }
}