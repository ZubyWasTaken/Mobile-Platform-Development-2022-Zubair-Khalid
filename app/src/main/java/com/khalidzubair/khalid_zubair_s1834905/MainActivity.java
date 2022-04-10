/*
 * Name: Zubair Khalid
 * Matriculation Number: S1843905
 */

package com.khalidzubair.khalid_zubair_s1834905;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navBottom = findViewById(R.id.bottom_navigation);
        navBottom.setOnNavigationItemSelectedListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        TextView text = findViewById(R.id.text);

        text.setVisibility(View.INVISIBLE);

        Fragment fragmentSelected = null;

        switch (item.getItemId()) {
            case R.id.nav_plannedroadworks:
                fragmentSelected = new PlannedRoadworks();
                break;
            case R.id.nav_roadworks:
                fragmentSelected = new Roadworks();
                break;
            case R.id.nav_currentincidents:
                fragmentSelected = new CurrentIncident();
                break;
        }
        return loadFragment(fragmentSelected);
    }

    private boolean loadFragment(Fragment fragmentSelected) {
        if (fragmentSelected != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragmentSelected).commit();
            return true;
        }
        return false;
    }

}