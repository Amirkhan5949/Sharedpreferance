package com.example.myapplication;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.fregment.AccessibilityFragment;
import com.example.myapplication.fregment.AccountBalanceFragment;
import com.example.myapplication.fregment.AccountBoxFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView menuicon,search;
    BottomNavigationView bottomNavigationView;


    TextView textView1;
    TextView textView2;
    public TextView ticttok;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        drawerLayout=findViewById(R.id.drawer);
        frameLayout=findViewById(R.id.frame);
        navigationView=findViewById(R.id.navigation);
        menuicon=findViewById(R.id.menuicon);
        search=findViewById(R.id.search);
        ticttok=findViewById(R.id.tictok);
        bottomNavigationView=findViewById(R.id.bottom);


        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        RelativeLayout relativeLayout=(RelativeLayout)navigationView.getHeaderView(0);
        textView1=relativeLayout.findViewById(R.id.name);
        textView2=relativeLayout.findViewById(R.id.email);

        textView1.setText("Aamirkhan");
        textView2.setText("AmirDeveloper97@gmail.com");


        menuicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.accountbox:
                        replace( new AccountBoxFragment());
                        return true;

                    case R.id.accessibility:

                        replace(new AccessibilityFragment());
                        return true;

                    case R.id.accountbalance:
                        replace( new AccountBalanceFragment());
                        return true;
                    default:
                        return false;

                }
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(Gravity.LEFT);
                switch (item.getItemId()){

                    case R.id.accountbox:
                        replace( new AccountBoxFragment());
                        bottomNavigationView.getMenu().findItem(R.id.accountbox).setChecked(true);
                        return true;

                    case R.id.accessibility:
                        replace(new AccessibilityFragment());
                        bottomNavigationView.getMenu().findItem(R.id.accessibility).setChecked(true);
                        return true;

                    case R.id.accountbalance:
                        replace( new AccountBalanceFragment());
                        bottomNavigationView.getMenu().findItem(R.id.accountbalance).setChecked(true);
                        return true;
                    default:
                        return false;

                }


            }


        });



    }
    void replace(Fragment frame){
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame,frame);
        ft.commit();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }
}