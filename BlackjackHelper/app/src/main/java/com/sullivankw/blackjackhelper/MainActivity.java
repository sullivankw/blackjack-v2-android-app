package com.sullivankw.blackjackhelper;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements OnCardSelectedListener {

    private ViewPager viewPager;
    private CardSelectionPagerAdaptor pagerAdaptor;
    private TabLayout tabLayout;
    private CardSelectedViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setUpViewPager();
        setUpTabLayout();
        setupViewModel();
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(CardSelectedViewModel.class);

        viewModel.getAdviceFromNetworkResponse().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s != null) {
                //todo, maybe not needed since i wanna display on the fourth frag
                }
            }
        });

    }

    private void findViews() {
    }

    private void setUpTabLayout() {
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
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
            case R.id.item1 : {

            }
            case R.id.item2 : {

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
    public void onCardSelected(int currentViewPagerPosition, String card) {
        if (currentViewPagerPosition == 2) {
            viewModel.getAdvice();
        }
            viewPager.setCurrentItem(currentViewPagerPosition + 1);
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
