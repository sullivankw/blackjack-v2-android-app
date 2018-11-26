package com.sullivankw.blackjackhelper;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;
    private CardSelectionPagerAdaptor pagerAdaptor;
    private TabLayout tabLayout;
    private CardSelectedViewModel viewModel;
    private BottomNavigationView bottomNav;
    private Switch switchVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setUpViewPager();
        setUpTabLayout();
        setupViewModel();
        bottomNav = setupBottomNav(R.id.bottom_nav_layout);
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(CardSelectedViewModel.class);

        viewModel.getNextPage().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer != null) {
                    viewPager.setCurrentItem(integer);
                    if (integer == 3) {
                        if (viewModel.getPlayerCardOne().getValue() != null && viewModel.getPlayerCardTwo().getValue() != null
                                && viewModel.getDealerCard().getValue() != null) {
                            viewModel.getAdvice();
                        } else {
                            //TODO this works if you dont just click on the rESULTS directly
                            Toast.makeText(getBaseContext(), "All cards need to be selected.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }

    private void findViews() {
        switchVersion = findViewById(R.id.switchVersion);
        switchVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchVersion.isChecked()) {
                    viewModel.setHitSoft17(true);
                }
                if (!switchVersion.isChecked()) {
                    viewModel.setHitSoft17(false);
                }
            }
        });
    }

    private void setUpTabLayout() {
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 3) {
                    Toast.makeText(getBaseContext(), "tab chosen", Toast.LENGTH_SHORT).show();
                }
             }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpViewPager() {
        pagerAdaptor = new CardSelectionPagerAdaptor(getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdaptor);
        viewPager.setOffscreenPageLimit(2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1: {

            }
            case R.id.item2: {

            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() != 0) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        } else {
            super.onBackPressed();
        }
    }
}
