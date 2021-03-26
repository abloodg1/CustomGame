package com.example.customgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    private TextView timerText;
    private TextView happiText;
    private TextView hungerText;
    private TextView knowText;
    private TextView sleepText;
    private TextView dayText;

    private CountDownTimer countDownTimer;
    private long timeLeft = 50 * 60 * 24 * 90;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        timerText = findViewById(R.id.TimeCounter);
        happiText = findViewById(R.id.Happiness);
        hungerText = findViewById(R.id.Hunger);
        knowText = findViewById(R.id.Knowledge);
        sleepText = findViewById(R.id.Sleep);
        dayText = findViewById(R.id.Day);

        startTimer();
    }


    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeft, 50) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                updateTimer();

            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    public void updateTimer(){

    }
};