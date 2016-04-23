package com.example.asamoahfamily.games;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeScreen extends AppCompatActivity{

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        list = (ListView) findViewById(R.id.homeGameList);
        ArrayAdapter<String> mItems = new ArrayAdapter<>(this,R.layout.activity_home_screen,
                R.id.listID,getResources().getStringArray(R.array.game_titles));

        list.setAdapter(mItems);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        toTTT(view);
                        break;
                    case 1:
                        toNim(view);
                        break;
                    default:
                        toHome(view);
                        break;
                }
            }
        });

    }

    public void toTTT(View v){
        Intent i = new Intent(this,TicTacToe.class);
        startActivity(i);
        finish();
    }

    public void toNim(View v){
        Intent i = new Intent(this,Nim.class);
        startActivity(i);
        finish();
    }

    public void toHome(View v){
        Intent i = new Intent(this,HomeScreen.class);
        startActivity(i);
        finish();
    }
}
