package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int MyHP = 100;
    int EnemyHP = 100;
    int i = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar MyHPBar = findViewById(R.id.MyHPBar);
        ProgressBar EnemyHPBar = findViewById(R.id.EnemyHPBar);
        TextView txtTurn = findViewById(R.id.txtTurn);
        TextView txtMyHP = findViewById(R.id.txtMyHP);
        TextView txtEnemyHP = findViewById(R.id.txtEnemyHP);
        TextView txtEnemyAtack = findViewById(R.id.txtEnemyAtack);
        TextView txtResult = findViewById(R.id.txtResult);
        Button btnAttack = findViewById(R.id.btnAttack);
        Button btnGuard = findViewById(R.id.btnGuard);
        Button btnHeal = findViewById(R.id.btnHeal);
        ImageView imgMe = findViewById(R.id.imgMe);

        MyHPBar.setMax(100);
        MyHPBar.setProgress(MyHP);

        EnemyHPBar.setMax(100);
        EnemyHPBar.setProgress(EnemyHP);

        txtTurn.setText("Turn　" + i);

        txtMyHP.setText(MyHP + " / 100");
        txtEnemyHP.setText(EnemyHP + " / 100");

        btnAttack.setOnClickListener(view -> {
            switch (i%5){
                case 1:
                    MyHP -= 20;
                    txtEnemyAtack.setText("ひっかく　20ダメージ");
                    break;
                case 2:
                    MyHP -= 20;
                    txtEnemyAtack.setText("ひっかく　20ダメージ");
                    break;
                case 3:
                    MyHP -= 20;
                    txtEnemyAtack.setText("ひっかく　20ダメージ");
                    break;
                case 4:
                    txtEnemyAtack.setText("相手は力を溜めている");
                    break;
                case 0:
                    MyHP -= 100;
                    txtEnemyAtack.setText("はかいこうせん　100ダメージ");
                    break;
                default:
                    break;
            }

            EnemyHP -= 10;
            EnemyHPBar.setProgress(EnemyHP);
            MyHPBar.setProgress(MyHP);
            txtMyHP.setText(MyHP + " / 100");
            txtEnemyHP.setText(EnemyHP + " / 100");
            imgMe.setImageResource(R.drawable.yuusya_game);

            if (EnemyHP<=0 && MyHP<=0){
                Intent i = new Intent(MainActivity.this, DrawActivity.class);
                startActivity(i);
            } else if (EnemyHP<=0) {
                Intent i = new Intent(MainActivity.this, WinActivity.class);
                startActivity(i);
            } else if (MyHP<=0) {
                Intent i = new Intent(MainActivity.this, LoseActivity.class);
                startActivity(i);
            } else {
                i++;
                txtTurn.setText("Turn　"+i);
            }

        });

        btnGuard.setOnClickListener(view -> {
            switch (i%5){
                case 1:
                    txtEnemyAtack.setText("ひっかくをガードした");
                    break;
                case 2:
                    txtEnemyAtack.setText("ひっかくをガードした");
                    break;
                case 3:
                    txtEnemyAtack.setText("ひっかくをガードした");
                    break;
                case 4:
                    txtEnemyAtack.setText("相手は力を溜めている");
                    break;
                case 0:
                    txtEnemyAtack.setText("はかいこうせんをガードした");
                    break;
                default:
                    break;
            }

            EnemyHPBar.setProgress(EnemyHP);
            MyHPBar.setProgress(MyHP);
            txtMyHP.setText(MyHP + " / 100");
            txtEnemyHP.setText(EnemyHP + " / 100");
            imgMe.setImageResource(R.drawable.war_shield_man);

            if (EnemyHP<=0 && MyHP<=0){
                Intent i = new Intent(MainActivity.this, DrawActivity.class);
                startActivity(i);
            } else if (EnemyHP<=0) {
                Intent i = new Intent(MainActivity.this, WinActivity.class);
                startActivity(i);
            } else if (MyHP<=0) {
                Intent i = new Intent(MainActivity.this, LoseActivity.class);
                startActivity(i);
            } else {
                i++;
                txtTurn.setText("Turn　"+i);
            }

        });

        btnHeal.setOnClickListener(view -> {
            switch (i%5){
                case 1:
                    txtEnemyAtack.setText("ひっかく　20ダメージ");
                    break;
                case 2:
                    txtEnemyAtack.setText("ひっかく　20ダメージ");
                    break;
                case 3:
                    txtEnemyAtack.setText("ひっかく　20ダメージ");
                    break;
                case 4:
                    MyHP += 20;
                    txtEnemyAtack.setText("相手は力を溜めている");
                    break;
                case 0:
                    MyHP -= 80;
                    txtEnemyAtack.setText("はかいこうせん　100ダメージ");
                    break;
                default:
                    break;
            }

            EnemyHPBar.setProgress(EnemyHP);
            MyHPBar.setProgress(MyHP);
            txtMyHP.setText(MyHP + " / 100");
            txtEnemyHP.setText(EnemyHP + " / 100");
            imgMe.setImageResource(R.drawable.mahoutsukai_man);

            if (EnemyHP<=0 && MyHP<=0){
                Intent i = new Intent(MainActivity.this, DrawActivity.class);
                startActivity(i);
            } else if (EnemyHP<=0) {
                Intent i = new Intent(MainActivity.this, WinActivity.class);
                startActivity(i);
            } else if (MyHP<=0) {
                Intent i = new Intent(MainActivity.this, LoseActivity.class);
                startActivity(i);
            } else {
                i++;
                txtTurn.setText("Turn　"+i);
            }

        });


    }

}