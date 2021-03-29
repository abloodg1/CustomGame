package com.example.customgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    private TextView timerText;
    private TextView happiText;
    private TextView hungerText;
    private TextView knowText;
    private TextView sleepText;
    private TextView dayText;
    private int happy;
    private int hunger;
    private int know;
    private int sleep;
    private int day;

    private CountDownTimer countDownTimer;
    private long timeLeft = 50 * 60 * 24 * 90;
    private long timeLapsed = 50 * 60 * 24 * 90 - timeLeft;
    private int hour = 12;
    private int minute = 0;
    private boolean am = true;
    private String AM = " AM";
    private boolean sleeping = false;
    private boolean eating = false;
    private boolean studying = false;
    private boolean playing = false;


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
        happy = 80;
        hunger = 80;
        know = 80;
        sleep = 80;
        day = 1;
        startTimer();
    }


    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeft, 50) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                updateTimer();
                if(minute < 59) {
                    minute++;
                }else{
                    minute = 0;
                    if(hour < 12){
                        if(hour  == 11){
                            if(am == true){
                                am = false;
                                AM = " PM";
                            }else{
                                am = true;
                                AM = " AM";
                                day++;
                                dayText.setText("Day "+ day);
                            }
                        }
                        hour++;
                    }else{
                        hour = 1;
                    }
                }
                if(minute < 10){
                    timerText.setText(hour + ":0" + minute + AM);
                }else{
                    timerText.setText(hour + ":" + minute + AM);
                }
                if (minute == 0){
                    hunger = updateStat(hunger, 5, 10,15, eating);
                    happy = updateStat(happy, 5, 5,15, playing);
                    know = updateStat(know, 5, 10,10, studying);
                    sleep = updateStat(sleep, 5,20,5, sleeping);
                }
                happiText.setText("Happiness: " + happy);
                hungerText.setText("Hunger: " + hunger);
                knowText.setText("Knowledge: " + know);
                sleepText.setText("Sleep: " + sleep);

            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    public void updateTimer(){

    }

    private int updateStat(int stat, int decay, int addAM, int addPM, boolean x){
        if(x){
            if(am){
                stat = stat + addAM;
            }else{
                stat = stat + addPM;
            }
            if(stat > 100){
                stat = 100;
            }
            return stat;
        }else{
            stat = stat - decay;
            if (stat < 0){
                stat = 0;
            }
            return stat;
        }
    }

    public void sleep(View view){
        sleeping = true;
        eating = false;
        studying = false;
        playing = false;
    }

    public void eat(View view){
        sleeping = false;
        eating = true;
        studying = false;
        playing = false;
    }

    public void study(View view){
        sleeping = false;
        eating = false;
        studying = true;
        playing = false;
    }

    public void relax(View view){
        sleeping = false;
        eating = false;
        studying = false;
        playing = true;
    }
};