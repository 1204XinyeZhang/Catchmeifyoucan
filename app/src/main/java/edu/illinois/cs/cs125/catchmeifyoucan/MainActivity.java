package edu.illinois.cs.cs125.catchmeifyoucan;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    protected Button[] buttons = new Button[16];
    protected EditText scoreText;
    protected Integer score = 0;
    protected Integer counter = 0;
    protected Integer stage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons[0] = findViewById(R.id.button2);
        buttons[1] = findViewById(R.id.button3);
        buttons[2] = findViewById(R.id.button4);
        buttons[3] = findViewById(R.id.button5);
        buttons[4] = findViewById(R.id.button6);
        buttons[5] = findViewById(R.id.button7);
        buttons[6] = findViewById(R.id.button8);
        buttons[7] = findViewById(R.id.button9);
        buttons[8] = findViewById(R.id.button10);
        buttons[9] = findViewById(R.id.button11);
        buttons[10] = findViewById(R.id.button12);
        buttons[11] = findViewById(R.id.button13);
        buttons[12] = findViewById(R.id.button14);
        buttons[13] = findViewById(R.id.button15);
        buttons[14] = findViewById(R.id.button16);
        buttons[15] = findViewById(R.id.button17);

        scoreText = findViewById(R.id.scoreText);
        scoreText.setEnabled(false);
        scoreText.setText("Score: "+score.toString()+" | Stage: "+stage.toString());

        for(int i=0;i<16;i++) {
            buttons[i].setEnabled(false);
            buttons[i].setBackgroundColor(0xffffffff);
        }

    }

    protected void startGame(View view){
        view.setEnabled(false);
        if(counter>=10) {
            stage += 1;
            counter -= 10;
        }

        final Handler handler = new Handler();
        final int delay = 1000; //milliseconds

        handler.postDelayed(new Runnable(){
            public void run(){
                int random = new Random().nextInt(16);
                boolean check=false;
                for(int i=0;i<16;i++)
                    check=check | buttons[i].isEnabled();
                if(check) {
                    for(int i=0;i<16;i++) {
                        buttons[i].setEnabled(false);
                        buttons[i].setBackgroundColor(0xffffffff);
                    }
                }else{
                    buttons[random].setEnabled(true);
                    buttons[random].setBackgroundColor(0xffff0000);
                }

                handler.postDelayed(this, delay);
            }
        }, delay);
        scoreText.setText("Score: "+score.toString()+" | Stage: "+stage.toString());
    }

    protected void incrementScore(View view){
        score += 1;
        counter += 1;
        if(counter>=10) {
            Button startBtn;
            startBtn = findViewById(R.id.startBtn);
            startBtn.setEnabled(true);
            startBtn.setText("FASTER!");
        }

        view.setEnabled(false);
        view.setBackgroundColor(0xffffffff);
        scoreText.setText("Score: "+score.toString()+" | Stage: "+stage.toString());
    }



}
