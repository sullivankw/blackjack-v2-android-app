package com.sullivankw.blackjackhelper.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.sullivankw.blackjackhelper.BaseActivity;
import com.sullivankw.blackjackhelper.R;

import java.util.ArrayList;
import java.util.List;

public class PracticeModeActivity extends BaseActivity {

    private BottomNavigationView bottomNav;
    private Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_mode);
        bottomNav = setupBottomNav(R.id.bottom_nav_practice_layout);
        spinner = findViewById(R.id.spinner);
        populateSpinner();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void populateSpinner() {
        List<String> items = new ArrayList<String>();
        items.add("All");
        items.add("Female");
        items.add("Male");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
}
