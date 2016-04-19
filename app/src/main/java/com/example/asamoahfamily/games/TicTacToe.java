package com.example.asamoahfamily.games;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;

public class TicTacToe extends BaseAct {

    GridLayout mBoard;
    Drawable p1,p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tic_tac_toe);
        super.onCreate(savedInstanceState);

        screenTools();

        BoardPiece.setType(1);

        p1 = ContextCompat.getDrawable(this,R.drawable.o);
        p2 = ContextCompat.getDrawable(this,R.drawable.x);

        mBoard = (GridLayout) findViewById(R.id.ttt);
        assert mBoard!=null;
        Log.d(TAG,mBoard.getWidth()+"");

        //True --> Player 1
        //False --> Player 2

    }

    public Drawable getImage(boolean b){
        if(b)
            return p1;
        else
            return p2;
    }

    public void swapPiece(View v){

//        int width = v.getWidth();
//        int height = v.getHeight();
        int i = mBoard.indexOfChild(v);

        mBoard.removeView(v);
        v.destroyDrawingCache();

        BoardPiece mPiece = new BoardPiece(this);
        mPiece.setBackground(getImage(turn));

        mPiece.setxCo(i%3+1);
        mPiece.setyCo((i/3) +1);

        mPiece.setMaxWidth(100);
        mPiece.setMaxHeight(100);
        mBoard.addView(mPiece,i);

        turn =!turn;
        player.setText(changePlayer());
    }
    
    //TODO: Change gridlayout to tablelayout for array stuffs


//    private void checkWin(){
//        int x = 0;
//        for(int i = 0; i < mBoard.getWidth(); i++){
//            for(int j = 0; j < mBoard.getHeight(); j++){
//                if(((BoardPiece) mBoard.getChildAt(x)).getx )
//            }
//        }
//    }

}
