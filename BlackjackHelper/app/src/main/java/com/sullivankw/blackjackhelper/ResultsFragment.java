package com.sullivankw.blackjackhelper;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsFragment extends Fragment implements View.OnClickListener, OnCardSelectedListener {

    private TextView dealerCardText;
    private TextView playerCard1Text;
    private TextView playerCard2Text;
    private TextView adviceText;
    private String dealerCard;
    private String playerCard1;
    private String playerCard2;
    private String advice;
    private Button returnBtn;
    private OnCardSelectedListener listener;

    private static String NETWORK_ERROR = "network error";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        setupViewsAndListeners(view);
        configureViewModel();
        return view;
    }

    private void configureViewModel() {
        if (getActivity() == null) {
            return;
        }
        /**
         * the card variables are set with each btn click so no need to observe those.
         * TODO will work diff over no network, so will need alt paths here
         * TODO note that this method gets called way before its visible, so, we dont yet have
         * pcard 1 and 2....so they cant be renederd. observers would prolly fix it...but wbetter way???
         *
         * **/

        ViewModelProviders.of(getActivity()).
                get(CardSelectedViewModel.class).getAdviceFromNetworkResponse().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s != null) {
                    advice = s;
                    adviceText.setText(getResources().getString(R.string.advice_message, advice));
                }
            }
        });

        ViewModelProviders.of(getActivity()).
                get(CardSelectedViewModel.class).getNetworkError().observe(getActivity(), new Observer<Throwable>() {
            @Override
            public void onChanged(@Nullable Throwable t) {
                if (t != null) {
                    Log.d(NETWORK_ERROR, t.getMessage());
                    Toast.makeText(getActivity(), "Error retrieving advice", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupViewsAndListeners(View view) {
        dealerCardText = view.findViewById(R.id.textViewDealerCard);
        playerCard1Text = view.findViewById(R.id.textViewPlayerCard1);
        adviceText = view.findViewById(R.id.textViewAdvice);
        playerCard2Text = view.findViewById(R.id.textViewPlayerCard2);
        returnBtn = view.findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(this);
        listener = (OnCardSelectedListener) getContext();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(v.findViewById(R.id.returnBtn))) {
            listener.onCardSelected(3);
        }
    }

    @Override
    public void onCardSelected(int currentViewPagerPosition) {

    }
    /**
     * Since using a View Pager the views are created before they are actually visible.
     * The flow is such that the values needed to set in the text views arent set until just before
     * the fragment is actual in the foreground. Normally, overriding onResume would work, not here
     * This override works
     * **/
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            dealerCard = ViewModelProviders.of(getActivity()).
                    get(CardSelectedViewModel.class).getDealerCard().getValue();
            dealerCardText.setText(dealerCard);

            playerCard1 = ViewModelProviders.of(getActivity()).
                    get(CardSelectedViewModel.class).getPlayerCardOne().getValue();
            playerCard1Text.setText(playerCard1);

            playerCard2 = ViewModelProviders.of(getActivity()).
                get(CardSelectedViewModel.class).getPlayerCardTwo().getValue();
            playerCard2Text.setText(playerCard2);
        }
    }
}
