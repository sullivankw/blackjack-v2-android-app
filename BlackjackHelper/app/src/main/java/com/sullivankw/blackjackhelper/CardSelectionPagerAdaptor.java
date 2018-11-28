package com.sullivankw.blackjackhelper;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class CardSelectionPagerAdaptor extends FragmentPagerAdapter {

    private DealerCardFragment fragmentDealerCard;
    private PlayerCardOneFragment fragmentPlayerCardOne;
    private PlayerCardTwoFragment fragmentPlayerCardTwo;
    private ResultsFragment resultsFragment;

    @Override
    public int getCount() {
        return 4;
    }

    public CardSelectionPagerAdaptor(FragmentManager fm) {
        super(fm);
        fragmentDealerCard = new DealerCardFragment();
        fragmentPlayerCardOne = new PlayerCardOneFragment();
        fragmentPlayerCardTwo = new PlayerCardTwoFragment();
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
                return  "Your First Card";
            }
            case 2 : {
                return "Your Next Card";
            }
            case 3 : {
                return "Results";
            }
            default:
                return null;

        }
    }
}
