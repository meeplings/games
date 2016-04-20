package com.example.asamoahfamily.games;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;


public class BoardPiece extends ImageButton {

    private Drawable blank,complete;
    private Drawable state1,state2;
    private int xCo,yCo;
    private int type;

    public BoardPiece(Context c, Drawable blank, Drawable complete, Drawable one,Drawable two){
        super(c);
        type = 0;
        this.blank = blank;
        this.complete = complete;
        state1 = one;
        state2 = two;
    }

    public BoardPiece(Context c){
        super(c);
    }

    public void setBlank(Drawable blank) {
        this.blank = blank;
    }

    public void setComplete(Drawable complete) {
        this.complete = complete;
    }

    public void setState1(Drawable state1) {
        this.state1 = state1;
    }

    public void setState2(Drawable state2) {
        this.state2 = state2;
    }

    public Drawable getBlank() {
        return blank;
    }

    public Drawable getState1() {
        return state1;
    }

    public Drawable getState2() {
        return state2;
    }

    public int getyCo() {
        return yCo;
    }

    public void setyCo(int yCo) {
        this.yCo = yCo;
    }

    public int getxCo() {
        return xCo;
    }

    public void setxCo(int xCo) {
        this.xCo = xCo;
    }

    public Drawable getComplete() {
        return complete;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
