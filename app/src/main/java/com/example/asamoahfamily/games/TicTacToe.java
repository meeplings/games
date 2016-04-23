package com.example.asamoahfamily.games;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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

        //True --> Player 1
        //False --> Player 2

    }

    public Drawable getImage(boolean b){
        if(b)
            return p1;
        else
            return p2;
    }

    public void tttPiece(View v){
        
        //Sets up characteristics of clicked buttons 

        TableRow mParent = (TableRow) v.getParent();

        BoardPiece mPiece = new BoardPiece(this);
        mPiece.setBackground(getImage(turn));

        mPiece.setLayoutParams(v.getLayoutParams());

        mPiece.setxCo(mParent.indexOfChild(v));
        mPiece.setClickable(false);
        mPiece.setTag(Boolean.toString(turn));
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

        if(checkTie())
            tiePopup();

        else if(checkWin(mPiece))
            gameOverPopup();

    }
    
    public boolean checkWin(View v){

        TableRow par = (TableRow) v.getParent();

        boolean hor,vert,dia;

        hor = checkHor(par);
        vert = checkVert(par.indexOfChild(v));
        dia = checkDiagonal();

        return hor || vert || dia;
    }

    public boolean checkHor(TableRow v){
        //Checks to see if the tags for each button in a row are =
        return (v.getChildAt(0).getTag().equals(v.getChildAt(1).getTag())
                && v.getChildAt(0).getTag().equals(v.getChildAt(2).getTag()));
    }

    public boolean checkVert(int index){
        //Checks to see if the tags for each button in a col are =
        return r1.getChildAt(index).getTag().equals(r2.getChildAt(index).getTag()) &&
                r1.getChildAt(index).getTag().equals(r3.getChildAt(index).getTag());
    }

    public boolean checkDiagonal() {
        //Checks to see if the 2 diagonals for TTT have equal tags for their buttons
        //Also makes sure that they aren't the starting tags
        //TODO Figure out why it says P1 wins after each round ends
        return !(r3.getChildAt(0).getTag().equals(getResources().getString(R.string.empty_box)) && r3.getChildAt(2).getTag().equals(getResources().getString(R.string.empty_box))) &&
                ((r1.getChildAt(0).getTag().equals(r2.getChildAt(1).getTag()) && r1.getChildAt(0).getTag().equals(r3.getChildAt(2).getTag())) ||
                        (r1.getChildAt(2).getTag().equals(r2.getChildAt(1).getTag()) && r1.getChildAt(2).getTag().equals(r3.getChildAt(0).getTag())));
    }

    public boolean checkTie(){
        //Checks to make sure each box is clicked
        for(int i = 0; i < r1.getChildCount(); i++){
            if(r1.getChildAt(i).isClickable() ||
                    r2.getChildAt(i).isClickable() ||
                    r3.getChildAt(i).isClickable())
                return false;
        }
        return true;
    }

}
