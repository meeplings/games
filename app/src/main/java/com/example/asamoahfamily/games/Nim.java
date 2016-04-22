package com.example.asamoahfamily.games;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TableRow;

public class Nim extends BaseAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nim);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void nimPiece(View v){

        TableRow mParent = (TableRow) v.getParent();

        BoardPiece mPiece = new BoardPiece(this);
        mPiece.setBackground(ContextCompat.getDrawable(this,R.drawable.x));

        mPiece.setLayoutParams(v.getLayoutParams());

        mPiece.setxCo(mParent.indexOfChild(v));
        switch(mParent.getId()) {
            case R.id.nimr1:
                mPiece.setyCo(1);
                break;
            case R.id.nimr2:
                mPiece.setyCo(2);
                break;
            case R.id.nimr3:
                mPiece.setyCo(3);
                break;
            default:
                throw new IndexOutOfBoundsException("CHILD NOT IN PARENT");
        }

        mParent.removeView(v);
        v.destroyDrawingCache();

        mParent.addView(mPiece,mPiece.getxCo());

        turn =!turn;
        player.setText(changePlayer(mPiece.getType()));

    }

}
