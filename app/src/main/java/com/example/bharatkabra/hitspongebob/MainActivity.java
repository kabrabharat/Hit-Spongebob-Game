package com.example.bharatkabra.hitspongebob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

   private int SleepTimer=3;
   private ImageView iv_sponge;   // imageview for sponge bob
   //private ImageView iv_petrik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Full screen window
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        LogoLauncher logolauncher = new LogoLauncher();
        logolauncher.start();
    }
        private class LogoLauncher extends Thread
        {
            public void run()
            {
                try
                {
                    sleep(1000*SleepTimer);     // for 3 seconds of splash screen

                } catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }

        }
    }





