package com.example.bob_book.exoplayer;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    Button button;
    MyTask myTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        startPlayerService();


    }
    public void onclick(View v){
        myTask=new MyTask();
        myTask.execute();
    }

    public void startPlayerService() {
        Intent serviceIntent = new Intent(MainActivity.this, RadioService.class);

        startService(serviceIntent);
        Log.v(TAG, "startSerivce");
    }


    class MyTask extends AsyncTask<Void, Void, Void> {
        String radioURL = "http://relay4.181.fm:8128";

        @Override
        protected Void doInBackground(Void... params) {

            Player.start(radioURL, this);
            return null;
        }
    }
}

