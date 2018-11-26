package com.sullivankw.blackjackhelper;

import android.arch.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ClientCardAdviceServiceNetworkImpl implements ClientCardAdviceService{
    private static ClientCardAdviceServiceNetworkImpl service;

    private static final String URL = "http://10.0.2.2:8080/";
    //If you are referring your localhost on your system from the Android emulator then you have to
    // use http://10.0.2.2:8080/ Because Android emulator runs in a Virtual Machine therefore here 127.0.0.1 or localhost will be emulator's own loopback address.
    //http://localhost:8080/advice?hitSoft=false&dealerCard=five&playerCard1=eight&playerCard2=three
    private CardAdviceRetrofitClient cardAdviceRetrofitClient;

    public static ClientCardAdviceServiceNetworkImpl getClientCardAdviceServiceNetwork() {
        if (service == null) {
            service = new ClientCardAdviceServiceNetworkImpl();
        }
        return service;
    }

    @Override
    public String getAdvice(boolean hitSoft, String dealerCard, String playerCard1, String playerCard2,
                            final BaseViewModel viewModel) {

        cardAdviceRetrofitClient = getRetrofitClient();
        Call<String> call = cardAdviceRetrofitClient.getAdvice(hitSoft, dealerCard, playerCard1, playerCard2);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String advice = response.body();
                    viewModel.setAdviceFromNetworkResponse(HandAdvice.fromEnum(advice));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                viewModel.setNetworkError(t);
            }
        });
        //non network version impl will return the String advice
        return null;
    }
        private  CardAdviceRetrofitClient getRetrofitClient() {

        if (cardAdviceRetrofitClient != null) {
            return cardAdviceRetrofitClient;
        }
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(ScalarsConverterFactory.create());
            Retrofit retrofit = builder.build();
            return retrofit.create(CardAdviceRetrofitClient.class);
    }
}
