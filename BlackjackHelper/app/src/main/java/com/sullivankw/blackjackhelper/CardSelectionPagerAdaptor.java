package com.sullivankw.blackjackhelper;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sullivankw.blackjackhelper.gethelpflow.DealerCardFragment;
import com.sullivankw.blackjackhelper.gethelpflow.PlayerCardOneFragment;
import com.sullivankw.blackjackhelper.gethelpflow.PlayerCardTwoFragment;
import com.sullivankw.blackjackhelper.gethelpflow.ResultsFragment;


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
                return  "First Card";
            }
            case 2 : {
                return "Other Card";
            }
            case 3 : {
                return "Results";
            }
            default:
                return null;

        }
    }
}
