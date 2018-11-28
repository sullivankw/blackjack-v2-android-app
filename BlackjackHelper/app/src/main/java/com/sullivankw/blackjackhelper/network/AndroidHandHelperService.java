package com.sullivankw.blackjackhelper.network;

import com.sullivankw.blackjackhelper.HandAdvice;
import com.sullivankw.blackjackhelper.base.BaseViewModel;
import com.sullivankw.blackjackhelper.jar.BlackjackHelperServiceException;
import com.sullivankw.blackjackhelper.jar.HandHelperService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AndroidHandHelperService {
    private static AndroidHandHelperService service;

    private static final String URL = "http://10.0.2.2:8080/";
    //If you are referring your localhost on your system from the Android emulator then you have to
    // use http://10.0.2.2:8080/ Because Android emulator runs in a Virtual Machine therefore here 127.0.0.1 or localhost will be emulator's own loopback address.
    //http://localhost:8080/advice?hitSoft=false&dealerCard=five&playerCard1=eight&playerCard2=three

    private AndroidHandHelperService() {
    }

    /**
     * Only creating if overHttp is true
     **/
    private HandHelperRetrofitClient handHelperRetrofitClient;

    public static AndroidHandHelperService getAndroidHandHelperService() {
        if (service == null) {
            service = new AndroidHandHelperService();
        }
        return service;
    }

    public String getHandHelp(boolean hitSoft, String dealerCard, String playerCard1, String playerCard2,
                              final BaseViewModel viewModel, boolean overHttp) throws BlackjackHelperServiceException {
        if (overHttp) {
            return getHttpAdvice(hitSoft, dealerCard, playerCard1, playerCard2, viewModel);
        }
        String advice  = HandHelperService.getHandHelperService().
                getHandHelp(hitSoft, dealerCard, playerCard1, playerCard2);
        viewModel.setHandHelpResponse(HandAdvice.fromEnum(advice));
        return advice;
    }

    private String getHttpAdvice(boolean hitSoft, String dealerCard, String playerCard1, String playerCard2,
                               final BaseViewModel viewModel) {
        handHelperRetrofitClient = getRetrofitClient();
        Call<String> call = handHelperRetrofitClient.getAdvice(hitSoft, dealerCard, playerCard1, playerCard2);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String advice = response.body();
                    viewModel.setHandHelpResponse(HandAdvice.fromEnum(advice));
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

    private HandHelperRetrofitClient getRetrofitClient() {
        if (handHelperRetrofitClient != null) {
            return handHelperRetrofitClient;
        }
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(ScalarsConverterFactory.create());
            Retrofit retrofit = builder.build();
            return retrofit.create(HandHelperRetrofitClient.class);
    }
}
