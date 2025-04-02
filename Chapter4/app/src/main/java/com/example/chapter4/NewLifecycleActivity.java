package com.example.chapter4;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewLifecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_lifecycle);
        Log.d("TWO_ON_CREATE","Activity is created");

    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("TWO_ON_START","Activity is start");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("TWO_ON_RESUME","Activity is resumed");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("TWO_ON_PAUSE","Activity is paussed");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("TWO_ON_STOP","Activity is stopped");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("TWO_ON_DESTROY","Activity is destoyed");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("TWO_ON_RESTART","Activity is restarted");
    }
}