package com.example.asamoahfamily.games;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class BaseAct extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    protected boolean turn;
    public static final String TAG = "QQQQQ";
    protected Toolbar top;
    protected TextView player;
    protected DrawerLayout drawer;
    protected NavigationView mNav;

    protected DisplayMetrics dm;
    protected int scale;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        turn = true;
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
        //Sets up Action bar + Side Nav bar

        dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        scale = Math.round(dm.density);

        top = (Toolbar) findViewById(R.id.toolbar);
        player = new TextView(this);
        player.setText(R.string.p1);

        top.addView(player);
        player.setVisibility(View.VISIBLE);
        setSupportActionBar(top);
        assert getSupportActionBar()!=null;
        getSupportActionBar().show();


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, top, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();


        mNav = (NavigationView) findViewById(R.id.nav_view);
        assert mNav!=null;
        mNav.setNavigationItemSelectedListener(this);

    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            startActivity(getParentActivityIntent());
            finish();
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch(id){
            case R.id.navTTT:
                toTTT(item);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.navNim:
                toNim(item);
                drawer.closeDrawer(GravityCompat.START);
                break;
            default:
                toHome(item);
                drawer.closeDrawer(GravityCompat.START);
                break;
        }

        return true;
    }

    public void toTTT(MenuItem v){
        Intent i = new Intent(this,TicTacToe.class);
        startActivity(i);
        finish();
    }

    public void toNim(MenuItem v){
        Intent i = new Intent(this,Nim.class);
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

        return super.onOptionsItemSelected(item);
    }

    public void gameOverPopup(){
        //Popup dialog for winner
        String mess = getResources().getString(R.string.p2);
        if(!turn)
            mess = getResources().getString(R.string.p1);

        AlertDialog.Builder mBuild = new AlertDialog.Builder(this);
        mBuild.setMessage(getResources().getString(R.string.gameOver) + "\n"
                + mess + " wins!");
        mBuild.setNegativeButton(R.string.repeat, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recreate();
            }
        });
        mBuild.setPositiveButton(R.string.home, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getBaseContext(),HomeScreen.class);
                startActivity(i);
                finish();
            }
        });
        AlertDialog pop = mBuild.create();
        pop.show();
    }

    public void tiePopup(){
        //Popup dialog for tie game
        AlertDialog.Builder mBuild = new AlertDialog.Builder(this);
        mBuild.setMessage(R.string.tie);
        mBuild.setNegativeButton(R.string.repeat, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recreate();
            }
        });
        mBuild.setPositiveButton(R.string.home, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getBaseContext(),HomeScreen.class);
                startActivity(i);
                finish();
            }
        });
        AlertDialog pop = mBuild.create();
        pop.show();
    }

}
