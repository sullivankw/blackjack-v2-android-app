package com.sullivankw.blackjackhelper;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class CardSelectionPagerAdaptor extends FragmentPagerAdapter {

    private FragmentDealerCard fragmentDealerCard;
    private FragmentPlayerCardOne fragmentPlayerCardOne;
    private FragmentPlayerCardTwo fragmentPlayerCardTwo;
    private ResultsFragment resultsFragment;

    @Override
    public int getCount() {
        return 4;
    }

    public CardSelectionPagerAdaptor(FragmentManager fm) {
        super(fm);
        fragmentDealerCard = new FragmentDealerCard();
        fragmentPlayerCardOne = new FragmentPlayerCardOne();
        fragmentPlayerCardTwo = new FragmentPlayerCardTwo();
        resultsFragment = new ResultsFragment();
    }

    @Override
    public Fragment getItem(int i) {
        Fragment frag = null;
        switch (i) {
            case 0:
                frag = fragmentDealerCard;
                break;
            case 1:
                frag = fragmentPlayerCardOne;
                break;
            case 2:
                frag = fragmentPlayerCardTwo;
                break;
            case 3:
                frag = resultsFragment;
                break;
        }

        return frag;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0 : {
                return "Dealer Card";
            }
            case 1 : {
                return  "Player's First Card";
            }
            case 2 : {
                return "Player's Second Card";
            }
            case 3 : {
                return "Results";
            }
            default:
                return null;

        }
    }
}
