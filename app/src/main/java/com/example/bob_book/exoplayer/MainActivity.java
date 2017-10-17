package com.example.bob_book.exoplayer;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    Button button;

    String radioURL = "http://relay4.181.fm:8128";
    String radioURL2="http://http-live.sr.se/p3-mp3-192";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);



    }
    public void onclick(View v){
        startService(new Intent(this, RadioService.class));
        //startPlayerService();

    }

    public void onclickStop(View v){
        stopService(new Intent(this, RadioService.class));
        System.out.println("Stop");
    }

    public void onclickOne(View v){
        startService(new Intent(this, RadioService.class).putExtra("radioURL2",radioURL));
    }

    public void onclickTwo(View v){
        startService(new Intent(this, RadioService.class).putExtra("radioURL2",radioURL2));
    }

    public void startPlayerService() {
        Intent serviceIntent = new Intent(MainActivity.this, RadioService.class);

        startService(serviceIntent);
        Log.v(TAG, "startSerivce");
    }



}

