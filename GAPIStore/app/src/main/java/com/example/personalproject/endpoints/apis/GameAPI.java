package com.example.personalproject.endpoints.apis;

import com.example.personalproject.model.GameListResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface GameAPI {


    @GET("games")
    Call<GameListResponse> listGames(@QueryMap Map<String, Object> params);
}
