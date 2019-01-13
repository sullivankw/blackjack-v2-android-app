package com.sullivankw.blackjackhelper.base;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.sullivankw.blackjackhelper.LeaderBoardActivity;
import com.sullivankw.blackjackhelper.MainActivity;
import com.sullivankw.blackjackhelper.R;
import com.sullivankw.blackjackhelper.practice.PracticeModeActivity;

public class BaseActivity extends AppCompatActivity {

    public void setupBottomNav(int bottomNavId) {
        BottomNavigationView bottomNav = findViewById(bottomNavId);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
                    case (R.id.navToLeaderBoard): {
                        startActivity(new Intent(getBaseContext(), LeaderBoardActivity.class));
                        break;
                    }
                    default:
                        return false;
                }
                return true;
            }
        });
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
