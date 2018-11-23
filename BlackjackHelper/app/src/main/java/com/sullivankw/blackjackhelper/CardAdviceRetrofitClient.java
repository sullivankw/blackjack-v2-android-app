package com.sullivankw.blackjackhelper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CardAdviceRetrofitClient {

        @GET("advice")
        Call<String> getAdvice(@Query("hitSoft") boolean hitSoft, @Query("dealerCard") String dealerCard,
                                          @Query("playerCard1") String playerCard1, @Query("playerCard2") String playerCard2);
}
