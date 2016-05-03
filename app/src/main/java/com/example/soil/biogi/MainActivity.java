package com.example.soil.biogi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.soil.biogi.healthCheck.healthCheck;
import com.example.soil.biogi.measure.measureClass;
import com.example.soil.biogi.memberSet.memberSetIn;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView txtName, txtDate;
    static Context context ;
    static DrawerLayout drawer;
    private SaveText db;
    private SessionManger session;
    public static ActionBarDrawerToggle toggle ;
    String fragmentArray[] = {"健檢","量測","聊天室","帳戶設定選項","營養建議","後續追蹤"} ;
    public static Toolbar toolbar ;
    static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        db = new SaveText(getApplicationContext());
        session = new SessionManger(getApplicationContext()); //need before if session
        context = this.getApplicationContext() ;
        //session check is login in ;
        if (!session.isLoggedIn()) {
            logout_check();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        HashMap<String, String> user = db.getUserDetails();


        toggle = new ActionBarDrawerToggle(
               this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle(true);

        Intent intent = getIntent() ;
        if(intent.getBooleanExtra("loginin",true)){
            Fragment fragment = new healthCheck();
            toolbar(fragmentArray[0]);
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.beginTransaction().replace(R.id.homepageLayout, fragment, "healthcheck")
                    .addToBackStack("healthcheck")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        };

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.inflateHeaderView(R.layout.nav_header_main);


        txtName = (TextView) header.findViewById(R.id.TextName);
        txtDate = (TextView) header.findViewById(R.id.TextDate);
        txtName.setText( user.get("username"));
        txtDate.setText( user.get("created_at"));
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.healthCheck) {
            toolbar(fragmentArray[0]);
            fragment = new healthCheck();
        } else if (id == R.id.measureClass) {

            toolbar(fragmentArray[1]);
            fragment = new measureClass();

        } else if (id == R.id.talkroom) {
            toolbar(fragmentArray[2]);
            fragment = new talkroom();
        } else if (id == R.id.memberSetIn) {
            toolbar(fragmentArray[3]);
            fragment = new memberSetIn();
        }
        else if(id==R.id.suggest){
            toolbar(fragmentArray[4]);
            fragment = new suggest();
        } else if(id==R.id.fllowup){
            toolbar(fragmentArray[5]);
            fragment = new followUp();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.homepageLayout, fragment)
                .addToBackStack("tradeCustomer")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
        assert drawer != null;
        drawer.closeDrawers();   //自動關閉選單


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void logout_check() {
        session.setLogin(false);
        db.deleteUsers();
        Intent intent = new Intent(MainActivity.this,
                LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public static void toolbar(String title){

        toolbar.setTitle(title);

    }

    public static void toggle(Boolean ind){

        drawer.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(ind);
        toggle.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp) ;

                toggle.syncState();
    }
    public static void toggleClass(){

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new measureClass();
  //              fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentManager.beginTransaction().replace(R.id.homepageLayout, fragment, "measureItem")
                        .addToBackStack("measureItem")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        });

    }
}
