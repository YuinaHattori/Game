package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.game.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private ActivityMainBinding binding;
    int MyHP = 100;
    int EnemyHP = 100;
    int LB = 0;
    int i = 1;
    int j;
    int Healcnt = 5;
    Random rnd;
    int[] damage = {10, 15, 20,0,5};
    String[] txtDamage = {"☜　10ダメージ", "☜　15ダメージ", "☜　20ダメージ","相手は力を溜めている", "☜　5ダメージ"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.MyHPBar.setMax(100);
        binding.MyHPBar.setProgress(MyHP);

        binding.EnemyHPBar.setMax(100);
        binding.EnemyHPBar.setProgress(EnemyHP);

        binding.LimitGauge.setMax(10);
        binding.LimitGauge.setProgress(LB);

        binding.txtTurn.setText("Turn　" + i);

        binding.txtMyHP.setText(MyHP + " / 100");
        binding.txtEnemyHP.setText(EnemyHP + " / 100");

        binding.txtHealcnt.setText(Healcnt+ " / 5");

        Attack();
        Guard();
        Heal();
        LB();
    }

    private void EnemyAttack(){
        if (j==3){
            MyHP -= 80;
            binding.txtEnemyAttack.setText("☜　80ダメージ");
            j = 0;
            binding.imgEnemy.setImageResource(R.drawable.fantasy_game_character_slime);
        } else {
            rnd = new Random();
            j = rnd.nextInt(5);
            MyHP -= damage[j];
            binding.txtEnemyAttack.setText(txtDamage[j]);
            if (j==3){
                binding.imgEnemy.setImageResource(R.drawable.red_slime);
            }
        }
    }

    private void LimitGauge(){
        if (LB < 9) {
            LB ++;
            binding.LimitGauge.setProgress(LB);
        } else if (LB == 9){
            LB++;
            binding.LimitGauge.setProgress(LB);
            binding.LimitBreak.setBackgroundColor(Color.YELLOW);
        }
    }

    private void MyResult(){
        binding.EnemyHPBar.setProgress(EnemyHP);
        binding.MyHPBar.setProgress(MyHP);
        binding.txtMyHP.setText(MyHP + " / 100");
        binding.txtEnemyHP.setText(EnemyHP + " / 100");
        binding.txtHealcnt.setText(Healcnt+ " / 5");
        if(EnemyHP<=0) {
            Intent i = new Intent(MainActivity.this, WinActivity.class);
            startActivity(i);
        } else {
            i++;
            binding.txtTurn.setText("Turn　"+i);
        }
    }
    private void EnemyResult(){
        binding.EnemyHPBar.setProgress(EnemyHP);
        binding.MyHPBar.setProgress(MyHP);
        binding.txtMyHP.setText(MyHP + " / 100");
        binding.txtEnemyHP.setText(EnemyHP + " / 100");
        binding.txtHealcnt.setText(Healcnt+ " / 5");
        if (MyHP<=0) {
            Intent i = new Intent(MainActivity.this, LoseActivity.class);
            startActivity(i);
        } else {
            i++;
            binding.txtTurn.setText("Turn　"+i);
        }
    }

    private View.OnClickListener onClick_atkButton = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            double d = Math.random();
            if (d < 0.05){
                EnemyHP -= 20;
                binding.txtMyAttack.setText("Critical　20ダメージ　☞");
            } else {
                EnemyHP -= 10;
                binding.txtMyAttack.setText("10ダメージ　☞");
            }
            binding.imgMe.setImageResource(R.drawable.yuusya_game);
            binding.txtEnemyAttack.setText("");
            LimitGauge();
            MyResult();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (EnemyHP > 0){
                        binding.txtMyAttack.setText("");
                        EnemyAttack();
                        EnemyResult();
                    }
                }
            }, 1000);
        }
    };

    private View.OnClickListener onClick_guardButton = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            binding.txtMyAttack.setText("ガード！");
            binding.imgMe.setImageResource(R.drawable.war_shield_man);
            binding.txtEnemyAttack.setText("");
            LimitGauge();
            MyResult();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (EnemyHP > 0){
                        binding.txtMyAttack.setText("");
                        int HP = MyHP;
                        EnemyAttack();
                        if (HP-MyHP <50){
                            MyHP = HP;
                            binding.txtEnemyAttack.setText("攻撃がガードされた");
                        } else {
                            MyHP = MyHP + 50;
                            binding.txtEnemyAttack.setText("☜　30ダメージ");
                        }
                        EnemyResult();
                    }
                }
            }, 1000);
        }
    };

    private View.OnClickListener onClick_healButton = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if (Healcnt > 0) {
                if (MyHP >= 80) {
                    MyHP = 100;
                } else {
                    MyHP += 20;
                }
                binding.txtMyAttack.setText("20ダメージ回復");
                binding.txtEnemyAttack.setText("");
                binding.imgMe.setImageResource(R.drawable.mahoutsukai_man);
                Healcnt--;
                if (Healcnt == 0) {
                    binding.btnHeal.setBackgroundColor(Color.GRAY);
                }
                LimitGauge();
                MyResult();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (EnemyHP > 0){
                            binding.txtMyAttack.setText("");
                            EnemyAttack();
                            EnemyResult();
                        }
                    }
                }, 1000);
            }
        }
    };

    private View.OnClickListener onClick_LBButton = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if (LB == 10) {
                EnemyHP -= 30;
                binding.txtMyAttack.setText("LimitBreak　30ダメージ　☞");
                binding.imgLimitBreak.setImageResource(R.drawable.limitbreak);
                binding.imgMe.setImageResource(R.drawable.yuusya_game);
                binding.txtEnemyAttack.setText("");
                LB = 0;
                binding.LimitGauge.setProgress(LB);
                binding.LimitBreak.setBackgroundColor(Color.GRAY);
                MyResult();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (EnemyHP > 0) {
                            binding.imgLimitBreak.setImageDrawable(null);
                            binding.txtMyAttack.setText("");
                            EnemyAttack();
                            EnemyResult();
                        }
                    }
                }, 1000);
            }
        }
    };

    private void Attack(){
        binding.btnAttack.setOnClickListener(onClick_atkButton);
    }
    private  void Guard(){
        binding.btnGuard.setOnClickListener(onClick_guardButton);
    }
    private  void Heal(){
        binding.btnHeal.setOnClickListener(onClick_healButton);
    }
    private  void LB(){
        binding.LimitBreak.setOnClickListener(onClick_LBButton);
    }

}