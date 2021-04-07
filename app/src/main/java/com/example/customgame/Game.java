package com.example.customgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    private TextView timerText;
    private TextView happiText;
    private TextView hungerText;
    private TextView knowText;
    private TextView sleepText;
    private TextView dayText;
    private TextView gradeText;
    private TextView nextText;
    private ImageView gameImage;
    private int happy;
    private int hunger;
    private int know;
    private int sleep;
    private int day;
    public int score = 0;
    private int interval = 1;
    private CountDownTimer countDownTimer;
    private long timeLeft = interval * 60 * 24 * 900;
    private long timeLapsed = interval * 60 * 24 * 900 - timeLeft;
    private int hour = 12;
    private int minute = 0;
    private boolean am = true;
    private String AM = " AM";
    private boolean sleeping = false;
    private boolean eating = false;
    private boolean studying = false;
    private boolean playing = false;
    Button buttonEnd;

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
        gradeText = findViewById(R.id.Grades);
        nextText = findViewById(R.id.NextExam);
        gameImage = findViewById(R.id.gameImg);
        buttonEnd = findViewById(R.id.endButton);
        happy = 80;
        hunger = 80;
        know = 80;
        sleep = 80;
        day = 1;
        startTimer();
        buttonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                endGame();
            }
        });

    }


    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeft, interval) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                updateTimer();
                if(minute % 30 == 0){
                    if(sleeping == true){
                        if(minute == 30){
                            gameImage.setImageResource(R.drawable.bed1);
                        }else{
                            gameImage.setImageResource(R.drawable.bed2);
                        }
                    }else if(eating == true){
                        if(minute == 30){
                            gameImage.setImageResource(R.drawable.eat1);
                        }else{
                            gameImage.setImageResource(R.drawable.eat2);
                        }
                    }else if(studying == true){
                        if(minute == 30){
                            gameImage.setImageResource(R.drawable.study1);
                        }else{
                            gameImage.setImageResource(R.drawable.study2);
                        }
                    }else if(playing == true){
                        if(minute == 30){
                            gameImage.setImageResource(R.drawable.relax1);
                        }else{
                            gameImage.setImageResource(R.drawable.relax2);
                        }
                    }
                }
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
                                if(day == 10){
                                    endGame();
                                }
                                day++;
                                dayText.setText("Day "+ day);
                            }
                        }
                        hour++;
                        if(am == false && hour==2 && minute==00 && day % 2 == 0){
                            int temp = (happy + hunger + sleep + know) / 4;
                            String s = Integer.toString(temp);

                            gradeText.setText(gradeText.getText() + s + ", ");
                            score += temp;
                            if(day == 10){
                                nextText.setText("EXAMS DONE");
                            }else {
                                s = Integer.toString(day + 2);
                                nextText.setText("Next Exam: Day " + s +" , 2:00 PM");
                            }
                            score += temp;
                        }
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
                    hunger = updateStat(hunger, 5, 20,30, eating);
                    happy = updateStat(happy, 5, 20,25, playing);
                    know = updateStat(know, 5, 20,20, studying);
                    sleep = updateStat(sleep, 5,25,15, sleeping);
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
        gameImage.setImageResource(R.drawable.bed1);
    }

    public void eat(View view){
        sleeping = false;
        eating = true;
        studying = false;
        playing = false;
        gameImage.setImageResource(R.drawable.eat1);
    }

    public void study(View view){
        sleeping = false;
        eating = false;
        studying = true;
        playing = false;
        gameImage.setImageResource(R.drawable.study1);
    }

    public void relax(View view){
        sleeping = false;
        eating = false;
        studying = false;
        playing = true;
        gameImage.setImageResource(R.drawable.relax1);
    }

    public void endGame(){
        EndScreen.score = score;
        Intent intent = new Intent(this, EndScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
};