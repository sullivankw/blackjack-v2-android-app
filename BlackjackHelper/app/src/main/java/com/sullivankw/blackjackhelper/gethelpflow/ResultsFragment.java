package com.sullivankw.blackjackhelper.gethelpflow;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sullivankw.blackjackhelper.viewmodel.CardSelectedViewModel;
import com.sullivankw.blackjackhelper.R;
import com.sullivankw.blackjackhelper.base.BaseFragment;
import com.sullivankw.blackjackhelper.jar.HandAdvice;

public class ResultsFragment extends BaseFragment implements View.OnClickListener {

    private TextView dealerCardText;
    private TextView playerCardsText;
    private TextView adviceText;
    private String dealerCard;
    private String playerCard1;
    private String playerCard2;
    private String advice;
    private Button returnBtn;
    private CardSelectedViewModel viewModel;

    private static String NETWORK_ERROR = "network error";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        setupViewsAndListeners(view);
        configureViewModel();

        return view;
    }

    private void configureViewModel() throws ActivityNotFoundException {
        /**
         * the card variables are set with each btn click so no need to observe those.
         * **/
        viewModel = getViewModel();

        if (getActivity() == null) {
            return;
        }
        viewModel.getHandHelpResponse().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s != null) {
                    advice = s;
                    adviceText.setTypeface(null, Typeface.BOLD);
                    if (advice.equals(HandAdvice.BLACKJACK.name())) {
                        adviceText.setText(getResources().getString(R.string.advice_message_bj, advice));
                    } else {
                        adviceText.setText(getResources().getString(R.string.advice_message, advice));
                    }
                }
            }
        });

        viewModel.getNetworkError().observe(getActivity(), new Observer<Throwable>() {
            @Override
            public void onChanged(@Nullable Throwable t) {
                if (t != null) {
                    Log.e(NETWORK_ERROR, t.getMessage());
                    Toast.makeText(getActivity(), "Error retrieving advice", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupViewsAndListeners(View view) {
        dealerCardText = view.findViewById(R.id.textViewDealerCard);
        playerCardsText = view.findViewById(R.id.textViewPlayersCard);
        adviceText = view.findViewById(R.id.textViewAdvice);
        returnBtn = view.findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(v.findViewById(R.id.returnBtn))) {
            viewModel.setNextPage(0);
            viewModel.resetCardValues();
        }
    }

    /**
     * Since using a View Pager the views are created before they are actually visible.
     * The flow is such that the values needed to set in the text views aren't set until just before
     * the fragment is actually in the foreground. Normally, overriding onStart would work usually, not for VP
     * This override works
     * **/
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (getActivity() == null) {
                return;
            }
            dealerCard = ViewModelProviders.of(getActivity()).
                    get(CardSelectedViewModel.class).getDealerCard().getValue();
            playerCard1 = ViewModelProviders.of(getActivity()).
                    get(CardSelectedViewModel.class).getPlayerCardOne().getValue();
            playerCard2 = ViewModelProviders.of(getActivity()).
                    get(CardSelectedViewModel.class).getPlayerCardTwo().getValue();

                dealerCardText.setText(getResources().getString(R.string.dealer_message_results, dealerCard));
                playerCardsText.setText(getResources().getString(R.string.player_message_results, playerCard1, playerCard2));
        }
    }
}
