package com.example.asamoahfamily.games;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BaseAct extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    protected boolean turn;
    public static final String TAG = "QQQQQ";
    protected Toolbar top;
    protected ImageView bg;
    protected ImageButton oMenu;
    protected TextView player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        turn = true;
    }

    protected Drawable changeTurn(Drawable a, Drawable b){
        Drawable newImage;
        if(turn)
            newImage = a;
        else
            newImage = b;
        turn = !turn;
        return newImage;
    }

    public Drawable getImage(boolean b){

        return ContextCompat.getDrawable(this,R.drawable.box);
    }

    public int changePlayer(){
        if(turn)
            return R.string.p1;
        else
            return R.string.p2;
    }

    public int changeType(int x){
        if(x == 1)
            x = 2;
        else if(x == 2)
            x = 1;
        return x;
    }

    protected void screenTools(){
        bg = (ImageView) findViewById(R.id.bg);

        top = (Toolbar) findViewById(R.id.toolbar);
        player = new TextView(this);
        player.setText(R.string.p1);
        oMenu = new ImageButton(this);
        oMenu.setBackground(ContextCompat.getDrawable(this,R.drawable.list_ingredients));
        oMenu.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                getSupportActionBar().openOptionsMenu();
            }
        });

        top.addView(player);
        player.setVisibility(View.VISIBLE);
        top.setOverflowIcon(ContextCompat.getDrawable(this,R.drawable.list_ingredients));
        setSupportActionBar(top);


        assert  getSupportActionBar()!=null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().show();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id){
            case R.id.menuTTT:
                toTTT(item);
                break;
            case R.id.menuNight:
                bg.setBackground(ContextCompat.getDrawable(this,R.drawable.night_bng));
                Toast.makeText(this,R.string.night,Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuSea:
                bg.setBackground(ContextCompat.getDrawable(this,R.drawable.sea_bng));
                Toast.makeText(this,R.string.sea,Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuVS:
                bg.setBackground(ContextCompat.getDrawable(this,R.drawable.vs_bng));
                Toast.makeText(this,R.string.vs,Toast.LENGTH_SHORT).show();
                break;
            default:
                toHome(item);
                break;
        }

        return false;
    }

    public void toTTT(MenuItem v){
        Intent i = new Intent(this,TicTacToe.class);
        startActivity(i);
        finish();
    }

    public void toHome(MenuItem v){
        Intent i = new Intent(this,HomeScreen.class);
        startActivity(i);
        finish();
    }
    
    public void checkWin(boolean b){
        return false;
    }
}
