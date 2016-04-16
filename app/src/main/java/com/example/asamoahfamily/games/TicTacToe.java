package com.example.asamoahfamily.games;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class TicTacToe extends AppCompatActivity {

    BoardPiece[][] ttt;
    boolean turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //True --> Player 1
        //False --> Player 2
        turn = true;

        ttt = new BoardPiece[3][3];
        for(int i = 0; i < ttt.length; i++){
            for(int j = 0; j < ttt[i].length; j++){
                ttt[i][j] = new BoardPiece(this);
                ttt[i][j].setxCo(i);
                ttt[i][j].setyCo(j);
            }
        }
    }

    public void clickPiece(View v){
        turn =!turn;
        v.setBackgroundResource(getImage(turn));
    }

    public int getImage(boolean b){
        if(b)
            return R.drawable.o;
        else
            return R.drawable.x;
    }

}
