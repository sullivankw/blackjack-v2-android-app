package com.sullivankw.blackjackhelper;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements OnCardSelectedListener {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private CardSelectionPagerAdaptor pagerAdaptor;
    private TabLayout tabLayout;
    private CardSelectedViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Blackjack Helper");
        }
        setUpViewPager();
        setUpTabLayout();
        setupViewModel();
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(CardSelectedViewModel.class);
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
        //create the menu here, not in the xml!!!!
        //must add tools:context=".MainActivity">
        getMenuInflater().inflate(R.menu.menu_items, menu);

        return true;
    }

    @Override
    public void onCardSelected(int currentViewPagerPosition, String card) {
        if (currentViewPagerPosition == 2) {
            //back to start with popup is best approach?
            //lock ui
            //make call to service
            //unlock
            viewPager.setCurrentItem(0);
        } else {
            viewPager.setCurrentItem(currentViewPagerPosition + 1);
        }

        //TODO override backbutton, cause it kills app
    }
}
