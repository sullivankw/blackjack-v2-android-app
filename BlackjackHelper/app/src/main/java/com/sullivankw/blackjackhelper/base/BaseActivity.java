package com.sullivankw.blackjackhelper.base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.sullivankw.blackjackhelper.MainActivity;
import com.sullivankw.blackjackhelper.R;
import com.sullivankw.blackjackhelper.practice.PracticeModeActivity;

public class BaseActivity extends AppCompatActivity {

    public BottomNavigationView setupBottomNav(int bottomNavId) {
        BottomNavigationView bottomNav = findViewById(bottomNavId);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            //todo keep up with current activity in a view model to prevent unecessary switches or will framework handle?
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case (R.id.navToMain): {
                        startActivity(new Intent(getBaseContext(), MainActivity.class));
                        break;
                    }
                    case (R.id.navToPractice): {
                        startActivity(new Intent(getBaseContext(), PracticeModeActivity.class));
                        break;
                    }
                    default:
                        return false;
                }
                return true;
            }
        });

        return bottomNav;
    }
}
