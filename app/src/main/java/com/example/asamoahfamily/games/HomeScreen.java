package com.example.asamoahfamily.games;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    public void toTTT(View v){
        Intent i = new Intent(this,TicTacToe.class);
        startActivity(i);
        finish();
    }
}
