package com.sullivankw.blackjackhelper;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsFragment extends Fragment {

    private TextView dealerCardText;
    private TextView playerCard1Text;
    private TextView playerCard2Text;
    private TextView adviceText;
    private String dealerCard;
    private String playerCard1;
    private String playerCard2;
    private String advice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        findViews(view);
        setupObservables();
        return view;
    }

    private void setupObservables() {
        if (getActivity() == null) {
            return;
        }
        dealerCard = ViewModelProviders.of(getActivity()).
                get(CardSelectedViewModel.class).getDealerCard().getValue();
        dealerCardText.setText(dealerCard);

        playerCard1 = ViewModelProviders.of(getActivity()).
                get(CardSelectedViewModel.class).getPlayerCardOne().getValue();
        playerCard1Text.setText(playerCard1);

        playerCard2 = ViewModelProviders.of(getActivity()).
                get(CardSelectedViewModel.class).getPlayerCardTwo().getValue();
        playerCard2Text.setText(playerCard2);

        ViewModelProviders.of(getActivity()).
                get(CardSelectedViewModel.class).getAdviceFromNetworkResponse().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s != null) {
                    advice = s;
                    adviceText.setText(advice);
                }
            }
        });

        ViewModelProviders.of(getActivity()).
                get(CardSelectedViewModel.class).getNetworkError().observe(getActivity(), new Observer<Throwable>() {
            @Override
            public void onChanged(@Nullable Throwable t) {
                if (t != null) {
                    Toast.makeText(getActivity(), "Error retrieving advice", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findViews(View view) {
        dealerCardText = view.findViewById(R.id.textViewDealerCard);
        playerCard1Text = view.findViewById(R.id.textViewPlayerCard1);
        adviceText = view.findViewById(R.id.textViewAdvice);
        playerCard2Text = view.findViewById(R.id.textViewPlayerCard2);

    }

}
