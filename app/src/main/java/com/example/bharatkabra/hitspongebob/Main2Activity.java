package com.example.bharatkabra.hitspongebob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    View view;
    // image view for white/black block images in the game
    ImageView iv_11,iv_12,iv_13,
            iv_21,iv_22,iv_23,
            iv_31,iv_32,iv_33,
            iv_41,iv_42,iv_43,
            iv_51,iv_52,iv_53;

    Button b_play;

    TextView tv_time,tv_score,tv_best;

    Random r;

    int rock1,rock2,rock3,rock4,rock5;

    int frameImage,pawInFrameImage,emptyImage,tapImage;

    int currentScore=0;
    int bestScore;

    CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        view=this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.darkgrey);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        bestScore = preferences.getInt("highscore", 0);

        setupUIView();

        tv_score.setText("SCORE: " + currentScore);
        tv_best.setText("BEST: " + bestScore);
        tv_time.setText("TIME: " + millisToTime(15000));

        r = new Random();

        loadImages();

        timer = new CountDownTimer(15000, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                long miUF=millisUntilFinished+1;
                tv_time.setText("TIME: " + millisToTime(miUF));
            }

            @Override
            public void onFinish() {
                tv_time.setText("TIME: " + millisToTime(0));

                iv_31.setEnabled(false);
                iv_32.setEnabled(false);
                iv_33.setEnabled(false);

                b_play.setVisibility(View.VISIBLE);

                iv_11.setImageResource(emptyImage);
                iv_12.setImageResource(emptyImage);
                iv_13.setImageResource(emptyImage);

                iv_21.setImageResource(emptyImage);
                iv_22.setImageResource(emptyImage);
                iv_23.setImageResource(emptyImage);

                iv_31.setImageResource(emptyImage);
                iv_32.setImageResource(emptyImage);
                iv_33.setImageResource(emptyImage);

                iv_41.setImageResource(emptyImage);
                iv_42.setImageResource(emptyImage);
                iv_43.setImageResource(emptyImage);

                iv_51.setImageResource(emptyImage);
                iv_52.setImageResource(emptyImage);
                iv_53.setImageResource(emptyImage);

                Toast.makeText(Main2Activity.this, "Game Over!", Toast.LENGTH_SHORT).show();

                if (currentScore > bestScore) {
                    bestScore = currentScore;
                    tv_best.setText("BEST: " + bestScore);

                    SharedPreferences preferences1 = getSharedPreferences("PREFS", 0);

                    SharedPreferences.Editor editor = preferences1.edit();

                    editor.putInt("highscore", bestScore);
                    editor.apply();

                }
            }
        };

        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rock3 == 1) {
                    continueGame();
                } else
                    endGame();
            }
        });


        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rock3 == 2) {
                    continueGame();
                } else
                    endGame();
            }
        });


        iv_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rock3 == 3) {
                    continueGame();
                } else
                    endGame();
            }
        });

        b_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGame();
            }
        });
    }
    private void continueGame()
    {
        //Row5
        rock5=rock4;
        setRockLocation(rock5,5);

        //Row4
        rock4=rock3;
        setRockLocation(rock4,4);

        //Row3
        rock3=rock2;
        setRockLocation(rock3,3);

        //Row2
        rock2=rock1;
        setRockLocation(rock2,2);

        //Row5
        rock1=r.nextInt(3) + 1 ;
        setRockLocation(rock1,1);

        currentScore++;

        tv_score.setText("SCORE: " + currentScore);


    }

    private void initGame()
    {
        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        b_play.setVisibility(View.INVISIBLE);

        currentScore = 0 ;
        tv_score.setText("SCORE: " + currentScore);

        timer.start();

        //row5 = nothing

        //row4
        rock4=2;
        iv_42.setImageResource(pawInFrameImage);


        //row3
        rock3=2;
        iv_32.setImageResource(tapImage);


        //row2
        rock2 = r.nextInt(3) + 1 ;
        setRockLocation(rock2 , 2 );

        //row1
        rock1 = r.nextInt(3) + 1 ;
        setRockLocation(rock1 , 1 );


    }

    public void endGame()
    {
        timer.cancel();

        iv_31.setEnabled(false);
        iv_32.setEnabled(false);
        iv_33.setEnabled(false);

        b_play.setVisibility(View.VISIBLE);

        iv_11.setImageResource(emptyImage);
        iv_12.setImageResource(emptyImage);
        iv_13.setImageResource(emptyImage);

        iv_21.setImageResource(emptyImage);
        iv_22.setImageResource(emptyImage);
        iv_23.setImageResource(emptyImage);

        iv_31.setImageResource(emptyImage);
        iv_32.setImageResource(emptyImage);
        iv_33.setImageResource(emptyImage);

        iv_41.setImageResource(emptyImage);
        iv_42.setImageResource(emptyImage);
        iv_43.setImageResource(emptyImage);

        iv_51.setImageResource(emptyImage);
        iv_52.setImageResource(emptyImage);
        iv_53.setImageResource(emptyImage);

        Toast.makeText(Main2Activity.this,"Failed!",Toast.LENGTH_SHORT).show();

    }



    private void setRockLocation(int place,int row)
    {
        if(row==1) {
            iv_11.setImageResource(emptyImage);
            iv_12.setImageResource(emptyImage);
            iv_13.setImageResource(emptyImage);

            switch (place) {
                case 1:
                    iv_11.setImageResource(frameImage);
                    break;
                case 2:
                    iv_12.setImageResource(frameImage);
                    break;
                case 3:
                    iv_13.setImageResource(frameImage);
                    break;

            }
        }

        if(row==2)
        {
            iv_21.setImageResource(emptyImage);
            iv_22.setImageResource(emptyImage);
            iv_23.setImageResource(emptyImage);

            switch(place)
            {
                case 1:
                    iv_21.setImageResource(frameImage);
                    break;
                case 2:
                    iv_22.setImageResource(frameImage);
                    break;
                case 3:
                    iv_23.setImageResource(frameImage);
                    break;
            }
        }


        if(row==3) {
            iv_31.setImageResource(emptyImage);
            iv_32.setImageResource(emptyImage);
            iv_33.setImageResource(emptyImage);

            switch (place) {
                case 1:
                    iv_31.setImageResource(tapImage);
                    break;
                case 2:
                    iv_32.setImageResource(tapImage);
                    break;
                case 3:
                    iv_33.setImageResource(tapImage);
                    break;
            }
        }

        if(row==4) {
            iv_41.setImageResource(emptyImage);
            iv_42.setImageResource(emptyImage);
            iv_43.setImageResource(emptyImage);

            switch (place) {
                case 1:
                    iv_41.setImageResource(pawInFrameImage);
                    break;
                case 2:
                    iv_42.setImageResource(pawInFrameImage);
                    break;
                case 3:
                    iv_43.setImageResource(pawInFrameImage);
                    break;
            }
        }

        if(row==5) {
            iv_51.setImageResource(emptyImage);
            iv_52.setImageResource(emptyImage);
            iv_53.setImageResource(emptyImage);


            switch (place) {
                case 1:
                    iv_51.setImageResource(frameImage);
                    break;
                case 2:
                    iv_52.setImageResource(frameImage);
                    break;
                case 3:
                    iv_53.setImageResource(frameImage);
                    break;
            }
        }
    }

    private int millisToTime(long millis)
    {
        return (int)millis/1000;

    }

    private void loadImages()
    {
        frameImage=R.drawable.boundaryone;
        pawInFrameImage=R.drawable.blacktile;
        tapImage = R.drawable.toonface;
        emptyImage=R.drawable.whitetile;


    }

    private void setupUIView()
    {
        iv_11=(ImageView)findViewById(R.id.iv_11);
        iv_12=(ImageView)findViewById(R.id.iv_12);
        iv_13=(ImageView)findViewById(R.id.iv_13);

        iv_21=(ImageView)findViewById(R.id.iv_21);
        iv_22=(ImageView)findViewById(R.id.iv_22);
        iv_23=(ImageView)findViewById(R.id.iv_23);

        iv_31=(ImageView)findViewById(R.id.iv_31);
        iv_32=(ImageView)findViewById(R.id.iv_32);
        iv_33=(ImageView)findViewById(R.id.iv_33);

        iv_41=(ImageView)findViewById(R.id.iv_41);
        iv_42=(ImageView)findViewById(R.id.iv_42);
        iv_43=(ImageView)findViewById(R.id.iv_43);

        iv_51=(ImageView)findViewById(R.id.iv_51);
        iv_52=(ImageView)findViewById(R.id.iv_52);
        iv_53=(ImageView)findViewById(R.id.iv_53);

        b_play=(Button)findViewById(R.id.b_play);

        tv_best=(TextView)findViewById(R.id.tv_best);

        tv_score=(TextView)findViewById(R.id.tv_score);

        tv_time=(TextView)findViewById(R.id.tv_time);

    }
}
