package com.example.customgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {

    TextView scoreText;
    Button again;
    public int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        scoreText = findViewById(R.id.score);
        scoreText.setText(score);
        again = findViewById(R.id.button);
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAgain();
            }
        });
    }

    public void playAgain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}