package com.example.asamoahfamily.games;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;

public class Nim extends BaseAct {

    TableRow[] r = new TableRow[3];
    protected int side,off;
    TableRow.LayoutParams rowlp,butlp;

    private static final int SQUARE_BASE = 50;
    private static final int RL_MARGIN = 1;
    private static final int TB_MARGIN = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_nim);
        super.onCreate(savedInstanceState);
        screenTools();

        side = scale*SQUARE_BASE;
        off = scale*RL_MARGIN;
        butlp = new TableRow.LayoutParams(side,side);
        butlp.gravity = Gravity.CENTER;
        butlp.leftMargin = RL_MARGIN*scale;
        butlp.rightMargin = RL_MARGIN*scale;

        rowlp = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rowlp.gravity = Gravity.CENTER;
        rowlp.topMargin= TB_MARGIN*scale;
        rowlp.bottomMargin = TB_MARGIN*scale;

        r[0] = (TableRow) findViewById(R.id.nimr1);
        r[1] = (TableRow) findViewById(R.id.nimr2);
        r[2] = (TableRow) findViewById(R.id.nimr3);

        r[0].setLayoutParams(rowlp);
        for(int i = 0; i < 3; i++){
            r[0].addView(fillRow(i,0),i);
        }
        r[1].setLayoutParams(rowlp);
        for(int i = 0; i < 5; i++){
            r[1].addView(fillRow(i,1),i);
        }
        r[2].setLayoutParams(rowlp);
        for(int i = 0; i < 7; i++){
            r[2].addView(fillRow(i,2),i);
        }

    }

    private void lockRow(TableRow r){
        for(int i = 0; i < r.getChildCount(); i++){
            if(r.getChildAt(i).isClickable())
                ((BoardPiece)r.getChildAt(i)).setImageDrawable(((BoardPiece)r.getChildAt(i)).getState1());
            r.getChildAt(i).setEnabled(false);
        }
    }

    public void changeTurn(View v){
        turn =!turn;
        unlockRows();
        if(!turn)
            player.setText(getResources().getString(R.string.p2));
        else
            player.setText(getResources().getString(R.string.p1));
    }

    private void unlockRows(){
        for (TableRow aR : r) {
            for (int j = 0; j < aR.getChildCount(); j++) {
                if(aR.getChildAt(j).isClickable()) {
                    aR.getChildAt(j).setEnabled(true);
                    ((BoardPiece) aR.getChildAt(j)).setImageDrawable(((BoardPiece) aR.getChildAt(j)).getBlank());
                }
            }
        }
    }

    private BoardPiece fillRow(int x, int y){
        BoardPiece i = new BoardPiece(this);
        i.setBlank(ContextCompat.getDrawable(this,R.drawable.o));
        i.setImageDrawable(i.getBlank());
        i.setLayoutParams(butlp);
        i.setState1(ContextCompat.getDrawable(this,R.drawable.dash));
        i.setState2(ContextCompat.getDrawable(this,R.drawable.x));

        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BoardPiece)v).setBlank(ContextCompat.getDrawable(Nim.this,R.drawable.x));
                ((BoardPiece)v).setImageDrawable(((BoardPiece)v).getBlank());
                v.setClickable(false);
                v.setEnabled(false);
                TableRow mParent = (TableRow) v.getParent();
                switch(mParent.getId()) {
                    case R.id.nimr1:
                        lockRow(r[1]);
                        lockRow(r[2]);
                        break;
                    case R.id.nimr2:
                        lockRow(r[0]);
                        lockRow(r[2]);
                        break;
                    case R.id.nimr3:
                        lockRow(r[0]);
                        lockRow(r[1]);
                        break;
                    default:
                        throw new IndexOutOfBoundsException("CHILD NOT IN PARENT");
                }
                if(checkWin())
                    gameOverPopup();
            }
        });

        i.setCropToPadding(true);
        i.setContentDescription(getResources().getString(R.string.empty_box));
        i.setScaleType(ImageView.ScaleType.FIT_XY);
        i.setPadding(off,off,off,off);
        i.setAdjustViewBounds(true);
        i.setxCo(x);
        i.setyCo(y);

        return i;
    }

    private boolean checkWin(){
        for(TableRow aRow: r){
            for(int i =0; i < aRow.getChildCount(); i++){
                if(aRow.getChildAt(i).isClickable())
                    return false;
            }
        }
        return true;
    }

}
