package com.example.asamoahfamily.games;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BaseAct extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    protected boolean turn;
    public static final String TAG = "QQQQQ";
    protected Toolbar top;
    protected ImageView bg;
    protected TextView player;
    protected DrawerLayout drawer;

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

    public int changePlayer(int x){
        changeType(x);
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

        top.addView(player);
        player.setVisibility(View.VISIBLE);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, top, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setSupportActionBar(top);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView!=null;
        navigationView.setNavigationItemSelectedListener(this);

        assert  getSupportActionBar()!=null;
        getSupportActionBar().show();

    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent i;

        switch(id){
            case R.id.navTTT:
                i = new Intent(this,TicTacToe.class);
                startActivity(i);
                finish();
                break;
            case R.id.navNim:
                i = new Intent(this,Nim.class);
                startActivity(i);
                finish();
                break;
            default:
                i = new Intent(this,HomeScreen.class);
                startActivity(i);
                finish();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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

        return super.onOptionsItemSelected(item);
    }

}
