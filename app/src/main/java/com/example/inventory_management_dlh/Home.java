package com.example.inventory_management_dlh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.grid.CustomAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView view;

    Fragment fragment;
    FragmentTransaction transaction;

    GridView gridView;
    Context context;
    ArrayList prgName;

//    public static String [] NameList = {"Add","Report","Admin","Products","Help"};
//    public static int [] ImageList = {R.drawable.ic_baseline_create_24,
//                                      R.drawable.ic_baseline_description_24,
//                                      R.drawable.ic_baseline_assignment_ind_24,
//                                      R.drawable.ic_baseline_assignment_24,
//                                      R.drawable.ic_baseline_help_24};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        gridView = (GridView) findViewById(R.id.gridview);
//        gridView.setAdapter(new CustomAdapter(Home.this,NameList,ImageList));

        toolbar = findViewById(R.id.topBar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawerLayout);
        view = findViewById(R.id.nav_view);


        ActionBarDrawerToggle action = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.open,
                R.string.close
        );

        drawer.addDrawerListener(action);
        action.syncState();
        view.setNavigationItemSelectedListener(this);

        view.getMenu().getItem(0).setChecked(true);
        PilihMenu(R.id.dashboard);

    }

    private void PilihMenu(int Menunya){
        fragment = null;
        switch (Menunya){
            case R.id.dashboard :
                fragment = new fragment_home();
//                gridView.setVisibility(View.VISIBLE);
                break;

            case R.id.profile :
                fragment = new fragment_profile();
//                gridView.setVisibility(View.GONE);
                break;
        }

        if(fragment!=null){
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.flayout,fragment);
            transaction.commit();
        }

        drawer.closeDrawers();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        PilihMenu(item.getItemId());
        return true;
    }

    public void onBacPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawers();
        }else{
            super.onBackPressed();
        }
    }
}