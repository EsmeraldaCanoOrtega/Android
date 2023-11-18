package com.example.personalproject.endpoints.apis;

import com.example.personalproject.core.model.GameListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GameAPI {


    @Headers({
            "key: 79749ec4d2534d149d3506e39b93e280"
    })
    @GET("games")
    Call<GameListResponse> listGames(@Query("key") String key);
}
