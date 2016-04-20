package com.example.asamoahfamily.games;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

public class TicTacToe extends BaseAct {

    TableLayout mBoard;
    TableRow r1,r2,r3;
    Drawable p1,p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tic_tac_toe);
        super.onCreate(savedInstanceState);

        screenTools();

        p1 = ContextCompat.getDrawable(this,R.drawable.o);
        p2 = ContextCompat.getDrawable(this,R.drawable.x);

        mBoard = (TableLayout) findViewById(R.id.ttt);
        r1 = (TableRow) findViewById(R.id.tttr1);
        r2 = (TableRow) findViewById(R.id.tttr2);
        r3 = (TableRow) findViewById(R.id.tttr3);

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

        TableRow mParent = (TableRow) v.getParent();

        BoardPiece mPiece = new BoardPiece(this);
        mPiece.setBackground(getImage(turn));

        mPiece.setLayoutParams(v.getLayoutParams());

        mPiece.setxCo(mParent.indexOfChild(v));
        switch(mParent.getId()) {
            case R.id.tttr1:
                mPiece.setyCo(1);
                break;
            case R.id.tttr2:
                mPiece.setyCo(2);
                break;
            case R.id.tttr3:
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

//        if(checkHor())
//            Toast.makeText(this,"GAME OVER",Toast.LENGTH_LONG).show();
    }
    
    //TODO: Change gridlayout to tablelayout for array stuffs

    public boolean checkHor(){

        return (((BoardPiece) r1.getChildAt(0)).getType() == ((BoardPiece) r1.getChildAt(1)).getType() &&
                ((BoardPiece) r1.getChildAt(0)).getType() == ((BoardPiece) r1.getChildAt(2)).getType()) ||
                (((BoardPiece) r2.getChildAt(0)).getType() == ((BoardPiece) r2.getChildAt(1)).getType() &&
                ((BoardPiece) r2.getChildAt(0)).getType() == ((BoardPiece) r2.getChildAt(2)).getType())||
                (((BoardPiece) r3.getChildAt(0)).getType() == ((BoardPiece) r3.getChildAt(1)).getType() &&
                ((BoardPiece) r3.getChildAt(0)).getType() == ((BoardPiece) r3.getChildAt(2)).getType());
    }

}
